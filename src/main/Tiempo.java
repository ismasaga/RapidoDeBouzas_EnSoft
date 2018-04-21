package main;

import java.util.Comparator;

public class Tiempo implements Comparator<Tiempo>, Comparable<Tiempo> {
    private int hora;
    private int minuto;
    private int segundo;

    public Tiempo() {
    }

    public Tiempo(int hora, int minuto, int segundo) {
        this.hora = hora;
        this.minuto = minuto;
        this.segundo = segundo;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public int getSegundo() {
        return segundo;
    }

    public void setSegundo(int segundo) {
        this.segundo = segundo;
    }

    @Override
    public int compare(Tiempo t1, Tiempo t2) {
        if (t1.getHora() == t2.getHora()) {
            if (t1.getMinuto() == t2.getMinuto()) {
                return t1.getSegundo() - t2.getSegundo();
            } else {
                return t1.getMinuto() - t2.getMinuto();
            }
        } else {
            return t1.getHora() - t2.getHora();
        }
    }

    @Override
    public int compareTo(Tiempo t) {
        return 1;
    }
}
