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
        const { sub: username, app_user_id: userId, email, authorities: authoritiesString } = jwtDecode(token);

        // Split the authorities string into an array of roles
        const roles = authoritiesString.split(',');

        // Create the "user" object
        const user = {
            username,
            userId,
            email,
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