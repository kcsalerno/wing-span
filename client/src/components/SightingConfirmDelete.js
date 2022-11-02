import {useEffect, useState} from "react";
import { Link, useHistory, useParams } from "react-router-dom";
import { deleteById, findBySightingId } from "../services/sightings";

function SightingConfirmDelete() {
    const [sighting, setSighting] = useState({});

    const history = useHistory();
    const {sightingId} =useParams();

    useEffect(() => {
        if (!sightingId) {
            history.push("/");
        }

        findBySightingId(sightingId)
            .then(setSighting)
            .catch(() => history.push("/"));
    }, [history, sightingId])

    function handleDelete() {
        deleteById(sighting.sightingId)
        .then(() => history.goBack())
        .catch(() => history.push("/error"));
    };

    return (
        <div>
            <h2>Confirm Delete</h2>
            <div className="alert alert-danger">
                <p>
                    This will permantely delete sighting for bird {sighting.sightingBirdId}.
                </p>
            </div>
            <div>
                <button className="btn btn-danger me-2" onClick={handleDelete}>Delete</button>
                <Link to="/sightings" className="btn btn-warning">Cancel</Link>
            </div>

{/* <button onclick="document.getElementById('id01').style.display='block'">Open Modal</button>

<div id="id01" class="modal">
  <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
  <form class="modal-content" action="/action_page.php">
    <div class="container">
      <h1>Delete Account</h1>
      <p>Are you sure you want to delete your account?</p>

      <div class="clearfix">
            <Link className="btn btn-danger me-2" onClick={handleDelete} to="/sightings">Delete</Link>
            <Link to="/sightings" className="btn btn-warning">Cancel</Link>
      </div>
    </div>
  </form>
    </div>*/}
        </div>
    )
}

export default SightingConfirmDelete;