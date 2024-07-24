// src/App.js
import React, { useState } from 'react';
import UrlForm from './components/UrlForm';
import UrlList from './components/UrlList';

const App = () => {
  const [shortenedUrl, setShortenedUrl] = useState('');

  return (
      <div className="App">
        <h1 className={"App-header"}>URL Shortener</h1>
        <UrlForm onShorten={setShortenedUrl} />
        {shortenedUrl && (
            <div>
              <h2>Shortened URL:</h2>
              <a href={shortenedUrl} target="_blank" rel="noopener noreferrer">
                {shortenedUrl}
              </a>
            </div>
        )}
        <UrlList />
      </div>
  );
};

export default App;
