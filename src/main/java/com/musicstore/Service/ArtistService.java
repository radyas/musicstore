package com.musicstore.Service;

import com.musicstore.Model.Artist;
import com.musicstore.Repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistService {
    @Autowired
    private ArtistRepository artistRepository;

    public Artist findArtistById(Long id){
        return artistRepository.findArtistById(id);
    }
}
