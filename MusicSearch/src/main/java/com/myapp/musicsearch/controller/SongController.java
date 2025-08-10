package com.myapp.musicsearch.controller;

import com.myapp.musicsearch.entity.Song;
import com.myapp.musicsearch.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
@CrossOrigin(origins = "*")
public class SongController {

    @Autowired
    private SongService songService;

    @PostMapping
    public Song addSong(@RequestBody Song song) {
        return songService.save(song);
    }

    @GetMapping("/search")
    public List<Song> searchSongs(@RequestParam String q) {
        return songService.search(q);
    }

    @GetMapping
    public Iterable<Song> getAll() {
        return songService.findAll();
    }
}
