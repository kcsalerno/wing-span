import { useContext, useState } from "react";
import { Link, useHistory } from "react-router-dom";
import { authenticate } from "../services/auth";
import AuthContext from "../contexts/AuthContext";

function Login() {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [hasErr, setHasErr] = useState(false);

    const { login } = useContext(AuthContext);
    const history = useHistory();

    const handleUsernameChange = (evt) => setUsername(evt.target.value);
    const handlePasswordChange = (evt) => setPassword(evt.target.value);

    function handleSubmit(evt) {
        evt.preventDefault();

        const user = {
            username,
            password
        };

        authenticate(user)
            .then(u => {
                login(u);
                history.push("/");
            })
            .catch(() => setHasErr(true));
    }

    return (
        <form onSubmit={handleSubmit}>
            <div className="mb-2">
                <label htmlFor="username" className="form-label">Username</label>
                <input type="text" id="username" name="username" className="form-control"
                    value={username} onChange={handleUsernameChange}></input>
            </div>
            <div className="mb-2">
                <label htmlFor="password" className="form-label">Password</label>
                <input type="password" id="password" name="password" className="form-control"
                    value={password} onChange={handlePasswordChange}></input>
            </div>
            {hasErr && <div className="alert alert-danger">
                Bad Credentials. :(
            </div>}
            <div>
                <button className="btn btn-primary me-2" type="submit">Login</button>
                <Link className="btn btn-warning" to="/">Cancel</Link>
            </div>
        </form>
    );
}

export default Login;