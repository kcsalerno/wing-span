import { Link } from "react-router-dom";
import { useContext } from 'react';
import AuthContext from "../contexts/AuthContext";

function Footer() {
    const auth = useContext(AuthContext);

    return (
        <footer>
            <div className="footer-one" id="footer">
                <h2>Contact Us</h2>
                <address>
                <a href="mailto: gkaneza@dev-10.com">Gracia Kaneza</a><br/>
                <a href="mailto: ksalerno@dev-10.com">Kristopher Salerno</a><br/>
                <a href="mailto: cshawyer@dev-10.com">Collin Shawyer</a>
                </address>
            </div>
            <div>
                <nav>
                    <ul>
                        <li>
                            <Link to="/">Home</Link>
                        </li>
                        <li>
                            <Link to="/about">About</Link>
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
            </div>
            <div>
                <img src="https://images.unsplash.com/photo-1549608276-5786777e6587?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80" alt="3 parrots perching on a branch"/>   
            </div>
        </footer>
    )
}

export default Footer;