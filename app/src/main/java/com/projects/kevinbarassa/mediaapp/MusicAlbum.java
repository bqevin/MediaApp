package com.projects.kevinbarassa.mediaapp;

/**
 * Created by Kevin Barassa on 02-Feb-17.
 */

public class MusicAlbum {
    private String name;
    private int numOfSongs;
    private int thumbnail;

    public MusicAlbum() {
    }

    public MusicAlbum(String name, int numOfSongs, int thumbnail) {
        this.name = name;
        this.numOfSongs = numOfSongs;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfSongs() {
        return numOfSongs;
    }

    public void setNumOfSongs(int numOfSongs) {
        this.numOfSongs = numOfSongs;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

}