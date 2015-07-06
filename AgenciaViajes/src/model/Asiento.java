package model;

public class Asiento {
	
	private int id;
	private String nombre;
	private int tipo;
	private boolean ocupado;
	public static final int DISPONIBLE = 1;
	public static final int NODISPONIBLE = 0;
	
	public Asiento() {
		this.id = 0;
		this.nombre = "";
		this.tipo = 1;
		this.ocupado = false;
	}

	public Asiento(int id, String nombre, int tipo, boolean ocupado) {
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.ocupado = ocupado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public boolean isOcupado() {
		return ocupado;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}
	
	@Override
	public String toString() {
		return "Butaca " + id + " " + nombre + " " + (isOcupado()?"Ocupado":"Disponible");
	}
}
