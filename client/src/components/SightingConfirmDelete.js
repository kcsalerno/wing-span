import {useEffect, useState} from "react";
import { Link, useHistory, useParams } from "react-router-dom";
import { deleteById, findBySightingId } from "../services/sightings";

function SightingConfirmDelete() {
    // const endpoint = "http://localhost:8080/api/sighting";
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

    // useEffect(() => {
    //     if (sightingId) {
    //         fetch(`${endpoint}/${sightingId}`)
    //             .then(response => response.json())
    //             .then((data) => setSighting(data));
    //     }
    // }, [sightingId]);


    function handleDelete() {

        deleteById(sighting.sightingId)
            .then(() => history.push("/"))
            .catch(() => history.push("/error"));

        // const remove = {
        //     method: `DELETE`,
        //     headers: {
        //         "Authorization": `Bearer ${localStorage.getItem("jwt")}`
        //     }
        // };
        // fetch(`${endpoint}/${sightingId}`, remove)
        //     .then(response => {
        //         if (response.ok) {
        //             history.push('/sightings');
        //             return null;
        //         }
        //         else {
        //             return Promise.reject();
        //         }
        //     })
        //     .catch(console.log);
    
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