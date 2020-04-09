package cursos;

import java.util.Comparator;

/**
 * Esta clase es un criterio de ordenacion para utilizarla con el metodo Collection.sort(lista, criterio)
 * para que ordene la lista con este criterio.
 */
public class CriterioOrdenacionAlumnosPorDni implements Comparator<Alumno> {
    /**
     * Este criterio compara los alumnos por dni     *
     * @return devuelve un -1 si o1 es menor, 0 si son iguales y
     * si o1 es mayor.
     */
    @Override
    public int compare(Alumno o1, Alumno o2) {
        return o1.getDni().compareTo(o2.getDni());
    }
}
