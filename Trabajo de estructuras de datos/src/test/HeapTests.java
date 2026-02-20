package aed;

import static org.junit.jupiter.api.Assertions.assertEquals;

//falta: intercambiar, desencolar todos, 

import org.junit.jupiter.api.Test;

class HeapTests {
    @Test
    void nuevo_heap() {
        // caso de heap vacio
        Heap<Integer> heap = new Heap<Integer>(10);
        assertEquals(0, heap.cardinal());
        assertEquals("[]", heap.toString());
    }

    @Test
    void encolar_elementos() {
        Heap<Integer> heap = new Heap<Integer>(10);
        Handle<Integer> elem = new Handle<>(10, 0);
        heap.encolar(elem);

        assertEquals("[10]", heap.toString());
        int j = 10;
        for (int i = 1; i <= 5; i++) {
            j *= 10;
            elem = new Handle<>(j, i);
            heap.encolar(elem);
        }

        assertEquals("[10, 100, 1000, 10000, 100000, 1000000]", heap.toString());
    }

    @Test
    void desencolar_vacio() {
        Heap<Integer> heap = new Heap<Integer>(10);
        heap.desencolar();
        assertEquals("[]", heap.toString());
        assertEquals(0, heap.cardinal());

    }

    @Test
    void encolar_desencolar() {
        Heap<Integer> heap = new Heap<Integer>(10);
        Handle<Integer> elem = new Handle<>(10, 0);
        heap.encolar(elem);
        assertEquals(1, heap.cardinal());
        assertEquals(elem, heap.desencolar());
        assertEquals("[]", heap.toString());
        assertEquals(0, heap.cardinal());

    }

    @Test
    void desencolar_uno_solo() {
        Heap<Integer> heap = new Heap<Integer>(10);
        Handle<Integer> elem = new Handle<>(10, 0);
        heap.encolar(elem);

        assertEquals("[10]", heap.toString());
        int j = 10;
        for (int i = 1; i <= 5; i++) {
            j *= 10;
            elem = new Handle<>(j, i);
            heap.encolar(elem);
        }

        assertEquals(10, heap.desencolar().valor());
        assertEquals("[100, 10000, 1000, 1000000, 100000]", heap.toString());
        assertEquals(5, heap.cardinal());

    }

    @Test
    void desencolar_varios_elementos() {
        Heap<Integer> heap = new Heap<Integer>(10);
        Handle<Integer> elem = new Handle<>(10, 0);
        heap.encolar(elem);

        assertEquals("[10]", heap.toString());
        int j = 10;
        for (int i = 1; i <= 5; i++) {
            j *= 10;
            elem = new Handle<>(j, i);
            heap.encolar(elem);
        }

        assertEquals(10, heap.desencolar().valor());
        assertEquals("[100, 10000, 1000, 1000000, 100000]", heap.toString());

        assertEquals(100, heap.desencolar().valor());
        assertEquals("[1000, 10000, 100000, 1000000]", heap.toString());

        assertEquals(1000, heap.desencolar().valor());
        assertEquals("[10000, 1000000, 100000]", heap.toString());

        assertEquals(3, heap.cardinal());

    }

    @Test
    void desencolar_todos() {
        Heap<Integer> heap = new Heap<Integer>(10);
        Handle<Integer> elem = new Handle<>(10, 0);
        heap.encolar(elem);

        assertEquals("[10]", heap.toString());
        int j = 10;
        for (int i = 1; i <= 5; i++) {
            j *= 10;
            elem = new Handle<>(j, i);
            heap.encolar(elem);
        }

        heap.desencolar();
        heap.desencolar();
        heap.desencolar();
        heap.desencolar();
        heap.desencolar();

        assertEquals(1000000, heap.desencolar().valor());
        assertEquals("[]", heap.toString());

        assertEquals(null, heap.desencolar());
        assertEquals("[]", heap.toString());
        assertEquals(0, heap.cardinal());

    }
}