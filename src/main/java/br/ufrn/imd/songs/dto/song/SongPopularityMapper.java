package br.ufrn.imd.songs.dto.song;

import br.ufrn.imd.songs.model.SongPopularity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SongPopularityMapper {
    SongPopularityMapper INSTANCE = Mappers.getMapper(SongPopularityMapper.class);

    SongPopularity postToSongPopularity(SongPopularityPost songPopularityPost);

    SongPopularity putToSongPopularity(SongPopularityPut songPopularityPut);
}
