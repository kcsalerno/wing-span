import { useEffect, useState } from "react";
import { Link, useHistory, useParams } from "react-router-dom";
import { findBySightingId, save } from "../services/sightings";
import {findAllBirds} from "../services/birds";
import Bird from "./Bird";

function SightingForm() {

    const [sighting, setSighting] = useState({
        sightingId: 0,
        sightingUserId: 0,
        sightingBirdId: 0,
        date: "",
        city: "",
        state: "",
        daytime: true
    })

    const [birds, setBirds] = useState([]);
    const [errors, setErrors] = useState([]);
    
    const history = useHistory();
    const { sightingId, sightingBirdId } = useParams();

    useEffect(() => {
        if (sightingId) {
            findBySightingId(sightingId)
            .then(setSighting)
            .catch(() => history.push("/"));
        }
    }, [history, sightingId]);

    useEffect(() => {
        findAllBirds()
        .then(setBirds)
        .catch(() => history.push("/error"));
    }, [history]);

    function handleChange(event) {
        const nextSighting = { ...sighting };
        if (event.target.name === "daytime") {
            nextSighting.daytime = event.target.checked;
        } else {
            nextSighting[event.target.name] = event.target.value;
        }
        setSighting(nextSighting);
    }

    function handleSubmit(event) {
        event.preventDefault();

        save(sighting)
        .then(() => history.push("/sightings"))
        .catch(errors => {
            if (errors) {
                setErrors(errors);
            } else {
                history.push("/error")
            }
        });
    }

    return(
        <form onSubmit={handleSubmit}>
            <h2>{sightingId > 0 ? "Edit Sighting" : "Add Sighting"}</h2>
            <div className="form-group">
                <label htmlFor="bird">Bird:</label>
                <select name="bird" id="bird" className="form-control"
                    value={sighting.bird} onChange={handleChange}>
                        <option>
                            <div>
                                {birds.map(b => <Bird key={b.birdId} bird={b} />)}
                            </div>
                        </option>
                        <option></option>
                        <option></option>
                        <option></option>
                        <option></option>
                        <option></option>
                        <option></option>
                        <option></option>
                    </select>
            </div>
            <div className="form-group">
                <label htmlFor="date" className="form-label">Date:</label>
                <input type="date" name="date" id="date" className="form-control"
                    value={sighting.date} onChange={handleChange}></input>
            </div>
            <div className="form-group">
                <label htmlFor="city" className="form-label">City:</label>
                <input type="text" name="city" id="city" className="form-control"
                    value={sighting.city} onChange={handleChange}></input>
            </div>
            <div className="form-group">
                <label htmlFor="state" className="form-label">State:</label>
                <input type="text" name="state" id="state" className="form-control"
                    value={sighting.state} onChange={handleChange}></input>
            </div>
            <div>
                <label htmlFor="daytime">Daytime?</label>
                <input type="checkbox" name="daytime" id="daytime"
                    checked={sighting.daytime} onChange={handleChange}></input>
            </div>
            {errors.length !== 0 && <div className="alert alert-danger">
                <ul>
                    {errors.map(error => <li key={error}>{error}</li>)}
                </ul>
            </div>}
            <div className="mt-4">
                <button className="btn btn-primary me-2" type="submit">Save</button>
                <Link to="/sightings" className="btn btn-warning">Cancel</Link>
            </div>
        </form>
    );

}

export default SightingForm;