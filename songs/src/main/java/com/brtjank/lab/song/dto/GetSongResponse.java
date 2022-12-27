package com.brtjank.lab.song.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import com.brtjank.lab.song.entity.Song;

import java.time.LocalDate;
import java.util.function.Function;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString
public class GetSongResponse {

    private Long id;

    /**
     * Full name of the release of the song's version.
     */

    private String name;

    /**
     * Name of the song artist which this release belongs to.
     */

    private String artist;

    private String producer;

    private String length;

    /**
     * Release date.
     */

    private String releasedate;

    /**
     * Url of the song.
     */

    private String url;

    /**
     * @param artistFunction function for converting artist name to artist entity object
     * @param dateFunction function for converting LocalDate to date string
     * @return mapper for convenient converting entity object to dto object
     */
    public static Function<Song, GetSongResponse> entityToDtoMapper(
            Function<LocalDate, String> dateFunction) {
        return song -> GetSongResponse.builder()
                .id(song.getId())
                .name(song.getName())
                .artist(song.getArtist().getName())
                .producer(song.getProducer())
                .length(song.getLength())
                .releasedate(dateFunction.apply(song.getReleasedate()))
                .url(song.getUrl())
                .build();
            }
}
