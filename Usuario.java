package org.bryampaniagua.beans;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Usuario{
	private IntegerProperty idUsuario, idModulo, edad, telefono;
	private StringProperty nombre, contrasenia, correo, nick;
	public Usuario(){
		idUsuario = new SimpleIntegerProperty();
		edad = new SimpleIntegerProperty();
		idModulo = new SimpleIntegerProperty();
		telefono = new SimpleIntegerProperty();
		nombre = new SimpleStringProperty();
		contrasenia = new SimpleStringProperty();
		correo = new SimpleStringProperty();
		nick = new SimpleStringProperty();
	}
	public Usuario(int idUsuario, String nombre, String nick, String contrasenia, String correo, int edad, int telefono,int idModulo){
		this.idUsuario = new SimpleIntegerProperty(idUsuario);
		this.nombre = new SimpleStringProperty(nombre);
		this.nick = new SimpleStringProperty(nick);
		this.contrasenia = new SimpleStringProperty(contrasenia);
		this.correo = new SimpleStringProperty(correo);
		this.edad = new SimpleIntegerProperty(edad);
		this.telefono = new SimpleIntegerProperty(telefono);
		this.idModulo = new SimpleIntegerProperty(idModulo);
	}
	
	public int getIdUsuario(){
		return idUsuario.get();
	}
	public void setIdUsuario(int idUsuario){
		this.idUsuario.set(idUsuario);
	}
	public IntegerProperty idUsuarioProperty(){
		return idUsuario;
	}

	public int getIdModulo(){
		return idModulo.get();
	}
	public void setIdModulo(int idModulo){
		this.idModulo.set(idModulo);
	}
	public IntegerProperty idModuloProperty(){
		return idModulo;
	}

	public int getTelefono(){
		return telefono.get();
	}
	public void setTelefono(int telefono){
		this.telefono.set(telefono);
	}
	public IntegerProperty telefonoProperty(){
		return telefono;
	}

	public int getEdad(){
		return edad.get();
	}
	public void setEdad(int edad){
		this.edad.set(edad);
	}
	public IntegerProperty edadProperty(){
		return edad;
	}

	public String getNombre(){
		return nombre.get();
	}
	public void setNombre(String nombre){
		this.nombre.set(nombre);
	}
	public StringProperty nombreProperty(){
		return nombre;
	}

	public String getNick(){
		return nick.get();
	}
	public void setNick(String nick){
		this.nick.set(nick);
	}
	public StringProperty nickProperty(){
		return nick;
	}
	
	public String getContrasenia(){
		return contrasenia.get();
	}
	public void setContrasenia(String contrasenia){
		this.contrasenia.set(contrasenia);
	}
	public StringProperty contraseniaProperty(){
		return contrasenia;
	}
	
	public String getCorreo(){
		return correo.get();
	}
	public void setCorreo(String correo){
		this.correo.set(correo);
	}
	public StringProperty correoProperty(){
		return correo;
	}
}
