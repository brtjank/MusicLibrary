package com.brtjank.lab.song.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import com.brtjank.lab.artist.entity.Artist;
import com.brtjank.lab.song.entity.Song;

import java.time.LocalDate;
import java.util.function.Function;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CreateSongRequest {

    /**
     * Full name of the release of the song's version.
     */

    private String name;

    /**
     * Name of the song artist which this release belongs to.
     */

    private Long artist;

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
     * @param dateFunction function for converting date string to LocalDate
     * @return mapper for convenient converting dto object to entity object
     */
    public static Function<CreateSongRequest, Song> dtoToEntityMapper(
            Function<Long, Artist> artistFunction,
            Function<String, LocalDate> dateFunction) {
        return request -> Song.builder()
                .name(request.getName())
                .artist(artistFunction.apply(request.getArtist()))
                .producer(request.getProducer())
                .length(request.getLength())
                .releasedate(dateFunction.apply(request.getReleasedate()))
                .url(request.getUrl())
                .build();
        }
}

