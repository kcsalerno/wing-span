import AuthContext from "../contexts/AuthContext";
import { useContext, useState, useEffect } from 'react';
import { findAllSightings } from "../services/sightings";
import Sighting from "./Sighting";

function Profile() {
    const auth = useContext(AuthContext);
    const avatar = auth.user.avatar;

    const [sightings, setSightings] = useState([]);

    useEffect(() => {

        findAllSightings()
            .then(data => {
                setSightings(data);
            })
    }, []);

    const userSightings = sightings.filter((sighting) => sighting.sightingUserId === auth.user.userId)

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-4 mt-1">
                    <div className="card text-center sidebar">
                        <div className="card-body">
                            <h2>Profile</h2>
                            <img src={avatar.avatarImageUrl} className="ml-5" style={{ width: '200px' }} alt={avatar.avatarDescription}></img>
                            <div className="mt-3">
                                <h3>{auth.user.username}</h3>
                                <h3>{auth.user.email}</h3>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="col-md-8 mt-3">
                    <div className="card mb-3 content">
                        <h2 className="m-5">Badges</h2>
                        <div className="lead list-inline-item">
                            {userSightings.length >= 5 &&
                                (
                                    <>
                                        <div className="lead list-inline-item ml-4 mr-2">
                                            <img src="https://static.thenounproject.com/png/944533-200.png" alt="5 Sightings Badge. Black and white crow"
                                            style={{ width: "100px" }} className="mb-2" />
                                            <br />
                                            <h4>5 Bird Sightings</h4>
                                        </div>
                                    </>)}
                            {userSightings.length >= 10 &&
                                (
                                    <>
                                        <div className="lead list-inline-item ml-2 mr-2">
                                            <img src="https://static.thenounproject.com/png/944546-200.png" alt="10 Sightings Badge. Black and white flamingo."
                                                style={{ width: "100px" }} className="mb-2" />
                                            <br />
                                            <h4>10 Bird Sightings</h4>
                                        </div>
                                    </>
                                )
                            }
                            {userSightings.length >= 20 &&
                                (
                                    <>
                                        <div className="lead list-inline-item ml-2 mr-2">
                                            <img src="https://static.thenounproject.com/png/944545-200.png" alt="20 Sightings Badge. Black and white penguin"
                                                    style={{ width: "100px" }} className="mb-2" />
                                                <br />
                                                <h4>20 Bird Sightings</h4>
                                            </div>
                                        </>
                                    )
                                }
                                {userSightings.length >= 50 &&
                                    (
                                        <>
                                            <div className="lead list-inline-item ml-2 mr-2">
                                                <img src="https://static.thenounproject.com/png/944528-200.png" alt="50 Sightings Badge. Black and white hawk."
                                                    style={{ width: "100px" }} className="mb-2" />
                                                <br />
                                                <h4>50 Bird Sightings</h4>
                                            </div>
                                        </>
                                    )
                                }
                                {userSightings.length >= 100 &&
                                    (
                                        <>
                                            <div className="lead list-inline-item ml-2 mr-2">
                                                <img src="https://static.thenounproject.com/png/944535-200.png" alt="100 Sightings Badge. Black and white crane."
                                                    style={{ width: "100px" }} className="mb-2" />
                                                <br />
                                                <h4>100 Bird Sightings</h4>
                                            </div>
                                        </>
                                    )
                                }
                                </div>
                                {userSightings.length < 5 &&
                                    (
                                        <h3 className="ml-5 mt-3 mb-3">
                                            You need {5 - userSightings.length} more sightings to reach your next badge!
                                        </h3>
                                    )
                                }
                                {userSightings.length < 10 && userSightings.length >= 5 &&
                                    (
                                        <h3 className="ml-5 mt-3 mb-3">
                                            You need {10 - userSightings.length} more sightings to reach your next badge!
                                        </h3>
                                    )
                                }
                                {userSightings.length < 20 && userSightings.length >= 10 &&
                                    (
                                        <h3 className="ml-5 mt-3 mb-3">
                                            You need {20 - userSightings.length} more sightings to reach your next badge!
                                        </h3>
                                    )
                                }
                                {userSightings.length < 50 && userSightings.length >= 20 &&
                                    (
                                        <h3 className="ml-5 mt-3 mb-3">
                                            You need {50 - userSightings.length} more sightings to reach your next badge!
                                        </h3>
                                    )
                                }
                                {userSightings.length < 100 && userSightings.length >= 500 &&
                                    (
                                        <h3 className="ml-5 mt-3 mb-3">
                                            You need {100 - userSightings.length} more sightings to reach your next badge!
                                        </h3>
                                    )
                                }       
                        </div>
                    </div>
                    <div className="card mt-5 mb-5 content">
                        <h2 className="m-3">Sightings</h2>
                            <div className="card-body">
                                <div className="ml-4 row row-cols-3 g-2">
                                    {userSightings.map(s => <Sighting key={s.sightingId} sighting={s} />)}
                                </div>
                            </div>
                    </div>
                </div>
            </div>
    );
}

export default Profile;