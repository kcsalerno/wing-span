import { useState, useEffect } from "react";
import { useHistory } from "react-router-dom";
import { findAll } from "../services/birds"
import Bird from "./Bird";

function BirdGrid({ handleEdit, handleDelete}) {
    const [birds, setBirds] = useState([]);

    const history = useHistory();

    useEffect(() => {
        findAll()
        .then(setBirds)
        .catch(() => history.push("/error"))
    }, [history]);

    return (
        <>
            <div className="row row-cols-3 g-2">
                {birds.map(b => <Bird key={b.birdId} bird={b} handleEdit={handleEdit} handleDelete={handleDelete} />)}
            </div>
        </>
    )
}

export default BirdGrid;