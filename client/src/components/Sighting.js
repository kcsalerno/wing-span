import { useContext } from "react";
import { Link } from "react-router-dom";
import AuthContext from "../contexts/AuthContext";

function Sighting({ sighting }) {

    const auth = useContext(AuthContext);

    return (
        <div className="col">
            <div className="card mb-5">
                <img src={sighting.bird.birdImageUrl} className="card-img-top" alt={sighting.bird.commonName} style={{height: '300px'}}/>
                <div className="card-body">
                    <h5 className="card-title">Common Name: {sighting.bird.commonName}</h5>
                    <h5>Scientific Name: {sighting.bird.scientificName}</h5>
                    <h5>Date: {sighting.date}</h5>
                    <h5>Daytime: {sighting.daytime ? 'Yes' : 'No'}</h5>
                    <h5>City: {sighting.city}</h5>
                    <h5>State: {sighting.state}</h5>
                    <h5>Traits: {sighting.traits ? sighting.traits.map(t => `${t.name} `) : ''}</h5>
                </div>
                {auth.user && <div className="card-footer">
                    <Link className="btn add mr-2" to={`/sightings/edit/${sighting.sightingId}`}><i className='bi bi-pencil-square'></i> Edit</Link>
                    <Link className="btn delete" to={`/sightings/delete/${sighting.sightingId}`}><i className='bi bi-trash'></i> Delete</Link>
                </div>}
            </div>
        </div>
    )
}

export default Sighting;