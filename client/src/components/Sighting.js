function Sighting({ sighting }) {
    return (
        <div className="col">
            <div className="card mb-5">
                <img src={sighting.bird.birdImageUrl} className="card-img-top" alt={sighting.bird.commonName} style={{height: '300px'}}/>
                <div className="card-body">
                    <h5 className="card-title">Common Name: {sighting.bird.commonName}</h5>
                    <h5>Scientific Name: {sighting.bird.scientificName}</h5>
                    <h5>Date: {sighting.date}</h5>
                    <h5>Daytime: {sighting.daytime ? 'Yes' : 'No'}</h5>
                    <h5>City: {sighting.city}</h5>
                    <h5>State: {sighting.state}</h5>
                    <h5>Traits: {sighting.traits ? sighting.traits.map(t => `${t.name} `) : ''}</h5>
                </div>
            </div>
        </div>
    )
}

export default Sighting;