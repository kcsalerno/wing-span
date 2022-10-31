import AuthContext from "../contexts/AuthContext";
import { useContext, useState } from 'react';

function Profile() {
    const auth = useContext(AuthContext);
    const avatar = auth.user.avatar;

    // console.log(auth);
    // console.log(avatar);
    // console.log(auth.user.avatar.avatarImageUrl);
    // console.log(auth.user.avatar.avatarDescription);

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
                <h3>Sightings:</h3>
                <div className="row row-cols-3 g-2">
                    Sighting info here with picture of bird, plus edit and delete buttons
                </div>
            </section>
        </>
    );
}

export default Profile;