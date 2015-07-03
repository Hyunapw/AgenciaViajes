package model;

import java.util.Date;

public class ReservaCompleta {

	private Reserva reserva;
	private Vuelo vuelo;
	private Cliente cliente;
	
	public ReservaCompleta() {
	}

	public ReservaCompleta(Reserva reserva, Vuelo vuelo, Cliente cliente) {
		this.reserva = reserva;
		this.vuelo = vuelo;
		this.cliente = cliente;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Vuelo getVuelo() {
		return vuelo;
	}

	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getResId() {
		return reserva.getResId();
	}

	public void setResId(Integer id) {
		reserva.setResId(id);
	}

	public int getVu_id() {
		return reserva.getVu_id();
	}

	public void setVu_id(int vu_id) {
		reserva.setVu_id(vu_id);
	}

	public int getCli_id() {
		return reserva.getCli_id();
	}

	public void setCli_id(int cli_id) {
		reserva.setCli_id(cli_id);
	}

	public int getAsiento() {
		return reserva.getAsiento();
	}

	public void setAsiento(int asiento) {
		reserva.setAsiento(asiento);
	}

	public Integer getVuId() {
		return vuelo.getVuId();
	}

	public void setVuId(Integer id) {
		vuelo.setVuId(id);
	}

	public int getAv_id() {
		return vuelo.getAv_id();
	}

	public void setAv_id(int av_id) {
		vuelo.setAv_id(av_id);
	}

	public int getDes_id() {
		return vuelo.getDes_id();
	}

	public void setDes_id(int des_id) {
		vuelo.setDes_id(des_id);
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

	public Integer getCliId() {
		return cliente.getCliId();
	}

	public void setCliId(Integer id) {
		cliente.setCliId(id);
	}

	public String getNombre() {
		return cliente.getNombre();
	}

	public void setNombre(String nombre) {
		cliente.setNombre(nombre);
	}

	@Override
	public String toString() {
		return "Reserva " + reserva + " de " + cliente
				+ " en " + vuelo;
	}
	
}
