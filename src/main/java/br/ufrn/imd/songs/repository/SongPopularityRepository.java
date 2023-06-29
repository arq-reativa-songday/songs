package br.ufrn.imd.songs.repository;

import br.ufrn.imd.songs.model.SongPopularity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongPopularityRepository extends MongoRepository<SongPopularity, String> {
    SongPopularity findBySongId(String songId);
}
