package cursos;

import java.util.Calendar;
import java.util.Date;


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
        Calendar calendario = Calendar.getInstance();
        calendario.set(2014,Calendar.MAY,05);
        Date fecha = calendario.getTime();
        CursoPresencial designBD = new CursoPresencial("Diseño de Base de datos",
                fecha,fecha, 1, 50 ,
                20 , 1 );

        //Declara una variable que referencie a un curso online de título “Administración
        //de Bases de Datos” con fecha de inicio 12/05/2014 y fin 16/05/2014. El precio
        //del curso es de 25€, la duración es de 5 días, tiene un nivel 4 y como requisito
        //que se haya realizado previamente el curso presencial de “Diseño de Bases de
        //Datos”.
        calendario.set(2014,Calendar.MAY,12);
        Date inicio = calendario.getTime();
        calendario.set(2014,Calendar.MAY,16);
        Date fin =  calendario.getTime();
        CursoOnline admBD = new CursoOnline("Administracion de Base de datos", inicio, fin,
                5, 25, 4, designBD);

        //Matricula a los dos alumnos en el curso presencial.
        designBD.Matriculacion(pepe);
        designBD.Matriculacion(andrea);

        //Registra la asistencia del alumno “Pepe” en el día 1 del curso presencial.
        designBD.registroAsistencia(1, pepe);

        //Califica a los alumnos del curso presencial y muestra en la consola los
        //alumnos aptos. Sólo debe aparecer “Pepe”.
        designBD.calificar();
        System.out.println("**Alumnos aptos curso presencial:");
        System.out.println(designBD.getAlumnosAptos().toString());



        //Matricula a los dos alumnos en el curso online.
        admBD.Matriculacion(pepe);
        admBD.Matriculacion(andrea);

        //Muestra la lista de los alumnos matriculados en el curso online. Sólo debe
        //aparecer “Pepe”.
        System.out.println("\n**Alumnos matriculados curso online:");
        System.out.println(admBD.getAlumnosMatriculados().toString());

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
        System.out.println("\n**Alumnos matriculados curso presencial ordenados:");
        System.out.println(designBD.getAlumnosMatriculados().toString());


    }
}
