package com.brtjank.lab.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brtjank.lab.song.entity.Song;
import com.brtjank.lab.song.service.SongService;
import com.brtjank.lab.artist.entity.Artist;
import com.brtjank.lab.artist.service.ArtistService;

import java.time.LocalDate;

import javax.annotation.PostConstruct;

/**
 * Listener started automatically on CDI application context initialized. Injects proxy to the services and fills
 * database with default content. When using persistence storage application instance should be initialized only during
 * first run in order to init database with starting data. Good place to create first default admin user.
 */
@Component
public class DataInitializer {

    /**
     * Service for artist operations.
     */
    private final ArtistService artistService;

    /**
     * Service for songs operations.
     */
    private final SongService songService;


    @Autowired
    public DataInitializer(ArtistService artistService, SongService songService) {
        this.artistService = artistService;
        this.songService = songService;
    }

    /**
     * Initializes database with some example values. Should be called after creating this object. This object should
     * be created only once.
     */
    @PostConstruct
    private synchronized void init() {
        Artist rihanna = Artist.builder()
                .id((long) 1)
                .name("Rihanna")
                .build();

        Artist jayz = Artist.builder()
                .id((long) 2)        
                .name("Jay-Z")
                .build();

        Artist maneskin = Artist.builder()
                .id((long) 3)
                .name("Maneskin")
                .build();

        Artist taco = Artist.builder()
                .id((long) 4)
                .name("Taco Hemingway")
                .build();

        artistService.create(rihanna);
        artistService.create(jayz);
        artistService.create(maneskin);
        artistService.create(taco);

        Song neededme = Song.builder()
                .name("Needed Me")
                .artist(rihanna)
                .producer("Twice As Nice\nMustard\nKuk Harrell\nFrank Dudes")
                .length("03:14")
                .releasedate(LocalDate.of(2016, 4, 20))
                .url("https://www.youtube.com/watch?v=wfN4PVaOU5Q")
                .build();

        Song takeabow = Song.builder()
                .name("Take A Bow")
                .artist(rihanna)
                .producer("Ne-Yo\nStarGate")
                .length("03:50")
                .releasedate(LocalDate.of(2009, 11, 30))
                .url("https://www.youtube.com/watch?v=J3UjJ4wKLkg")
                .build();

        Song empire = Song.builder()
                .name("Empire State Of Mind")
                .artist(jayz)
                .producer("Angela Hunte\nShux\nJane't Sewell-Ulepic")
                .length("04:42")
                .releasedate(LocalDate.of(2009, 9, 8))
                .url("https://www.youtube.com/watch?v=vk6014HuxcE")
                .build();

        Song slave = Song.builder()
                .name("I wanna be your slave")
                .artist(maneskin)
                .producer("Maneskin\nFarbizio Ferraguzzo")
                .length("02:52")
                .releasedate(LocalDate.of(2021, 3, 26))
                .url("https://www.youtube.com/watch?v=J3UjJ4wKLkg")
                .build();
        
        songService.create(neededme);
        songService.create(takeabow);
        songService.create(empire);
        songService.create(slave);
    }

}
