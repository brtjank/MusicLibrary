package com.brtjank.lab.artist.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import com.brtjank.lab.artist.entity.Artist;

import java.util.function.Function;

@Getter
@Setter
@NoArgsConstructor
@ToString
@SuperBuilder
public class GetArtistResponse {

    private Long id;

    /**
     * Full name of the Artist.
     */

    private String name;

    private String genre;

    private String nationality;

 
    /**
     * @param artistFunction function for converting artist name to artist entity object
     * @return mapper for convenient converting entity object to dto object
     */
    public static Function<Artist, GetArtistResponse> entityToDtoMapper() {
        return artist -> GetArtistResponse.builder()
                .id(artist.getId())
                .name(artist.getName())
                .genre(artist.getGenre())
                .nationality(artist.getNationality())
                .build();
            }
}
