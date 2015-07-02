package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.sql.rowset.CachedRowSet;

import util.Utilidades;
import controller.BDD;
import model.Avion;
import model.Destino;
import model.Vuelo;
import model.VueloCompleto;

public class VuelosCompletos extends BDD {
	
	/*	CREATE TABLE vuelos (
    vu_id     INTEGER        PRIMARY KEY AUTOINCREMENT,
    vu_av_id  INTEGER        NOT NULL
                             REFERENCES aviones (av_id),
    vu_des_id INTEGER        NOT NULL
                             REFERENCES destinos (des_id),
    vu_fvuelo DATE           NOT NULL,
    vu_precio DECIMAL (7, 2) NOT NULL
);*/
	
	public ArrayList<VueloCompleto> recuperaPorFiltro(String filtro) {
		//String sql = "SELECT vu_id, vu_av_id, av_modelo, av_capacidad, vu_des_id, des_lugar, vu_fvuelo, vu_precio FROM vuelos, aviones, destinos WHERE ";
		String sql = "SELECT * FROM vuelos, aviones, destinos WHERE ";
		sql += "vuelos.vu_av_id=aviones.av_id AND vuelos.vu_des_id=destinos.des_id AND (";
		sql += filtro == null || filtro.length() == 0 ? "1" : filtro;
		sql += ") ORDER BY vuelos.vu_fvuelo";
		ArrayList<VueloCompleto> lista = null;
		CachedRowSet rs = consultaSQL(sql);
		if (rs!=null){
			try {
				lista = new ArrayList<>();
				while (rs.next() == true) {
					lista.add(new VueloCompleto(rs.getInt("vu_id"), 
							new Avion(rs.getInt("av_id"), 
									rs.getString("av_modelo"), 
									rs.getInt("av_capacidad")), 
									new Destino(rs.getInt("des_id"), 
									rs.getString("des_lugar")),
									Utilidades.validarFechaHora(rs.getString("vu_fvuelo")),
									rs.getDouble("vu_precio")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}
	
	public ArrayList<VueloCompleto> recuperaTodos(){
		return recuperaPorFiltro(null);
	}
	
	public VueloCompleto recuperaPorId(int id){
		if (id != 0) {
			String filtro = "vuelos.vu_id = " + id;
			ArrayList<VueloCompleto> lista = recuperaPorFiltro(filtro);
			return lista.get(0);
		} else {
			VueloCompleto c = new VueloCompleto();
			c.setId(0);
			return c;
		}
	}
	
	public Integer grabar(VueloCompleto vu) {
		String sql = null;
		if (vu.getId()==null) {
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
					" WHERE vuelos.vu_id = " + vu.getId()
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
		filtros.add("destinos.des_lugar LIKE '%" + txtFiltro + "%'");
		filtros.add("aviones.av_modelo LIKE '%" + txtFiltro + "%'");
		String filtro = Utilidades.creaFiltroOR(filtros);
		ArrayList<VueloCompleto> lista = recuperaPorFiltro(filtro);
		if (lista!=null) {
			tableData = new ArrayList<>();
			for (VueloCompleto vuelo : lista) {
				Vector<Object> filaData = new Vector<>();
				filaData.add(vuelo);
				filaData.add(vuelo.getCapacidad());
				filaData.add(vuelo.getPrecio());
				tableData.add(filaData);
			}
		}
		return tableData;
	}
	
}
