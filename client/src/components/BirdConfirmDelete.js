import {useEffect, useState} from "react";
import { Link, useHistory, useParams } from "react-router-dom";
import { deleteById, findByBirdId } from "../services/birds";

function BirdConfirmDelete() {
    
    const [bird, setBird] = useState({})
    const history = useHistory();
    const {birdId} = useParams();

    useEffect(() => {
        if (!birdId) {
            history.push("/");
        }

        findByBirdId(birdId)
        .then(setBird)
        .catch(() => history.push("/"));
    }, [history, birdId]);

    function handleDelete() {
        deleteById(bird.birdId)
        .then(() => history.push("/"))
        .catch(() => history.push("/error"));
    };

    return (
        <div>
            <h2>Confirm Delete</h2>
            <div className="alert alert-danger">
                <p>
                    This will permantely delete bird {bird.birdId}.
                </p>
            </div>
            <div>
                <Link to="/birds" className="btn btn-danger me-2" onClick={handleDelete}>Delete</Link>
                <Link to="/birds" className="btn btn-warning">Cancel</Link>
            </div>
        </div>
    )

}

export default BirdConfirmDelete;