package com.brtjank.lab.song.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.brtjank.lab.song.dto.GetSongsResponse;
import com.brtjank.lab.song.service.SongService;
import com.brtjank.lab.artist.entity.Artist;
import com.brtjank.lab.artist.service.ArtistService;

import java.util.Optional;

/**
 * REST controller for song resource. It does not return or receive entity objects but dto objects which present
 * only those fields which are converted to JSON.
 */
@RestController
@RequestMapping("api/artist/{id}/songs")
public class SongArtistController {

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
    public SongArtistController(SongService songService, ArtistService artistService) {
        this.songService = songService;
        this.artistService = artistService;
    }

    @GetMapping
    public ResponseEntity<GetSongsResponse> getSongs(@PathVariable("id") Long id) {
        Optional<Artist> artist = artistService.find(id);
        return artist.map(value -> ResponseEntity.ok(GetSongsResponse.entityToDtoMapper().apply(songService.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
