// src/components/UrlForm.js
import React, { useState } from 'react';
import axios from 'axios';
//import QRCode from 'qrcode.react'; // Import QRCode component not using

const UrlForm = ({ onShorten }) => {
    const [originalUrl, setOriginalUrl] = useState(''); // State to hold the original URL input by the user
    const [error, setError] = useState(''); // State to hold any error messages

    const handleSubmit = async (event) => {
        event.preventDefault();
        if (!originalUrl) { // If the input is empty, set an error message
            setError('Please enter a URL');
            return;
        }

        try {// Make a POST request to the backend API to shorten the URL
           // const response = await axios.post('http://localhost:8080/api/shorten', null, { params: { originalUrl } });
            const response = await axios.post('http://localhost:51491/api/shorten', null, { params: { originalUrl } });
            onShorten(response.data);// Call the onShorten function passed as a prop with the shortened URL data
            setOriginalUrl('');// Reset the input field and error state
            setError('');
        } catch (error) {
            setError('Failed to shorten the URL');// If the request fails, set an error message
        }
    };

    return ( // Form to take user input and handle submission
        <form onSubmit={handleSubmit}>
            <input
                type="url"
                value={originalUrl}
                onChange={(e) => setOriginalUrl(e.target.value)}
                placeholder="Enter URL"
                required
            />
            <button type="submit">Shorten</button>
            {error && <p style={{ color: 'red' }}>{error}</p>}
        </form>
    );
};

export default UrlForm;
