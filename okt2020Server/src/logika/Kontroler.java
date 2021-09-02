/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import db.DBBroker;
import domen.Korisnik;
import domen.Nastavnik;
import domen.PredajePredmet;
import domen.Predmet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import klasa.PomocnaKlasa;
import transfer.ServerskiOdgovor;

/**
 *
 * @author ANA
 */
public class Kontroler {

    private static Kontroler instanca;
    DBBroker db;

    private Kontroler() {
        db = new DBBroker();
    }

    public static Kontroler getInstanca() {
        if (instanca == null) {
            instanca = new Kontroler();
        }
        return instanca;
    }

    public ServerskiOdgovor vratiUlogovanog(Korisnik k) {
        ServerskiOdgovor so = new ServerskiOdgovor();
        db.ucitajDriver();
        db.otvoriKonekciju();
        Korisnik ulogovani = db.vratiUlogovanog(k);
        if (ulogovani != null) {
            so.setOdgovor(ulogovani);
            so.setPoruka("Korisnik je " + ulogovani.getKorisnickoIme());
        } else {
            so.setPoruka("Korisnik ne postoji");
            so.setOdgovor(ulogovani);
        }
        db.zatvoriKonekciju();
        return so;
    }

    public ArrayList<Nastavnik> vratiSveNastavnike() {
        ArrayList<Nastavnik> lista = new ArrayList<>();
        db.ucitajDriver();
        db.otvoriKonekciju();
        lista = db.vratiSveNastavnike();
        db.zatvoriKonekciju();
        return lista;
    }

    public ArrayList<Predmet> vratiSvePredmete() {
        ArrayList<Predmet> lista = new ArrayList<>();
        db.ucitajDriver();
        db.otvoriKonekciju();
        lista = db.vratiSvePredmete();
        db.zatvoriKonekciju();
        return lista;
    }

    public ServerskiOdgovor sacuvajAngazovanja(ArrayList<PredajePredmet> listaAngazovanja) {
        ServerskiOdgovor so = new ServerskiOdgovor();
        db.ucitajDriver();
        db.otvoriKonekciju();

        try {
            for (PredajePredmet pp : listaAngazovanja) {
                db.sacuvajAngazovanje(pp);
            }
            db.commit();
            so.setOdgovor(true);
            so.setPoruka("Uspesno sacuvano");
        } catch (SQLException ex) {
            db.rollback();
            so.setOdgovor(false);
            so.setPoruka("Neuspesno sacuvano");
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }

        db.zatvoriKonekciju();
        return so;
    }

    public ArrayList<PomocnaKlasa> vratiListuServeru() {
        ArrayList<PomocnaKlasa> lista= new ArrayList<>();
        db.ucitajDriver();
        db.otvoriKonekciju();
        lista= db.vratiServeru();
        db.zatvoriKonekciju();
        return lista;
        
    }

    public ArrayList<PomocnaKlasa> vratiListuPoSemestru(String where) {
         ArrayList<PomocnaKlasa> lista= new ArrayList<>();
        db.ucitajDriver();
        db.otvoriKonekciju();
        lista= db.vratiListuPoFilteru(where);
        db.zatvoriKonekciju();
        return lista;
    }

}
