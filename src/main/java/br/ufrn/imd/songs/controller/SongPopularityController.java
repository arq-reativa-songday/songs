package br.ufrn.imd.songs.controller;

import br.ufrn.imd.songs.dto.song.SongPopularityMapper;
import br.ufrn.imd.songs.dto.song.SongPopularityPost;
import br.ufrn.imd.songs.dto.song.SongPopularityPut;
import br.ufrn.imd.songs.model.SongPopularity;
import br.ufrn.imd.songs.service.SongPopularityService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/songpopularities")
public class SongPopularityController {

    private final SongPopularityService songPopularityService;

    @GetMapping
    public ResponseEntity<List<SongPopularity>> findAll(@RequestParam(defaultValue = "") String songId,
                                                        @RequestParam(defaultValue = "") LocalDate day,
                                                        @RequestParam(defaultValue = "") String userId,
                                                        @RequestParam(defaultValue = "") Long score) {

        return ResponseEntity.ok(songPopularityService.findAll(songId, day, userId, score));
    }



    @PostMapping
    public ResponseEntity<SongPopularity> save(@Valid @RequestBody SongPopularityPost songPopularityPost) {
        return new ResponseEntity<>(songPopularityService.save(songPopularityPost), HttpStatus.CREATED);
    }

    @PutMapping("/score")
    public ResponseEntity<Void> updateScore(@RequestParam(name = "songId") String songId) {

        List<SongPopularity> songPopularity = songPopularityService
                .findAll(songId, LocalDate.now(), "", null);

        SongPopularity songPopularityFound;

        if (songPopularity.isEmpty()){
            songPopularityFound = songPopularityService
                    .save(new SongPopularityPost(songId, LocalDate.now(), "", null));
        }
        else{
            songPopularityFound  = songPopularity.get(0);
        }

            songPopularityFound.setScore(songPopularityFound.getScore() + 1);
            SongPopularityPut songPopularityPut = SongPopularityMapper.INSTANCE
                    .songPopularityToPut(songPopularityFound);
            songPopularityService.update(songPopularityPut);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongPopularity> findById(@PathVariable String id) throws Exception {
        return ResponseEntity.ok(songPopularityService
                .findById(id)
                .orElseThrow(() -> new Exception("Song Popularity don't exists.")));
    }

    @PutMapping
    public ResponseEntity<SongPopularity> update(@RequestBody SongPopularityPut songPopularityPut) {
        return ResponseEntity.ok(songPopularityService.update(songPopularityPut));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        songPopularityService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
