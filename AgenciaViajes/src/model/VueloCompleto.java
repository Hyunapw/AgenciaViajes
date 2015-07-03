package model;

import java.util.Date;

import util.Utilidades;

public class VueloCompleto {
	
	Vuelo vuelo;
	Avion avion;
	Destino destino;
	
	public VueloCompleto() {
	}

	public VueloCompleto(Vuelo vuelo, Avion avion, Destino destino) {
		this.vuelo = vuelo;
		this.avion = avion;
		this.destino = destino;
	}
	
	public VueloCompleto(Integer id, Avion avion, Destino destino, Date fvuelo, double precio) {
		this.avion = avion;
		this.destino = destino;
		this.vuelo = new Vuelo(id, avion.getId(), destino.getId(), fvuelo, precio);
	}
	
	//VUELO
	public Vuelo getVuelo() {
		return vuelo;
	}
	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}
	public Integer getVuId() {
		return vuelo.getVuId();
	}
	public void setVuId(Integer id) {
		vuelo.setVuId(id);
	}
	public Date getFvuelo() {
		return vuelo.getFvuelo();
	}
	public void setFvuelo(Date fvuelo) {
		vuelo.setFvuelo(fvuelo);
	}
	public double getPrecio() {
		return vuelo.getPrecio();
	}
	public void setPrecio(double precio) {
		vuelo.setPrecio(precio);
	}
	//EL ID DEL AVION ESTA EN EL VUELO Y EN EL AVION
	public int getAv_id() {
		return vuelo.getAv_id();
	}
	public void setAv_id(int av_id) {
		vuelo.setAv_id(av_id);
	}
	//EL ID DEL DESTINO ESTA EN EL VUELO Y EN EL DESTINO
	public int getDes_id() {
		return vuelo.getDes_id();
	}
	public void setDes_id(int des_id) {
		vuelo.setDes_id(des_id);
	}

	//AVION
	public Avion getAvion() {
		return avion;
	}
	public void setAvion(Avion avion) {
		this.avion = avion;
	}
	public String getModelo() {
		return avion.getModelo();
	}
	public void setModelo(String modelo) {
		avion.setModelo(modelo);
	}
	public int getCapacidad() {
		return avion.getCapacidad();
	}
	public void setCapacidad(int capacidad) {
		avion.setCapacidad(capacidad);
	}

	//DESTINO
	public Destino getDestino() {
		return destino;
	}
	public void setDestino(Destino destino) {
		this.destino = destino;
	}
	public String getLugar() {
		return destino.getLugar();
	}
	public void setLugar(String lugar) {
		destino.setLugar(lugar);
	}

	@Override
	public String toString() {
		return Utilidades.fechaHoraToSQLite(getFvuelo()) + " Vuelo " + getVuId() + " a " + getLugar() + " en " + getModelo();
	}

}
