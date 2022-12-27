package com.brtjank.lab.song.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import com.brtjank.lab.song.entity.Song;

import java.time.LocalDate;
import java.util.function.BiFunction;
import java.util.function.Function;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UpdateSongRequest {
    
    private String length;
 
    private String producer;

    private String releasedate;

    private String url;

    /**
     * @return updater for convenient updating entity object using dto object
     */
    public static BiFunction<Song, UpdateSongRequest, Song> dtoToEntityUpdater(
            Function<String, LocalDate> dateFunction
    ) {
        return (song, request) -> {
            if (request.getLength() != null) song.setLength(request.getLength());
            if (request.getProducer() != null) song.setProducer(request.getProducer());
            if (request.getReleasedate() != null) song.setReleasedate(dateFunction.apply(request.getReleasedate()));
            if (request.getUrl() != null) song.setUrl(request.getUrl());
            return song;
        };
    }

}

