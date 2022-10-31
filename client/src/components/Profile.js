import AuthContext from "../contexts/AuthContext";
import { useContext } from 'react';

function Profile() {
    const auth = useContext(AuthContext);
    const avatar = auth.user.avatar;

    console.log(avatar);
    console.log(auth.user.avatar.avatarImageUrl);
    console.log(auth.user.avatar.avatarDescription);

    return (
        <>
        <h2>Profile</h2>
        <section>
            <div>
                <img src={avatar.avatarImageUrl} style={{width: '200px'}} alt={avatar.avatarDescription}></img>
            </div>
            Username: {auth.user.username}
            <br />
            Email: {auth.user.email}
        </section>
        </>
    );
}

export default Profile;