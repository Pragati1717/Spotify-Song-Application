package com.niit.Spotify.service;

import com.niit.Spotify.domain.Song;
import com.niit.Spotify.domain.User;
import com.niit.Spotify.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    private SongRepository songRepository;

    @Autowired
    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public User registerNewUser(User user) {
        //checking customer is already registered or not
        if (songRepository.findById(user.getEmailId()).isPresent()) {
            return null;
        }
        //if no then
        return songRepository.save(user);
    }

    @Override
    public User saveUserSong(Song song, String emailId) {
        //checking customer by id ,authentication check
        if (songRepository.findById(emailId).isEmpty()) {
            return null;
        }
        // user is authorized and getting customer object from customer id
        User userObj = songRepository.findById(emailId).get();
        //null describes 0 product
        if (userObj == null) {
            userObj.setSongList(Arrays.asList(song));
        } else {
            //create a list of product and adding product object
            List<Song> songList = userObj.getSongList();
            songList.add(song);
            userObj.setSongList(songList);
        }
        return songRepository.save(userObj);
    }

    @Override
    public User deleteSongOfUser(String emailId, int songId) {
        // created a variable of type boolean
        boolean songIdExist = false;
        //checking authentication
        if (songRepository.findById(emailId).isEmpty()) {
            return null;
        }
        //if authenticated, getting customer object using customer id
        User user = songRepository.findById(emailId).get();
        //creating list of product to get product list to verify product id
        List<Song> songList = user.getSongList();
        //java 8 collection, return type boolean
        songIdExist = songList.removeIf(i -> i.getSongId() == (songId));
        if (!songIdExist) {
            return null;
        }
        user.setSongList(songList);
        return songRepository.save(user);
    }

    @Override
    public List<Song> getAllSongOfUser(String emailId) {
        if (songRepository.findById(emailId).isEmpty()) {
            return null;
        }
        return songRepository.findById(emailId).get().getSongList();
    }
}