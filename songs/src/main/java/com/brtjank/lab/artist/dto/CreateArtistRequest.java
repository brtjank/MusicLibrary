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
public class CreateArtistRequest {

    private Long id;

    /**
     * Full name of the song artist.
     */

    private String name;

    /**
     * @return mapper for convenient converting dto object to entity object
     */
    public static Function<CreateArtistRequest, Artist> dtoToEntityMapper() {
        return request -> Artist.builder()
                .id(request.getId())
                .name(request.getName())
                .build();
        }
}

