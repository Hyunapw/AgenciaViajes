package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.sql.rowset.CachedRowSet;

import util.Utilidades;
import controller.BDD;
import model.Cliente;

public class Clientes extends BDD {
	
//	TABLA clientes
//	id (integer)
//	nombre vachar(40)
	
	public ArrayList<Cliente> recuperaPorFiltro(String filtro) {
		String sql = "SELECT * FROM clientes WHERE ";
		sql += filtro == null || filtro.length() == 0 ? "1" : filtro;
		sql += " ORDER BY clientes.cli_id";
		ArrayList<Cliente> lista = null;
		CachedRowSet rs = consultaSQL(sql);
		if (rs!=null){
			try {
				lista = new ArrayList<>();
				while (rs.next() == true) {
					int id = rs.getInt("cli_id");
					String nombre = rs.getString("cli_nombre");
					lista.add(new Cliente(id, nombre));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}
	
	public ArrayList<Cliente> recuperaTodos(){
		return recuperaPorFiltro(null);
	}
	
	public Cliente recuperaPorId(int cliId){
		if (cliId != 0) {
			String filtro = "clientes.cli_id = " + cliId;
			ArrayList<Cliente> lista = recuperaPorFiltro(filtro);
			return lista.get(0);
		} else {
			Cliente c = new Cliente();
			c.setCliId(0);
			return c;
		}
	}
	
	public Integer grabar(Cliente cli) {
		String sql = null;
		if (cli.getCliId()==null) {
			/*SQLite*/
			sql = "INSERT INTO clientes (cli_nombre) VALUES ('"+ cli.getNombre() +"')";
			/*MySQL
			 * sql = "INSERT INTO clientes SET " +
					"clientes.cli_nombre = '" + cli.getNombre() + "' "*/
		} else {		
			sql = "UPDATE clientes SET " +
					"cli_nombre = '" + cli.getNombre() + "' WHERE clientes.cli_id = " + cli.getCliId()
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
		filtros.add("clientes.cli_nombre LIKE '%" + txtFiltro + "%'");
		String filtro = Utilidades.creaFiltroOR(filtros);
		ArrayList<Cliente> lista = recuperaPorFiltro(filtro);
		if (lista!=null) {
			tableData = new ArrayList<>();
			for (Cliente cliente : lista) {
				Vector<Object> filaData = new Vector<>();
				filaData.add(cliente);
				filaData.add(cliente.getNombre());
				tableData.add(filaData);
			}
		}
		return tableData;
	}
	
}
