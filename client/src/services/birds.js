const BIRD_API_URL = "http://localhost:8080/api/bird";

export async function findAllBirds() {
    const response = await fetch(BIRD_API_URL);
    if (response.ok) {
        return response.json();
    } else {
        return Promise.reject();
    }
}

export async function findByBirdId(birdId) {
    const reponse = await fetch(`${BIRD_API_URL}/${birdId}`);
    if (reponse.ok) {
        return reponse.json();
    } else {
        return Promise.reject();
    }
}