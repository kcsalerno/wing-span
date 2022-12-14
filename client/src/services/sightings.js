const SIGHTING_API_URL = "http://wing-span-app.us-east-1.elasticbeanstalk.com/api/sighting";

export async function findAllSightings() {
    const response = await fetch(SIGHTING_API_URL);
    if (response.ok) {
        return response.json();
    } else {
        return Promise.reject();
    }
}

export async function findBySightingId(sightingId) {
    const response = await fetch(`${SIGHTING_API_URL}/${sightingId}`)
    if (response.ok) {
        return response.json();
    } else {
        return Promise.reject();
    }
}

async function add(sighting) {
    const init = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${localStorage.getItem("jwt")}`
        },
        body: JSON.stringify(sighting)
    };

    console.log(sighting);

    const response = await fetch(SIGHTING_API_URL, init);
    if (response.ok) {
        return Promise.resolve();
    } else if (response.status === 400) {
        const errors = await response.json();
        return Promise.reject(errors);
    } else {
        return Promise.reject();
    }
}

async function update(sighting) {
    const init = {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${localStorage.getItem("jwt")}`
        },
        body: JSON.stringify(sighting)
    };

    const response = await fetch(`${SIGHTING_API_URL}/${sighting.sightingId}`, init);
    if (response.ok) {
        return Promise.resolve();
    } else if (response.status === 400) {
        const errors = await response.json();
        return Promise.reject(errors);
    } else {
        return Promise.reject();
    }
}

export async function save(sighting) {
    return sighting.sightingId > 0 ? update(sighting) : add(sighting);
}

export async function deleteById(sightingId) {
    const init = {
        method: "DELETE",
        headers: {
            "Authorization": `Bearer ${localStorage.getItem("jwt")}`
        }
    };

    const response = await fetch(`${SIGHTING_API_URL}/${sightingId}`, init);
    if (response.ok) {
        return Promise.resolve();
    } else {
        return Promise.reject();
    }
}