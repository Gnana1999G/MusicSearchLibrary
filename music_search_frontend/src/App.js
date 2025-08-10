import React, { useState } from "react";
import axios from "axios";
import "./App.css";

function App() {
  // ----- State for ADD Form -----
  const [song, setSong] = useState({
    songTitle: "",
    artist: "",
    language: "",
    album: "",
    year: "",
    youtubeUrl: "",
  });

  // ----- State for SEARCH -----
  const [search, setSearch] = useState("");
  const [results, setResults] = useState([]);

  // ----- Handlers -----
  const handleChange = (e) => {
    setSong({ ...song, [e.target.name]: e.target.value });
  };

  const addSong = async () => {
    try {
      await axios.post("http://localhost:8080/api/songs", song);
      alert("Song added successfully!");
      setSong({ songTitle: "", artist: "", language: "", album: "", year: "", youtubeUrl: "" });
    } catch (err) {
      alert("Error adding song!");
      console.log(err);
    }
  };

  const searchSongs = async () => {
    const res = await axios.get(`http://localhost:8080/api/songs/search?q=${search}`);
    setResults(res.data);
  };

  // ----- UI -----
  return (
    <div className="App">
      <h2>🎶 Music Metadata Manager</h2>

      <div className="form">
        <h3>Add New Song</h3>
        <input name="songTitle" value={song.songTitle} onChange={handleChange} placeholder="Song Title" />
        <input name="artist" value={song.artist} onChange={handleChange} placeholder="Artist" />
        <input name="language" value={song.language} onChange={handleChange} placeholder="Language" />
        <input name="album" value={song.album} onChange={handleChange} placeholder="Album" />
        <input name="year" value={song.year} onChange={handleChange} placeholder="Year" />
        <input name="youtubeUrl" value={song.youtubeUrl} onChange={handleChange} placeholder="Youtube URL" />
        <button onClick={addSong}>Add Song</button>
      </div>

      <hr />

      <div className="search">
        <h3>Search Songs</h3>
        <input value={search} onChange={(e)=>setSearch(e.target.value)} placeholder="Search by title ..." />
        <button onClick={searchSongs}>Search</button>
      </div>

      <div className="results">
        {results.map((s, idx)=>(
          <div className="card" key={idx}>
            <b>{s.songTitle}</b> <br />
            Artist: {s.artist} | Lang: {s.language} | Year: {s.year} <br/>
            <a href={s.youtubeUrl} target="_blank" rel="noopener noreferrer">Watch</a>
          </div>
        ))}
      </div>
    </div>
  );
}

export default App;
