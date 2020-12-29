package com.tts.PositiveVibes.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;




@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tag_id")
    private Long id;
    private String phrase;
    
    @ManyToMany(mappedBy = "tags")
    private List<Vibe> vibes;

    // If Lombok doesn't work for you then use:
     public Long getId() {
     return id;
     }

     public String getPhrase() {
     return phrase;
     }

     public void setPhrase(String phrase) {
     this.phrase = phrase;
     }

     public List<Vibe> getvibes() {
     return vibes;
     }

     public void setTweets(List<Vibe> vibes) {
     this.vibes = vibes;
     }

     @Override
     public String toString() {
     return "Tag [id=" + id + ", phrase=" + phrase + ", vibes=" + vibes + "]";
     }

}

