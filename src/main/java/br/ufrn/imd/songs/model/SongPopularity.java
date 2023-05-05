package br.ufrn.imd.songs.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "songsPopularity")
@Data
public class SongPopularity {

    @Id
    private String id;

    private String songId;

    private LocalDate day;

    private String userId;

    private Long score;
}
