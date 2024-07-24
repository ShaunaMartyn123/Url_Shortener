// src/components/UrlForm.js
import React, { useState } from 'react';
import axios from 'axios';

const UrlForm = ({ onShorten }) => {
    const [originalUrl, setOriginalUrl] = useState('');
    const [error, setError] = useState('');

    const handleSubmit = async (event) => {
        event.preventDefault();
        if (!originalUrl) {
            setError('Please enter a URL');
            return;
        }

        try {
            const response = await axios.post('http://localhost:8080/api/shorten', null, { params: { originalUrl } });
            onShorten(response.data);
            setOriginalUrl('');
            setError('');
        } catch (error) {
            setError('Failed to shorten the URL');
        }
    };

    return (
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
