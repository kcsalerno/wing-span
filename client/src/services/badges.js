const BADGE_API_URL = "http://wing-span-web-app.us-east-1.elasticbeanstalk.com/api/badge";

export async function findAllBadges() {
    const response = await fetch(BADGE_API_URL);
    if (response.ok) {
        return response.json();
    } else {
        return Promise.reject();
    }
}

export async function findById(badgeId) {
    const response = await fetch(`${BADGE_API_URL}/${badgeId}`);
    if (response.ok) {
        return response.json();
    } else {
        return Promise.reject();
    }
}