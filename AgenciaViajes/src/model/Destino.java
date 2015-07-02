package model;

public class Destino {
	
//	CREATE TABLE destinos (
//		    des_id    INTEGER  PRIMARY KEY AUTOINCREMENT
//		                       NOT NULL,
//		    des_lugar TEXT (0) NOT NULL
//		);
	
	Integer id;
	String lugar;
	
	public Destino() {
	}

	public Destino(Integer id, String lugar) {
		this.id = id;
		this.lugar = lugar;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	@Override
	public String toString() {
		return "Destino " + id + ", " + lugar;
	}

	
	
	
}
