package com.choucair.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) // Ignora campos extras que envíe la API
public class MovieModel {
    private String title;
    private int year;
    private String genre;
    private String director;
    private int rate;

    // 1. CONSTRUCTOR VACÍO (Obligatorio para la Question)
    public MovieModel() {
    }

    // 2. CONSTRUCTOR CON PARÁMETROS (Para tus Tasks)
    public MovieModel(String title, int year, String genre, String director, int rate) {
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.director = director;
        this.rate = rate;
    }

    // 3. GETTERS (Obligatorios para las validaciones de 'seeThat')
    public String getTitle() { return title; }
    public int getYear() { return year; }
    public String getGenre() { return genre; }
    public String getDirector() { return director; }
    public int getRate() { return rate; }
}