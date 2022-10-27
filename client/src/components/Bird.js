function Bird({ bird }) {
    return (
        <div className="col">
            <div className="card">
                <img src={bird.birdImageUrl} className="card-img-top" alt={bird.commonName} />
                <div className="card-body">
                    <h5 className="card-title">{bird.commonName}</h5>
                    <h5>{bird.scientificName}</h5>
                </div>
            </div>
        </div>
    )
}

export default Bird;