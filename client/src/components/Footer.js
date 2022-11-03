import { Link } from "react-router-dom";
import { useContext } from 'react';
import AuthContext from "../contexts/AuthContext";

function Footer() {
    const auth = useContext(AuthContext);

    return (
        <footer>
            <div className="footer-one">
                <h3>Contact Us</h3>
                <address>
                Gracia Kaneza: <a href="mailto: gkaneza@dev-10.com">gkaneza@dev-10.com</a><br/>
                Kristopher Salerno: <a href="mailto: ksalerno@dev-10.com">ksalerno@dev-10.com</a><br/>
                Collin Shawyer: <a href="mailto: cshawyer@dev-10.com">cshawyer@dev-10.com</a>
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