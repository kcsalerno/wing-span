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

async function add(bird) {
    const init = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${localStorage.getItem("jwt")}`
        },
        body: JSON.stringify(bird)
    };

    const response = await fetch(BIRD_API_URL, init);
    if (response.ok) {
        return Promise.resolve();
    } else if (response.status === 400) {
        const errors = await response.json();
        return Promise.reject(errors);
    } else {
        return Promise.reject();
    }
}

async function update(bird) {
    const init = {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${localStorage.getItem("jwt")}`
        },
        body: JSON.stringify(bird)
    };

    const response = await fetch(`${BIRD_API_URL}/${bird.birdID}`, init);
    if (response.ok) {
        return Promise.resolve();
    } else if (response.status === 400) {
        const errors = await response.json();
        return Promise.reject(errors)
    } else {
        return Promise.reject();
    }
}

export async function save(bird) {
    return bird.birdId > 0 ? update(bird) : add(bird);
}

export async function deleteById(birdId) {
    const init = {
        method: "DELETE",
        headers: {
            "Authorization": `Bearer ${BIRD_API_URL}/${birdId}`
        }
    };

    const response = await fetch(`${BIRD_API_URL}/${birdId}`, init);
    if (response.ok) {
        return Promise.resolve();
    } else {
        return Promise.reject();
    }
}
