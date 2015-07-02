package model;

import java.util.Date;

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
	public Integer getId() {
		return vuelo.getId();
	}
	public void setId(Integer id) {
		vuelo.setId(id);
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
	
	//METODO PARA CONVERTIR DE VUELOCOMPLETO A VUELO
	public Vuelo toVuelo() {
		return new Vuelo(this.getId(), this.getAv_id(), this.getDes_id(), this.getFvuelo(), this.getPrecio());
	}

}
