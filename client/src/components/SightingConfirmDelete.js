import {useEffect, useState} from "react";
import { Link, useHistory, useParams } from "react-router-dom";
import { deleteById, findBySightingId } from "../services/sightings";

function SightingConfirmDelete() {
    const [sighting, setSighting] = useState({});

    const history = useHistory();
    const {sightingId} =useParams();

    useEffect(() => {
        if (!sightingId) {
            history.push("/");
        }

        findBySightingId(sightingId)
            .then(setSighting)
            .catch(() => history.push("/"));
    }, [history, sightingId])

    function handleDelete() {
        deleteById(sighting.sightingId)
        .then(() => history.goBack())
        .catch(() => history.push("/error"));
    };

    return (
        <div>
            <h2>Confirm Delete</h2>
            <div className="alert alert-danger">
                <p>
                    This will permanently delete sighting for bird {sighting.sightingBirdId}.
                </p>
            </div>
            <div>
                <button className="btn btn-danger me-2" onClick={handleDelete}><i className='bi bi-trash'></i> Delete</button>
                <Link to="/sightings" className="btn btn-warning"><i className='bi bi-file-earmark-excel'></i> Cancel</Link>
            </div>
        </div>
    )
}

export default SightingConfirmDelete;