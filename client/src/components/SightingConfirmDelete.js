import {useEffect, useState} from "react";
import { Link, useHistory, useParams } from "react-router-dom";
import { deleteById, findBySightingId } from "../services/sightings";

function SightingConfirmDelete() {
    const [sighting, setSighting] = useState({});

    const history = useHistory;
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
        .then(() => history.push("/"))
        .catch(() => history.push("/error"));
    };

    return (
        <div>
            <h2>Confirm Delete</h2>
            <div className="alert alert-danger">
                <p>
                    This will permantely delete sighting for bird {sighting.sightingBirdId}.
                </p>
            </div>
            <div>
                <Link className="btn btn-danger me-2" onClick={handleDelete} to="/sightings">Delete</Link>
                <Link to="/sightings" className="btn btn-warning">Cancel</Link>
            </div>
        </div>
    )
}

export default SightingConfirmDelete;