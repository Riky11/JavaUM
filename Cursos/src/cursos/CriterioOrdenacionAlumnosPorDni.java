package cursos;

import java.util.Comparator;

/**
 * Esta clase es un criterio de ordenacion para utilizarla con el metodo Collextion.sort(lista, criterio)
 * para que ordene la lista con este criterio.
 */
public class CriterioOrdenacionAlumnosPorDni implements Comparator<Alumno> {
    /**
     * Este criterio compara los alumnos por dni
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(Alumno o1, Alumno o2) {
        return o1.getDni().compareTo(o2.getDni());
    }
}
