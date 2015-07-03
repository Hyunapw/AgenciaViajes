package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.sql.rowset.CachedRowSet;

import util.Utilidades;
import controller.BDD;
import model.Vuelo;

public class Vuelos extends BDD {
	
	/*	CREATE TABLE vuelos (
    vu_id     INTEGER        PRIMARY KEY AUTOINCREMENT,
    vu_av_id  INTEGER        NOT NULL
                             REFERENCES aviones (av_id),
    vu_des_id INTEGER        NOT NULL
                             REFERENCES destinos (des_id),
    vu_fvuelo DATE           NOT NULL,
    vu_precio DECIMAL (7, 2) NOT NULL
);*/
	
	public ArrayList<Vuelo> recuperaPorFiltro(String filtro) {
		String sql = "SELECT * FROM vuelos WHERE ";
		sql += filtro == null || filtro.length() == 0 ? "1" : filtro;
		sql += " ORDER BY vuelos.vu_id";
		ArrayList<Vuelo> lista = null;
		CachedRowSet rs = consultaSQL(sql);
		if (rs!=null){
			try {
				lista = new ArrayList<>();
				while (rs.next() == true) {
					int id = rs.getInt("vu_id");
					int av_id = rs.getInt("vu_av_id");
					int des_id = rs.getInt("vu_des_id");
					Date fvuelo = Utilidades.validarFechaHora(rs.getString("vu_fvuelo"));
					double precio = rs.getDouble("vu_precio");;
					lista.add(new Vuelo(id, av_id, des_id, fvuelo, precio));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}
	
	public ArrayList<Vuelo> recuperaTodos(){
		return recuperaPorFiltro(null);
	}
	
	public Vuelo recuperaPorId(int vuId){
		if (vuId != 0) {
			String filtro = "vuelos.vu_id = " + vuId;
			ArrayList<Vuelo> lista = recuperaPorFiltro(filtro);
			return lista.get(0);
		} else {
			Vuelo c = new Vuelo();
			c.setVuId(0);
			return c;
		}
	}
	
	public Integer grabar(Vuelo vu) {
		String sql = null;
		if (vu.getVuId()==null) {
			/*SQLite*/
			sql = "INSERT INTO vuelos (vu_av_id, vu_des_id, vu_fvuelo, vu_precio) VALUES (" +
					vu.getAv_id() + "," +
					vu.getDes_id() + "," +
					"'" + Utilidades.fechaHoraToSQLite(vu.getFvuelo()) + "'," +
					vu.getPrecio() +
					")";
		} else {		
			sql = "UPDATE vuelos SET " +
					"vu_av_id=" + vu.getAv_id() + "," +
					"vu_des_id=" + vu.getDes_id() + "," +
					"vu_fvuelo='" + Utilidades.fechaHoraToSQLite(vu.getFvuelo()) + "'," +
					"vu_precio=" + vu.getPrecio() +
					" WHERE vuelos.vu_id = " + vu.getVuId()
					;
		}
		return ejecutaSQL(sql);
	}
	
	/*
	 * RECUPERAR TABLAS ESPECIALES
	 * */
	public ArrayList<Vector<Object>> recuperaTablaVuelos(String txtFiltro) {
		ArrayList<Vector<Object>> tableData = null;
		ArrayList<String> filtros = new ArrayList<>();
		filtros.add("vuelos.vu_precio LIKE '%" + txtFiltro + "%'");
		String filtro = Utilidades.creaFiltroOR(filtros);
		ArrayList<Vuelo> lista = recuperaPorFiltro(filtro);
		if (lista!=null) {
			tableData = new ArrayList<>();
			for (Vuelo vuelo : lista) {
				Vector<Object> filaData = new Vector<>();
				filaData.add(vuelo);
				filaData.add(vuelo.getAv_id());
				filaData.add(vuelo.getDes_id());
				filaData.add(vuelo.getFvuelo());
				filaData.add(vuelo.getPrecio());
				tableData.add(filaData);
			}
		}
		return tableData;
	}
	
}
