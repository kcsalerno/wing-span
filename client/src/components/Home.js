function Home() {
    return (
        <div className="home">
            <h2>Welcome to WingSpan!</h2>
            <p>The premier bird watching application!</p>
            <div className="sliderContainer">
                <div className="slideImages">
                    <div className="imageContainer">
                        <img src="https://images.unsplash.com/photo-1620588280212-bf1d2b23b112?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80"
                        alt="Eastern Bluebird"/>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Home;