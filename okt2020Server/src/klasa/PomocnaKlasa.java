/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klasa;

/**
 *
 * @author ANA
 */
public class PomocnaKlasa {
    private String imePrezime;
    private int brojPredmeta;

    public PomocnaKlasa() {
    }

    public PomocnaKlasa(String imePrezime, int brojPredmeta) {
        this.imePrezime = imePrezime;
        this.brojPredmeta = brojPredmeta;
    }

    public int getBrojPredmeta() {
        return brojPredmeta;
    }

    public void setBrojPredmeta(int brojPredmeta) {
        this.brojPredmeta = brojPredmeta;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }
    
}
