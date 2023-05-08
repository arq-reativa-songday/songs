package br.ufrn.imd.songs.dto.song;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record SongPopularityPost(@NotNull String songId,
                                 @NotNull LocalDate day,
                                 @NotNull String userId,
                                 Long score) {
}
