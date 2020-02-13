package com.example.jparelationdemo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="album")
public class Album {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "song_id")
    private List<Song> songs = new ArrayList<>();

    public Album(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public Album() {
    }

    public void removeComment(Song comment) {
        songs.remove(comment);
        comment.setAlbum(null);
    }
}
