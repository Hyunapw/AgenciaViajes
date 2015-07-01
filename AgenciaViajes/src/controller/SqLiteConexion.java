package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class SqLiteConexion {
	Connection conexionActual = null;
	public String ruta;

	public SqLiteConexion() {
		ruta = "viajes.db3";
	}

	public Connection getConection() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		try {
			conexionActual = DriverManager.getConnection("jdbc:sqlite:" + ruta);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return conexionActual;
	}
}