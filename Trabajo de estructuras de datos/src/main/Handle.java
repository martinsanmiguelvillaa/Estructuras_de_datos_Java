package aed;

public class Handle<T extends Comparable<T>> implements Comparable<Handle<T>> {
    private T apuntado;
    private int pos;

    public Handle() {
        this.apuntado = null; // O(1)
        this.pos = -1; // O(1)
    }

    public Handle(T t, int p) {
        this.apuntado = t; // O(1)
        this.pos = p; // O(1)
    }

    public T valor() {
        return apuntado; // O(1)
    }

    public int posicion() {
        return pos; // O(1)
    }

    public void nuevaPosicion(int p) {
        pos = p; // O(1)
    }

    @Override
    public int compareTo(Handle<T> other) { // O(1)
        return apuntado.compareTo(other.apuntado);
    }
}
