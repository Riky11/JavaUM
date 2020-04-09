package cursos;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Clase que hereda de la clase Curso y representa un curso online que pueden realizar alumnos.
 */
public class CursoOnline extends Curso {
    private final int nivelMax;
    private HashMap<Alumno, Integer> seguimientoAlumnos;
    private final HashSet<Curso> cursoPrevios;

    //Constructor:

    /**
     *  Constructor que inicializa el cursoOnline con el titulo, fecha de inicio y finalizacion, el numero de dias,
     *  el precio de la matricula, el nivel maximo y los cursos previos.
     * @param titulo representa el nombre del curso
     * @param inicio fecha de inicio del curso
     * @param finalizacion fecha de finalizacion del curso
     * @param numDias numero de dias que dura el curso
     * @param precioMatricula precio de la matricula del curso
     * @param nivelMax nivel maximo que puede alcanzar un alumno en el curso
     * @param cursoPrevios cursos que necesita tener un alumno para matricularse en este curso
     */
    public CursoOnline(String titulo, LocalDate inicio, LocalDate finalizacion, int numDias, int precioMatricula, int nivelMax, Curso... cursoPrevios ){
        super(titulo, inicio, finalizacion, numDias, precioMatricula);
        this.nivelMax = nivelMax;
        this.cursoPrevios = new HashSet<Curso>();
        this.cursoPrevios.addAll(Arrays.asList(cursoPrevios));
        this.seguimientoAlumnos = new HashMap<Alumno, Integer>();
    }

    //Metodos:

    /**
     * Metodo de consulta del nivel maximo del curso
     * @return devuelve el valor del nivel maximo del curso
     */
    public int getNivelMax() {
        return nivelMax;
    }

    /**
     * Metodo de consulta de los cursos previos necesarios para realizar el curso
     * @return devuelve el cojunto de cursos necesarios
     */
    public Set<Curso> getCursoPrevios() {
        HashSet<Curso> copia = new HashSet<Curso>(this.cursoPrevios);
        return copia;
    }

    /**
     * Metodo de consulta del nivel del alumno en el curso.
     * @param a alumno sobre el que se hace la consulta
     * @return devuelve el nivel del alumno
     */
    public int nivelAlumno(Alumno a){
        return (this.getAlumnosMatriculados().contains(a)) ? this.seguimientoAlumnos.get(a) : -1 ;
    }

    /**
     * Metodo que incrementa a un nivel mas, el nivel del alumno, si no ha alcanzado el maximo.
     * @param a alumno al que se le incrementa el nivel
     * @return devuelve true si se ha incrementado el nivel o false en caso contrario.
     */
    public boolean superarNivel(Alumno a){
        if(this.getAlumnosMatriculados().contains(a) && this.nivelAlumno(a) < this.nivelMax){
            this.seguimientoAlumnos.put(a, this.seguimientoAlumnos.get(a) + 1);
            return true;
        }
        return false;
    }

    /**
     * Metodo que permite a un alumno matricularse en el curso si cumple los siguientes requisitos:
     * 	- Tiene el credito suficiente.
     * 	- Tiene los cursos previos necesarios
     * @param a alumno que se quiere matricular
     * @return devuelve true si el alumno se ha podido matricular o false en caso contrario.
     */
    @Override
    public boolean Matriculacion(Alumno a) {
        for (Curso c: this.cursoPrevios) {
            if(!c.EsApto(a))
                return false;
        }
        if(super.Matriculacion(a)){
            this.seguimientoAlumnos.put(a, 0);
            return true;
        }
        return false;
    }

    /**
     * Metodo que indica si un alumno es apto, es decir, si su nivel es por lo menos
     * la mitad del nivel maximo del curso.
     * @param a alumno sobre el que se quiere consultar.
     * @return devuelve true si el alumno es apto o false en caso contrario.
     */
    @Override
    public boolean EsApto(Alumno a) {
        return this.seguimientoAlumnos.get(a) >= this.nivelMax/2;

    }

    /**
     * Metodo clone() adaptado para que realice una copia de los cursos en la que se hace primero
     * una copia superficial del curso y despues modificamos la lista de alumnos matriculados,
     * aptos y el numero de matriculados en el clone() de la clase padre y por ultimo modificamos
     * la lista del seguimiento de alumnos en este clone() redefinido para que no apunten a las
     * listas del curso original y esten vacias.
     * @return retorna una copia del curso de tipo CursoOnline
     */
    //Cambiamos el tipo de retorno para que sea el de la clase 'CursoOnline' (regla covariante).
    @Override
    public CursoOnline clone()  {
        CursoOnline copia = (CursoOnline) super.clone();
        copia.seguimientoAlumnos = new HashMap<Alumno,Integer>();
        return copia;
    }
}
