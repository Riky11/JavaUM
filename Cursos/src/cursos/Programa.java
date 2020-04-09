package cursos;

import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.List;


public class Programa {
    public static void main(String[] args) {
        //Declara una variable que referencie a un Alumno con DNI “34678904” y
        //nombre “Pepe”.
        Alumno pepe = new Alumno("Pepe", "34678904");

        //Declara una variable que referencie a un Alumno con DNI “17679456” y
        //nombre “Andrea” con crédito inicial de 125€.
        Alumno andrea = new Alumno("Andrea", "17679456", 125);

        //- Declara una variable que referencie a un curso presencial de título “Diseño de
        //Bases de Datos” con fecha de inicio y fin 5/05/2014. El precio del curso es de
        //50€, la duración es de un día de clase, un cupo de 20 y el número mínimo de
        //asistencias 1.
        LocalDate fecha = LocalDate.of(2014, 5,05);
        CursoPresencial designBD = new CursoPresencial("Diseño de Base de datos", fecha,fecha, 1, 50 , 20 , 1 );

        //Declara una variable que referencie a un curso online de título “Administración
        //de Bases de Datos” con fecha de inicio 12/05/2014 y fin 16/05/2014. El precio
        //del curso es de 25€, la duración es de 5 días, tiene un nivel 4 y como requisito
        //que se haya realizado previamente el curso presencial de “Diseño de Bases de
        //Datos”.
        LocalDate inicio = LocalDate.of(2014,Month.MAY,12);
        LocalDate fin =  LocalDate.of(2014,5,16);

        CursoOnline admBD = new CursoOnline("Administracion de Base de datos", inicio, fin, 5, 25, 4, designBD);

        //Matricula a los dos alumnos en el curso presencial.
        designBD.Matriculacion(pepe);
        designBD.Matriculacion(andrea);

        //Registra la asistencia del alumno “Pepe” en el día 1 del curso presencial.
        designBD.registroAsistencia(1, pepe);

        //Califica a los alumnos del curso presencial y muestra en la consola los
        //alumnos aptos. Sólo debe aparecer “Pepe”.
        designBD.calificar();
        System.out.println("**Alumnos aptos curso presencial:");
        //Forma rapida pero pobre -> System.out.println(designBD.getAlumnosAptos().toString());
        for (Alumno a :designBD.getAlumnosAptos()) {
            System.out.println("    - " + a.toString());
        }

        //Matricula a los dos alumnos en el curso online.
        admBD.Matriculacion(pepe);
        admBD.Matriculacion(andrea);

        //Muestra la lista de los alumnos matriculados en el curso online. Sólo debe
        //aparecer “Pepe”.
        System.out.println("\n**Alumnos matriculados curso online:");
        for (Alumno a :admBD.getAlumnosMatriculados()) {
            System.out.println("    - " + a.toString());
        }

        //Registra en el curso online que “Pepe” ha superado el nivel.
        admBD.superarNivel(pepe);

        //Califica a los alumnos del curso online y muestra en la consola los alumnos
        //aptos. La colección debe estar vacía porque “pepe” está en el nivel 1 y el
        //mínimo es 2.
        admBD.calificar();
        System.out.println("\n**Alumnos aptos curso online:");
        System.out.println(admBD.getAlumnosAptos().toString());

        //Muestra los alumnos matriculados en el curso presencial ordenados utilizando
        //el criterio de ordenación implementado.
        Comparator<Alumno> criterio = new CriterioOrdenacionAlumnosPorDni();
        List<Alumno> listaOrdenada = designBD.getALumnosMatriculadosOrdenados(criterio);
        System.out.println("\n**Alumnos matriculados curso presencial ordenados por dni:");
        for (Alumno a :listaOrdenada ) {
            System.out.println("    - " + a.toString());
        }

    }
}
