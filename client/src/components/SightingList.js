import { useState, useEffect, useContext } from "react";
import { useHistory, Link } from "react-router-dom";
import { findAllSightings } from "../services/sightings";
import { findAllBirds } from "../services/birds";
import { deleteById } from "../services/sightings"
import Bird from "./Bird";
import AuthContext from "../contexts/AuthContext";

function SightingList() {
    const [sightings, setSightings] = useState([]);
    const history = useHistory();

    const auth = useContext(AuthContext);

    // console.log(auth);
    // console.log(auth.user);

    useEffect(() => {
        findAllSightings()
            .then(async data => {
                const response = await findAllBirds();
                const temp = [...data];
                temp.forEach((sighting, index) => {
                    const birdId = sighting.sightingBirdId;
                    const bird = response.find(bird => bird.birdId === birdId);
                    temp[index].birdCommonName = bird.commonName;
                });
                setSightings(temp);
                console.log(temp);
            })
            .catch(() => history.push("/error"))
    }, [history]);

    return (
        <>
            <h2>Sightings</h2>
            <Link className="btn btn-success" to="/sightings/add" id="add">Add Sighting</Link>
            <table className="table table-bordered table-hover table-striped">
                {/* <caption>List of user sightings</caption> */}
                <thead className="thead-dark">
                    <tr>
                        <th scope="col">Date</th>
                        <th scope="col">User</th>
                        <th scope="col">Bird</th>
                        <th scope="col">City</th>
                        <th scope="col">State</th>
                        <th scope="col">Daytime?</th>
                        {auth.user && auth.user.hasRole('ADMIN') &&
                            <th>&nbsp;</th>
                        }
                    </tr>
                </thead>
                <tbody>
                    {sightings.map((s) => (
                        <tr key={s.sightingId}>
                            <td>{s.date}</td>
                            <td>{s.username}</td>
                            <td>
                                <Link to={`/birds/${Bird.birdId}`}>{s.birdCommonName}</Link>
                            </td>
                            <td>{s.city}</td>
                            <td>{s.state}</td>
                            <td>{s.daytime ? "Yes" : "No"}</td>
                            {/* Just a quick test of conditional rendering for matching usernames, this is temporary - will implement in Profile */}
                            {auth.user && auth.user.hasRole('ADMIN') && auth.user.username === s.username &&
                                <td className="buttonContainer">
                                    <Link className="btn btn-primary" to={`/sightings/edit/${s.sightingId}`}>Edit</Link>
                                    <Link className="btn btn-danger" to={`/sightings/deletebyId/${s.sightingId}`}>Delete</Link>
                                </td>}
                        </tr>
                    ))}
                </tbody>
            </table>
        </>
    )
}

export default SightingList;