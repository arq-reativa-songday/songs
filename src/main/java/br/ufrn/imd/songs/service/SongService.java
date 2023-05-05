package br.ufrn.imd.songs.service;

import br.ufrn.imd.songs.dto.song.SongMapper;
import br.ufrn.imd.songs.dto.song.SongPost;
import br.ufrn.imd.songs.model.Song;
import br.ufrn.imd.songs.repository.SongRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SongService {

    private final SongRepository songRepository;

    public Song save(SongPost songPost){
        Song song = SongMapper.INSTANCE.postToSong(songPost);
        return songRepository.save(song);
    }

    public Optional<Song> findById(String id){
        return songRepository.findById(id);
    }

    public List<Song> findAll(){
        return songRepository.findAll();
    }

    public List<Song> findAllByGenre(String genre){
        return songRepository.findAllByGenre(genre);
    }

    public List<Song> findAllByName(String name){
        return songRepository.findAllByName(name);
    }

    public List<Song> findAllByArtist(String artist){
        return songRepository.findAllByArtist(artist);
    }

    public void delete(String id){
        songRepository.deleteById(id);
    }
}
