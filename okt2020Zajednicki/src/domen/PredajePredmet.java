/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ANA
 */
public class PredajePredmet implements Serializable {

    private Nastavnik nastavni;
    private Predmet predmet;
    private int semestar;
    private StudijskiProgram studijskiProgram;
    private Date datumAngazovanja;
    private Korisnik korisnik;

    public PredajePredmet() {
    }

    public PredajePredmet(Nastavnik nastavni, Predmet predmet, int semestar, StudijskiProgram studijskiProgram, Date datumAngazovanja, Korisnik korisnik) {
        this.nastavni = nastavni;
        this.predmet = predmet;
        this.semestar = semestar;
        this.studijskiProgram = studijskiProgram;
        this.datumAngazovanja = datumAngazovanja;
        this.korisnik = korisnik;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Nastavnik getNastavni() {
        return nastavni;
    }

    public void setNastavni(Nastavnik nastavni) {
        this.nastavni = nastavni;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public int getSemestar() {
        return semestar;
    }

    public void setSemestar(int semestar) {
        this.semestar = semestar;
    }

    public StudijskiProgram getStudijskiProgram() {
        return studijskiProgram;
    }

    public void setStudijskiProgram(StudijskiProgram studijskiProgram) {
        this.studijskiProgram = studijskiProgram;
    }

    public Date getDatumAngazovanja() {
        return datumAngazovanja;
    }

    public void setDatumAngazovanja(Date datumAngazovanja) {
        this.datumAngazovanja = datumAngazovanja;
    }

    @Override
    public String toString() {
        return nastavni + " predaje " + predmet;
    }

}
