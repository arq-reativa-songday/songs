package br.ufrn.imd.songs.service;

import br.ufrn.imd.songs.dto.song.SongMapper;
import br.ufrn.imd.songs.dto.song.SongPost;
import br.ufrn.imd.songs.dto.song.SongPut;
import br.ufrn.imd.songs.exception.NotFoundException;
import br.ufrn.imd.songs.model.Song;
import br.ufrn.imd.songs.repository.SongDynamicQueryRepository;
import br.ufrn.imd.songs.repository.SongRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SongService {

    private final SongRepository songRepository;
    private final SongDynamicQueryRepository songDynamicQuery;

    public Song save(SongPost songPost) {
        Song song = SongMapper.INSTANCE.postToSong(songPost);
        return songRepository.save(song);
    }

    public Long count() {
        return songRepository.count();
    }

    public Optional<Song> findById(String id) {
        return songRepository.findById(id);
    }

    public List<Song> findAll(String genre, String artist, String name) {
        return songDynamicQuery.findAllByGenreAndArtistAndNameParams(genre, artist, name);
    }

    public Song update(SongPut songPut) {
        Song savedSong = songRepository.findById(songPut.id())
                .orElseThrow(() -> new NotFoundException("Song don't exists."));

        Song song = SongMapper.INSTANCE.putToSong(songPut);
        song.setId(savedSong.getId());

        return songRepository.save(song);
    }

    public void delete(String id) {
        songRepository.deleteById(id);
    }
}
