package model;

public class Avion {
	
//	CREATE TABLE aviones (
//		    av_id        INTEGER      PRIMARY KEY ON CONFLICT ROLLBACK AUTOINCREMENT
//		                              NOT NULL,
//		    av_modelo    VARCHAR (40) NOT NULL,
//		    av_capacidad INTEGER      NOT NULL
//		);

	Integer id;
	String modelo;
	int capacidad;
	
	public Avion() {
		super();
	}
	
	public Avion(Integer id, String modelo, int capacidad) {
		super();
		this.id = id;
		this.modelo = modelo;
		this.capacidad = capacidad;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	@Override
	public String toString() {
		return "Avion " + id + " " + modelo + " (" + capacidad + " plazas)";
	}
	
}
