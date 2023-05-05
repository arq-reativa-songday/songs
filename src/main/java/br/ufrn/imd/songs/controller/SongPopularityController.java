package br.ufrn.imd.songs.controller;

import br.ufrn.imd.songs.model.SongPopularity;
import br.ufrn.imd.songs.service.SongPopularityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
public class SongPopularityController {

    private final SongPopularityService songPopularityService;

    public ResponseEntity<List<SongPopularity>> findAll(@RequestParam(required = false) String songId,
                                                        @RequestParam(required = false) LocalDate day,
                                                        @RequestParam(required = false) String userId,
                                                        @RequestParam(required = false) Long score){
        return ResponseEntity.ok(songPopularityService.findAll());
    }
}
