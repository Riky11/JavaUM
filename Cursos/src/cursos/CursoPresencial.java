package cursos;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Clase que hereda de la clase Curso y representa un curso presencial que pueden realizar alumnos.
 */
public class CursoPresencial extends Curso {
    private final int cupo;
    private final int asistenciaMin;
    private HashMap<Integer, HashSet<Alumno>> asistencias;
    private int plazas;

    //Constructor:

    /**
     *  Constructor que inicializa el curso presencial con el titulo, fecha de inicio y finalizacion, el numero de dias,
     *  el precio de la matricula, el cupo maximo y las asistencias minimas.
     * @param titulo representa el nombre del curso
     * @param inicio fecha de inicio del curso
     * @param finalizacion fecha de finalizacion del curso
     * @param numDias numero de dias que dura el curso
     * @param precioMatricula precio de la matricula del curso
     * @param cupo cupo de alumnos que se pueden matricular en el curso
     * @param asistenciaMin asistencia minimas establecidas en el curso para superarlo
     */
    public CursoPresencial(String titulo, LocalDate inicio, LocalDate finalizacion, int numDias, int precioMatricula, int cupo, int asistenciaMin){
        super(titulo, inicio, finalizacion, numDias, precioMatricula);
        this.cupo = cupo;
        this.asistenciaMin = asistenciaMin;
        this.asistencias = new HashMap<Integer, HashSet<Alumno>>();
        plazas = this.cupo;
    }

    /**
     * Metodo de consulta del cupo del curso
     * @return devuelve el valor del cupo maximo del curso
     */
    public int getCupo() {
        return cupo;
    }

    /**
     * Metodo de consulta de la asistencia minima
     * @return devuelve el valor de la asistencia minima del curso
     */
    public int getAsistenciaMin() {
        return asistenciaMin;
    }

    /**
     * Metodo de consulta de la plazas libres del curso.
     * @return devuelve el valor de plazas libres actuales del curso
     */
    public int getPlazas() {
        return plazas;
    }

    /**
     * Metodo que registra la asistencia de un alumno en el dia indicado.
     * @param dia numero del dia del curso en el que el alumno ha asistido
     * @param a alumno al que se registra la asistencia
     * @return devuelve true si se registra la asistencia o false en caso contrario.
     */
    public boolean registroAsistencia(int dia, Alumno a) {
        if (dia >= 1 && dia <= this.getNumDias() && this.getAlumnosMatriculados().contains(a)){
            if(!this.asistencias.containsKey(dia)){
                this.asistencias.put(dia, new HashSet<Alumno>());

            }
            this.asistencias.get(dia).add(a);
            return true;
         }
        return false;
    }

    /**
     * Metodo de consulta del numero de asistencia que tiene un alumno.
     * @param a alumno sobre el que se realiza la consulta
     * @return devuelve el numero de asistencias que tiene el alumno
     */
    public int numeroAsistencias(Alumno a){
        int num = 0;
        for (HashSet<Alumno> lista: this.asistencias.values()) {
            if(lista.contains(a)) num++;
        }
        return num;
    }

    /**
     * Metodo que permite a un alumno matricularse en el curso si cumple los siguientes requisitos:
     * 	- Tiene el credito suficiente.
     * 	- Hay plazas libres
     * @param a alumno que se quiere matricular
     * @return devuelve true si el alumno se ha podido matricular o false en caso contrario.
     */
    @Override
    public boolean Matriculacion(Alumno a) {
        if(plazas > 0){
            if(super.Matriculacion(a)){
                plazas--;
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo que indica si un alumno es apto, es decir, si su numero de asistencias es igual
     * o mayor que el minimo establecido por el curso.
     * @param a alumno sobre el que se quiere consultar.
     * @return devuelve true si el alumno es apto o false en caso contrario.
     */
    @Override
    public boolean EsApto(Alumno a) {
        return this.numeroAsistencias(a) >= asistenciaMin;
    }

    /**
     * Metodo clone() adaptado para que realice una copia de los cursos en la que se hace primero
     * una copia superficial del curso y despues modificamos la lista de alumnos matriculados,
     * aptos y el numero de matriculados en el clone() de la clase padre y por ultimo modificamos
     * la lista de asistencias en este clone() redefinido para que no apunten a las listas del
     * curso original y esten vacias.
     * @return retorna una copia del curso de tipo CursoPresencial
     */
   @Override
    public CursoPresencial clone()  {
       CursoPresencial copia = (CursoPresencial) super.clone();
       copia.asistencias = new HashMap<Integer, HashSet<Alumno>>();
       copia.plazas = this.cupo;
       return copia;
    }
}
