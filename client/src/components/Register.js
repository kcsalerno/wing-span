import { useState, useContext, useEffect } from "react";
import { Link, useHistory } from "react-router-dom";

import AuthContext from "../contexts/AuthContext.js";
import Error from "./Error.js";
import { findAllAvatars, findById } from "../services/avatars.js";

function Register() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [email, setEmail] = useState("");
    const [errors, setErrors] = useState([]);
    const [avatars, setAvatars] = useState([findAllAvatars]);
    const [avatar, setAvatar] = useState([]);
    const [avatarId, setAvatarId] = useState("");
    const [avatarDescription, setAvatarDescription] = useState("");
    const [avatarImageUrl, setAvatarImageUrl] = useState("");

    const auth = useContext(AuthContext);
    const history = useHistory();

    useEffect(() => {
        findAllAvatars()
        .then(data => {
            setAvatars(data)
            if (!avatarId) {
                const temp = {...data[0]}
                setAvatarId(temp.avatarId);
                setAvatar(data[0]);
            }
        });
    }, [avatarId]);


    const handleSubmit = (event) => {
        event.preventDefault();

        if (password !== confirmPassword) {
            setErrors(["Your passwords do not match."]);
            return;
        }

        // I know this is not the best way to do this, but it was the easiest and fastest to get things going.
        // I would rather move things into the auth service, but for now this will do.

        const init = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                username,
                password,
                email,
                avatarId,
                avatarDescription,
                avatarImageUrl
            })
        };

        fetch("http://wing-span-web-app.us-east-1.elasticbeanstalk.com/create_account", init)
            .then(response => {
                if (response.status === 201 || response.status === 400) {
                    return response.json();
                }
                else {
                    return Promise.reject(`Unexpected status code: ${response.status}`);
                }
            })
            .then(data => {
                if (data.appUserId) {
                    fetch("http://wing-span-web-app.us-east-1.elasticbeanstalk.com/authenticate", {
                        method: "POST",
                        headers: {
                            "Content-Type": "application/json",
                        },
                        body: JSON.stringify({
                            username,
                            password,
                        }),
                    })
                        .then(response => {
                            if (response.status === 200) {
                                return response.json();
                            }
                            else if (response.status === 403) {
                                return null;
                            }
                            else {
                                return Promise.reject(`Unexpected status code: ${response.status}`);
                            }
                        })
                        .then(data => {
                            if (data) {
                                // console.log(data);
                                // console.log(data.jwt_token);

                                const user_jwt_token = data.jwt_token;

                                // console.log(user_jwt_token);

                                localStorage.setItem("jwt", user_jwt_token);

                                // console.log(localStorage.getItem("jwt"));

                                auth.login(user_jwt_token);

                                history.push("/");
                            }
                            else {
                                setErrors(["Login failed."]);
                            }
                        })
                        .catch(console.log);
                }
                else {
                    setErrors(data);
                }
            })
            .catch(console.log);
    };

    const handleUsernameChange = (event) => {
        setUsername(event.target.value);
    };

    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
    }

    const handleConfirmPasswordChange = (event) => {
        setConfirmPassword(event.target.value);
    }

    const handleEmailChange = (event) => {
        setEmail(event.target.value);
    }

    const handleAvatarChange = (event) => {
        setAvatarId(event.target.value);
        findById(event.target.value)
        .then(data => {
            setAvatar(data);
            setAvatarDescription(avatar.avatarDescription);
            setAvatarImageUrl(avatar.avatarImageUrl);
        })
    }

    return (
        <div className="login">
            <div>
            <h2 className="mb-3">Register</h2>
            <Error errors={errors} />
            <form onSubmit={handleSubmit}>
                <div className="mb-2">
                    <label htmlFor="username" className="mr-1">Username: </label>
                    <input
                        type="text"
                        id="username"
                        onChange={handleUsernameChange}
                        value={username}
                    />
                </div>
                <div className="mb-2" >
                    <label htmlFor="password" className="mr-1">Password: </label>
                    <input
                        type="password"
                        id="password"
                        onChange={handlePasswordChange}
                        value={password}
                    />
                </div>
                <div className="mb-2" >
                    <label htmlFor="confirmPassword" className="mr-1">Confirm Password: </label>
                    <input
                        type="password"
                        id="confirmPassword"
                        onChange={handleConfirmPasswordChange}
                        value={confirmPassword}
                    />
                </div>
                <div className="mb-2">
                    <label htmlFor="email" className="mr-1">Email: </label>
                    <input
                        type="text"
                        id="email"
                        onChange={handleEmailChange}
                        value={email}
                    />
                </div>
                <div className="mb-2">
                    <label htmlFor="avatar" className="mr-2">Avatar: </label>
                    <select name="avatar"
                        id="avatar"
                        value={avatarId}
                        onChange={handleAvatarChange}
                    >
                        {avatars.map((avatar) => (
                            <option key={`${avatar.avatarId}-${avatar.avatarDescription}`} value={avatar.avatarId}>{avatar.avatarDescription}</option>
                        ))}
                    </select>
                    <img className="ml-4" src={avatar.avatarImageUrl} alt={avatar.avatarDescription} style={{width: '150px'}}/>
                </div>
                <div className="mb-2">
                    <button className="btn add me-2 mr-2" type="submit"><i className='bi bi-file-earmark-check'></i> Register</button>
                    <Link className="btn register me-2 mr-2" to="/login"><i class="bi bi-person-check"></i> I Already Have An Account</Link>
                    <Link className="btn delete me-2" to="/"><i className='bi bi-file-earmark-excel'></i> Cancel</Link>
                </div>
            </form>
            </div>
        </div>
    );
}

export default Register;