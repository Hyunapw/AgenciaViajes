package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.sql.rowset.CachedRowSet;

import util.Utilidades;
import controller.BDD;
import model.Avion;

public class Aviones extends BDD {
	
//	CREATE TABLE aviones (
//		    av_id        INTEGER      PRIMARY KEY ON CONFLICT ROLLBACK AUTOINCREMENT
//		                              NOT NULL,
//		    av_modelo    VARCHAR (40) NOT NULL,
//		    av_capacidad INTEGER      NOT NULL
//		);
	
	public ArrayList<Avion> recuperaPorFiltro(String filtro) {
		String sql = "SELECT * FROM aviones WHERE ";
		sql += filtro == null || filtro.length() == 0 ? "1" : filtro;
		sql += " ORDER BY aviones.av_id";
		ArrayList<Avion> lista = null;
		CachedRowSet rs = consultaSQL(sql);
		if (rs!=null){
			try {
				lista = new ArrayList<>();
				while (rs.next() == true) {
					lista.add(new Avion(rs.getInt("av_id"), rs.getString("av_modelo"), rs.getInt("av_capacidad")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}
	
	public ArrayList<Avion> recuperaTodos(){
		return recuperaPorFiltro(null);
	}
	
	public Avion recuperaPorId(int id){
		if (id != 0) {
			String filtro = "aviones.av_id = " + id;
			ArrayList<Avion> lista = recuperaPorFiltro(filtro);
			return lista.get(0);
		} else {
			Avion c = new Avion();
			c.setId(0);
			return c;
		}
	}
	
	public Integer grabar(Avion av) {
		String sql = null;
		if (av.getId()==null) {
			/*SQLite*/
			sql = "INSERT INTO aviones (av_modelo, av_capacidad) VALUES ('"+ av.getModelo() +"'," + av.getCapacidad() + ")";
			/*MySQL
			 * sql = "INSERT INTO clientes SET " +
					"clientes.cli_nombre = '" + cli.getNombre() + "' "*/
		} else {		
			sql = "UPDATE aviones SET " +
					"av_modelo = '" + av.getModelo() + "', av_capacidad=" + av.getCapacidad() + " WHERE aviones.av_id = " + av.getId()
					;
		}
		return ejecutaSQL(sql);
	}
	
	/*
	 * RECUPERAR TABLAS ESPECIALES
	 * */
	public ArrayList<Vector<Object>> recuperaTablaAviones(String txtFiltro) {
		ArrayList<Vector<Object>> tableData = null;
		ArrayList<String> filtros = new ArrayList<>();
		filtros.add("aviones.av_modelo LIKE '%" + txtFiltro + "%'");
		String filtro = Utilidades.creaFiltroOR(filtros);
		ArrayList<Avion> lista = recuperaPorFiltro(filtro);
		if (lista!=null) {
			tableData = new ArrayList<>();
			for (Avion avion : lista) {
				Vector<Object> filaData = new Vector<>();
				filaData.add(avion);
				filaData.add(avion.getModelo());
				filaData.add(avion.getCapacidad());
				tableData.add(filaData);
			}
		}
		return tableData;
	}
	
}
