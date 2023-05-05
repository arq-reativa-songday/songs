package br.ufrn.imd.songs.dto.song;

import br.ufrn.imd.songs.model.Song;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SongMapper {
    SongMapper INSTANCE = Mappers.getMapper(SongMapper.class);
    Song postToSong(SongPost songPost);
}
