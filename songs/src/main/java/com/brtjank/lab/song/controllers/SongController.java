package com.brtjank.lab.song.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.brtjank.lab.song.dto.CreateSongRequest;
import com.brtjank.lab.song.dto.GetSongResponse;
import com.brtjank.lab.song.dto.GetSongsResponse;
import com.brtjank.lab.song.dto.UpdateSongRequest;
import com.brtjank.lab.song.entity.Song;
import com.brtjank.lab.song.service.SongService;
import com.brtjank.lab.artist.service.ArtistService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * REST controller for song resource. It does not return or receive entity objects but dto objects which present
 * only those fields which are converted to JSON.
 */
@RestController
@RequestMapping("api/songs")
public class SongController {

    /**
     * Service for managing songs.
     */
    private SongService songService;

    /**
     * Service for managing artist.
     */
    private ArtistService artistService;

    /**
     * @param songService  service for managing songs
     * @param artistService service for managing artist
     */
    @Autowired
    public SongController(SongService songService, ArtistService artistService) {
        this.songService = songService;
        this.artistService = artistService;
    }

    /**
     * @return list of songs which will be converted to JSON
     */
    @GetMapping
    public ResponseEntity<GetSongsResponse> getSongs() {
        List<Song> all = songService.findAll();
        Function<Collection<Song>, GetSongsResponse> mapper = GetSongsResponse.entityToDtoMapper();
        GetSongsResponse response = mapper.apply(all);
        return ResponseEntity.ok(response);
    }

    /**
     * @param id id of the song
     * @return single song in JSON format or 404 when song does not exist
     */
    @GetMapping("{id}")
    public ResponseEntity<GetSongResponse> getSong(@PathVariable("id") Long id) {
        return  songService.find(id)
                .map(value -> ResponseEntity.ok(GetSongResponse
                                                .entityToDtoMapper(localDate -> localDate.toString())
                                                .apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * @param request new song parsed from JSON
     * @param builder URI builder
     * @return response with location header
     */
    @PostMapping
    public ResponseEntity<Void> createSong(@RequestBody CreateSongRequest request, UriComponentsBuilder builder) {
        Song song = CreateSongRequest
                .dtoToEntityMapper(id -> artistService.find(id).orElseThrow(), date -> LocalDate.parse(date))
                .apply(request);
        song = songService.create(song);
        return ResponseEntity.created(builder.pathSegment("api", "songs", "{id}")
                .buildAndExpand(song.getId()).toUri()).build();
    }

    /**
     * Deletes selected song.
     *
     * @param id song's id
     * @return accepted for not found if song does not exist
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable("id") Long id) {
        Optional<Song> song = songService.find(id);
        if (song.isPresent()) {
            songService.delete(song.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Updates existing song.
     *
     * @param request song's data parsed from JSON
     * @param id      song's id
     * @return accepted or not found if song does not exist
     */
    @PutMapping("{id}")
    public ResponseEntity<Void> updateSong(@RequestBody UpdateSongRequest request, @PathVariable("id") Long id) {
        Optional<Song> song = songService.find(id);
        if (song.isPresent()) {
            UpdateSongRequest.dtoToEntityUpdater(date -> LocalDate.parse(date)).apply(song.get(), request);
            songService.update(song.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
