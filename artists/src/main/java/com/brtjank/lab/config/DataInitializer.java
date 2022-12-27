package com.brtjank.lab.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brtjank.lab.artist.entity.Artist;
import com.brtjank.lab.artist.service.ArtistService;

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

    @Autowired
    public DataInitializer(ArtistService artistService) {
        this.artistService = artistService;
    }

    /**
     * Initializes database with some example values. Should be called after creating this object. This object should
     * be created only once.
     */
    @PostConstruct
    private synchronized void init() {
        Artist rihanna = Artist.builder()
                .name("Rihanna")
                .genre("pop")
                .nationality("Barbados")
                .build();

        Artist jayz = Artist.builder()
                .name("Jay-Z")
                .genre("rap")
                .nationality("USA")
                .build();

        Artist maneskin = Artist.builder()
                .name("Maneskin")
                .genre("rock")
                .nationality("Italy")
                .build();

        Artist taco = Artist.builder()
                .name("Taco Hemingway")
                .genre("rap")
                .nationality("Poland")
                .build();

        artistService.create(rihanna);
        artistService.create(jayz);
        artistService.create(maneskin);
        artistService.create(taco);
    }

}
