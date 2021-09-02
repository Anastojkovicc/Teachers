/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import klasa.PomocnaKlasa;

/**
 *
 * @author ANA
 */
public class ModelTabeleServer extends AbstractTableModel {

    ArrayList<PomocnaKlasa> lista;
    
    String[] kolone = {"Ime i prezime nastavnika", "Broj predmeta"};

    public ModelTabeleServer() {
        lista= new ArrayList<>();
    }
    
    

    public ModelTabeleServer(ArrayList<PomocnaKlasa> lista) {
        this.lista = lista;
    }

    public void setLista(ArrayList<PomocnaKlasa> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }

    public ArrayList<PomocnaKlasa> getLista() {
        return lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PomocnaKlasa pk = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return pk.getImePrezime();
            case 1:
                return pk.getBrojPredmeta();
            default:
                return "n/a";

        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

}
