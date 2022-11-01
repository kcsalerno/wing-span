import AuthContext from "../contexts/AuthContext";
import { useContext, useState, useEffect } from 'react';
import { Link } from "react-router-dom";
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

    console.log(sightings);

    const userSightings = sightings.filter((sighting) => sighting.sightingUserId === auth.user.userId)

    console.log(userSightings);

    console.log(userSightings[0]);

    return (
        <>
            <h2>Profile</h2>
            <section className="mt-4 mb-3">
                <h3 className="mt-2">Username: {auth.user.username}</h3>
                <h3>Email: {auth.user.email}</h3>
                <div className="ml-5">
                    <img src={avatar.avatarImageUrl} style={{ width: '200px' }} alt={avatar.avatarDescription}></img>
                </div>
            </section>
            <section>
                <div>
                    Badges:
                </div>
                {userSightings.length >= 5 &&
                    (
                        <>

                            <div className="lead list-inline-item ml-5 mr-5">
                                <img src="https://static.thenounproject.com/png/1120113-200.png" alt="5 Sightings Badge. A bird in a circle."
                                    style={{ width: "100px" }} className="mb-2" />
                                <br />
                                5 Bird Sightings
                            </div>

                        </>)}
                {userSightings.length >= 10 &&
                    (
                        <>
                            <div className="lead list-inline-item ml-5 mr-5">
                                <img src="https://static.thenounproject.com/png/1188264-200.png" alt="10 Sightings Badge. A bird in binocular view-finder."
                                    style={{ width: "100px" }} className="mb-2" />
                                <br />
                                10 Bird Sightings
                            </div>
                        </>
                    )
                }
                {userSightings.length >= 20 &&
                    (
                        <>
                            <div className="lead list-inline-item ml-5 mr-5">
                                <img src="https://static.thenounproject.com/png/4451522-200.png" alt="20 Sightings Badge. A bird sitting on top of binoculars."
                                    style={{ width: "100px" }} className="mb-2" />
                                <br />
                                20 Bird Sightings
                            </div>
                        </>
                    )
                }
                {userSightings.length >= 50 &&
                    (
                        <>
                            <div className="lead list-inline-item ml-5 mr-5">
                                <img src="https://static.thenounproject.com/png/1511937-200.png" alt="50 Sightings Badge. Outline of an eye with a bird inside it."
                                    style={{ width: "100px" }} className="mb-2" />
                                <br />
                                50 Bird Sightings
                            </div>
                        </>
                    )
                }
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
            </section>
            <section>
                Sightings:
                <div className="ml-4 row row-cols-3 g-2">
                    {userSightings.map(s => <Sighting key={s.sightingId} sighting={s} />)}
                </div>
            </section>
        </>
    );
}

export default Profile;