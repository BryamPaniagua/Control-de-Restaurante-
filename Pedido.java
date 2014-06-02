package org.bryampaniagua.beans;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Pedido{
	private IntegerProperty idPedido, idCliente, idUsuario, idEstado;
	private StringProperty fecha;
	public Pedido(){
		idPedido = new SimpleIntegerProperty();
		idCliente = new SimpleIntegerProperty();
		idUsuario = new SimpleIntegerProperty();
		idEstado = new SimpleIntegerProperty();
		fecha = new SimpleStringProperty();
	}
	public Pedido(int idPedido, int idCliente, int idUsuario,int idEstado, String fecha){
		this.idPedido = new SimpleIntegerProperty(idPedido);
		this.idCliente = new SimpleIntegerProperty(idCliente);
		this.idUsuario = new SimpleIntegerProperty(idUsuario);
		this.idEstado = new SimpleIntegerProperty(idEstado);
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

	public int getIdUsuario(){
		return idUsuario.get();
	}
	public void setIdUsuario(int idUsuario){
		this.idUsuario.set(idUsuario);
	}
	public IntegerProperty idUsuarioProperty(){
		return idUsuario;
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
