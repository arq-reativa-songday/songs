package br.ufrn.imd.songs.controller;

import br.ufrn.imd.songs.dto.song.SongPost;
import br.ufrn.imd.songs.dto.song.SongPut;
import br.ufrn.imd.songs.exception.NotFoundException;
import br.ufrn.imd.songs.model.Song;
import br.ufrn.imd.songs.service.SongService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;


    @GetMapping()
    public ResponseEntity<List<Song>> findAll(
            @RequestParam(required = false, defaultValue = "") String genre,
            @RequestParam(required = false, defaultValue = "") String artist,
            @RequestParam(required = false, defaultValue = "") String name) {

        return ResponseEntity.ok(songService.findAll(genre, artist, name));
    }

    @PostMapping
    public ResponseEntity<Song> save(@RequestBody @Valid SongPost song) {
        return new ResponseEntity<>(songService.save(song), HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Song> findById(@PathVariable String id) throws Exception {
        return ResponseEntity.ok(songService
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Song don't exists.")));
    }

    @PutMapping
    public ResponseEntity<Song> update(@RequestBody SongPut songPut){
        return ResponseEntity.ok(songService.update(songPut));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        songService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
