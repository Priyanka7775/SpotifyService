package com.example.SpotifyService.domain;

import org.springframework.data.annotation.Id;

public class Song {
    @Id
    private int songId;
    private String songName;
    private String artistName;
    private String genre;

    public Song(int songId, String songName, String artistName, String genre) {
        this.songId = songId;
        this.songName = songName;
        this.artistName = artistName;
        this.genre = genre;
    }

    public Song() {
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Song{" +
                "songId=" + songId +
                ", songName='" + songName + '\'' +
                ", artistName='" + artistName + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
