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

import org.bryampaniagua.database.Conexion;
import org.bryampaniagua.manejadores.ManejadorMesero;
import org.bryampaniagua.manejadores.ManejadorChef;
import org.bryampaniagua.beans.Usuario;

public class App extends Application implements EventHandler<Event>{
	private Stage primaryStage;
	private Scene primaryScene;
	private TabPane tpPrincipal;
	private Tab tbLogin, tabInicial, tbDBoard, tbEditarPerfil;
	private GridPane gpLogin, gpEditarPerfil;
	private Label lbNombreLog, lbContraseniaLog, lbBienvenida;
	private TextField tfNombreLog, tfNombre, tfNick, tfCorreo, tfEdad, tfTelefono;
	private PasswordField pfContraseniaLog;
	private Button btnLogin, btnHome, btnEditarPerfil, btnCerrarSesion;
	private Image imgLogin, imgHome;
	private BorderPane bpPrincipal, bpDBoard, bpEditarPerfil;
	private ToolBar tbInicial;
	private Image imgInicial;
	private ImageView imgvwInicial;
	private HBox hbCuentaPrincipal;
	
	private Conexion cnxConexion;
	private ManejadorMesero meseroInstancia;
	private ManejadorChef chefInstancia;
	
	public void start(Stage primaryStage){
		this.primaryStage = primaryStage;
		cnxConexion = new Conexion();
		
		this.setManejadorMesero(new ManejadorMesero(cnxConexion));
		this.setManejadorChef(new ManejadorChef(cnxConexion));
		
		primaryStage.setTitle("RESTAURANTE"); 
		
		primaryScene = new Scene(this.getTabPane());
		
		primaryStage.setScene(primaryScene);
		primaryStage.setWidth(700);
		primaryStage.setHeight(700);
		primaryStage.setResizable(true); 
		primaryStage.show();
	}
	
	public TabPane getTabPane(){
		if(tpPrincipal == null){
			tpPrincipal = new TabPane();
			
			tpPrincipal.getTabs().add(this.getTabInicial());
		}
		return tpPrincipal;
	}
	
	public Tab getTabLogin(){
		if(tbLogin == null){
			tbLogin = new Tab("LOGIN");
			tpPrincipal.setStyle("-fx-background-color: #80BFFF;");
			
			imgLogin = new Image("Imagenes/log.jpg");
			
			tbLogin.setContent(this.getContentLogin());
		}
		return tbLogin;
	}
	public Tab getTabInicial(){
		if(tabInicial == null){
			tabInicial = new Tab("Home");
			tabInicial.setStyle("-fx-background-color: #00A090;");
			
			tabInicial.setContent(this.getContentPrincipal());
		}
		return tabInicial;
	}
	public Tab getTabPrincipal(){
		if(tbDBoard == null){
			tbDBoard = new Tab("Principal");
			
			tbDBoard.setContent(this.getContenedorDBoard());
		}
		return tbDBoard;
	}
	public Tab getTabEditarPerfil(){
		if(tbEditarPerfil == null){
			tbEditarPerfil= new Tab("Editar Perfil");
			
			tbEditarPerfil.setContent(this.getContenedorEditarPerfil());
		}
		return tbEditarPerfil;
	}
	
	public BorderPane getContenedorDBoard(){
		if(bpDBoard == null){
			bpDBoard = new BorderPane();
				
			String nombreIngresado = null;
			if(meseroInstancia.getUsuarioIngresado() != null){
				nombreIngresado = meseroInstancia.getUsuarioIngresado().getNombre();
			}else{
				nombreIngresado = chefInstancia.getUsuarioIngresado().getNombre();
			}
			
			hbCuentaPrincipal = new HBox();
			
			btnCerrarSesion = new Button("SALIR");
			BorderPane.setMargin(btnCerrarSesion, new Insets(10,3,3,3));
			btnCerrarSesion.addEventHandler(ActionEvent.ACTION, this);
			
			lbBienvenida = new Label("BIENVENIDO, has ingresado como "+nombreIngresado);
			BorderPane.setMargin(lbBienvenida, new Insets(3,3,3,3));
			
			btnEditarPerfil = new Button("Editar PERFIL");
			BorderPane.setMargin(btnEditarPerfil, new Insets(10,3,3,3));
			btnEditarPerfil.addEventHandler(ActionEvent.ACTION, this);
			
			hbCuentaPrincipal.getChildren().addAll(lbBienvenida, btnCerrarSesion, btnEditarPerfil);
			
			Image imgPrincipal = new Image("ren.jpg");
			ImageView imgViewPrincipal = new ImageView(imgPrincipal);
			
			bpDBoard.setTop(hbCuentaPrincipal);
			bpDBoard.setCenter(imgViewPrincipal);
		}
		return bpDBoard;
	}
	public BorderPane getContentPrincipal(){
		if(bpPrincipal == null){
			bpPrincipal= new BorderPane();
			bpPrincipal.setStyle("-fx-background-color: #356aa0;");
			
			btnLogin = new Button("LOGEAR");
			btnLogin.addEventHandler(ActionEvent.ACTION, this);
			
			tbInicial = new ToolBar();
			tbInicial.setStyle("-fx-background-color: #d9edf2;");
			tbInicial.getItems().add(btnLogin);
			
			bpPrincipal.setTop(this.tbInicial);
				imgInicial = new Image("inicial.jpg");
		 
				imgvwInicial = new ImageView();
				imgvwInicial.setImage(imgInicial);
		
			bpPrincipal.setCenter(imgvwInicial);
		}
		return bpPrincipal;
	}	
	public GridPane getContentLogin(){
		// if(gpLogin != null){
			// tfNombreLog.setText("");
			// pfContraseniaLog.setText("");
		// }
		if(gpLogin == null){
			gpLogin = new GridPane();
			
			lbNombreLog = new Label("Usuario o e-mail");
			lbContraseniaLog = new Label("Contrasenia ");
			
			tfNombreLog = new TextField();
			tfNombreLog.setPromptText("ej. 'mario' o 'asad@ha.com'");
			GridPane.setMargin(tfNombreLog, new Insets(3,3,15,25));
			tfNombreLog.addEventHandler(KeyEvent.KEY_RELEASED, this);
			
			pfContraseniaLog = new PasswordField();
			pfContraseniaLog.setPromptText("Contrasenia");
			GridPane.setMargin(pfContraseniaLog, new Insets(3,3,3,25));
			pfContraseniaLog.setMinHeight(100);
			pfContraseniaLog.addEventHandler(KeyEvent.KEY_RELEASED, this);
			
			btnHome = new Button("HOME");
			btnHome.addEventHandler(ActionEvent.ACTION, this);
			
			gpLogin.add(lbNombreLog, 0, 0);
			gpLogin.add(lbContraseniaLog, 0, 1);
			gpLogin.add(tfNombreLog, 1, 0);
			gpLogin.add(pfContraseniaLog, 1, 1);
			gpLogin.add(btnHome, 1, 3, 2, 1);
		}
		return gpLogin;
	}
	public BorderPane getContenedorEditarPerfil(){
		if(bpEditarPerfil == null){
			gpEditarPerfil = new GridPane();
			
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
	
	public void setDatosUsuario(Usuario usuario){
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
	
	public void setManejadorMesero(ManejadorMesero instancia){
		this.meseroInstancia = instancia;
	}
	public void setManejadorChef(ManejadorChef instancia){
		this.chefInstancia = instancia;
	}
	
	public void handle(Event event){
		if(event instanceof ActionEvent){
			if(event.getSource().equals(btnLogin)){
				tpPrincipal.getTabs().clear();
				// ResultSet re = cnxConexion.execConsulta("SELECT nombre FROM Usuario WHERE idUsuario = 1");
				// System.out.println(cnxConexion.execConsulta("SELECT nombre FROM Usuario WHERE idUsuario = 1"));
				tpPrincipal.getTabs().add(this.getTabLogin());
			}else if(event.getSource().equals(btnHome)){
				tpPrincipal.getTabs().clear();
				tpPrincipal.getTabs().add(this.getTabInicial());
			}else if(event.getSource().equals(btnEditarPerfil)){
				if(meseroInstancia.getUsuarioIngresado() != null){
					this.setDatosUsuario(meseroInstancia.getUsuarioIngresado());
				}else{
					this.setDatosUsuario(chefInstancia.getUsuarioIngresado());
				}
				if(!getTabPane().getTabs().contains(this.getTabEditarPerfil())){
					tpPrincipal.getTabs().add(this.getTabEditarPerfil());
				}
				setDatosUsuario(new Usuario());
			}else if(event.getSource().equals(btnCerrarSesion)){
				if(meseroInstancia.getUsuarioIngresado() != null){
					meseroInstancia.salirDeSesion();
				}else{
					chefInstancia.salirDeSesion();					
				}
				tpPrincipal.getTabs().clear();
				tpPrincipal.getTabs().add(this.getTabInicial());
			}
		}else if(event instanceof KeyEvent){
			KeyEvent keyEvent = (KeyEvent)event;
			if(keyEvent.getCode()==KeyCode.ENTER){
				if(event.getSource().equals(tfNombreLog) || event.getSource().equals(pfContraseniaLog)){
					if(!tfNombreLog.getText().trim().equals(null) && !pfContraseniaLog.getText().trim().equals(null)){
						tpPrincipal.getTabs().clear();
						if(meseroInstancia.comprobar(tfNombreLog.getText().trim(), pfContraseniaLog.getText().trim()) != false){
							tpPrincipal.getTabs().add(this.getTabPrincipal());
						}else{
							if(chefInstancia.comprobar(tfNombreLog.getText(), pfContraseniaLog.getText())){
								tpPrincipal.getTabs().add(this.getTabPrincipal());
							} else{
								tpPrincipal.getTabs().add(this.getTabInicial());
							}
						}
					}
				}			
			}
		}
	}
}
