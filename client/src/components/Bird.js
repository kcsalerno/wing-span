import { useContext } from "react";
import { Link } from "react-router-dom";
import AuthContext from "../contexts/AuthContext";

function Bird({ bird }) {

    const auth = useContext(AuthContext);

    return (
        <div className="col">
            <div className="card mb-5">
                <img src={bird.birdImageUrl} className="card-img-top" alt={bird.commonName} style={{ height: '300px' }} />
                <div className="card-body">
                    <h5 className="card-title">Common Name: {bird.commonName}</h5>
                    <h5>Scientific Name: {bird.scientificName}</h5>
                </div>
                {auth.user &&
                    <div className="card-footer">
                        <Link to={`/birds/edit/${bird.birdId}`} className="btn add mr-2"><i className='bi bi-pencil-square'></i> Edit</Link>
                        {auth.user.hasRole('ADMIN') &&
                            <Link to={`/birds/delete/${bird.birdId}`} className="btn delete"><i className='bi bi-trash'></i> Delete</Link>}
                    </div>
                }
            </div>
        </div>
    )
}

export default Bird;