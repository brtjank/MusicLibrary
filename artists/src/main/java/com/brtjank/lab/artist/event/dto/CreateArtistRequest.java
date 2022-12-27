package com.brtjank.lab.artist.event.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.brtjank.lab.artist.entity.Artist;

import java.util.function.Function;

/**
 * POST artist request. Contains only fields that can be set during artist creation. Artist is defined in
 * {@link com.brtjank.lab.artist.entity.Artist}.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateArtistRequest {

    private Long id;

    /**
     * Artist's name.
     */
    private String name;

    /**
     * @return mapper for convenient converting dto object to entity object
     */
    public static Function<Artist, CreateArtistRequest> entityToDtoMapper() {
        return entity -> CreateArtistRequest.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

}
