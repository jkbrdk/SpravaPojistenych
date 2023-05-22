package org.example;

import java.util.Objects;

public class Pojistenci {

    private String jmeno;
    private String prijmeni;
    private int vek;
    private String telefoniCislo;

    public Pojistenci(String jmeno, String prijmeni, int vek, String telefoniCislo) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.vek = vek;
        this.telefoniCislo = telefoniCislo;
    }

    public String getJmeno() {

        return jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }


    @Override
    public String toString() {
        return jmeno + " " + prijmeni + " " + vek + " " + telefoniCislo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pojistenci that = (Pojistenci) o;
        return vek == that.vek && telefoniCislo == that.telefoniCislo && Objects.equals(jmeno, that.jmeno) && Objects.equals(prijmeni, that.prijmeni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jmeno, prijmeni, vek, telefoniCislo);
    }
}


