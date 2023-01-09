package com.niit.Spotify.service;

import com.niit.Spotify.domain.Song;
import com.niit.Spotify.domain.User;

import java.util.List;

public interface SongService {


    //registering new user
    User registerNewUser(User user);

    //saving song of a specific user by user id
    User saveUserSong(Song song, String userId);

    //deleting song of a specific user by user id and product id
    User deleteSongOfUser(String emailId, int songId);

    //getting all products for a specific customer by customer id
    List<Song> getAllSongOfUser(String emailId);

}
