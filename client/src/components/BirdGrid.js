import { useState, useEffect } from "react";
import { useHistory, Link } from "react-router-dom";
import { findAllBirds } from "../services/birds"
import Bird from "./Bird";

function BirdGrid() {
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


            <Link className="btn btn-success" to="/birds/add" id="addBird"><i className='bi bi-plus-circle'></i> Add Bird</Link>
            <div className="row row-cols-3 g-2">
                {birds.map(b => <Bird key={b.birdId} bird={b} />)}
            </div>
        </>
    )
}

export default BirdGrid;