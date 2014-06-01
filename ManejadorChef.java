package org.bryampaniagua.manejadores;

import org.bryampaniagua.database.Conexion;
import org.bryampaniagua.beans.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ManejadorChef{
	private Conexion conexion;
	// private ResultSet rsResultado;
	private Usuario enSesion;

	public ManejadorChef(Conexion cntConection){
		this.conexion = cntConection;
	}
}