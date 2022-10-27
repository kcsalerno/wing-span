import { Link } from "react-router-dom";
import { useContext } from 'react';

import AuthContext from "../contexts/AuthContext";

function Navigation() {
    const auth = useContext(AuthContext);
    
    return (
        <nav>
            {auth.user && (
            <div className="text-center lead">
                Welcome, {auth.user.username}!
                <button className="btn btn-sm btn-link" onClick={() => auth.logout()}>Logout</button>
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
                {!auth.user && (
                <>
                    <li className="list-inline-item"><Link to="/login">Login</Link></li>
                    <li className="list-inline-item"><Link to="/register">Register</Link></li>
                </>
            )}
            {auth.user && (
                <li className="list-inline-item"></li>
            )}
            </ul>
        </nav>
    );
}

export default Navigation;