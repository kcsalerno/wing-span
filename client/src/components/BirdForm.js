import { useEffect, useState } from "react";
import { Link, useHistory, useParams } from "react-router-dom";
import { findByBirdId, save } from "../services/birds";

function BirdForm() {

    const [bird, setBird] = useState({
        birdId: 0,
        commonName: "",
        scientificName: "",
        birdImageUrl: ""
    })

    const [errors, setErrors] = useState([]);
    const history = useHistory();
    const { birdId } = useParams();

    useEffect(() => {
        if (birdId) {
            findByBirdId(birdId)
            .then(setBird)
            .catch(() => history.push("/birds"))
        }
    }, [history, birdId]);

    function handleChange(event) {
        const newBird = {...bird};
        newBird[event.target.name] = event.target.value;
        setBird(newBird);
    }

    function handleSubmit(event) {
        event.preventDefault();
        save(bird)
        .then(() => history.push("/birds"))
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
            <h2>{birdId > 0 ? "Edit Bird" : "Add Bird"}</h2>
            <div className="mb-3">
                <label htmlFor="commonName" className="form-lable">Common Name:</label>
                <input type="text" name="commonName" id="commonName" className="form-control"
                    value={bird.commonName} onChange={handleChange}></input>
            </div>
            <div className="mb-3">
                <label htmlFor="scientificName" className="form-lable">Scientific Name:</label>
                <input type="text" name="scientificName" id="scientificName" className="form-control"
                    value={bird.scientificName} onChange={handleChange}></input>
            </div>
            <div className="mb-3">
                <label htmlFor="birdImageUrl" className="form-lable">Bird Image URL:</label>
                <input type="text" name="birdImageUrl" id="birdImageUrl" className="form-control"
                    value={bird.birdImageUrl} onChange={handleChange}></input>
            </div>
            {errors.length !== 0 && <div className="alert alert-danger">
                <ul>
                    {errors.map(error => <li key={error}>{error}</li>)}
                </ul>
            </div>}
            <div className="mb-3">
                <button className="btn btn-primay me-2" type="submit">Save</button>
                <Link to="/birds" className="btn btn-warning">Cancel</Link>
            </div>
        </form>
    );

}

export default BirdForm;