package br.ufrn.imd.songs.dto.song;

import jakarta.validation.constraints.NotNull;

public record SongPut(@NotNull String id,
                      @NotNull String name,
                      @NotNull String artist,
                      @NotNull String genre) {
}
