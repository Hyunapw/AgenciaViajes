package model;

public class Reserva {
	
/*	CREATE TABLE reservas (
		    res_id      INTEGER PRIMARY KEY AUTOINCREMENT
		                        NOT NULL,
		    res_vu_id   INTEGER NOT NULL
		                        REFERENCES vuelos (vu_id),
		    res_cli_id  INTEGER REFERENCES clientes (cli_id) 
		                        NOT NULL,
		    res_asiento INTEGER NOT NULL
		);*/
	
	Integer id;
	int vu_id;
	int cli_id;
	int asiento;
	
	public Reserva() {
	}

	public Reserva(Integer id, int vu_id, int cli_id, int asiento) {
		this.id = id;
		this.vu_id = vu_id;
		this.cli_id = cli_id;
		this.asiento = asiento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getVu_id() {
		return vu_id;
	}

	public void setVu_id(int vu_id) {
		this.vu_id = vu_id;
	}

	public int getCli_id() {
		return cli_id;
	}

	public void setCli_id(int cli_id) {
		this.cli_id = cli_id;
	}

	public int getAsiento() {
		return asiento;
	}

	public void setAsiento(int asiento) {
		this.asiento = asiento;
	}

}
