package model;

import java.util.Date;

import util.Utilidades;

public class Vuelo {
	
/*	CREATE TABLE vuelos (
		    vu_id     INTEGER        PRIMARY KEY AUTOINCREMENT,
		    vu_av_id  INTEGER        NOT NULL
		                             REFERENCES aviones (av_id),
		    vu_des_id INTEGER        NOT NULL
		                             REFERENCES destinos (des_id),
		    vu_fvuelo DATE           NOT NULL,
		    vu_precio DECIMAL (7, 2) NOT NULL
		);*/
	
	Integer vuId;
	int av_id;
	int des_id;
	Date fvuelo;
	double precio;
	
	public Vuelo() {
	}

	public Vuelo(Integer id, int av_id, int des_id, Date fvuelo, double precio) {
		this.vuId = id;
		this.av_id = av_id;
		this.des_id = des_id;
		this.fvuelo = fvuelo;
		this.precio = precio;
	}

	public Integer getVuId() {
		return vuId;
	}

	public void setVuId(Integer id) {
		this.vuId = id;
	}

	public int getAv_id() {
		return av_id;
	}

	public void setAv_id(int av_id) {
		this.av_id = av_id;
	}

	public int getDes_id() {
		return des_id;
	}

	public void setDes_id(int des_id) {
		this.des_id = des_id;
	}

	public Date getFvuelo() {
		return fvuelo;
	}

	public void setFvuelo(Date fvuelo) {
		this.fvuelo = fvuelo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Vuelo " + vuId + " (A" + av_id + "D"
				+ des_id + " del " + Utilidades.fechaHoraToSQLite(fvuelo);
	}
	
	
	
}
