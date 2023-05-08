package br.ufrn.imd.songs.dto.song;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record SongPopularityPut(@NotNull String id,
                                @NotNull String songId,
                                @NotNull LocalDate day,
                                @NotNull String userId,
                                @NotNull Long score) {
}
