# Implementación de Estructuras de Datos en Java

## Descripcion

Sistema de evaluacion y deteccion de plagios en examenes universitarios, construido desde cero sobre estructuras de datos propias implementadas en Java puro. El proyecto integra una logica  (simulacion de aula, deteccion de copia, ranking dinamico de estudiantes) con estructuras de datos eficientes.

---

## Estructuras de datos implementadas

| Estructura | Archivo | Complejidad clave |
|---|---|---|
| Min-Heap generico con handles | `Heap.java` + `Handle.java` | Insercion/extraccion O(log N), actualizacion O(log N) |
| Pila generica (linked nodes) | `Pila.java` | Push/pop/peek O(1) |

### Heap con Handles
Implementacion de un min-heap generico (`Heap<T extends Comparable<T>>`) que mantiene el seguimiento de posiciones mediante objetos `Handle<T>`. Esto permite actualizar la prioridad de un elemento en O(log N) sin necesidad de buscarlo, clave para el ranking dinamico de estudiantes.

### Pila generica
Stack LIFO implementado con nodos enlazados. Sin dependencia de arrays, todas las operaciones en O(1) incluyendo consulta de tamanio.

---

## Aplicacion: sistema de examenes (EDR)

La clase `Edr` modela un aula universitaria y expone las siguientes operaciones:

| Operacion | Complejidad | Descripcion |
|---|---|---|
| `resolver(id, pregunta, respuesta)` | O(log E) | Registra la respuesta de un estudiante y actualiza su ranking |
| `copiarse(id)` | O(R + log E) | Simula que el estudiante copia del mejor vecino disponible |
| `consultarDarkWeb(ids[], respuestas[])` | O(K(R + log E)) | Inyecta respuestas externas a un grupo de estudiantes |
| `entregar(id)` | O(log E) | Marca el examen como entregado y reordena el ranking |
| `chequearCopias()` | O(E * R) | Detecta sospechosos por frecuencia de respuestas identicas |
| `corregir()` | O(E log E) | Extrae el ranking final excluyendo sospechosos |

> E = cantidad de estudiantes, R = cantidad de preguntas, K = estudiantes del grupo

---

## Algoritmo de deteccion de plagios

`chequearCopias()` construye una matriz de frecuencias por pregunta y marca como sospechoso a cualquier estudiante cuya respuesta correcta coincida con la de mas del 25% de sus compañeros. Complejidad O(E * R).

---

## Tecnologias

- **Java 8**
- **Maven** (gestion de dependencias y build)

---

## Estructura del proyecto

```
src/
├── main/
│   ├── Heap.java          # Min-heap generico con handles
│   ├── Handle.java        # Wrapper de referencia con posicion en heap
│   ├── Pila.java          # Stack generico con nodos enlazados
│   ├── Estudiante.java    # Modelo de estudiante con logica de ranking
│   ├── NotaFinal.java     # Resultado final de un estudiante
│   └── Edr.java           # Motor principal del sistema de examenes
└── test/
    ├── HeapTests.java
    ├── EdrTests.java
    ├── TestsPila.java
    └── testsNuestrosEdR.java
```
