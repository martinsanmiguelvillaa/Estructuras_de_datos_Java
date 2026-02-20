package aed;

public class Pila<T> {
    private Nodo ultimo;
    private int size;

    private class Nodo {
        T valor; // O(1)
        Nodo sig; // O(1)

        Nodo(T t, Nodo sig) {

            valor = t; // O(1)
            this.sig = sig; // O(1)
        }
    }

    public Pila() {
        ultimo = null; // O(1)
        size = 0; // O(1)
    }

    public int longitud() {
        return this.size; // O(1)
    }

    public void apilar(T elem) {
        Nodo nuevo = new Nodo(elem, ultimo); // O(1)
        ultimo = nuevo; // O(1)
        this.size++; // O(1)
    }

    public T desapilar() {
        if (this.size == 0) { // O(1)
            return null;
        }
        T res = ultimo.valor; // O(1)
        ultimo = ultimo.sig; // O(1)
        this.size--; // O(1)
        return res;
    }

    public T tope() { // O(1)
        return ultimo.valor;
    }

    public boolean EstaVacia() { // O(1)
        return this.size == 0;
    }
}
