const TRAIT_API_URL = "http://localhost:8080/api/trait";

export async function findAllTraits() {
    const response = await fetch(TRAIT_API_URL);
    if (response.ok) {
        return response.json();
    } else {
        return Promise.reject();
    }
}

export async function findById(traitId) {
    const response = await fetch(`${TRAIT_API_URL}/${traitId}`);
    if (response.ok) {
        return response.json();
    } else {
        return Promise.reject();
    }
}