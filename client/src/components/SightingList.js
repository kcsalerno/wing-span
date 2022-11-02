import { useState, useEffect, useContext } from "react";
import { useHistory, Link } from "react-router-dom";
import { findAllSightings } from "../services/sightings";
import { findAllBirds } from "../services/birds";
import AuthContext from "../contexts/AuthContext";

function SightingList() {
    const [sightings, setSightings] = useState([]);
    const history = useHistory();
    const auth = useContext(AuthContext);

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
            // eslint-disable-next-line
    }, []);

    return (
        <>
            <h2>Sightings</h2>
            <Link className="btn btn-success" to="/sightings/add" id="add"><i className='bi bi-plus-circle'></i> Add Sighting</Link>
            <table className="table table-bordered table-hover table-striped">
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
                                <Link to={`/birds`}>{s.birdCommonName}</Link>
                            </td>
                            <td>{s.city}</td>
                            <td>{s.state}</td>
                            <td>{s.daytime ? "Yes" : "No"}</td>
                            {((auth.user && auth.user.hasRole('ADMIN')) || (auth.user && auth.user.hasRole('USER'))) &&
                                <td className="buttonContainer">
                                    <Link className="btn btn-primary" to={`/sightings/edit/${s.sightingId}`}><i className='bi bi-pencil-square'></i> Edit</Link>
                                    <Link className="btn btn-danger" to={`/sightings/delete/${s.sightingId}`}><i className='bi bi-trash'></i> Delete</Link>
                                </td>}
                        </tr>
                    ))}
                </tbody>
            </table>
        </>
    )
}

export default SightingList;