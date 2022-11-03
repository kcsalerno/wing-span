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
        <div className="container" id="bird-form">
            <form onSubmit={handleSubmit}>
                <h2>{birdId > 0 ? "Edit Bird" : "Add Bird"}</h2>
                {errors.length !== 0 && <div className="alert alert-danger">
                    <ul>
                        {errors.map(error => <li key={error}>{error}</li>)}
                    </ul>
                </div>}
                <div className="form-group mb-3">
                    <label htmlFor="commonName" className="form-label">Common Name:</label>
                    <input type="text" name="commonName" id="commonName" className="form-control form-control-lg"
                        value={bird.commonName} onChange={handleChange}></input>
                </div>
                <div className="form-group mb-3">
                    <label htmlFor="scientificName" className="form-label">Scientific Name:</label>
                    <input type="text" name="scientificName" id="scientificName" className="form-control form-control-lg"
                        value={bird.scientificName} onChange={handleChange}></input>
                </div>
                <div className="form-group mb-3">
                    <label htmlFor="birdImageUrl" className="form-label">Bird Image URL:</label>
                    <input type="text" name="birdImageUrl" id="birdImageUrl" className="form-control form-control-lg"
                        value={bird.birdImageUrl} onChange={handleChange}></input>
                </div>
                <div className="mb-3">
                    <button className="btn add btn-lg me-2 mr-2" type="submit"><i className='bi bi-file-earmark-check'></i> Save</button>
                    <Link to="/birds" className="btn delete btn-lg "><i className='bi bi-file-earmark-excel'></i> Cancel</Link>
                </div>
            </form>
        </div>
    );

}

export default BirdForm;