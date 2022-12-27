package com.brtjank.lab.song.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.brtjank.lab.artist.entity.Artist;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "songs")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Song implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Full name of the release of the song's version.
     */

    private String name;

    /**
     * Artist which this release belongs to.
     */

    @ManyToOne
    @JoinColumn(name = "artist")
    private Artist artist;

    private String producer;

    private String length;

    /**
     * Release date.
     */

    private LocalDate releasedate;

    /**
     * Url of the song
     */

    private String url;


    // Overriding equals() to compare two Song objects 
    @Override
    public boolean equals(Object o) { 
  
        // If the object is compared with itself then return true   
        if (o == this) { 
            return true; 
        } 
  
        /* Check if o is an instance of Song or not 
          "null instanceof [type]" also returns false */
        if (!(o instanceof Song)) { 
            return false; 
        } 
          
        // typecast o to Song so that we can compare data members  
        Song c = (Song) o; 
          
        // Compare the data members and return accordingly  
        return Long.compare(id, c.id) == 0;
    } 
}
