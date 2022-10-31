// I know this is not the best way to do this, but it was the easiest and fastest to get things going.
// This definitely needs cleaned up because there is plenty of duplicated code going on in multiple places.
// I would have liked to move more things in here, but again, for now this is going to have to do.

import jwtDecode from "jwt-decode";

const API_URL = "http://localhost:8080";

const LOCAL_STORAGE_TOKEN_KEY = "wingspanToken";

export async function refresh() {
    console.log('refresh was called!');
    const token = localStorage.getItem(LOCAL_STORAGE_TOKEN_KEY);

    const init = {
        method: "POST",
        headers: {
            "Authorization": `Bearer ${localStorage.getItem("jwt")}`
        }
    }

    const response = await fetch(`${API_URL}/refresh_token`, init);
    if (response.ok) {
        // const body = await response.json();
        // return makeUser(body);

        const { jwt_token } = await response.json();

        console.log(jwt_token);

        localStorage.setItem("jwt", jwt_token);

        localStorage.setItem(LOCAL_STORAGE_TOKEN_KEY, token);

        // Decode the token
        const { sub: username, app_user_id: userId, email, avatar, authorities: authoritiesString } = jwtDecode(token);

        // Split the authorities string into an array of roles
        const roles = authoritiesString.split(',');

        // Create the "user" object
        const user = {
            username,
            userId,
            email,
            avatar,
            roles,
            token,
            hasRole(role) {
                return this.roles.includes(role);
            }
        };

        return user;
    }

    return Promise.reject();
}