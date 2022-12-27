package com.brtjank.lab.artist.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import com.brtjank.lab.artist.entity.Artist;

import java.util.function.BiFunction;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString
public class UpdateArtistRequest {
    
    private String genre;

    private String nationality;

    /**
     * @return updater for convenient updating entity object using dto object
     */
    public static BiFunction<Artist, UpdateArtistRequest, Artist> dtoToEntityUpdater() {
        return (artist, request) -> {
            if (request.getGenre() != null) artist.setGenre(request.getGenre());
            if (request.getNationality() != null) artist.setNationality(request.getNationality());
            return artist;
        };
    }

}

