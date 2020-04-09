package cursos;

import java.util.HashSet;
import java.util.Set;

/**
 * La clase alumno representa la persona que puede realizar un curso.
 */
public class Alumno{
	private final String nombre;
	private final String dni;
	private int credito;
	private Set<Curso> cursosMatriculados;


	/**
	 * Constructor que inicliaza el Alumno con su nombre y dni
	 * @param nombre nombre del Alumno
	 * @param dni dni del alumno
	 */
	public Alumno(String nombre, String dni) {
		this.nombre = nombre;
		this.dni = dni;
		this.credito = 100;
		this.cursosMatriculados = new HashSet<Curso>();
	}

	/**
	 * Constructor que inicliaza el Alumno con su nombre, dni y credito
	 * @param nombre nombre del Alumno
	 * @param dni dni del alumno
	 * @param credito valor inicial del credito del Alumno
	 */
	public Alumno(String nombre, String dni, int credito) {
		this.nombre = nombre;
		this.dni = dni;
		this.credito = credito;
		this.cursosMatriculados = new HashSet<Curso>();
	}

	/**
	 * Metodo de consulta del nombre del Alumno
	 * @return devuelve el nombre del Alumno
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo de consulta del dni del Alumno
	 * @return devuelve el dni del Alumno
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Metodo de consulta del credito del Alumno
	 * @return devuelve el credito del Alumno
	 */
	public int getCredito() {
		return credito;
	}

	/**
	 * Metodo de consulta de los cursos en los que se ha matriculado el alumno.
	 * @return devuelve el cojunto de cursos
	 */
	public Set<Curso> getCursosMatriculados() {
		return cursosMatriculados;
	}


	/**
	 * Metodo que incrementa la cantidad recibida por parametro al credito actual del alumno.
	 * @param cantidad valor que se va a incrementar al credito del alumno.
	 */
	public void IncrementarCredito(int cantidad) {
		this.credito += cantidad; 
	}

	/**
	 * Metodo que decrementa la cantidad recibida por parametro al credito actual del alumno.
	 * @param cantidad valor que se va a decrementar al credito del alumno.
	 */
	public void DecrementarCredito(int cantidad) {
		this.credito -= cantidad; 
	}

	/**
	 * Metodo que añade un curso al cojunto de cursos matriculados del alumno.
	 * @param c curso en el que alumno se ha matriculado.
	 */
	public void AddCurso(Curso c) {
		this.cursosMatriculados.add(c);
	}

	/**
	 * Metodo toString() de la clase alumno que define al alumno mediante un string.
	 * @return devuelve una cadena que define al alumno con su nombre, dni, credito y
	 * numero de cursos en los que se ha matricula.
	 */
	@Override
	public String toString() {
		return "Alumno: "+ this.nombre + ", dni:" + this.dni + ", credito: " + this.credito +
				", Numero cursos matriculado: " + this.cursosMatriculados.size();
	}

}
