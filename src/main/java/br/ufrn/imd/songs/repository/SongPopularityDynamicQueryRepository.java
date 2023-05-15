package br.ufrn.imd.songs.repository;

import br.ufrn.imd.songs.model.SongPopularity;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class SongPopularityDynamicQueryRepository {

    private final MongoTemplate mongoTemplate;

    public List<SongPopularity> findAllBySongIdAndDayAndUserIdAndScore(String songId,
                                                                       LocalDate day,
                                                                       String userId,
                                                                       Long score) {
        Query dynamicQuery = new Query();

        if (!songId.isBlank()) {
            Criteria songIdCriteria = Criteria.where("songId").is(songId);
            dynamicQuery.addCriteria(songIdCriteria);
        }
        if (day != null) {
            Criteria dayCriteria = Criteria.where("day").is(day);
            dynamicQuery.addCriteria(dayCriteria);
        }
        if (!userId.isBlank()) {
            Criteria userIdCriteria = Criteria.where("userId").is(userId);
            dynamicQuery.addCriteria(userIdCriteria);
        }
        if (score != null) {
            Criteria scoreCriteria = Criteria.where("score").is(score);
            dynamicQuery.addCriteria(scoreCriteria);
        }

        return mongoTemplate.find(dynamicQuery, SongPopularity.class, "songsPopularity");
    }
}
