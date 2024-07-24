// src/components/UrlList.js
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import '../App.css';

const UrlList = () => {
    const [urls, setUrls] = useState([]);

    useEffect(() => {
        const fetchUrls = async () => {
            try{
                const response = await axios.get('http://localhost:8080/api/urls');
                setUrls(response.data);
            } catch (error) {
                console.error('Error fetching URLs', error);
            }
        };
        fetchUrls();
    },[]);

    const deleteUrl = async (id) => {
        try {
            await axios.delete(`http://localhost:8080/api/urls/${id}`);
            setUrls(urls.filter(url => url.id !== id));
        } catch (error) {
            console.error('Error deleting URL', error);
        }
    };

    return (
        <div className="App">
            <div className="App-header">
                <h1>Shortened URLs</h1>
            </div>
            <ul>
                {urls.map((url) => (
                    <li key={url.id}>
                        <a href={url.originalUrl} target="_blank" rel="noopener noreferrer" className="Shortened-Url">
                            {url.shortenedUrl}
                        </a>
                        <button onClick={() => deleteUrl(url.id)}>
                            Delete
                        </button>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default UrlList;
