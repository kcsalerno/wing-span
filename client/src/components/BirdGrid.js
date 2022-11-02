import { useState, useEffect } from "react";
import { useHistory, Link } from "react-router-dom";
import { findAllBirds } from "../services/birds"
import Bird from "./Bird";

function BirdGrid({ handleEdit, handleDelete }) {
    const [birds, setBirds] = useState([]);

    const history = useHistory();

    useEffect(() => {
        findAllBirds()
        .then(setBirds)
        .catch(() => history.push("/error"))
        // eslint-disable-next-line
    }, []);

    return (
        <>
            <h2>Birds</h2>
            <Link className="btn btn-dark" to="/birds/add" id="addBird">Add Bird</Link>
            <div className="row row-cols-3 g-2">
                {birds.map(b => <Bird key={b.birdId} bird={b} />)}
            </div>
        </>
    )
}

export default BirdGrid;