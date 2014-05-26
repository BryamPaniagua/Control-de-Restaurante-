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
	public boolean comprobar(String correo, String pass){
		ResultSet resultado = conexion.execConsulta("SELECT idUsuario, nombre, contrasenia, nick, correo, telefono,edad, idModulo FROM Usuario WHERE correo = '"+correo+"' AND contrasenia = '"+pass+"' ");
		try{
			if(resultado != null){
				if(resultado.next()){
					enSesion = new Usuario(resultado.getInt("idUsuario"), resultado.getString("nombre"), resultado.getString("nick"), resultado.getString("contrasenia"), resultado.getString("correo"), resultado.getInt("edad"), resultado.getInt("telefono"), resultado.getInt("idModulo"));
					return true;
				}
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return false;
	}
	// public boolean comprobar(String correo, String pass){
		// if(correo.equals("jp@yahoo.com") && pass.equals("jop")){
			// enSesion = new Usuario();
			
			// enSesion.setIdUsuario(2);
			// enSesion.setIdModulo(2);
			// enSesion.setEdad(25);
			// enSesion.setTelefono(21104244);
			// enSesion.setNombre("Juan");
			// enSesion.setNick("jop");
			// enSesion.setContrasenia("jop");
			// enSesion.setCorreo("jp@yahoo.com");
			// enSesion = new Usuario(2,"Juan", "jop", "jop", "jp@yahoo.com", 25, 21104244, 2);
			// return true;
		// }
		// return false;
	// }
	public Usuario getUsuarioIngresado(){
		return enSesion;
	}
	public void salirDeSesion(){
		enSesion = null;
	}
}