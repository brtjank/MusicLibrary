package com.brtjank.lab.artist.service;

import java.util.Optional;

import com.brtjank.lab.artist.entity.Artist;
import com.brtjank.lab.artist.repository.ArtistRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service layer for all business actions regarding artist entity.
 */
@Service
public class ArtistService {
    
    private ArtistRepository repository;

    /**
     * @param repository repository for artist entity
     */
    @Autowired
    public ArtistService(ArtistRepository repository) {
        this.repository = repository;
    }

    /**
     * Finds single artist.
     *
     * @param id id of the artist
     * @return container with artist
     */
    public Optional<Artist> find(Long id) {
        return repository.findById(id);
    }

    /**
     * Creates new artist.
     *
     * @param artist new artist
     */
    @Transactional
    public Artist create(Artist artist) {
        return repository.save(artist);
    }

    /**
     * Deletes existing artist.
     *
     * @param artist existing artist id to be deleted
     */
    @Transactional
    public void delete(Long artist) {
        repository.deleteById(artist);
    }
}