const AVATAR_API_URL = "http://localhost:8080/api/avatar";

export async function findAllAvatars() {
    const response = await fetch(AVATAR_API_URL);
    if (response.ok) {
        return response.json();
    } else {
        return Promise.reject();
    }
}

export async function findById(avatarId) {
    const response = await fetch(`${AVATAR_API_URL}/${avatarId}`);
    if (response.ok) {
        return response.json();
    } else {
        return Promise.reject();
    }
}