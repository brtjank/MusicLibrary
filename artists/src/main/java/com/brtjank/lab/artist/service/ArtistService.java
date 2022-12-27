package com.brtjank.lab.artist.service;

import java.util.List;
import java.util.Optional;

import com.brtjank.lab.artist.entity.Artist;
import com.brtjank.lab.artist.repository.ArtistRepository;
import com.brtjank.lab.artist.event.repository.ArtistEventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service layer for all business actions regarding artist entity.
 */
@Service
public class ArtistService {
    
    private ArtistRepository repository;

    private ArtistEventRepository eventRepository;

    /**
     * @param repository repository for artist entity
     * @param eventRepository repository for sending events about actions on artist entities
     */
    @Autowired
    public ArtistService(ArtistRepository repository, ArtistEventRepository eventRepository) {
        this.repository = repository;
        this.eventRepository = eventRepository;
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
     * @return all available artist
     */
    public List<Artist> findAll() {
        return repository.findAll();
    }

    /**
     * Creates new artist.
     *
     * @param artist new artist
     */
    @Transactional
    public void create(Artist artist) {
        repository.save(artist);
        eventRepository.create(artist);
    }

    /**
     * Updates existing artist.
     *
     * @param artist new artist
     */
    @Transactional
    public void update(Artist artist) {
        repository.save(artist);
    }

    /**
     * Deletes existing artist.
     *
     * @param artist existing artist id to be deleted
     */
    @Transactional
    public void delete(Long artist) {
        Optional<Artist> artistToDel = repository.findById(artist);
        eventRepository.delete(artistToDel.get());
        repository.deleteById(artist);
    }

}