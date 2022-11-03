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
        <div className="container">
            <div className="button-head-birds">
                <h2>Birds</h2>
                <Link className="btn add" to="/birds/add" id="add"><i className='bi bi-plus-circle'></i> Add Bird</Link>
            </div>
            <div className="row row-cols-3 g-2">
                {birds.map(b => <Bird key={b.birdId} bird={b} />)}
            </div>
        </div>
    )
}

export default BirdGrid;