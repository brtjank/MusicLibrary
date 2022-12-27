package com.brtjank.lab.artist.entity;

import com.brtjank.lab.song.entity.Song;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Id;

/**
 * Song artist entity class.
 */
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "artist")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Artist implements Serializable{

    @Id
    private Long id;

    /**
     * Song artist general name.
     */

    private String name;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    private List<Song> songs;
}
