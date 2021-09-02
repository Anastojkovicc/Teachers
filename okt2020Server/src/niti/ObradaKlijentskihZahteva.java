/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import domen.Korisnik;
import domen.Nastavnik;
import domen.PredajePredmet;
import domen.Predmet;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import konstante.Operacija;
import logika.Kontroler;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author ANA
 */
public class ObradaKlijentskihZahteva extends Thread {

    Socket s;

    public ObradaKlijentskihZahteva(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        while (true) {
            ServerskiOdgovor so = new ServerskiOdgovor();
            KlijentskiZahtev kz = primiZahtev();
            switch (kz.getOperacija()) {
                case Operacija.VRATI_KORISNIKA:
                    Korisnik k = (Korisnik) kz.getParametar();
                    so = Kontroler.getInstanca().vratiUlogovanog(k);
                    break;
                case Operacija.VRATI_NASTAVNIKE:
                    ArrayList<Nastavnik> listaNastavnika = Kontroler.getInstanca().vratiSveNastavnike();
                    so.setOdgovor(listaNastavnika);
                    break;
                case Operacija.VRATI_PREDMETE:
                    ArrayList<Predmet> listaPredmeta = Kontroler.getInstanca().vratiSvePredmete();
                    so.setOdgovor(listaPredmeta);
                    break;
                case Operacija.SACUVAJ_ANGAZOVANJA:
                    ArrayList<PredajePredmet> listaAngazovanja = (ArrayList<PredajePredmet>) kz.getParametar();
                    so = Kontroler.getInstanca().sacuvajAngazovanja(listaAngazovanja);
                    break;
            }
            posaljiOdgovor(so);
        }
    }

    private KlijentskiZahtev primiZahtev() {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            kz = (KlijentskiZahtev) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kz;

    }

    private void posaljiOdgovor(ServerskiOdgovor so) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(so);
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
