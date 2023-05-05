package br.ufrn.imd.songs.repository;

import br.ufrn.imd.songs.model.SongPopularity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface SongPopularityRepository extends MongoRepository<SongPopularity, String> {
    List<SongPopularity> findFirstByScore();
    SongPopularity findBySongId(String songId);
    SongPopularity findByDay(LocalDate day);
    SongPopularity findByUserId(String userId);
}
