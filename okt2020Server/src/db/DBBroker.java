/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.Korisnik;
import domen.Nastavnik;
import domen.PredajePredmet;
import domen.Predmet;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import klasa.PomocnaKlasa;

/**
 *
 * @author ANA
 */
public class DBBroker {

    Connection konekcija;

    public void ucitajDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void otvoriKonekciju() {
        try {
            konekcija = DriverManager.getConnection("jdbc:mysql://localhost:3306/prosoftokt20", "root", "");
            konekcija.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void zatvoriKonekciju() {
        try {
            konekcija.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void commit() {
        try {
            konekcija.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void rollback() {
        try {
            konekcija.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Korisnik vratiUlogovanog(Korisnik k) {
        Korisnik korisnik = null;
        String sql = " SELECT * FROM korisnik WHERE KorisnickoIme='" + k.getKorisnickoIme() + "' AND Lozinka='" + k.getLozinka() + "'";

        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                int korisnikID = rs.getInt("KorisnikID");
                String ime = rs.getString("Ime");
                String prezime = rs.getString("Prezime");
                String korIme = rs.getString("KorisnickoIme");
                String lozinka = rs.getString("Lozinka");

                korisnik = new Korisnik(korisnikID, ime, prezime, korIme, lozinka);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return korisnik;
    }

    public ArrayList<Nastavnik> vratiSveNastavnike() {
        ArrayList<Nastavnik> lista = new ArrayList<>();
        String sql = "SELECT * FROM nastavnik";
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                int nasID = rs.getInt("NastavnikID");
                String ime = rs.getString("Ime");
                String prezime = rs.getString("Prezime");
                String zvanje = rs.getString("Zvanje");
                Nastavnik n = new Nastavnik(nasID, ime, prezime, zvanje);
                lista.add(n);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public ArrayList<Predmet> vratiSvePredmete() {
        ArrayList<Predmet> lista = new ArrayList<>();
        String sql = "SELECT * FROM predmet";
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                int predID = rs.getInt("PredmetID");
                String naziv = rs.getString("Naziv");
                int ESPB = rs.getInt("ESPB");
                Predmet p = new Predmet(predID, naziv, ESPB);
                lista.add(p);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public void sacuvajAngazovanje(PredajePredmet pp) throws SQLException {
        String sql = "INSERT INTO predajepredmet VALUES (?,?,?,?,?,?)";
        PreparedStatement ps = konekcija.prepareStatement(sql);
        ps.setInt(1, pp.getNastavni().getNastavnikID());
        ps.setInt(2, pp.getPredmet().getPredmetID());
        ps.setInt(3, pp.getSemestar());
        ps.setString(4, pp.getStudijskiProgram().toString());
        ps.setDate(5, new Date(pp.getDatumAngazovanja().getTime()));
        ps.setInt(6, pp.getKorisnik().getKorisnikID());
        ps.executeUpdate();
    }

    public ArrayList<PomocnaKlasa> vratiServeru() {
         ArrayList<Nastavnik> listaN = new ArrayList<>();
        String sql = "SELECT * FROM nastavnik";
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                int nasID = rs.getInt("NastavnikID");
                String ime = rs.getString("Ime");
                String prezime = rs.getString("Prezime");
                String zvanje = rs.getString("Zvanje");
                Nastavnik n = new Nastavnik(nasID, ime, prezime, zvanje);
                listaN.add(n);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<PomocnaKlasa> listaVrati= new ArrayList<>();
        for (Nastavnik nastavnik : listaN) {
            String upit="SELECT COUNT(PredmetID) as brojPredmeta FROM predajepredmet WHERE NastavnikID="+ nastavnik.getNastavnikID();
             try {
                 Statement s= konekcija.createStatement();
                 ResultSet rs= s.executeQuery(upit);
                 while(rs.next()){
                     int br= rs.getInt("brojPredmeta");
                     PomocnaKlasa pk= new PomocnaKlasa(nastavnik.toString(), br);
                     listaVrati.add(pk);
             }
             } catch (SQLException ex) {
                 Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
             }
            
        }
        return listaVrati;
    }

    public ArrayList<PomocnaKlasa> vratiListuPoFilteru(String where) {
         ArrayList<Nastavnik> listaN = new ArrayList<>();
        String sql = "SELECT * FROM nastavnik";
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                int nasID = rs.getInt("NastavnikID");
                String ime = rs.getString("Ime");
                String prezime = rs.getString("Prezime");
                String zvanje = rs.getString("Zvanje");
                Nastavnik n = new Nastavnik(nasID, ime, prezime, zvanje);
                listaN.add(n);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<PomocnaKlasa> listaVrati= new ArrayList<>();
        for (Nastavnik nastavnik : listaN) {
            String upit="SELECT COUNT(PredmetID) as brojPredmeta FROM predajepredmet WHERE NastavnikID="+ nastavnik.getNastavnikID() +" AND " +where;
             try {
                 Statement s= konekcija.createStatement();
                 ResultSet rs= s.executeQuery(upit);
                 while(rs.next()){
                     int br= rs.getInt("brojPredmeta");
                     if(br!=0){
                     PomocnaKlasa pk= new PomocnaKlasa(nastavnik.toString(), br);
                     listaVrati.add(pk);
                     }
             }
             } catch (SQLException ex) {
                 Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
             }
            
        }
        return listaVrati;
    }
    
    
}
