package com.niit.Spotify.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Song {

    private int songId;
    private String songName;
    private String artist;
    private String songGenre;
    private int yearReleased;
}
