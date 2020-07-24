package com.se.embeded.service;

import com.se.embeded.exception.SongNotFoundException;
import com.se.embeded.entity.Song;
import com.se.embeded.entity.SongId;
import com.se.embeded.model.SongDto;
import com.se.embeded.repo.SongsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SongsService {
    @Autowired
    SongsRepository repository;

    public Song addSong(SongDto dto) {
        return repository.save(dtoToSong(dto));
    }

    public Song find(SongDto dto) {
        return repository.findById(dtoToSongId(dto)).orElseThrow(SongNotFoundException::new);
    }

    private Song dtoToSong(SongDto dto) {
        return new Song(dtoToSongId(dto), dto.getDuration(), dto.getGenre(), dto.getReleaseDate(), dto.getRating(), dto.getDownloadUrl());
    }

    private SongId dtoToSongId(SongDto dto) {
        return new SongId(dto.getName(), dto.getAlbum(), dto.getArtist());
    }
}