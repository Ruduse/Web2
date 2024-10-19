package org.example.day1;



public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world! This is my favourite song");
        System.out.println("Its name is Perfect");
        LyricsOfPerfectSong song = new LyricsOfPerfectSong();
        System.out.println(song.getLyrics());
    }
}