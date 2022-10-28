import { Link } from "react-router-dom";
import { useContext } from 'react';

import AuthContext from "../contexts/AuthContext";

function Navigation() {
    const auth = useContext(AuthContext);
    
    return (
        <nav>
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
            </ul>
        </nav>
    );
}

export default Navigation;