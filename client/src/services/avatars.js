const AVATAR_API_URL = "http://wing-span-web-app.us-east-1.elasticbeanstalk.com/api/avatar";

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