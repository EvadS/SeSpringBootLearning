package com.se.embeded.repo;

import com.se.embeded.entity.Song;
import com.se.embeded.entity.SongId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface SongsRepository extends CrudRepository<Song, SongId> {
    List<Song> findByIdNameAndIdArtist(String name, String artist);
}