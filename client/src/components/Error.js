function Error({ errors }) {
    if (!errors || errors.length === 0) {
        return null;
    }

    return (
        <div id='errors' className='ml-5 mr-5 w-77 rounded alert alert-danger'>
            <p className='ml-3 mt-3'><u>Please Correct the Following Errors:</u></p>
            <ul className='ml-4'>
                {errors.map(error => (
                    <li key={error}>{error}</li>
                ))}
            </ul>
        </div>
    );
}

export default Error;