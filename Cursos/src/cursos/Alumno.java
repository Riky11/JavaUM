package cursos;

import java.util.HashSet;
import java.util.Set;

public class Alumno {
	// Atributos
	private final String nombre;
	private final String dni;
	private int credito;
	private Set<Curso> cursosMatriculados;
	
	// Constructores
	public Alumno(String nombre, String dni) {
		this.nombre = nombre;
		this.dni = dni;
		this.credito = 100;
		this.cursosMatriculados = new HashSet<Curso>();
	}
	
	// Consultas de los atributos
	public Alumno(String nombre, String dni, int credito) {
		this.nombre = nombre;
		this.dni = dni;
		this.credito = credito;
		this.cursosMatriculados = new HashSet<Curso>();
	}

	public String getNombre() {
		return nombre;
	}

	public String getDni() {
		return dni;
	}

	public int getCredito() {
		return credito;
	}

	public Set<Curso> getCursosMatriculados() {
		return cursosMatriculados;
	}

	// Incrementar credito
	public void IncrementarCredito(int cantidad) {
		this.credito += cantidad; 
	}
	
	// Decrementar credito
	public void DecrementarCredito(int cantidad) {
		this.credito -= cantidad; 
	}
	
	public void AddCurso(Curso c) {
		this.cursosMatriculados.add(c);
	}

	@Override
	public String toString() {
		return "Alumno: "+ this.nombre + "\ndni:" + this.dni + "\ncredito: " + this.credito +
				"\nNumero cursos matriculado" + this.cursosMatriculados.size();
	}
}
