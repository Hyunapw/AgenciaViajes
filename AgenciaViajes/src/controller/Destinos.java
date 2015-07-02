package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.sql.rowset.CachedRowSet;

import util.Utilidades;
import controller.BDD;
import model.Destino;

public class Destinos extends BDD {
	
//	CREATE TABLE destinos (
//    des_id    INTEGER  PRIMARY KEY AUTOINCREMENT
//                       NOT NULL,
//    des_lugar TEXT (0) NOT NULL
//);
	
	public ArrayList<Destino> recuperaPorFiltro(String filtro) {
		String sql = "SELECT * FROM destinos WHERE ";
		sql += filtro == null || filtro.length() == 0 ? "1" : filtro;
		sql += " ORDER BY destinos.des_id";
		ArrayList<Destino> lista = null;
		CachedRowSet rs = consultaSQL(sql);
		if (rs!=null){
			try {
				lista = new ArrayList<>();
				while (rs.next() == true) {
					lista.add(new Destino(rs.getInt("des_id"), rs.getString("des_lugar")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}
	
	public ArrayList<Destino> recuperaTodos(){
		return recuperaPorFiltro(null);
	}
	
	public Destino recuperaPorId(int id){
		if (id != 0) {
			String filtro = "destinos.des_id=" + id;
			ArrayList<Destino> lista = recuperaPorFiltro(filtro);
			return lista.get(0);
		} else {
			Destino c = new Destino();
			c.setId(0);
			return c;
		}
	}
	
	public Integer grabar(Destino des) {
		String sql = null;
		if (des.getId()==null) {
			/*SQLite*/
			sql = "INSERT INTO destinos (des_lugar) VALUES ('"+ des.getLugar() +"')";
		} else {		
			sql = "UPDATE destinos SET " +
					"des_lugar='" + des.getLugar() + "' WHERE destinos.des_id=" + des.getId()
					;
		}
		return ejecutaSQL(sql);
	}
	
	/*
	 * RECUPERAR TABLAS ESPECIALES
	 * */
	public ArrayList<Vector<Object>> recuperaTablaDestinos(String txtFiltro) {
		ArrayList<Vector<Object>> tableData = null;
		ArrayList<String> filtros = new ArrayList<>();
		filtros.add("destinos.des_lugar LIKE '%" + txtFiltro + "%'");
		String filtro = Utilidades.creaFiltroOR(filtros);
		ArrayList<Destino> lista = recuperaPorFiltro(filtro);
		if (lista!=null) {
			tableData = new ArrayList<>();
			for (Destino destino : lista) {
				Vector<Object> filaData = new Vector<>();
				filaData.add(destino);
				filaData.add(destino.getLugar());
				tableData.add(filaData);
			}
		}
		return tableData;
	}
	
}
