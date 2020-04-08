package cursos;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;

public class CursoPresencial extends Curso {
    private final int cupo;
    private final int asistenciaMin;
    private HashMap<Integer, HashSet<Alumno>> asistencias;
    private int plazas;

    //Constructor
    public CursoPresencial(String titulo, LocalDate inicio, LocalDate finalizacion, int numDias, int preciMatricula, int cupo, int asistenciaMin){
        super(titulo, inicio, finalizacion, numDias, preciMatricula);
        this.cupo = cupo;
        this.asistenciaMin = asistenciaMin;
        this.asistencias = new HashMap<Integer, HashSet<Alumno>>();
        plazas = this.cupo;
    }

    //Funcionalidades
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

    public int numeroAsistencias(Alumno a){
        int num = 0;
        for (HashSet<Alumno> lista: this.asistencias.values()) {
            if(lista.contains(a)) num++;
        }
        return num;
    }


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

    @Override
    public boolean EsApto(Alumno a) {
        return this.numeroAsistencias(a) >= asistenciaMin;
    }

   @Override
    protected Object clone() throws CloneNotSupportedException {
        return (CursoPresencial) super.clone();
    }
}
