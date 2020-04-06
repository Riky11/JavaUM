package cursos;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;



public abstract class Curso {
	private String titulo;
	private Date inicico;
	private Date finalizacion;
	private int numDias;
	private int precioMatricula;
	private Set<Alumno> alumnosMatriculados;
	private Set<Alumno> alumnosAptos;
	private int numMatricludaos;
	
	public Curso(String titulo, Date inicio, Date finalizacion, int numDias, int preciMatricula) {
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

	public Date getInicico() {
		return inicico;
	}

	public Date getFinalizacion() {
		return finalizacion;
	}

	public int getNumDias() {
		return numDias;
	}

	public int getPrecioMatricula() {
		return precioMatricula;
	}

	public Set<Alumno> getAlumnosMatriculados() {
		return alumnosMatriculados;
	}

	public Set<Alumno> getAlumnosAptos() {
		return alumnosAptos;
	}

	public int getNumMatricludaos() {
		return numMatricludaos;
	}
	
	// Funciones
	public boolean Haterminado() {
		Date actual = new Date();
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
