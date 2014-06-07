package org.bryampaniagua.sistema;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;

import org.bryampaniagua.database.Conexion;
import org.bryampaniagua.manejadores.ManejadorMesero;
import org.bryampaniagua.manejadores.ManejadorChef;
import org.bryampaniagua.manejadores.ManejadorPedido;
import org.bryampaniagua.manejadores.ManejadorUsuario;
import org.bryampaniagua.beans.Usuario;
import org.bryampaniagua.beans.Pedido;

public class App extends Application implements EventHandler<Event>{
	private Stage primaryStage;
	private Scene primaryScene;
	private TabPane tpPrincipal;
	private Tab tbLogin, tbPrincipal, tbPrincipalMesero, tbPrincipalChef, tbEditarPerfil, tbUsuarioNoEncontrado, tbSecundario;
	private Tab tbAgregarPedido, tbCRUDPedidos, tbPrincipalAdmin;
	private GridPane gpLogin, gpEditarPerfil, gpUsuarioNoEncontrado, gpAgregarPedido;
	private Label lbNombreLog, lbContraseniaLog, lbBienvenida, lbUsuarioNoEncontrado, lbRestaurante;
	private TextField tfNombreLog, tfNombre, tfNick, tfCorreo, tfEdad, tfTelefono;
	private PasswordField pfContraseniaLog;
	private Button btnLogin, btnHome, btnEditarPerfil, btnCerrarSesion, btnPedido, btnAgregarPedido, btnCancelarPedido;
	private Button btnModificarPedido, btnVerPedido, btnRegresar;
	private BorderPane bpPrincipal, bpPropiedadesChef, bpPropiedadesMesero, bpEditarPerfil, bpPropiedadesAdmin, bpPedidos;
	private ToolBar tlbPrincipal, tlbCRUD;
	private Image imgLogin, imgHome;
	private Image imgPrincipal;
	private ImageView imgvwPrincipal, imgvwLogin;
	private HBox hbConfiguracion;
	private TableView<Pedido> tvListaPedidos;
	
	private Conexion cnx;
	private ManejadorMesero meseroInstancia;
	private ManejadorPedido pedidoInstancia;
	private ManejadorChef chefInstancia;
	private ManejadorUsuario usuarioInstancia;
	
	public void start(Stage primaryStage){
		this.primaryStage = primaryStage;
		cnx = new Conexion();
		
		this.setManejadorMesero(new ManejadorMesero(cnx));
		this.setManejadorUsuario(new ManejadorUsuario(cnx));
		this.setManejadorChef(new ManejadorChef(cnx));
		this.setManejadorPedido(new ManejadorPedido(cnx));
		
		
		primaryScene = new Scene(this.getTabPane());
		
		primaryStage.setTitle("RESTAURANTE"); 
		primaryStage.setScene(primaryScene);
		primaryStage.setResizable(false); 
		primaryStage.setHeight(565);
		primaryStage.setWidth(680);
		primaryStage.show();
	}
	
	public TabPane getTabPane(){
		if(tpPrincipal == null){
			tpPrincipal = new TabPane();
			tpPrincipal.setStyle("-fx-background-image: url("+"back.jpg"+")");
			
			tpPrincipal.getTabs().add(this.getTabPrincipal());
		}
		return tpPrincipal;
	}
	
	public Tab getTabPrincipal(){
		if(tbPrincipal == null){
			tbPrincipal = new Tab("Restaurante");
			tbPrincipal.setClosable(false);
			tbPrincipal.setStyle("-fx-background-color: #00A090;");
			
			tbPrincipal.setContent(this.getContentPrincipal());
		}
		return tbPrincipal;
	}
	public Tab getTabLogin(){
		if(tbLogin == null){
			tbLogin = new Tab("LOGIN");
			tbLogin.setClosable(false);
			tbLogin.setStyle("-fx-background-color: #00A080;");
			
			tbLogin.setContent(this.getContentLogin());
		}
		return tbLogin;
	}
	public Tab getTabPrincipalChef(){
		if(tbPrincipalChef == null){
			tbPrincipalChef = new Tab(("Bienvenido "+usuarioInstancia.getUsuarioIngresado().getNick()));
			tbPrincipalChef.setClosable(false);
			
			tbPrincipalChef.setContent(this.getContenedorPropiedadesChef());
		}
		return tbPrincipalChef;
	}
	public Tab getTabPrincipalAdministrador(){
		if(tbPrincipalAdmin == null){
			tbPrincipalAdmin = new Tab(("Bienvenido "+usuarioInstancia.getUsuarioIngresado().getNick()));
			tbPrincipalAdmin.setClosable(false);
			
			tbPrincipalAdmin.setContent(this.getContenedorPropiedadesAdmin());
		}
		return tbPrincipalAdmin;
	}
	// public Tab getTabSecundario(){
		// tbSecundario = new Tab(("Bienvenido "+usuarioInstancia.getUsuarioIngresado().getNick()));
		// tbSecundario.setClosable(false);
			
		// int idModulo = usuarioInstancia.getUsuarioIngresado().getIdModulo();
		// if(idModulo == 1){
			// tbSecundario.setContent(this.getContenedorPropiedadesMesero());
		// }else if(idModulo == 2){
			// tbSecundario.setContent(this.getContenedorPropiedadesChef());
		// }else if(idModulo == 3){
			// tbSecundario.setContent(this.getContenedorPropiedadesAdmin());
		// }
		// return tbSecundario;
	// }
	public Tab getTabEditarPerfil(){
		if(tbEditarPerfil == null){
			tbEditarPerfil = new Tab("Editar Perfil");
			tbEditarPerfil.setClosable(false);
			
			tbEditarPerfil.setContent(this.getContenedorEditarPerfil(usuarioInstancia.getUsuarioIngresado()));
		}
		return tbEditarPerfil;
	}
	public Tab getTabUsuarioNoEncontrado(){
		if(tbUsuarioNoEncontrado == null){
			tbUsuarioNoEncontrado = new Tab("No encontrado");
			tbUsuarioNoEncontrado.setClosable(false);
			
			tbUsuarioNoEncontrado.setContent(this.getContentUsuarioNoEncontrado());
		}
		return tbUsuarioNoEncontrado;
	}
	public Tab getTabAgregarPedido(){
		tbAgregarPedido = new Tab("Agregar Pedido");
		tbAgregarPedido.setClosable(false);
		tbAgregarPedido.setStyle("-fx-background-color: #090030");
		
		tbAgregarPedido.setContent(this.getContenedorAgregarPedido());
		return tbAgregarPedido;
	}
	public Tab getTabPrincipalMesero(){
		tbPrincipalMesero = new Tab(("Bienvenido "+usuarioInstancia.getUsuarioIngresado().getNick()));
		tbPrincipalMesero.setClosable(false);
		tbPrincipalMesero.setStyle("-fx-background-color: #00A090;");
		
		tbPrincipalMesero.setContent(this.getContenedorPropiedadesMesero());
		return tbPrincipalMesero;
	}
	public Tab getTabCRUDPedidos(){
		tbCRUDPedidos = new Tab("Pedidos");
				
			tbCRUDPedidos.setContent(this.getContenedorPedidos());
		return tbCRUDPedidos;
	}
	
	public BorderPane getContentPrincipal(){
		if(bpPrincipal == null){
			bpPrincipal= new BorderPane();
			bpPrincipal.setStyle("-fx-background-color: #356aa0;");
			bpPrincipal.setStyle("-fx-background-image: url("+"back2.jpg"+");-fx-background-repeat: no-repeat");
			
			btnLogin = new Button("LOGEAR");
			btnLogin.addEventHandler(ActionEvent.ACTION, this);
			
			tlbPrincipal = new ToolBar();
			tlbPrincipal.setStyle("-fx-background-color: #d9edf2;");
			tlbPrincipal.getItems().add(btnLogin);
				
			lbRestaurante = new Label("THE GREAT TIME.\nRestaurant and conference center");
			lbRestaurante.setStyle("-fx-color: #000000;-fx-text-decoration: line-through;");
		
			bpPrincipal.setTop(this.tlbPrincipal);
			bpPrincipal.setLeft(lbRestaurante);
		}
		return bpPrincipal;
	}	
	public GridPane getContentLogin(){
			gpLogin = new GridPane();
			gpLogin.setStyle("-fx-background-image: url("+"back3.jpg"+");");
			gpLogin.setAlignment(Pos.CENTER);
			
			lbNombreLog = new Label("Correo Electronico");
			lbContraseniaLog = new Label("Contrasenia ");
			
			tfNombreLog = new TextField();
			tfNombreLog.setPromptText("ej. 'asad@ha.com'");
			tfNombreLog.addEventHandler(KeyEvent.KEY_RELEASED, this);
			GridPane.setMargin(tfNombreLog, new Insets(3,3,15,25));
			
			pfContraseniaLog = new PasswordField();
			pfContraseniaLog.setPromptText("Contrasenia");
			pfContraseniaLog.addEventHandler(KeyEvent.KEY_RELEASED, this);
			GridPane.setMargin(pfContraseniaLog, new Insets(3,3,3,25));
			
			gpLogin.add(lbNombreLog, 0, 0);
			gpLogin.add(lbContraseniaLog, 0, 1);
			gpLogin.add(tfNombreLog, 1, 0);
			gpLogin.add(pfContraseniaLog, 1, 1, 2,3);
			gpLogin.add(this.getBtnHome(), 0, 3, 2, 1);
		return gpLogin;
	}
	public BorderPane getContenedorPropiedadesChef(){
		if(bpPropiedadesChef == null){
			bpPropiedadesChef = new BorderPane();
			bpPropiedadesChef.setStyle("-fx-backgroud-image: url("+"chef4.jpg"+")");
			
			bpPropiedadesChef.setTop(this.getContentConfiguracion());
		}
		return bpPropiedadesChef;
	}
	public BorderPane getContenedorPropiedadesMesero(){
		if(bpPropiedadesMesero == null){
			bpPropiedadesMesero = new BorderPane();
			bpPropiedadesMesero.setStyle("-fx-background-image: url("+"back9.jpg"+");-fx-background-position: center;-fx-background-repeat: no-repeat;-fx-background-color: #356aa0;");
			
			bpPropiedadesMesero.setTop(this.getContentConfiguracion());
		}
		return bpPropiedadesMesero;
	}
	public BorderPane getContenedorPropiedadesAdmin(){
		if(bpPropiedadesAdmin == null){
			bpPropiedadesAdmin = new BorderPane();
			bpPropiedadesAdmin.setStyle("-fx-background-image: url("+"HML.png"+")");
			
			bpPropiedadesAdmin.setTop(this.getContentConfiguracion());
		}
		return bpPropiedadesAdmin;
	}
	public HBox getContentConfiguracion(){		
		hbConfiguracion = new HBox();
		hbConfiguracion.setStyle("-fx-backgroud-color: #356aa0;");
		
		btnCerrarSesion = new Button("SALIR");
		BorderPane.setMargin(btnCerrarSesion, new Insets(0,10,0,10));
		btnCerrarSesion.addEventHandler(ActionEvent.ACTION, this);
		
		lbBienvenida = new Label("BIENVENIDO, has ingresado como "+usuarioInstancia.getUsuarioIngresado().getNombre());
		BorderPane.setMargin(lbBienvenida, new Insets(0,10,0,10));
		
		btnEditarPerfil = new Button("Editar PERFIL");
		BorderPane.setMargin(btnEditarPerfil, new Insets(10,0,10,0));
		btnEditarPerfil.addEventHandler(ActionEvent.ACTION, this);
		
		hbConfiguracion.getChildren().addAll(lbBienvenida, btnCerrarSesion, btnEditarPerfil);
		if(usuarioInstancia.getUsuarioIngresado().getIdModulo() == 1){
			btnPedido = new Button("PEDIDOS");
			GridPane.setMargin(btnPedido, new Insets(10,10,10,10));
			btnPedido.addEventHandler(ActionEvent.ACTION, this);
			hbConfiguracion.getChildren().add(btnPedido);
		}
		
		return hbConfiguracion;
	}
	public BorderPane getContenedorEditarPerfil(Usuario usuario){
		if(bpEditarPerfil == null){
			gpEditarPerfil = new GridPane();
			
			tfNombre.addEventHandler(KeyEvent.KEY_RELEASED, this);
			
			tfNick.addEventHandler(KeyEvent.KEY_RELEASED, this);
			
			tfCorreo.addEventHandler(KeyEvent.KEY_RELEASED, this);

			tfEdad.addEventHandler(KeyEvent.KEY_RELEASED, this);
			
			tfTelefono.addEventHandler(KeyEvent.KEY_RELEASED, this);
			
			gpEditarPerfil.add(new Label("Nombre"), 0,0);
			gpEditarPerfil.add(new Label("Nick"), 0,1);
			gpEditarPerfil.add(new Label("Correo"), 0,2);
			gpEditarPerfil.add(new Label("Edad"), 0,3);
			gpEditarPerfil.add(new Label("Telefono"), 0,4);
				
			gpEditarPerfil.add(tfNombre, 1,0);
			gpEditarPerfil.add(tfNick, 1,1);
			gpEditarPerfil.add(tfCorreo, 1,2);
			gpEditarPerfil.add(tfEdad, 1,3);
			gpEditarPerfil.add(tfTelefono, 1,4);
			
			bpEditarPerfil = new BorderPane();
			
			bpEditarPerfil.setCenter(this.gpEditarPerfil);
		}
		return bpEditarPerfil;
	}
	public GridPane getContentUsuarioNoEncontrado(){
		if(gpUsuarioNoEncontrado == null){
			gpUsuarioNoEncontrado = new GridPane();
			gpUsuarioNoEncontrado.setAlignment(Pos.CENTER);
			
			lbUsuarioNoEncontrado = new Label("Compruebe que sus datos esten correctos.\nUsuario no encontrado");
			
			gpUsuarioNoEncontrado.add(this.lbUsuarioNoEncontrado, 0,0);
			gpUsuarioNoEncontrado.add(this.getBtnHome(), 0,1);
		}
		return gpUsuarioNoEncontrado;
	}
	public BorderPane getContenedorPedidos(){
		if(bpPedidos == null){
			bpPedidos = new BorderPane();
			
			bpPedidos.setTop(this.getToolBarCRUD());
			bpPedidos.setCenter(this.getListaPedidos());
		}
		return bpPedidos;
	}
	public GridPane getContenedorAgregarPedido(){
		gpAgregarPedido = new GridPane();
		
		return gpAgregarPedido;
	}
	
	public Button getBtnHome(){
		if(btnHome == null){
			btnHome = new Button("Regresar");
			btnHome.addEventHandler(ActionEvent.ACTION, this);
			GridPane.setMargin(btnHome, new Insets(50,3,3,25));
		}
		return btnHome;
	}
	public TableView<Pedido> getListaPedidos(){
		if(tvListaPedidos == null){
			tvListaPedidos = new TableView<Pedido>();

			tvListaPedidos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

			TableColumn<Pedido, String> columnaIdCliente = new TableColumn<Pedido, String>("Cliente");
			columnaIdCliente.setCellValueFactory(new PropertyValueFactory<Pedido, String>("cliente"));

			TableColumn<Pedido, String> columnaIdUsuario = new TableColumn<Pedido, String>("Mesero");
			columnaIdUsuario.setCellValueFactory(new PropertyValueFactory<Pedido, String>("usuario"));

			TableColumn<Pedido, Integer> columnaIdEstado = new TableColumn<Pedido, Integer>("Estado");
			columnaIdEstado.setCellValueFactory(new PropertyValueFactory<Pedido, Integer>("estado"));

			TableColumn<Pedido, Integer> columnaFecha = new TableColumn<Pedido, Integer>("Fecha");
			columnaFecha.setCellValueFactory(new PropertyValueFactory<Pedido, Integer>("fecha"));

			tvListaPedidos.getColumns().setAll(columnaIdCliente, columnaIdUsuario, columnaIdEstado, columnaFecha);

			tvListaPedidos.setItems(pedidoInstancia.getListaDePedidos());
		}
		return tvListaPedidos;
	}
	public ToolBar getToolBarCRUD(){
		tlbCRUD = new ToolBar();
			
			btnVerPedido = new Button("Ver");
			btnVerPedido.addEventHandler(ActionEvent.ACTION, this);
			
			btnAgregarPedido = new Button("Agregar");
			btnAgregarPedido.addEventHandler(ActionEvent.ACTION, this);
			
			btnModificarPedido = new Button("Modificar");
			btnModificarPedido.addEventHandler(ActionEvent.ACTION, this);
			
			btnCancelarPedido = new Button("Cancelar");
			btnCancelarPedido.addEventHandler(ActionEvent.ACTION, this);
			
			btnRegresar = new Button("Hecho");
			btnRegresar.addEventHandler(ActionEvent.ACTION, this);
			
			tlbCRUD.getItems().addAll(btnVerPedido, btnAgregarPedido, btnModificarPedido, btnCancelarPedido, btnRegresar);
		
		return tlbCRUD;
	}
	
	public void setManejadorMesero(ManejadorMesero instancia){
		this.meseroInstancia = instancia;
	}
	public void setManejadorChef(ManejadorChef instancia){
		this.chefInstancia = instancia;
	}
	public void setManejadorUsuario(ManejadorUsuario instancia){
		this.usuarioInstancia = instancia;
	}
	public void setManejadorPedido(ManejadorPedido instancia){
		this.pedidoInstancia = instancia;
	}
	
	public void setEditarUsuario(Usuario usuario){
		tfNombre = new TextField();
		tfNombre.setText(usuario.getNombre());
		
		tfNick = new TextField();
		tfNick.setText(usuario.getNick());
		
		tfCorreo = new TextField();
		tfCorreo.setText(usuario.getCorreo());
					
		tfEdad = new TextField();
		tfEdad.setText(String.valueOf(usuario.getEdad()));
		
		tfTelefono = new TextField();
		tfTelefono.setText(String.valueOf(usuario.getTelefono()));
	}
	public void enviarAModulo(){
		int modulo = usuarioInstancia.getUsuarioIngresado().getIdModulo();
		if(modulo == 1){
			tpPrincipal.getTabs().add(getTabPrincipalMesero());
		}else if(modulo == 2){
			tpPrincipal.getTabs().add(this.getTabPrincipalChef());					
		}else if(modulo == 3){
			tpPrincipal.getTabs().add(this.getTabPrincipalAdministrador());					
		}
	}
	
	public void handle(Event event){
		if(event instanceof ActionEvent){
			tpPrincipal.getTabs().clear();
			if(event.getSource().equals(btnLogin)){
				tpPrincipal.getTabs().add(this.getTabLogin());
			}else if(event.getSource().equals(btnHome)){
				tpPrincipal.getTabs().add(this.getTabPrincipal());
			}else if(event.getSource().equals(btnEditarPerfil)){
				tpPrincipal.getTabs().add(this.getTabEditarPerfil());	
			}else if(event.getSource().equals(btnCerrarSesion)){
				usuarioInstancia.salirDeSesion();
				tpPrincipal.getTabs().add(this.getTabPrincipal());
				this.setEditarUsuario(new Usuario());
			}else if(event.getSource().equals(btnPedido)){
				tpPrincipal.getTabs().add(this.getTabCRUDPedidos());
			}else if(event.getSource().equals(btnAgregarPedido)){
				tpPrincipal.getTabs().add(getTabAgregarPedido());
			}else if(event.getSource().equals(btnRegresar)){
				this.enviarAModulo();
			}
		}else if(event instanceof KeyEvent){
			KeyEvent keyEvent = (KeyEvent)event;
			if(keyEvent.getCode() == KeyCode.ENTER){
				tpPrincipal.getTabs().clear();
				if(event.getSource().equals(tfNombreLog) || event.getSource().equals(pfContraseniaLog)){
					if(!tfNombreLog.getText().trim().equals(null) && !pfContraseniaLog.getText().trim().equals(null)){
						if(usuarioInstancia.comprobar(tfNombreLog.getText().trim(), pfContraseniaLog.getText().trim()) != false){
							setEditarUsuario(usuarioInstancia.getUsuarioIngresado());
							this.enviarAModulo();
						}else{
							tpPrincipal.getTabs().add(this.getTabUsuarioNoEncontrado());
						}
					}
				}else if(event.getSource().equals(tfNombre) || event.getSource().equals(tfNick) || event.getSource().equals(tfCorreo) || event.getSource().equals(tfEdad) || event.getSource().equals(tfTelefono)){
					if(!tfNombre.getText().equals(null) && !tfNick.getText().equals(null) && !tfCorreo.getText().equals(null) && !tfEdad.getText().equals(null) && !tfTelefono.getText().equals(null)){
						Usuario usuario = new Usuario();
						usuario.setNombre(tfNombre.getText());
						usuario.setNick(tfNick.getText());
						usuario.setCorreo(tfCorreo.getText());
						usuario.setEdad(Integer.parseInt(tfEdad.getText()));
						usuario.setTelefono(Integer.parseInt(tfTelefono.getText()));
						System.out.println(usuario.getCorreo());
						usuarioInstancia.modificarUsuario(usuario);
						// this.setEditarUsuario(new Usuario());	
					}	
					this.enviarAModulo();
				}			
			}
		}
	}
}
