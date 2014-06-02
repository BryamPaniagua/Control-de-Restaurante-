package org.bryampaniagua.manejadores;

import org.bryampaniagua.database.Conexion;
import org.bryampaniagua.beans.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ManejadorUsuario{
	private Conexion cnx;
	private ResultSet rsResultado;
	private Usuario enSesion;
	
	public ManejadorUsuario(Conexion cnx){
		this.cnx = cnx;		
	}
	public boolean comprobar(String correo, String pass){
		ResultSet resultado = cnx.execConsulta("SELECT idUsuario, nombre, contrasenia, nick, correo, telefono,edad, idModulo FROM Usuario WHERE correo = '"+correo+"' AND contrasenia = '"+pass+"' ");
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
	public Usuario getUsuarioIngresado(){
		return enSesion;
	}
	public void salirDeSesion(){
		enSesion = null;
	}
	public void modificarUsuario(Usuario usuario){
		enSesion.setNombre(usuario.getNombre());
		enSesion.setNick(usuario.getNick());
		enSesion.setCorreo(usuario.getCorreo());
		enSesion.setTelefono(usuario.getTelefono());
		enSesion.setEdad(usuario.getEdad());
	}
}