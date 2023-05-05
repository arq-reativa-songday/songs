package br.ufrn.imd.songs.repository;

import br.ufrn.imd.songs.model.Song;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SongRepository extends MongoRepository<Song, String> {
    List<Song> findAllByGenre(String genre);
    List<Song> findAllByArtist(String artist);
    List<Song> findAllByName(String name);
}
