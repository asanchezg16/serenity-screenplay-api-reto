package com.choucair.api.models;

public class MovieModel {
    private String title;
    private int year;
    private String genre;
    private String director;
    private int rate;

    public MovieModel(String title, int year, String genre, String director, int rate) {
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.director = director;
        this.rate = rate;
    }

    public String getTitle() { return title; }
    public int getYear() { return year; }
    public String getGenre() { return genre; }
    public String getDirector() { return director; }
    public int getRate() { return rate; }
}