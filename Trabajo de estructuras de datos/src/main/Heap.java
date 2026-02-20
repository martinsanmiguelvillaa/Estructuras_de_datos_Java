package aed;

import java.util.ArrayList;

public class Heap<T extends Comparable<T>> extends Handle<T> {
    private ArrayList<Handle<T>> heap;
    private int cardinal;

    public Heap(int longitud) {
        heap = new ArrayList<Handle<T>>(longitud);
        cardinal = 0;
    }

    public int cardinal() { // O(1)
        return cardinal;
    }

    public int subir(Handle<T> elem, int posicion) { // para llamarlo en edr le pasamos handle.pos() O(log N)
        // Lógica para subir el nodo n en el heap
        if (posicion != 0) {
            int posPadre = posicionPadre(posicion); // O(1)
            while (elem.compareTo(heap.get(posPadre)) < 0 && posPadre >= 0) { // elem es menor que su padre O (log N)
                intercambiar(posicion, posPadre); // O(1)
                posicion = posPadre; // O(1)
                posPadre = posicionPadre(posPadre); // O(1)
                elem = heap.get(posicion); // O(1)
            }
        }
        return posicion;
    }

    public int bajar(Handle<T> elem, int posicion) {
        // Lógica para bajar el nodo n en el heap
        int posHijoIzq = posicionHijoIzq(posicion); // O(1)
        int posHijoDer = posicionHijoDer(posicion); // O(1)

        while ((posHijoDer < cardinal && elem.compareTo(heap.get(posHijoDer)) > 0)
                || (posHijoIzq < cardinal && elem.compareTo(heap.get(posHijoIzq)) > 0)) { // posHijoIzq < cardinal O
                                                                                          // (log N)
            int menor = posHijoIzq; // o(1)
            if (posHijoDer < cardinal && heap.get(posHijoDer).compareTo(heap.get(posHijoIzq)) < 0) { // Hijo derecho es
                                                                                                     // menor que el
                                                                                                     // izquierdo
                menor = posHijoDer; // O(1)
            }
            intercambiar(posicion, menor); // O(1)
            posicion = menor; // O(1)
            posHijoIzq = posicionHijoIzq(posicion); // O(1)
            posHijoDer = posicionHijoDer(posicion); // O(1

        }
        return posicion;

    }

    private void intercambiar(int posicion1, int posicion2) {
        Handle<T> valor1 = heap.get(posicion1); // O(1)
        Handle<T> valor2 = heap.get(posicion2); // O(1)
        heap.set(posicion1, valor2); // O(1)
        heap.set(posicion2, valor1); // O(1)
        valor1.nuevaPosicion(posicion2); // O(1)
        valor2.nuevaPosicion(posicion1); // O(1)
    }

    public int encolar(Handle<T> nuevo) { // O(log N)
        // Lógica para insertar el nuevo nodo en la posición correcta
        heap.add(nuevo); // O(1)
        int posicion = cardinal - 1; // O(1)
        if (cardinal > 0) {
            posicion = subir(nuevo, cardinal); // O(log N)
        }
        cardinal++; // O(1)
        return posicion;
    }

    public Handle<T> desencolar() { // O(log N)
        // Lógica para eliminar y retornar el nodo con el valor mínimo
        if (cardinal == 0) { // O(1)
            return null;
        }
        Handle<T> min = heap.get(0); // O(1)

        cardinal--; // O(1)
        if (cardinal > 0) {
            Handle<T> ultimo = heap.get(cardinal); // O(1)
            heap.set(0, ultimo);// O(1)
            heap.remove(cardinal); // heap.set(cardinal - 1, null); O(1)

            bajar(heap.get(0), 0); // O(log N)
        }
        return min;
    }

    public Handle<T> consultarMin() { // O(1)
        return heap.get(0);
    }

    public int posicionPadre(int posicion) { // no cambia
        int padre = (posicion - 1) / 2; // O(1)
        return padre;
    }

    public int posicionHijoIzq(int posicion) { // no cambia
        int hijoIzq = posicion * 2 + 1; // O(1)
        return hijoIzq;
    }

    public int posicionHijoDer(int posicion) { // no cambia
        int hijoDer = posicion * 2 + 2; // O(1)
        return hijoDer;
    }

    @Override
    public String toString() {
        String res = "["; // O(1)
        int contador = 0; // O(1)
        while (contador < cardinal - 1) { // O(N)
            res += heap.get(contador).valor(); // O(1)
            res += ", "; // O(1)
            contador += 1; // O(1)
        }
        if (contador == cardinal - 1) { // O(1)
            res += heap.get(contador).valor(); // O(1)
        }
        return res += "]";
    }

}