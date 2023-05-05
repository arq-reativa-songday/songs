package br.ufrn.imd.songs.service;

import br.ufrn.imd.songs.model.Song;
import br.ufrn.imd.songs.model.SongPopularity;
import br.ufrn.imd.songs.repository.SongPopularityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SongPopularityService {

    private final SongPopularityRepository songPopularityRepository;

    public SongPopularity save(SongPopularity songPopularity){
        return songPopularityRepository.save(songPopularity);
    }

    public Optional<SongPopularity> findById(String id){
        return songPopularityRepository.findById(id);
    }

    public SongPopularity findBySongId(String songId){
        return songPopularityRepository.findBySongId(songId);
    }

    public SongPopularity findByUserId(String userId){
        return songPopularityRepository.findByUserId(userId);
    }

    public SongPopularity findByDay(LocalDate day){
        return songPopularityRepository.findByDay(day);
    }

    public List<SongPopularity> findAll(){
        return songPopularityRepository.findAll();
    }

    public void delete(String id){
        songPopularityRepository.deleteById(id);
    }
}
