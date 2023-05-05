package br.ufrn.imd.songs.controller;

import br.ufrn.imd.songs.dto.song.SongPost;
import br.ufrn.imd.songs.model.Song;
import br.ufrn.imd.songs.service.SongService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("songs")
@AllArgsConstructor
public class SongController {

    private final SongService songService;


    @GetMapping()
    public ResponseEntity<List<Song>> findAll(@RequestParam(required = false) String genre,
                                              @RequestParam(required = false) String artist,
                                              @RequestParam(required = false) String name) {

        if(genre != null) return ResponseEntity.ok(songService.findAllByGenre(genre));
        else if(artist != null) return ResponseEntity.ok(songService.findAllByArtist(artist));
        else if(name != null) return ResponseEntity.ok(songService.findAllByName(name));
        else return ResponseEntity.ok(songService.findAll());
    }

    @PostMapping
    public ResponseEntity<Song> save(@RequestBody @Valid SongPost song) {
        return new ResponseEntity<>(songService.save(song), HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Song> findById(@PathVariable String id) throws Exception {
        return ResponseEntity.ok(songService
                .findById(id)
                .orElseThrow(() -> new Exception("User don't exists.")));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        songService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
