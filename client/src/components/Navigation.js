import { Link } from "react-router-dom";
import { useContext } from 'react';

import AuthContext from "../contexts/AuthContext";

function Navigation() {
    const auth = useContext(AuthContext);

    return (
        <nav className="container">
            {auth.user && (
                <div className="text-center">
                    Welcome, {auth.user.username}!
                    <button className="btn btn-lg btn-link" onClick={() => auth.logout()}>Logout</button>
                </div>
            )}
            <ul>
                <li>
                    <Link to="/">Home</Link>
                </li>
                <li>
                    <Link to="/sightings">Sightings</Link>
                </li>
                <li>
                    <Link to="/birds">Birds</Link>
                </li>
                {!auth.user ?
                    <li className="list-inline-item"><Link to="/login">Login/Register</Link></li>
                    :
                    <li className="list-inline-item"><Link to={`/profile/${auth.user.username}`}>Profile</Link></li>
                }
            </ul>
        </nav>
    );
}

export default Navigation;