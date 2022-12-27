package com.brtjank.lab.artist.event.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import com.brtjank.lab.artist.entity.Artist;
import com.brtjank.lab.artist.event.dto.CreateArtistRequest;

@Repository
public class ArtistEventRepository {

    private RestTemplate restTemplate;

    @Autowired
    public ArtistEventRepository(@Value("${lab.songs.url}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(Artist artist) {
        restTemplate.delete("/artist/{id}", artist.getId());
    }

    public void create(Artist artist) {
        restTemplate.postForLocation("/artist", CreateArtistRequest.entityToDtoMapper().apply(artist));
    }
}
