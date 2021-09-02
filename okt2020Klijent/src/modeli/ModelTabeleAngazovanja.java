/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.PredajePredmet;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ANA
 */
public class ModelTabeleAngazovanja extends AbstractTableModel {

    ArrayList<PredajePredmet> lista;

    public ModelTabeleAngazovanja() {
        lista = new ArrayList<>();
    }

    public ArrayList<PredajePredmet> getLista() {
        return lista;
    }

    public void setLista(ArrayList<PredajePredmet> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PredajePredmet pp = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return pp.getPredmet();
            case 1:
                return pp.getNastavni();
            case 2:
                return pp.getStudijskiProgram().toString();
            case 3:
                return pp.getSemestar();

            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Predmet";
            case 1:
                return "Nastavnik";
            case 2:
                return "Studijski program";
            case 3:
                return "Semestar";

            default:
                return "n/a";
        }

    }

    public void obrisiRed(int red) {
        lista.remove(red);
        fireTableDataChanged();
    }

    public void dodajUTabelu(PredajePredmet pp) {
        lista.add(pp);
        fireTableDataChanged();
    }

}
