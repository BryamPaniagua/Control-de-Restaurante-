package org.bryampaniagua.manejadores;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.bryampaniagua.database.Conexion;
import org.bryampaniagua.beans.Pedido;

public class ManejadorPedido{
	private Conexion cnx;
	private ObservableList<Pedido> listaDePedidos;

	public ManejadorPedido(Conexion cnx){
		this.cnx = cnx;
		this.listaDePedidos = FXCollections.observableArrayList();
	}
	public void actualizarListaDePedidos(){
		listaDePedidos.clear();
		ResultSet resultado = cnx.execConsulta("SELECT idPedido, Cliente.idCliente, Cliente.nombre AS cliente, Usuario.idUsuario, Usuario.nombre AS usuario, Estado.idEstado, Estado.descripcion AS estado, fecha FROM Pedido INNER JOIN Cliente ON Pedido.idCliente = Cliente.idCliente INNER JOIN Usuario ON Pedido.idUsuario = Usuario.idUsuario INNER JOIN Estado ON Pedido.idEstado = Estado.idEstado");
		try{
			if(resultado != null){
				while(resultado.next()){
					Pedido pedido = new Pedido(resultado.getInt("idPedido"),resultado.getInt("idCliente"), resultado.getString("cliente"),resultado.getInt("idUsuario"), resultado.getString("usuario"),resultado.getInt("idEstado"), resultado.getString("estado"), resultado.getString("fecha"));
					listaDePedidos.add(pedido);
				}
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
	}
	public ObservableList<Pedido> getListaDePedidos(){
		actualizarListaDePedidos();
		return listaDePedidos;
	}
	public void eliminarPedido(Pedido pedido){
		cnx.execConsulta("DELETE FROM Pedido WHERE idPedido = "+pedido.getIdPedido());
		actualizarListaDePedidos();
	}
	public void agregarPedido(Pedido pedido){
		cnx.execConsulta("INSERT INTO Pedido(idCliente, idUsuario, idEstado, fecha) VALUES ('"+pedido.getIdCliente()+"','"+pedido.getIdUsuario()+"',"+pedido.getIdEstado()+","+pedido.getFecha()+")");
		actualizarListaDePedidos();

	}
	public void modificarPedido(Pedido pedido){
		cnx.execConsulta("UPDATE Pedido SET idCliente = '"+pedido.getIdCliente()+"', idUsuario = '"+pedido.getIdUsuario()+"', idEstado = "+pedido.getIdEstado()+", fecha = "+pedido.getFecha()+" WHERE idPedido = "+pedido.getIdPedido());
		actualizarListaDePedidos();
	}
}