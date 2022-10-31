import AuthContext from "../contexts/AuthContext";
import { useContext, useState, useEffect } from 'react';
import { findAllSightings } from "../services/sightings";

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
            <section className="lead mt-4 mb-3">
                {/* Note to self: Make the avatar image and user info inline with the badges.
                    Make the badges in a grid if possible, or as inline elements themselves.
                    Conditionally render them by finding all sightings, filtering that down to just the
                    current user's sightings, then determining total count, bird type, bird type count, etc. */}
                <div>
                    <img src={avatar.avatarImageUrl} style={{ width: '200px' }} alt={avatar.avatarDescription}></img>
                </div>
                Username: {auth.user.username}
                <br />
                Email: {auth.user.email}
            </section>
            <section>
                Badges: {userSightings.length >= 3 &&
                    (
                    <>
                    <div>
                    <img src="https://static.thenounproject.com/png/1120113-200.png" alt="3 Sightings Bird Badge. Picture of a bird in a circle"
                        style={{width: "100px"}}/>
                    3 Bird Sightings
                    </div>
                    </>)}
            </section>
            <section>
                Sightings:
                    {/* Sightings go here once that's done. */}
            </section>
        </>
    );
}

export default Profile;