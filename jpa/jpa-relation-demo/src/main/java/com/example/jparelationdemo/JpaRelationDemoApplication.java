package com.example.jparelationdemo;

import com.example.jparelationdemo.entity.Album;
import com.example.jparelationdemo.entity.Song;
import com.example.jparelationdemo.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

/**
 * keep working
 * https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/
 */
@SpringBootApplication
public class JpaRelationDemoApplication implements CommandLineRunner {


    @Autowired
    private AlbumRepository albumRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaRelationDemoApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        Album album = new Album("album name 1 ");
        album.getSongs().add(new Song("Song 1 name 1"));
        album.getSongs().add(new Song("Song 2 name 2"));
        album.getSongs().add(new Song("Song 3 name 3"));

        albumRepository.save(album);
        Album albums = albumRepository.findById(1L).get();

        albums.getSongs().remove(2);
        albums.getSongs().remove(3);
        albums.getSongs().remove(4);

        albumRepository.save(album);
       List<Album> albumsList = albumRepository.findAll();

        int a =0;
    }
}
