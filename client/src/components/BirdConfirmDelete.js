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
        .then(() => history.push("/birds"))
        .catch(() => history.push("/error"));
    };

    return (
        <div>
            <h2>Confirm Delete</h2>
            <div className="alert alert-danger">
                <p>
                    This will permanently delete bird {bird.birdId}.
                </p>
            </div>
            <div>
                <button className="btn btn-danger me-2 mr-2" onClick={handleDelete}><i className='bi bi-trash'></i> Delete</button>
                <Link to="/birds" className="btn btn-warning"><i className='bi bi-file-earmark-excel'></i> Cancel</Link>
            </div>
        </div>
    )

}

export default BirdConfirmDelete;