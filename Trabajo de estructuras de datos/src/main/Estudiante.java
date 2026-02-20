package aed;

public class Estudiante implements Comparable<Estudiante> {
    private int id;
    private Estudiante[] vecinos;
    private int[] examen;
    private boolean entregado;
    private double puntaje;
    private boolean sospechoso;

    public Estudiante(int i, int cantPreguntas) {
        this.id = i; // O(1)
        this.vecinos = new Estudiante[3]; // O(1)
        this.examen = new int[cantPreguntas]; // O(R)
        this.entregado = false; // O(1)
        this.puntaje = 0; // O(1)
        this.sospechoso = false; // O(1)
        for (int n = 0; n < cantPreguntas; n++) { // O(R)
            this.examen[n] = -1; // O(1)
        }
    }

    public int idEstudiante() {
        return id; // O(1)
    }

    public int[] examen() {
        return examen; // O(1)
    }

    public Estudiante[] vecinos() {
        return vecinos; // O(1)
    }

    public double puntaje() {
        return puntaje; // O(1)
    }

    public void setPuntaje(double nuevoPuntaje) {
        this.puntaje = nuevoPuntaje; // O(1)
    }

    public boolean sospechoso() {
        return sospechoso; // O(1)
    }

    public void setSospechoso(boolean sospecha) {
        this.sospechoso = sospecha; // O(1)
    }

    public boolean entregado() {
        return entregado; // O(1)
    }

    public void setEntregado(boolean entregado) {
        this.entregado = entregado; // O(1)
    }

    public int compareTo(Estudiante otro) { // O(1)
        int res = 0;
        if (this.entregado == true && otro.entregado == false) {
            res = 1; // O(1)

        } else if (this.entregado == false && otro.entregado == true) {
            res = -1; // O(1)

        } else { // ambos entregaron o ambos no entregaron
            if (this.puntaje > otro.puntaje) {
                res = 1; // O(1)
            } else if (this.puntaje < otro.puntaje) {
                res = -1; // O(1)
            } else { // puntajes iguales
                if (this.id > otro.id) {
                    res = 1; // O(1)
                } else if (this.id < otro.id) {
                    res = -1; // O(1)
                } else {
                    res = 0; // O(1)
                }
            }
        }
        return res;
    }

}
