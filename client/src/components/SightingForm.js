import { useEffect, useState } from "react";
import { Link, useHistory, useParams } from "react-router-dom";
import { findBySightingId, save } from "../services/sightings";
import {findAllBirds} from "../services/birds";
import { findAllTraits } from "../services/traits";
import Select from "react-select";

import { useContext } from 'react';
import AuthContext from "../contexts/AuthContext";


function SightingForm() {
    const auth = useContext(AuthContext);
    
    const [sighting, setSighting] = useState({
        sightingId: 0,
        sightingUserId: auth.user.userId,
        sightingBirdId: 0,
        date: "",
        city: "",
        state: "",
        daytime: false,
        traits: []
    })

    const [birds, setBirds] = useState([]);
    const [traits, setTraits] = useState([]);
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
            .then((data) => {
                setBirds(data)
                if (!sightingId) {
                    const temp = { ...sighting }
                    temp.sightingBirdId = data[0].birdId;
                    setSighting(temp);
                }
            })
            .catch(() => history.push("/error"));
    }, [history, sightingBirdId]);

    useEffect(() => {
        findAllTraits()
            .then((data) => {
                setTraits(data)
            })
            .catch(() => history.push("/error"));
    },[history, sightingId])

    function handleChange(event) {
        const nextSighting = { ...sighting };
        if (event.target.name === "daytime") {
            nextSighting.daytime = event.target.checked;
        } else {
            nextSighting[event.target.name] = event.target.value;
        }

        if (event.target.name === "traits") {
            nextSighting.traits = event.target.checked;
        //     const traitId = parseInt(event.target.value);
        //     if (event.target.checked) {
        //         nextSighting.traits.push(traits.find(t => t.traitId === traitId));
        //     } else {
        //         nextSighting.traits = nextSighting.traits.filter(t => t.traitId !== traitId);
        //     }
        // } else {
        //     nextSighting[event.target.name] = event.target.value;
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

    return (
        <form onSubmit={handleSubmit}>
            <h2>{sightingId > 0 ? "Edit Sighting" : "Add Sighting"}</h2>
            <div className="form-group">
                <label htmlFor="sightingBirdId">Bird:</label>
                <select name="sightingBirdId" id="sightingBirdId" className="form-control"
                    onChange={handleChange} value={sighting.sightingBirdId}>
                    {birds.map((b, i) => <option selected={i === 1} key={b.birdId} value={b.birdId}>{b.commonName}</option>)}
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
                <label htmlFor="daytime" className="mr-2">Daytime?</label>
                <input type="checkbox" name="daytime" id="daytime"
                    checked={sighting.daytime} onChange={handleChange}></input>
            </div>

            <div className="mt-2">
                {traits.map(trait => (
                    <div>
                        <label htmlFor={"trait" + trait.traitId}>{trait.name}</label>
                        {/* <input type="checkbox"
                            name="traits"
                            id="traits"
                            value={trait.traitId}
                            checked={sighting.traitId}
                            // {traits.find(t => t.traitId === trait.traitId) !== undefined}
                            onChange={handleChange}>
                        </input> */}
                        <select name="traits" closeMenuOnSelect={false} onChange={handleChange} isMulti options={traits.map((t, key) => ({
                            label: t,
                            value: key,
                        }))}></select>
                    </div>
                ))}
            </div>
            {errors.length !== 0 && <div className="alert alert-danger">
                <ul>
                    {errors.map(error => <li key={error}>{error}</li>)}
                </ul>
            </div>}
            <div className="mt-4">
                <button className="btn btn-primary me-2 mr-2" type="submit">Save</button>
                <Link to="/sightings" className="btn btn-warning">Cancel</Link>
            </div>
        </form>
    );

}

export default SightingForm;