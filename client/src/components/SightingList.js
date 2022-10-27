import { useState, useEffect } from "react";
import { useHistory } from "react-router-dom";
import { findAllSightings } from "../services/sightings";


function SightingList() {
    const [sightings, setSightings] = useState([]);

    const history = useHistory();

    useEffect(() => {
        findAllSightings()
        .then(setSightings)
        .catch(() => history.push("/error"))
    }, [history]);

    return (
        <>
            <table>
                <thead>
                    <tr>
                        <th>Date</th>
                        <th>User</th>
                        <th>Bird</th>
                        <th>City</th>
                        <th>State</th>
                        <th>DayTime</th>
                    </tr>
                </thead>
                <tbody>
                    {sightings.map((s) => (
                        <tr key={s.sightingId}>
                            <td>{s.date}</td>
                            <td>{s.sightingUserId}</td>
                            <td>{s.sightingBirdId}</td>
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