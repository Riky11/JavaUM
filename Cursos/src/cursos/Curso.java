package cursos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


public abstract class Curso  {
	private String titulo;
	private LocalDate inicico;
	private LocalDate finalizacion;
	private int numDias;
	private int precioMatricula;
	private HashSet<Alumno> alumnosMatriculados;
	private HashSet<Alumno> alumnosAptos;
	private int numMatricludaos;
	
	public Curso(String titulo, LocalDate inicio, LocalDate finalizacion, int numDias, int preciMatricula) {
		this.titulo = titulo;
		this.inicico = inicio;
		this.finalizacion = finalizacion;
		this.numDias = numDias;
		this.precioMatricula = preciMatricula;
		this.alumnosMatriculados = new HashSet<Alumno>();
		this.alumnosAptos = new HashSet<Alumno>();
		this.numMatricludaos = 0;
	}
	
	//Metodos para consultar propiedades
	public String getTitulo() {
		return titulo;
	}

	public LocalDate getInicico() {
		return inicico;
	}

	public LocalDate getFinalizacion() {
		return finalizacion;
	}

	public int getNumDias() {
		return numDias;
	}

	public int getPrecioMatricula() {
		return precioMatricula;
	}

	public Set<Alumno> getAlumnosMatriculados() {
		//Forma 1 - Hacer una copia con clone
		HashSet<Alumno> copia = (HashSet<Alumno>) alumnosMatriculados.clone();
		return copia;
		//alternativa la coleccion no modificable ->  return Collections.unmodifiableList(pujas);
	}

	public Set<Alumno> getAlumnosAptos() {
		//Forma 2 - Hacer una copia constructor
		HashSet<Alumno> copia = new HashSet<Alumno>(this.alumnosAptos);
		return copia;
		//alternativa la coleccion no modificable ->  return Collections.unmodifiableList(pujas);
	}

	public int getNumMatricludaos() {
		return numMatricludaos;
	}
	
	// Funciones
	public boolean Haterminado() {
		LocalDate actual = LocalDate.now();
		if(actual.compareTo(finalizacion) > 0) {
			return true;
		}
		return false;
	}

	public abstract boolean EsApto(Alumno a);
	
	public boolean Matriculacion(Alumno a) {
		if(a.getCredito() > this.precioMatricula) {
			a.DecrementarCredito(this.precioMatricula);
			this.alumnosMatriculados.add(a);
			a.AddCurso(this);
			return true;
		}
		return false;
	}
	
	protected  boolean calificar(){
		if(this.Haterminado()){
			for (Alumno a : this.getAlumnosMatriculados()) {
				if (this.EsApto(a)) this.alumnosAptos.add(a);
			}
			return true;
		}
		return false;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
