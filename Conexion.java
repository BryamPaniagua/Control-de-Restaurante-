package org.bryampaniagua.database;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;

public class Conexion{
	public Connection cntConexion;
	private Statement estado;
	
	public Conexion(){
		try{		
			System.out.println("Cargando conexion...");
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("Cargando driver...");
			cntConexion = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=dbRestaurante2012442", "BP", "123");
			System.out.println("Cargando base de datos...");
			estado = cntConexion.createStatement();
			System.out.println("Creando estado..");
		}catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
			System.out.println("error "+cnfe);
		}catch(SQLException sqle){
			sqle.printStackTrace();
			System.out.println("error "+sqle);
		}	
	}
	public boolean execSentencia(String sentencia){
		boolean resultado = false;
		try{
			resultado = estado.execute(sentencia);
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return resultado;
	}
	public ResultSet execConsulta(String consulta){
		ResultSet rsResultado = null;
		try{
			rsResultado = estado.executeQuery(consulta);
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return rsResultado;
	}
}