package org.bryampaniagua.beans;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Pedido{
	private IntegerProperty idPedido, idCliente, idUsuario, idEstado;
	private StringProperty fecha, cliente, usuario, estado;
	public Pedido(){
		idPedido = new SimpleIntegerProperty();
		idCliente = new SimpleIntegerProperty();
		cliente = new SimpleStringProperty();
		idUsuario = new SimpleIntegerProperty();
		usuario = new SimpleStringProperty();
		idEstado = new SimpleIntegerProperty();
		estado = new SimpleStringProperty();
		fecha = new SimpleStringProperty();
	}
	public Pedido(int idPedido,int idCliente, String cliente,int idUsuario, String usuario,int idEstado, String estado, String fecha){
		this.idPedido = new SimpleIntegerProperty(idPedido);
		
		this.idCliente = new SimpleIntegerProperty(idCliente);
		this.cliente = new SimpleStringProperty(cliente);
		
		this.idUsuario = new SimpleIntegerProperty(idUsuario);
		this.usuario = new SimpleStringProperty(usuario);
		
		this.idEstado = new SimpleIntegerProperty(idEstado);
		this.estado = new SimpleStringProperty(estado);
		
		this.fecha = new SimpleStringProperty(fecha);
	}
	
	public int getIdPedido(){
		return idPedido.get();
	}
	public void setIdPedido(int idPedido){
		this.idPedido.set(idPedido);
	}
	public IntegerProperty idPedidoProperty(){
		return idPedido;
	}

	public int getIdCliente(){
		return idCliente.get();
	}
	public void setIdCliente(int idCliente){
		this.idCliente.set(idCliente);
	}
	public IntegerProperty idClienteProperty(){
		return idCliente;
	}
	public String getCliente(){
		return cliente.get();
	}
	public void setCliente(String cliente){
		this.cliente.set(cliente);
	}
	public StringProperty clienteProperty(){
		return cliente;
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
	public String getUsuario(){
		return usuario.get();
	}
	public void setUsuario(String usuario){
		this.usuario.set(usuario);
	}
	public StringProperty usuarioProperty(){
		return usuario;
	}

	public int getIdEstado(){
		return idEstado.get();
	}
	public void setIdEstado(int idEstado){
		this.idEstado.set(idEstado);
	}
	public IntegerProperty idEstadoProperty(){
		return idEstado;
	}
	public String getEstado(){
		return estado.get();
	}
	public void setEstado(String estado){
		this.estado.set(estado);
	}
	public StringProperty estadoProperty(){
		return estado;
	}

	public String getFecha(){
		return fecha.get();
	}
	public void setFecha(String fecha){
		this.fecha.set(fecha);
	}
	public StringProperty fechaProperty(){
		return fecha;
	}
}
