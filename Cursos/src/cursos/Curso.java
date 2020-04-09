package cursos;

import java.time.LocalDate;
import java.util.*;


/**
 *La clase Curso representa los cursos que pueden realizar los alumnos.
 *(implementa la interfaz Cloneable para que se puedas hacer copia de los
 * objetos Curso)
 */
public abstract class Curso implements Cloneable {
	private String titulo;
	private LocalDate inicio;
	private LocalDate finalizacion;
	private int numDias;
	private int precioMatricula;
	private HashSet<Alumno> alumnosMatriculados;
	private HashSet<Alumno> alumnosAptos;
	private int numMatricludaos;

	//Constructor:

	/**
	 * Constructor que inicializa el curso con el titulo, fecha de inicio y finalizacion, el numero de dias,
	 * y el precio de la matricula.
	 * @param titulo representa el nombre del curso
	 * @param inicio fecha de inicio del curso
	 * @param finalizacion fecha de finalizacion del curso
	 * @param numDias numero de dias que dura el curso
	 * @param precioMatricula precio de la matricula del curso
	 */
	public Curso(String titulo, LocalDate inicio, LocalDate finalizacion, int numDias, int precioMatricula) {
		this.titulo = titulo;
		this.inicio = inicio;
		this.finalizacion = finalizacion;
		this.numDias = numDias;
		this.precioMatricula = precioMatricula;
		this.alumnosMatriculados = new HashSet<Alumno>();
		this.alumnosAptos = new HashSet<Alumno>();
		this.numMatricludaos = 0;
	}

	//Metodos de consulta:

	/**
	 * Metodo de consulta del titulo del curso
	 * @return devuelve el nombre del curso
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Metodo de consulta la fecha de inicio del curso
	 * @return devuelve fecha de inicio
	 */
	public LocalDate getInicico() {
		return this.inicio;
	}

	/**
	 * Metodo de consulta la fecha de finalizacion del curso
	 * @return devuelve fecha de finalizacion
	 */
	public LocalDate getFinalizacion() {
		return finalizacion;
	}

	/**
	 * Metodo de consulta el numero de dias del curso
	 * @return devuelve el numero de dias del curso
	 */
	public int getNumDias() {
		return numDias;
	}

	/**
	 * Metodo de consulta del precio de la matricula
	 * @return devuelve el precio de la matricula
	 */
	public int getPrecioMatricula() {
		return precioMatricula;
	}

	/**
	 * Metodo de consulta de los alumnos matriculados
	 * @return devuelve el conjunto de alumnos matriculados en el curso
	 */
	public Set<Alumno> getAlumnosMatriculados() {
		//Forma 1 - Hacer una copia con clone
		HashSet<Alumno> copia = (HashSet<Alumno>) this.alumnosMatriculados.clone();
		return copia;
		//alternativa la coleccion no modificable ->  return Collections.unmodifiableList(alumnosMatriculados);
	}

	/**
	 * Metodo de consulta de los alumnos aptos
	 * @return devuelve el conjunto de alumnos aptos del curso
	 */
	public Set<Alumno> getAlumnosAptos() {
		//Forma 2 - Hacer una copia constructor
		HashSet<Alumno> copia = new HashSet<Alumno>(this.alumnosAptos);
		return copia;
		//alternativa la coleccion no modificable ->  return Collections.unmodifiableList(alumnosAptos);
	}

	/**
	 * Metodo de consulta del numero de alumnos matriculados
	 * @return devuelve el numero de alumnos matriculados en el curso
	 */
	public int getNumMatricludaos() {
		return numMatricludaos;
	}
	
	//Funciones:

	/**
	 * Metodo que indica si un curso ha terminado
	 * @return devuelve true si el ha terminado o false en caso contrario.
	 */
	public boolean Haterminado() {
		LocalDate actual = LocalDate.now();
		if(actual.compareTo(finalizacion) > 0) {
			return true;
		}
		return false;
	}

	/**
	 * Metodo abstracto que indica si un alumno es apto. Esto depende de cada tipo de curso.
	 * @param a alumno sobre el que se quiere consultar.
	 * @return devuelve true si el alumno es apto o false en caso contrario.
	 */
	public abstract boolean EsApto(Alumno a);

	/**
	 * Metodo que permite a un alumno matricularse en el curso si cumple los siguientes requisitos:
	 * 	- Tiene el credito suficiente.
	 * @param a alumno que se quiere matricular
	 * @return devuelve true si el alumno se ha podido matricular o false en caso contrario.
	 */
	public boolean Matriculacion(Alumno a) {
		if(a.getCredito() > this.precioMatricula) {
			a.DecrementarCredito(this.precioMatricula);
			this.alumnosMatriculados.add(a);
			this.numMatricludaos++;
			a.AddCurso(this);
			return true;
		}
		return false;
	}

	/**
	 * Metodo que califica a los alumnos matriculados para saber los que son aptos
	 * una vez que ha finalizado el curso.
	 * @return devuelve true si se ha realizado la calificacion o o false en caso contrario.
	 */
	protected  boolean calificar(){
		if(this.Haterminado()){
			for (Alumno a : this.getAlumnosMatriculados()) {
				if (this.EsApto(a)) this.alumnosAptos.add(a);
			}
			return true;
		}
		return false;
	}

	/**
	 * Metodo que ordena los alumnos matriculados segun un criterio de ordenacion.
	 * @param criterio criterio por el cual se ordenan los alumnos
	 * @return devuelve una lista ordenada de los alumnos matriculados.
	 */
	public List<Alumno> getALumnosMatriculadosOrdenados(Comparator<Alumno> criterio){
		List<Alumno> listaOrdenada = new ArrayList<Alumno>();
		listaOrdenada.addAll(this.getAlumnosMatriculados());
		Collections.sort(listaOrdenada, criterio);
		return listaOrdenada;
	}

	/**
	 * Metodo que realiza la copia superficial del metodo clone() de Object.
	 * La copia superficial que tiene los mismos valores en los atributos y apunta a los
	 * mismos objetos que el original.
	 * @return devuelve la copia superficial obtenida del metodo clone() de Object pero
	 * de tipo Curso o null en caso de que no se pueda realizar la copia.
	 */
	//Ponemos el metodo en protected porque solo nos interesa que se vea en el las clases del paquete.
	protected Curso copiaSuperficial(){
		//Este codigo tambia lo podemos poner dentro del metodo clone()
		try {
			Curso copiaSuperficial = (Curso) super.clone();
			return  copiaSuperficial;
		} catch (CloneNotSupportedException e) {
			System.err.println("\nError: La clase no es clonable\n");
		}
		return null;
	}

	/**
	 * Metodo clone() adaptado para que realice una copia de los cursos en la que
	 * se hace primero una copia superficial del curso y despues modificamos la lista
	 * de alumnos matriculados, aptos y el numero de matriculados para que no apunten
	 * a las listas del curso original y esten vacias.
	 * @return retorna una copia del curso de tipo Curso
	 */
	//Ponemos el metodo public para que se puedan hacer copias cundo se implemente la clase Curso
	//Cambios respecto al metodo clone() redefinido de object:
	//-	Elevaríamos la visibilidad de protected (visibilidad declarada en la clase Object) a public.
	//-	Cambiaríamos el tipo de retorno para que sea el de la clase (regla covariante).
	@Override
	public Curso clone() {
		//Hacemos la copia superficial
		Curso copia = this.copiaSuperficial();
		//Modificamos los valores que nos interesan de la copia
		copia.numMatricludaos=0;
		copia.alumnosMatriculados = new HashSet<Alumno>();
		copia.alumnosAptos = new HashSet<Alumno>();
		return copia;
	}
}
