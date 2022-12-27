package com.brtjank.lab.song.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brtjank.lab.song.entity.Song;
import com.brtjank.lab.song.repository.SongRepository;
import com.brtjank.lab.artist.entity.Artist;

/**
 * Service layer for all business actions regarding song entity.
 */
@Service
public class SongService {
    
    private SongRepository repository;

    /**
     * @param repository repository for song entity
     */
    @Autowired
    public SongService(SongRepository repository) {
        this.repository = repository;
    }

    /**
     * Finds single song.
     *
     * @param id id of the song
     * @return container with song
     */
    public Optional<Song> find(Long id) {
        return repository.findById(id);
    }

    /**
     * @return all available songs
     */
    public List<Song> findAll() {
        return repository.findAll();
    }

    /**
     * @param artist existing artist having its song(s)
     * @return all available songs of the selected song
     */
    public List<Song> findAll(Artist artist) {
        return repository.findAllByArtist(artist);
    }

    /**
     * Creates new song.
     *
     * @param song new song
     */
    @Transactional
    public Song create(Song song) {
        return repository.save(song);
    }

    /**
     * Updates existing song.
     *
     * @param song new song
     */
    @Transactional
    public void update(Song song) {
        repository.save(song);
    }

    /**
     * Deletes existing song.
     *
     * @param song existing song's id to be deleted
     */
    @Transactional
    public void delete(Long song) {
        repository.deleteById(song);
    }

}