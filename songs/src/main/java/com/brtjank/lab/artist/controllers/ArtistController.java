package com.brtjank.lab.artist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.brtjank.lab.artist.dto.CreateArtistRequest;
import com.brtjank.lab.artist.entity.Artist;
import com.brtjank.lab.artist.service.ArtistService;

import java.util.Optional;

/**
 * REST controller for artist resource. It does not return or receive entity objects but dto objects which present
 * only those fields which are converted to JSON.
 */
@RestController
@RequestMapping("api/artist")
public class ArtistController {

    /**
     * Service for managing artist.
     */
    private ArtistService artistService;

    /**
     * @param artistService service for managing artist
     */
    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    /**
     * @param request new artist parsed from JSON
     * @param builder URI builder
     * @return response with location header
     */
    @PostMapping
    public ResponseEntity<Void> createArtist(@RequestBody CreateArtistRequest request, UriComponentsBuilder builder) {
        Artist artist = CreateArtistRequest
                .dtoToEntityMapper()
                .apply(request);
        artist = artistService.create(artist);
        return ResponseEntity.created(builder.pathSegment("api", "artist", "{id}")
                .buildAndExpand(artist.getId()).toUri()).build();
    }

    /**
     * Deletes selected artist.
     *
     * @param id artist's id
     * @return accepted for not found if artist does not exist
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable("id") long id) {
        Optional<Artist> artist = artistService.find(id);
        if (artist.isPresent()) {
            artistService.delete(artist.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
