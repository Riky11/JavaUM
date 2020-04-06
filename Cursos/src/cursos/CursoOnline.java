package cursos;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class CursoOnline extends Curso {
    private final int nivelMax;
    private HashMap<Alumno, Integer> seguimientoAlumnos;
    private final HashSet<Curso> cursoPrevios;

    public CursoOnline(String titulo, Date inicio, Date finalizacion, int numDias, int precioMatricula, int nivelMax, Curso... cursoPrevios ){
        super(titulo, inicio, finalizacion, numDias, precioMatricula);
        this.nivelMax = nivelMax;
        this.cursoPrevios = new HashSet<Curso>();
        for (Curso c: cursoPrevios) {
            this.cursoPrevios.add(c);
        }
        this.seguimientoAlumnos = new HashMap<Alumno, Integer>();
    }

    //Funcionalidades
    public int nivelAlumno(Alumno a){
        return (this.getAlumnosMatriculados().contains(a)) ? this.seguimientoAlumnos.get(a) : -1 ;
    }

    public boolean superarNivel(Alumno a){
        if(this.getAlumnosMatriculados().contains(a) && this.nivelAlumno(a) < this.nivelMax){
            //Comprobar esto--------------------------------------------------------
            this.seguimientoAlumnos.put(a, this.seguimientoAlumnos.get(a) + 1);
            return true;
        }
        return false;
    }

    @Override
    public boolean Matriculacion(Alumno a) {
        for (Curso c: this.cursoPrevios) {
            if(!c.EsApto(a))
                return false;
        }
        return super.Matriculacion(a);
    }

    @Override
    public boolean EsApto(Alumno a) {
        return (this.seguimientoAlumnos.get(a) >= this.nivelMax/2) ? true : false;

    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return (CursoOnline) super.clone();
    }
}
