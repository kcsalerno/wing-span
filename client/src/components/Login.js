import { useState, useContext } from "react";
import { Link, useHistory } from "react-router-dom";

import AuthContext from "../contexts/AuthContext.js";

function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [errors, setErrors] = useState([]);

    const auth = useContext(AuthContext);
    const history = useHistory();

    const handleSubmit = async (event) => {
        event.preventDefault();

         // I know this is not the best way to do this, but it was the easiest and fastest to get things going.
        // I would rather move things into the auth service, but for now this will do.
        const response = await fetch("http://wing-span-web-app.us-east-1.elasticbeanstalk.com/authenticate", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                username,
                password,
            }),
        });

        // This code executes if the request is successful
        if (response.status === 200) {
            const { jwt_token } = await response.json();
            // console.log(jwt_token);
            // NEW: login!
            localStorage.setItem("jwt", jwt_token);
            // console.log(localStorage.getItem("jwt"));
            auth.login(jwt_token);
            history.goBack();
        } else if (response.status === 403) {
            setErrors(["Login failed."]);
        } else {
            setErrors(["Unknown error."]);
        }
    };

    const handleUsernameChange = (event) => {
        setUsername(event.target.value);
    };

    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
    }

    return (
        <div className="login">
            <div>
            <h2 className="mb-3">Login</h2>
            {errors.length !== 0 && <div className="alert alert-danger">
                    <ul>
                        {errors.map(error => <li key={error}>{error}</li>)}
                    </ul>
                </div>}
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
                <div className="mb-2">
                    <button className="btn add me-2 mr-2" type="submit"><i class="bi bi-box-arrow-in-right"></i> Login</button>
                    <Link className="btn register me-2 mr-2" to="/register"><i className='bi bi-file-earmark-check'></i> Register</Link>
                    <Link className="btn delete me-2" to="/"><i className='bi bi-file-earmark-excel'></i> Cancel</Link>
                </div>
            </form>
            </div>
        </div>
    );
}

export default Login;