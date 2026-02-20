/* 
  Resolución del trabajo práctico de Algoritmos y Estructuras de Datos. 
  Este archivo contiene la lógica principal de resolución de las consignas,
  utilizando las estructuras implementadas (Heap, Pila, etc).
 */
package aed;

import java.util.ArrayList;

public class Edr {
    private Heap<Estudiante> puntajes_estudiantes;
    private ArrayList<Handle<Estudiante>> estudiantes_por_id;
    private int[] examen_canonico;
    private int[] sospechosos;

    public Edr(int LadoAula, int Cant_estudiantes, int[] examen_canonico) {
        estudiantes_por_id = new ArrayList<Handle<Estudiante>>(Cant_estudiantes);// O(E)
        int cant_preguntas = examen_canonico.length; // O(1)
        this.examen_canonico = examen_canonico; // O(1)
        this.sospechosos = new int[0]; // O(1)

        puntajes_estudiantes = new Heap<Estudiante>(Cant_estudiantes); // O(E)

        for (int i = 0; i < Cant_estudiantes; i++) { // O(E)
            Estudiante e = new Estudiante(i, cant_preguntas); // O(R)
            Handle<Estudiante> handleEst = new Handle<Estudiante>(e, i); // O(1)
            estudiantes_por_id.add(handleEst); // O(1)
            puntajes_estudiantes.encolar(handleEst); // O(1) porque estan ordenados
        } // final O(E*R)
        for (int i = 0; i < Cant_estudiantes; i++) { // O(E)
            Estudiante e = estudiantes_por_id.get(i).valor(); // O(1)
            // Vecino izquierda
            if (i > 0) {
                e.vecinos()[0] = estudiantes_por_id.get(i - 1).valor(); // O(1)
            } else {
                e.vecinos()[0] = null;// O(1)
            }
            // Vecino derecha
            if (i < Cant_estudiantes - 1) {
                e.vecinos()[1] = estudiantes_por_id.get(i + 1).valor();// O(1)
            } else {
                e.vecinos()[1] = null; // O(1)
            }
            // Vecino adelante
            if (i - LadoAula > 0) {
                e.vecinos()[2] = estudiantes_por_id.get(i - LadoAula).valor();// O(1)
            } else {
                e.vecinos()[2] = null;// O(1)
            }
        }
        // final O(E*R) + O(E) + O(E) = O(E*R)
    }

    // -------------------------------------------------NOTAS--------------------------------------------------------------------------

    public double[] notas() {
        int cant_estudiantes = estudiantes_por_id.size(); // O(1)
        double[] res = new double[cant_estudiantes]; // O(E)
        for (int i = 0; i < cant_estudiantes; i++) { // O(E)
            Estudiante e = estudiantes_por_id.get(i).valor(); // O(1)
            res[i] = e.puntaje(); // O(1)
        }
        return res;
        // final O(E)
    }

    // ------------------------------------------------COPIARSE------------------------------------------------------------------------

    public void copiarse(int estudiante) {
        Estudiante e = estudiantes_por_id.get(estudiante).valor(); // O(1)
        Estudiante[] vecinos = e.vecinos(); // O(1)
        Estudiante mejor_vecino = null; // O(1)
        int max_completadas_diferentes = 0; // O(1)

        for (int k = 0; k < 3; k++) { // O(3) = O(1)
            Estudiante v = vecinos[k]; // O(1)
            if (v != null) {
                int completadas_que_no_tiene = 0; // O(1)
                int[] examen_estudiante = e.examen(); // O(1)
                int[] examen_vecino = v.examen(); // O(1)

                for (int j = 0; j < examen_estudiante.length; j++) { // O(R)
                    if (examen_estudiante[j] == -1 && examen_vecino[j] != -1) {
                        completadas_que_no_tiene++;// O(1)
                    }
                }

                if (completadas_que_no_tiene > max_completadas_diferentes ||
                        (completadas_que_no_tiene == max_completadas_diferentes &&
                                mejor_vecino != null && v.idEstudiante() > mejor_vecino.idEstudiante())) { // O(1)

                    max_completadas_diferentes = completadas_que_no_tiene; // O(1)
                    mejor_vecino = v; // O(1)
                }
            }
        } // O(R)
        if (mejor_vecino == null || max_completadas_diferentes <= 0)
            return;

        int[] examen_estudiante = e.examen(); // O(1)
        int[] examen_vecino = mejor_vecino.examen(); // O(1)

        int i = 0;// O(1)
        while (i < examen_estudiante.length - 1 && (examen_vecino[i] == -1 || examen_estudiante[i] != -1)) { // O(R)
            i += 1;// O(1)
        }
        resolver(estudiante, i, examen_vecino[i]); // O(Log E)
        // final O(R + log E)
    }

    // -----------------------------------------------RESOLVER----------------------------------------------------------------

    public void resolver(int estudiante, int nro_ejercicio, int res) {
        Handle<Estudiante> est_handle = estudiantes_por_id.get(estudiante); // O(1)
        Estudiante est = est_handle.valor(); // O(1)
        est.examen()[nro_ejercicio] = res; // O(1)
        est.setPuntaje(sumarEjercicio(nro_ejercicio, res) + est.puntaje()); // O(1)
        puntajes_estudiantes.bajar(est_handle, est_handle.posicion()); // O(log E)
        puntajes_estudiantes.subir(est_handle, est_handle.posicion()); // O(log E)
        // final O(log E)
    }

    private double sumarEjercicio(int nro_ejercicio, int res) { // O(1)
        double puntaje = 0;// O(1)
        if (res == examen_canonico[nro_ejercicio]) {
            puntaje = 100 / examen_canonico.length;// O(1)
        }
        return puntaje;

    }

    // ------------------------------------------------CONSULTAR DARK
    // WEB-------------------------------------------------------

    public void consultarDarkWeb(int k, int[] examenDW) {
        int i = 0;// O(1)
        Pila<Handle<Estudiante>> pila = new Pila<Handle<Estudiante>>(); // O(1)
        while (i < k) { // O(K)
            Handle<Estudiante> HandleE = puntajes_estudiantes.desencolar(); // O(log E)
            HandleE.valor().setPuntaje(0.0);// O(1)
            pila.apilar(HandleE);// O(1)
            for (int j = 0; j < examenDW.length; j++) { // O(R)
                resolverAux(HandleE, j, examenDW[j]);
            }
            i++;
        } // O(K(R + log E))
        while (!pila.EstaVacia()) { // O(K)
            Handle<Estudiante> e = pila.desapilar(); // O(1)
            puntajes_estudiantes.encolar(e); // O(log E)
        } // O(K*log E)
          // final O(K(R + log E))
    }

    private void resolverAux(Handle<Estudiante> est_handle, int nro_ejercicio, int res) {
        Estudiante est = est_handle.valor(); // O(1)
        est.examen()[nro_ejercicio] = res; // O(1)
        est.setPuntaje(sumarEjercicio(nro_ejercicio, res) + est.puntaje()); // O(1)
    }

    // -------------------------------------------------ENTREGAR-------------------------------------------------------------

    public void entregar(int estudiante) {
        Handle<Estudiante> est_handle = estudiantes_por_id.get(estudiante);
        Estudiante e = est_handle.valor();
        e.setEntregado(true);
        puntajes_estudiantes.bajar(est_handle, est_handle.posicion()); // O(log E)
        puntajes_estudiantes.subir(est_handle, est_handle.posicion()); // O(log E)
        // final O(log E)
    }

    // -----------------------------------------------------CORREGIR---------------------------------------------------------

    public NotaFinal[] corregir() {
        int cant_estudiantes = estudiantes_por_id.size(); // O(1)
        int no_sospechosos = cant_estudiantes - sospechosos.length; // O(1)
        NotaFinal[] res = new NotaFinal[no_sospechosos]; // O(E)
        int i = no_sospechosos - 1; // O(1)
        while (puntajes_estudiantes.cardinal() > 0 || i > 0) { // O(E)
            Estudiante e = puntajes_estudiantes.desencolar().valor(); // O(log E)
            if (!e.sospechoso()) {
                res[i] = new NotaFinal(e.puntaje(), e.idEstudiante()); // O(1)
                i--; // O(1)
            }
            // O(E*log E)
        }
        return res;
        // final O(E) + O(E*log E) = O(E*log E)
    }

    // -------------------------------------------------------CHEQUEAR
    // COPIAS-------------------------------------------------

    public int[] chequearCopias() {
        // Inicializaciones O(R)
        int cant_sospechosos = 0; // O(1)
        int e_menos1 = estudiantes_por_id.size() - 1; // O(1)
        int[][] matriz = new int[examen_canonico.length][10]; // O(R*10) = O(R)

        // Primer recorrido para llenar la matriz O(E*R)
        for (int i = 0; i < estudiantes_por_id.size(); i++) { // O(E)
            Estudiante e = estudiantes_por_id.get(i).valor();
            int[] examen = e.examen(); // O(1)
            for (int j = 0; j < examen.length; j++) { // O(R)
                int respuesta = examen[j];// O(1)
                if (respuesta != -1) {
                    matriz[j][respuesta]++;// O(1)
                }
            }
        }

        // Segundo recorrido chequea si el estudiante cumple con el 25% O(E*R)
        for (int i = 0; i < estudiantes_por_id.size(); i++) { // O(E)
            Estudiante e = estudiantes_por_id.get(i).valor(); // O(1)
            int[] examen = e.examen(); // O(1)
            int j = 0; // O(1)
            boolean todas_copiadas = true; // O(1)

            int cant_respuestas = 0;// O(1)
            while (j < examen.length && todas_copiadas) { // O(R)
                int respuesta = examen[j]; // O(1)
                if (respuesta != -1) {
                    cant_respuestas += 1;// O(1)
                    int coincidencias_con_otros = matriz[j][respuesta] - 1;// O(1)
                    if (coincidencias_con_otros * 4 < e_menos1) {
                        todas_copiadas = false; // O(1)
                    }
                }
                j++;
            }
            if (todas_copiadas && cant_respuestas > 0) {
                e.setSospechoso(true); // O(1)
                cant_sospechosos++; // O(1)
            }
        }

        // Tercer recorrido para armar el arreglo de sospechosos O(E)
        int[] res = new int[cant_sospechosos]; // O(1)
        int j = 0; // O(1)
        for (int i = 0; i < estudiantes_por_id.size(); i++) { // O(E)
            Estudiante e = estudiantes_por_id.get(i).valor(); // O(1)
            if (e.sospechoso()) {
                res[j] = e.idEstudiante(); // O(1)
                j++; // O(1)
            }
        }
        sospechosos = res; // O(1)
        return res; // O(1)
        // final O(R) + O(E*R) + O(E*R) + O(E) = O(E*R)
    }

}
