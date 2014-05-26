package org.bryampaniagua.manejadores;

import org.bryampaniagua.database.Conexion;
import org.bryampaniagua.beans.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ManejadorMesero{
	private Conexion conexion;
	private ResultSet rsResultado;
	private Usuario enSesion;

	public ManejadorMesero(Conexion cntConection){
		this.conexion = cntConection;
	}
	public boolean comprobar(String correo, String pass){
		ResultSet resultado = conexion.execConsulta("SELECT idUsuario, nombre, contrasenia, nick, correo, telefono,edad, idModulo FROM Usuario WHERE correo = '"+correo+"' AND contrasenia = '"+pass+"' ");
		try{
			if(resultado != null){
				if(resultado.next()){
					// enSesion.setIdUsuario(1);
					// enSesion.setIdModulo(1);
					// enSesion.setEdad(21);
					// enSesion.setTelefono(24751254);
					// enSesion.setNombre("Manuel");
					// enSesion.setNick("mm");
					// enSesion.setContrasenia("mm");
					// enSesion.setCorreo("m@gmail.com");
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
}