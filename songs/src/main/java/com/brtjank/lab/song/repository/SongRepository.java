package com.brtjank.lab.song.repository;

import java.util.List;
import java.util.Optional;

import com.brtjank.lab.song.entity.Song;
import com.brtjank.lab.artist.entity.Artist;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
        
    /**
     * Seeks for single song.
     *
     * @param id   song's id
     * @return container (can be empty) with song
     */
    Optional<Song> findById(Long id);

    /**
     * Seeks for all songs from artist .
     *
     * @param artist artist which song is a part of
     * @return list (can be empty) of songs of a artist
     */
    List<Song> findAllByArtist(Artist artist);

}
