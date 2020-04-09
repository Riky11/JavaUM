package cursos;

import java.time.LocalDate;
import java.time.Month;

//Programa que prueba todos los metodos y las copias de los objetos Curso
public class Programa2 {
    public static void main(String[] args) {
        //Creamos tres alumnos
        Alumno pepe = new Alumno("Pepe", "34678904");
        Alumno juan = new Alumno("Juan", "19615615",150);
        Alumno pedro = new Alumno("Pedro", "56181982", 300);

        //Creamos un curso presencial
        LocalDate fecha = LocalDate.of(2014, 5,5);
        CursoPresencial c1 = new CursoPresencial("Diseño de Base de datos", fecha,fecha, 1, 50 , 20 , 1 );

        //Creamos un curso online
        LocalDate inicio = LocalDate.of(2014, Month.MAY,12);
        LocalDate fin =  LocalDate.of(2014,5,16);
        CursoOnline c2 = new CursoOnline("Administracion de Base de datos", inicio, fin, 5, 25, 4, c1);

        System.out.println("\n------------------------------------------------------------");
        System.out.println("\nINFORMACION CURSO PRESENCIAL (" + c1.getTitulo() +  "):" );
        //Matriculamos a los dos alumnos en el curso presencial.
        c1.Matriculacion(pepe);
        c1.Matriculacion(juan);
        c1.Matriculacion(pedro);

        //Muestra la lista de los alumnos matriculados en el curso presencial.
        System.out.println("\n**Alumnos matriculados curso presencial:");
        for (Alumno a :c1.getAlumnosMatriculados()) {
            System.out.println("    - " + a.toString());
        }

        //Registramos la asistencia de los alumnos en el dia 1.
        c1.registroAsistencia(1, pepe);
        c1.registroAsistencia(1, juan);

        //Califica a los alumnos del curso presencial
        c1.calificar();

        //Mostramos a los alumno aptos en el curso presencial
        System.out.println("\n**Alumnos aptos curso presencial:");
        for (Alumno a :c1.getAlumnosAptos()) {
            System.out.println("    - " + a.toString());
        }

        //Mostramos las plazas libres y el cupo del curso presencial
        System.out.println("\nPlazas libres: " + c1.getPlazas() + ", Cupo: " + c1.getCupo());

        //Numero asistencias de pepe.
        int num = c1.numeroAsistencias(pepe);
        System.out.println("\n**Numero asistencias de pepe:" + num);

        System.out.println("\n------------------------------------------------------------");
        System.out.println("\nINFORMACION CURSO ONLINE (" + c2.getTitulo() +  "):" );

        //Muestra los cursos necesarios para hacer el curso online
        System.out.println("\n**Cursos previos que necesitas:");
        for(Curso c : c2.getCursoPrevios()){
            System.out.println("    - " + c.getTitulo());
        }

        //Matricula a los dos alumnos en el curso online.
        c2.Matriculacion(pepe);
        c2.Matriculacion(juan);
        c2.Matriculacion(pedro);

        //Muestra la lista de los alumnos matriculados en el curso online.
        System.out.println("\n**Alumnos matriculados curso online:");
        for (Alumno a :c2.getAlumnosMatriculados()) {
            System.out.println("    - " + a.toString());
        }

        //Registra en el curso online que “Pepe” ha superado el nivel.
        c2.superarNivel(pepe);
        c2.superarNivel(juan);
        c2.superarNivel(juan);

        //Califica a los alumnos del curso online y muestra en la consola los alumnos
        //aptos.
        c2.calificar();
        System.out.println("\n**Alumnos aptos curso online:");
        for (Alumno a :c2.getAlumnosAptos()) {
            System.out.println("    - " + a.toString());
        }

        //--------------------------------------------------------------------------------------------------------
        //Ahora vamos a probar las copias de los cursos presencial

        //Copia del curso presencial
        CursoPresencial copiaP = c1.clone();


        System.out.println("\n------------------------------------------------------------");
        System.out.println("\nINFORMACION PRUEBA COPIA CURSO PRESENCIAL + (" + copiaP.getTitulo() +  "):" );

        //Muestra las plazas y el cupo
        System.out.println("\n    - Plazas: " + copiaP.getPlazas() + ", Cupo: " + copiaP.getCupo());
        //Muestra la lista de los alumnos matriculados en el curso COPIA presencial.
        System.out.println("\n**Alumnos matriculados curso COPIA presencial:");
        for (Alumno a :copiaP.getAlumnosMatriculados()) {
            System.out.println("    - " + a.toString());
        }

        //Muestra la lista de los alumnos aptos en el curso COPIA presencial.
        System.out.println("\n**Alumnos aptos curso COPIA presencial:");
        for (Alumno a :copiaP.getAlumnosAptos()) {
            System.out.println("    - " + a.toString());
        }

        //Numero asistencias de pepe.
        int num2 = copiaP.numeroAsistencias(pepe);
        System.out.println("\n**Numero asistencias de pepe:" + num2);

        //--------------------------------------------------------------------------------------------------------
        //Ahora vamos a probar las copias de los cursos online

        //Hacemos una copia del curso
        CursoOnline copiaO = c2.clone();

        System.out.println("\n------------------------------------------------------------");
        System.out.println("\nINFORMACION PRUEBA COPIA CURSO ONLINE + (" + copiaO.getTitulo() +  "):" );

        //Muestra los cursos necesarios para hacer el curso
        System.out.println("\n**Cursos previos que necesitas:");
        for(Curso c : copiaO.getCursoPrevios()){
            System.out.println("    - " + c.getTitulo());
        }

        //Muestra la lista de los alumnos matriculados en el curso COPIA online.
        System.out.println("\n**Alumnos matriculados curso COPIA online:");
        for (Alumno a :copiaO.getAlumnosMatriculados()) {
            System.out.println("    - " + a.toString());
        }

        //Muestra la lista de los alumnos aptos en el curso COPIA online.
        System.out.println("\n**Alumnos aptos curso COPIA online:");
        for (Alumno a :copiaO.getAlumnosAptos()) {
            System.out.println("    - " + a.toString());
        }















    }
}
