package br.ufrn.imd.songs.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "songs")
@Data
public class Song {
    @Id
    private String id;

    private String name;

    private String artist;

    private String genre;
}
