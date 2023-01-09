package com.niit.Spotify.controller;

import com.niit.Spotify.domain.Song;
import com.niit.Spotify.domain.User;
import com.niit.Spotify.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/app/song")
public class SongController {


        private SongService songService;
        private ResponseEntity<?> responseEntity;

        @Autowired
        public SongController(SongService songService) {
            this.songService = songService;
        }

        @PostMapping("/register")
        public ResponseEntity<?> registerNewUser(@RequestBody User user){
            return new ResponseEntity<>(songService.registerNewUser(user),HttpStatus.OK);
        }

        @GetMapping("/get/{emailId}")
        public ResponseEntity<?> getAllSongsOfUser(@PathVariable String emailId){
            return new ResponseEntity<>(songService.getAllSongOfUser(emailId),HttpStatus.OK);
        }

        @PostMapping("/add/{emailId}")
        public ResponseEntity<?> saveSongOfUser(@RequestBody Song song, @PathVariable String emailId){
            return new ResponseEntity<>(songService.saveUserSong(song,emailId),HttpStatus.OK);
        }

        @DeleteMapping("/remove/{emailId}/{songId}")
        public ResponseEntity<?> deleteSong (@PathVariable String emailId, @PathVariable int songId){
            return new ResponseEntity<>(songService.deleteSongOfUser(emailId,songId),HttpStatus.OK);
        }

}
