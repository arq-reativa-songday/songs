package br.ufrn.imd.songs.service;

import br.ufrn.imd.songs.dto.song.SongPopularityMapper;
import br.ufrn.imd.songs.dto.song.SongPopularityPost;
import br.ufrn.imd.songs.dto.song.SongPopularityPut;
import br.ufrn.imd.songs.exception.NotFoundException;
import br.ufrn.imd.songs.model.SongPopularity;
import br.ufrn.imd.songs.repository.SongPopularityDynamicQueryRepository;
import br.ufrn.imd.songs.repository.SongPopularityRepository;
import br.ufrn.imd.songs.repository.SongRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SongPopularityService {

    private final SongPopularityRepository songPopularityRepository;
    private final SongRepository songRepository;
    private final SongPopularityDynamicQueryRepository songPopularityDynamicQueryRepository;

    public SongPopularity save(SongPopularityPost songPopularityPost) {
        SongPopularity songPopularity = SongPopularityMapper.INSTANCE.postToSongPopularity(songPopularityPost);
        songPopularity.setScore(1L);
        return songPopularityRepository.save(songPopularity);
    }

    public Optional<SongPopularity> findById(String id) {
        return songPopularityRepository.findById(id);
    }

    public List<SongPopularity> findAll(String songId, LocalDate day, String userId, Long score) {
        return songPopularityDynamicQueryRepository.findAllBySongIdAndDayAndUserIdAndScore(songId, day, userId, score);
    }

    public SongPopularity update(SongPopularityPut songPopularityPut) {
        SongPopularity savedSongPopularity = songPopularityRepository.findById(songPopularityPut.id())
                .orElseThrow(() -> new NotFoundException("Song popularity don't exists."));

        SongPopularity songPopularity = SongPopularityMapper.INSTANCE.putToSongPopularity(songPopularityPut);
        songPopularity.setId(savedSongPopularity.getId());

        return songPopularityRepository.save(songPopularity);
    }

    public void delete(String id) {
        songPopularityRepository.deleteById(id);
    }

    public void increasePopularity(String songId) {
        SongPopularity songPopularity = songPopularityRepository.findBySongId(songId);

        if(!Objects.isNull(songPopularity)){
            songPopularity.setScore(songPopularity.getScore() + 1);
            songPopularityRepository.save(songPopularity);
        }
        else{
            save(new SongPopularityPost(songId, LocalDate.now(), "Empty", 0L));
        }

    }
}
