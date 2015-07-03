package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.sql.rowset.CachedRowSet;

import util.Utilidades;
import model.Cliente;
import model.Reserva;
import model.ReservaCompleta;
import model.Vuelo;

public class ReservasCompletas extends Reservas {
	
	/*	CREATE TABLE reservas (
    res_id      INTEGER PRIMARY KEY AUTOINCREMENT
                        NOT NULL,
    res_vu_id   INTEGER NOT NULL
                        REFERENCES vuelos (vu_id),
    res_cli_id  INTEGER REFERENCES clientes (cli_id) 
                        NOT NULL,
    res_asiento INTEGER NOT NULL
);*/
	
	public ArrayList<ReservaCompleta> recuperaReservaCompletaPorFiltro(String filtro) {
		String sql = "SELECT * FROM reservas WHERE ";
		sql += filtro == null || filtro.length() == 0 ? "1" : filtro;
		sql += " ORDER BY reservas.res_id";
		ArrayList<ReservaCompleta> lista = null;
		CachedRowSet rs = consultaSQL(sql);
		if (rs!=null){
			try {
				lista = new ArrayList<>();
				while (rs.next() == true) {
					int id = rs.getInt("res_id");
					int vu_id = rs.getInt("res_vu_id");
					int cli_id = rs.getInt("res_cli_id");
					int asiento = rs.getInt("res_asiento");
					Reserva reserva = new Reserva(id, vu_id, cli_id, asiento);
					Vuelo vuelo = new Vuelos().recuperaPorId(vu_id);
					Cliente cliente = new Clientes().recuperaPorId(cli_id);
					lista.add(new ReservaCompleta(reserva, vuelo, cliente));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}
	
	/*
	 * RECUPERAR TABLAS ESPECIALES
	 * */
	public ArrayList<Vector<Object>> recuperaTablaReservasCompletas(String txtFiltro) {
		ArrayList<Vector<Object>> tableData = null;
		ArrayList<String> filtros = new ArrayList<>();
		filtros.add("reservas.res_id LIKE '%" + txtFiltro + "%'");
		String filtro = Utilidades.creaFiltroOR(filtros);
		ArrayList<ReservaCompleta> lista = recuperaReservaCompletaPorFiltro(filtro);
		if (lista!=null) {
			tableData = new ArrayList<>();
			for (ReservaCompleta reserva : lista) {
				Vector<Object> filaData = new Vector<>();
				filaData.add(reserva);
				filaData.add(reserva.getAsiento());
				tableData.add(filaData);
			}
		}
		return tableData;
	}
	
}
