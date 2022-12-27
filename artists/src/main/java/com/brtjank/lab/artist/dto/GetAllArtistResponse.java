package com.brtjank.lab.artist.dto;

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
public class GetAllArtistResponse {

    /**
     * Represents single artist in list.
     */
    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @ToString
    public static class Artist {

        /**
         * Unique id identifying artist.
         */
        private Long id;

        /**
         * Name of the artist.
         */
        private String name;

    }

    /**
     * Name of the selected artist.
     */
    @Singular
    private List<Artist> artist_s;


    /**
     * @return mapper for convenient converting entity object to dto object
     */
    public static Function<Collection<com.brtjank.lab.artist.entity.Artist>, GetAllArtistResponse> entityToDtoMapper() {
        return artist_s -> {
            GetAllArtistResponseBuilder response = GetAllArtistResponse.builder();
            artist_s.stream()
                    .map(artist -> Artist.builder()
                            .id(artist.getId())
                            .name(artist.getName())
                            .build())
                    .forEach(response::artist_);
            return response.build();
        };
    }

}
