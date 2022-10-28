import { useState, useEffect } from "react";
import { useHistory, Link } from "react-router-dom";
import { findAllSightings } from "../services/sightings";
import { findAllBirds } from "../services/birds";


function SightingList() {
    const [sightings, setSightings] = useState([]);
    // const [birds, setBirds] = useState([]);
    // const [loaded, setLoaded] = useState(false);

    const history = useHistory();

    useEffect(() => {
        findAllSightings()
        .then(async data => {
            const response = await findAllBirds();
            // temporary copy of sightings
            const temp = [...data];
            // loop through temporary copy
            temp.forEach((sighting, index) => {
                // get the birdId for sighting
                const birdId = sighting.sightingBirdId;
                // assign to a bird
                const bird = response.find(bird => bird.birdId === birdId);
                temp[index].birdCommonName = bird.commonName;
            });
            setSightings(temp);})
        .catch(() => history.push("/error"))
    }, [history]);

    // add a join to include users in sightings
    // or look into a promise all
    // or do a third .then

    console.log(sightings);

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