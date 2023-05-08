package br.ufrn.imd.songs.repository;

import br.ufrn.imd.songs.model.Song;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SongDynamicQueryRepository {

    private final MongoTemplate mongoTemplate;


    public List<Song> findAllByGenreAndArtistAndNameParams(String genre,
                                                    String artist,
                                                    String name) {
        Query dynamicQuery = new Query();

        if (!genre.isBlank()) {
            Criteria genreCriteria = Criteria.where("genre").is(genre);
            dynamicQuery.addCriteria(genreCriteria);
        }
        if (!artist.isBlank()) {
            Criteria artistCriteria = Criteria.where("artist").is(artist);
            dynamicQuery.addCriteria(artistCriteria);
        }
        if (!name.isBlank()) {
            Criteria nameCriteria = Criteria.where("name").is(name);
            dynamicQuery.addCriteria(nameCriteria);
        }

        return mongoTemplate.find(dynamicQuery, Song.class, "songs");
    }
}
