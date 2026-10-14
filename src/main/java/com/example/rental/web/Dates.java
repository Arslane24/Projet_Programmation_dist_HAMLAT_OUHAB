package com.example.rental.web;

// Entite : les deux dates envoyees dans le corps de la requete http
// "begin" et "end" gardent leur nom anglais : ils sont imposes par le sujet
public class Dates {

    private String begin;
    private String end;

    public Dates() {
    }

    public Dates(String begin, String end) {
        this.begin = begin;
        this.end = end;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
