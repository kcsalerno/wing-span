function Bird({ bird }) {
    return (
        <div className="col">
            <div className="card mb-5">
                <img src={bird.birdImageUrl} className="card-img-top" alt={bird.commonName} style={{height: '300px'}}/>
                <div className="card-body">
                    <h5 className="card-title">Common Name: {bird.commonName}</h5>
                    <h5>Scientific Name: {bird.scientificName}</h5>
                </div>
            </div>
        </div>
    )
}

export default Bird;