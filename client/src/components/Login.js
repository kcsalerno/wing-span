import { useState, useContext } from "react";
import { Link, useHistory } from "react-router-dom";

import AuthContext from "../contexts/AuthContext.js";
import Error from "./Error.js";

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
        const response = await fetch("http://localhost:8080/authenticate", {
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
            console.log(jwt_token);
            // NEW: login!
            localStorage.setItem("jwt", jwt_token);
            console.log(localStorage.getItem("jwt"));
            auth.login(jwt_token);
            history.push("/");
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
        <>
            <h2 className="mb-3">Login</h2>
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
                <div className="mb-2">
                    <button className="btn btn-primary me-2 mr-2" type="submit">Login</button>
                    <Link className="btn btn-success me-2 mr-2" to="/register">Register</Link>
                    <Link className="btn btn-warning me-2" to="/">Cancel</Link>
                </div>
            </form>
        </>
    );
}

export default Login;