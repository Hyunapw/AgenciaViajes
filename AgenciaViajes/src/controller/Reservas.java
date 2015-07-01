package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.sql.rowset.CachedRowSet;

import util.Utilidades;
import controller.BDD;
import model.Reserva;

public class Reservas extends BDD {
	
	/*	CREATE TABLE reservas (
    res_id      INTEGER PRIMARY KEY AUTOINCREMENT
                        NOT NULL,
    res_vu_id   INTEGER NOT NULL
                        REFERENCES vuelos (vu_id),
    res_cli_id  INTEGER REFERENCES clientes (cli_id) 
                        NOT NULL,
    res_asiento INTEGER NOT NULL
);*/
	
	public ArrayList<Reserva> recuperaPorFiltro(String filtro) {
		String sql = "SELECT * FROM reservas WHERE ";
		sql += filtro == null || filtro.length() == 0 ? "1" : filtro;
		sql += " ORDER BY reservas.res_id";
		ArrayList<Reserva> lista = null;
		CachedRowSet rs = consultaSQL(sql);
		if (rs!=null){
			try {
				lista = new ArrayList<>();
				while (rs.next() == true) {
					int id = rs.getInt("res_id");
					int vu_id = rs.getInt("res_vu_id");
					int cli_id = rs.getInt("res_cli_id");
					int asiento = rs.getInt("res_asiento");
					lista.add(new Reserva(id, vu_id, cli_id, asiento));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}
	
	public ArrayList<Reserva> recuperaTodos(){
		return recuperaPorFiltro(null);
	}
	
	public Reserva recuperaPorId(int id){
		if (id != 0) {
			String filtro = "reservas.res_id = " + id;
			ArrayList<Reserva> lista = recuperaPorFiltro(filtro);
			return lista.get(0);
		} else {
			Reserva c = new Reserva();
			c.setId(0);
			return c;
		}
	}
	
	public Integer grabar(Reserva res) {
		String sql = null;
		if (res.getId()==null) {
			/*SQLite*/
			sql = "INSERT INTO reservas (res_vu_id, res_cli_id, res_asiento) VALUES ("+ res.getVu_id() + "," + res.getCli_id() + "," + res.getAsiento() + ")";
		} else {		
			sql = "UPDATE reservas SET " +
					"res_vu_id=" + res.getVu_id() + ", res_cli_id=" + res.getCli_id() + ", res_asiento=" + res.getAsiento()+ " WHERE reservas.res_id=" + res.getId()
					;
		}
		return ejecutaSQL(sql);
	}
	
	/*
	 * RECUPERAR TABLAS ESPECIALES
	 * */
	public ArrayList<Vector<Object>> recuperaTablaClientes(String txtFiltro) {
		ArrayList<Vector<Object>> tableData = null;
		ArrayList<String> filtros = new ArrayList<>();
		filtros.add("reservas.res_id LIKE '%" + txtFiltro + "%'");
		String filtro = Utilidades.creaFiltroOR(filtros);
		ArrayList<Reserva> lista = recuperaPorFiltro(filtro);
		if (lista!=null) {
			tableData = new ArrayList<>();
			for (Reserva reserva : lista) {
				Vector<Object> filaData = new Vector<>();
				filaData.add(reserva);
				filaData.add(reserva.getAsiento());
				tableData.add(filaData);
			}
		}
		return tableData;
	}
	
}
