package aed;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class testsNuestrosEdR {
    // --------------------------------------------
    @Test
    void copiarse_no_hace_nada_si_no_hay_que_copiar() {
        int[] exCan = new int[] { 1, 2, 3, 4 };
        Edr edr = new Edr(2, 4, exCan);

        edr.resolver(1, 0, 1);
        edr.resolver(1, 1, 2);
        edr.resolver(1, 2, 3);
        edr.resolver(1, 3, 4);

        edr.copiarse(1);

        assertEquals(100.0, edr.notas()[1]);
    }

    @Test
    void copiarse_desempata_por_id_mayor() {
        int[] exCan = new int[] { 1, 1, 1, 1 };
        Edr edr = new Edr(3, 5, exCan);

        edr.resolver(1, 0, 2);
        edr.resolver(3, 2, 1);
        edr.copiarse(2); // si se copia del 3 el puntaje quedaria en 25, si se copia del 1 quedaria en 0

        assertEquals(25.0, edr.notas()[2]);

    }

    @Test
    void darkweb_desempate_por_menor_id() {
        int[] exCan = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        Edr edr_test = new Edr(5, 5, exCan);
        int[] solDark = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

        edr_test.resolver(0, 0, 0);
        edr_test.resolver(1, 1, 1);
        edr_test.resolver(2, 2, 2);
        edr_test.resolver(3, 3, 3);

        edr_test.resolver(4, 4, 4);
        edr_test.resolver(4, 5, 5);

        edr_test.consultarDarkWeb(2, solDark);

        double[] notas = edr_test.notas();
        double[] notas_esperadas = new double[] { 100.0, 100.0, 10.0, 10.0, 20.0 };

        assertTrue(Arrays.equals(notas_esperadas, notas));
    }
}
