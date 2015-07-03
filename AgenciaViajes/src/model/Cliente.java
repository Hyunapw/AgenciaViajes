package model;

public class Cliente {
	
	private Integer cliId;
	private String nombre;
	
	public Cliente() {
	}

	public Cliente(Integer id, String nombre) {
		this.cliId = id;
		this.nombre = nombre;
	}
	
	public Integer getCliId() {
		return cliId;
	}
	public void setCliId(Integer id) {
		this.cliId = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Cliente " + cliId + ", " + nombre + "";
	}
}
