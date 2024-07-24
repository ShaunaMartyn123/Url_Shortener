// src/components/UrlList.js
import React, { useEffect, useState } from 'react';
import axios from 'axios';

const UrlList = () => {
    const [urls, setUrls] = useState([]);

    useEffect(() => {
        const fetchUrls = async () => {
            const response = await axios.get('http://localhost:8080/api/urls');
            setUrls(response.data);
        };
        fetchUrls();
    }, []);

    return (
        <div>
            <h2>Shortened URLs</h2>
            <ul>
                {urls.map((url) => (
                    <li key={url.id}>
                        <a href={url.originalUrl} target="_blank" rel="noopener noreferrer">
                            {url.shortenedUrl}
                        </a>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default UrlList;
