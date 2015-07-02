package model;

import java.util.Date;

import util.Utilidades;

public class VueloCompleto_OLD {

	/*
	 * CREATE TABLE vuelos ( vu_id INTEGER PRIMARY KEY AUTOINCREMENT, vu_av_id
	 * INTEGER NOT NULL REFERENCES aviones (av_id), vu_des_id INTEGER NOT NULL
	 * REFERENCES destinos (des_id), vu_fvuelo DATE NOT NULL, vu_precio DECIMAL
	 * (7, 2) NOT NULL );
	 */

	Integer id;
	Avion avion;
	Destino destino;
	Date fvuelo;
	double precio;

	public VueloCompleto_OLD() {
	}

	public VueloCompleto_OLD(Integer id, Avion avion, Destino destino, Date fvuelo,
			double precio) {
		this.id = id;
		this.avion = avion;
		this.destino = destino;
		this.fvuelo = fvuelo;
		this.precio = precio;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Avion getAvion() {
		return avion;
	}

	public void setAvion(Avion avion) {
		this.avion = avion;
	}

	public Integer getAv_id() {
		return avion.getId();
	}

	public void setAv_id(Integer id) {
		avion.setId(id);
	}
	
	public Destino getDestino() {
		return destino;
	}

	public void setDestino(Destino destino) {
		this.destino = destino;
	}

	public Date getFvuelo() {
		return fvuelo;
	}
	
	public Integer getDes_id() {
		return destino.getId();
	}

	public void setDes_id(Integer id) {
		destino.setId(id);
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

	public void setModelo(String modelo) {
		avion.setModelo(modelo);
	}

	public void setCapacidad(int capacidad) {
		avion.setCapacidad(capacidad);
	}

	public String getLugar() {
		return destino.getLugar();
	}

	public void setLugar(String lugar) {
		destino.setLugar(lugar);
	}

	public String getModelo() {
		return avion.getModelo();
	}

	public int getCapacidad() {
		return avion.getCapacidad();
	}

	@Override
	public String toString() {
		return Utilidades.fechaAstring(fvuelo) + " Vuelo " + id + " a " + getLugar();
	}

	public Vuelo toVuelo() {
		return new Vuelo(this.getId(), this.getAv_id(),  this.getDes_id(), this.getFvuelo(), this.getPrecio());
	}

}
