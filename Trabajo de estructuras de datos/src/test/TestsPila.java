package aed;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class TestsPila {
    // --------------------------------------------
    @Test
    void PilaVacia() {
        Pila<Integer> pila = new Pila<>();
        assertEquals(null, pila.desapilar());
        assertEquals(0, pila.longitud());
    }

    @Test
    void ProbarTope() {
        Pila<Integer> pila = new Pila<>();
        pila.apilar(1);
        assertEquals(1, pila.longitud());
        assertEquals(1, pila.tope());
        assertEquals(1, pila.longitud());
    }

    @Test
    void PilaAgregoYDesapilo() {
        Pila<Integer> pila = new Pila<>();
        pila.apilar(10);
        assertEquals(1, pila.longitud());
        assertEquals(10, pila.desapilar());
        assertEquals(0, pila.longitud());

    }

    @Test
    void ApilarVarios() {
        Pila<Integer> pila = new Pila<>();
        pila.apilar(21);
        pila.apilar(20);
        pila.apilar(32);
        pila.apilar(43);
        pila.apilar(12);
        pila.apilar(41);
        assertEquals(6, pila.longitud());
        int[] Lista = new int[6];
        for (int i = 0; i < 6; i++) {
            Lista[i] = pila.desapilar();
        }
        int[] listaEsperada = new int[] { 41, 12, 43, 32, 20, 21 };
        assertArrayEquals(listaEsperada, Lista);
    }

    @Test
    void ApilarYDesapilarMuchos() {
        Pila<Integer> pila = new Pila<>();
        for (int i = 0; i < 1000; i++) {
            pila.apilar(i);
        }
        assertEquals(1000, pila.longitud());

        for (int i = 999; i >= 0; i--) {
            assertEquals(i, pila.desapilar());
        }

        assertEquals(0, pila.longitud());
        assertNull(pila.desapilar());
    }

    @Test
    void OperacionesConStrings() {
        Pila<String> pila = new Pila<>();
        pila.apilar("ABC");
        pila.apilar("XYZ");
        assertEquals("XYZ", pila.desapilar());
        pila.apilar("QWE");
        assertEquals(2, pila.longitud());
        assertEquals("QWE", pila.desapilar());
        assertEquals("ABC", pila.desapilar());
        assertEquals(null, pila.desapilar());
    }

    @Test
    void MezclaDeOperacionnes() {
        Pila<Integer> pila = new Pila<>();
        pila.apilar(1);
        pila.apilar(10);
        pila.apilar(100);
        pila.apilar(1000);
        assertEquals(1000, pila.tope());
        pila.apilar(10000);
        assertEquals(5, pila.longitud());
        assertEquals(10000, pila.desapilar());
        assertEquals(1000, pila.desapilar());
        int[] Lista = new int[6];
        for (int i = 0; i <= 4; i = i + 2) {
            Lista[i] = pila.tope();
            Lista[i + 1] = pila.desapilar();
        }
        int[] listaEsperada = new int[] { 100, 100, 10, 10, 1, 1 };
        assertArrayEquals(listaEsperada, Lista);
        pila.apilar(666);
        pila.apilar(777);
        pila.apilar(888);
        assertEquals(888, pila.desapilar());
        assertEquals(2, pila.longitud());
        assertEquals(777, pila.desapilar());
        assertEquals(666, pila.desapilar());
        assertEquals(null, pila.desapilar());
        assertEquals(0, pila.longitud());
    }
}
