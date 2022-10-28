import { useState, useEffect } from "react";
import { useHistory, Link } from "react-router-dom";
import { findAllSightings } from "../services/sightings";
import { findAllBirds } from "../services/birds";

function SightingList() {
    const [sightings, setSightings] = useState([]);
    const history = useHistory();

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
            setSightings(temp);})
        .catch(() => history.push("/error"))
    }, [history]);

    return (
        <>
            <h2>Sightings</h2>
            <Link className="btn btn-dark" to="/sightings/add" id="add">Add Sigthing</Link>
            <table className="table table-bordered table-hover table-striped">
                <caption>List of user sightings</caption>
                <thead className="thead-dark">
                    <tr>
                        <th scope="col">Date</th>
                        <th scope="col">User</th>
                        <th scope="col">Bird</th>
                        <th scope="col">City</th>
                        <th scope="col">State</th>
                        <th scope="col">Daytime?</th>
                    </tr>
                </thead>
                <tbody>
                    {sightings.map((s) => (
                        <tr key={s.sightingId}>
                            <td>{s.date}</td>
                            <td>{s.username}</td>
                            <td>{s.birdCommonName}</td>
                            <td>{s.city}</td>
                            <td>{s.state}</td>
                            <td>{s.daytime ? "Yes" : "No"}</td>
                        
                        </tr>
                    ))}
                </tbody>
            </table>
        </>
    )
}

export default SightingList;