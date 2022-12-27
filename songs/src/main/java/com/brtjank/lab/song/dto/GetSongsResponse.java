package com.brtjank.lab.song.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.Singular;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString
public class GetSongsResponse {

    /**
     * Represents single song in list.
     */
    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @ToString
    public static class Song {

        /**
         * Unique id identifying song.
         */
        private Long id;

        /**
         * Name of the song.
         */
        private String name;

    }

    /**
     * Name of the selected songs.
     */
    @Singular
    private List<Song> songs;


    /**
     * @return mapper for convenient converting entity object to dto object
     */
    public static Function<Collection<com.brtjank.lab.song.entity.Song>, GetSongsResponse> entityToDtoMapper() {
        return songs -> {
            GetSongsResponseBuilder response = GetSongsResponse.builder();
            songs.stream()
                    .map(song -> Song.builder()
                            .id(song.getId())
                            .name(song.getName())
                            .build())
                    .forEach(response::song);
            return response.build();
        };
    }

}
