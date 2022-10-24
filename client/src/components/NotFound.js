import { Link } from "react-router-dom";

function NotFound() {
    return (
        <div className="notFound">
            <h2>404: Not Found</h2>
            <p>
                Click <Link to="/">here</Link> to go back home.
            </p>
            <div className="notFound">
                <img src="https://media.giphy.com/media/C21GGDOpKT6Z4VuXyn/giphy.gif"
                alt="404 Not Found Bird jumping over numbers side scroller">
                </img>
            </div>
        </div>
    )
}

export default NotFound;