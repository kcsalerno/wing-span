import { useState, useContext } from "react";
import { Link, useHistory } from "react-router-dom";

import AuthContext from "../contexts/AuthContext.js";
import Error from "./Error.js";


function Register() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [email, setEmail] = useState("");
    const [errors, setErrors] = useState([]);

    const auth = useContext(AuthContext);
    const history = useHistory();

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
                email
            })
        };

        fetch("http://localhost:8080/create_account", init)
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
                    fetch("http://localhost:8080/authenticate", {
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

    return (
        <>
            <h2 className="mb-3">Register</h2>
            <Error errors={errors} />
            <form onSubmit={handleSubmit}>
                <div className="mb-2">
                    <label htmlFor="username" className="mr-1">Username:</label>
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
                    <label htmlFor="email" className="mr-1">Email:</label>
                    <input
                        type="text"
                        id="email"
                        onChange={handleEmailChange}
                        value={email}
                    />
                </div>
                <div className="mb-2">
                    <button className="btn btn-primary me-2 mr-2" type="submit">Register</button>
                    <Link className="btn btn-success me-2 mr-2" to="/login">Already Have An Account</Link>
                    <Link className="btn btn-warning me-2" to="/">Cancel</Link>
                </div>
            </form>
        </>
    );
}

export default Register;