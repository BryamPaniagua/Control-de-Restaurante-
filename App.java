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
import javafx.geometry.Pos;

import org.bryampaniagua.database.Conexion;
import org.bryampaniagua.manejadores.ManejadorMesero;
import org.bryampaniagua.manejadores.ManejadorChef;
import org.bryampaniagua.manejadores.ManejadorUsuario;
import org.bryampaniagua.beans.Usuario;

public class App extends Application implements EventHandler<Event>{
	private Stage primaryStage;
	private Scene primaryScene;
	private TabPane tpPrincipal;
	private Tab tbLogin, tabPrincipal, tbPrincipalMesero, tbPrincipalChef, tbEditarPerfil, tbUsuarioNoEncontrado;
	private GridPane gpLogin, gpEditarPerfil, gpUsuarioNoEncontrado;
	private Label lbNombreLog, lbContraseniaLog, lbBienvenida, lbUsuarioNoEncontrado;
	private TextField tfNombreLog, tfNombre, tfNick, tfCorreo, tfEdad, tfTelefono;
	private PasswordField pfContraseniaLog;
	private Button btnLogin, btnHome, btnEditarPerfil, btnCerrarSesion;
	private BorderPane bpPrincipal, bpPropiedadesChef, bpPropiedadesMesero, bpEditarPerfil;
	private ToolBar tlbPrincipal;
	private Image imgLogin, imgHome;
	private Image imgPrincipal;
	private ImageView imgvwPrincipal, imgvwLogin;
	private HBox hbConfiguracion;
	
	private Conexion cnx;
	private ManejadorMesero meseroInstancia;
	private ManejadorChef chefInstancia;
	private ManejadorUsuario usuarioInstancia;
	
	public void start(Stage primaryStage){
		this.primaryStage = primaryStage;
		cnx = new Conexion();
		
		this.setManejadorMesero(new ManejadorMesero(cnx));
		this.setManejadorUsuario(new ManejadorUsuario(cnx));
		this.setManejadorChef(new ManejadorChef(cnx));
		
		
		primaryScene = new Scene(this.getTabPane());
		
		primaryStage.setTitle("RESTAURANTE"); 
		primaryStage.setScene(primaryScene);
		primaryStage.setResizable(true); 
		primaryStage.setHeight(700);
		primaryStage.setWidth(700);
		primaryStage.show();
	}
	
	public TabPane getTabPane(){
		if(tpPrincipal == null){
			tpPrincipal = new TabPane();
			
			tpPrincipal.getTabs().add(this.getTabPrincipal());
		}
		return tpPrincipal;
	}
	
	public Tab getTabPrincipal(){
		if(tabPrincipal == null){
			tabPrincipal = new Tab("Restaurante");
			tabPrincipal.setClosable(false);
			tabPrincipal.setStyle("-fx-background-color: #00A090;");
			
			tabPrincipal.setContent(this.getContentPrincipal());
		}
		return tabPrincipal;
	}
	public Tab getTabLogin(){
		if(tbLogin == null){
			tbLogin = new Tab("LOGIN");
			tbLogin.setClosable(false);
			tbLogin.setStyle("-fx-background-color: #00A080;");
			
			imgLogin = new Image("Imagenes/log.jpg");
			
			imgvwLogin = new ImageView();
			imgvwLogin.setImage(imgLogin);
			
			tbLogin.setContent(this.getContentLogin());
		}
		return tbLogin;
	}
	public Tab getTabPrincipalMesero(){
		if(tbPrincipalMesero == null){
			tbPrincipalMesero = new Tab(("Bienvenido "+usuarioInstancia.getUsuarioIngresado().getNick()));
			tbPrincipalMesero.setClosable(false);
			
			tbPrincipalMesero.setContent(this.getContenedorPropiedadesMesero());
		}
		return tbPrincipalMesero;
	}
	public Tab getTabPrincipalChef(){
		if(tbPrincipalChef == null){
			tbPrincipalChef = new Tab(("Bienvenido "+usuarioInstancia.getUsuarioIngresado().getNick()));
			tbPrincipalChef.setClosable(false);
			
			tbPrincipalChef.setContent(this.getContenedorPropiedadesChef());
		}
		return tbPrincipalChef;
	}
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
	
	public BorderPane getContentPrincipal(){
		if(bpPrincipal == null){
			bpPrincipal= new BorderPane();
			bpPrincipal.setStyle("-fx-background-color: #356aa0;");
			
			btnLogin = new Button("LOGEAR");
			btnLogin.addEventHandler(ActionEvent.ACTION, this);
			
			tlbPrincipal = new ToolBar();
			tlbPrincipal.setStyle("-fx-background-color: #d9edf2;");
			tlbPrincipal.getItems().add(btnLogin);
			
			bpPrincipal.setTop(this.tlbPrincipal);
				imgPrincipal = new Image("inicial.jpg");
		 
				imgvwPrincipal = new ImageView();
				imgvwPrincipal.setImage(imgPrincipal);
		
			bpPrincipal.setCenter(imgvwPrincipal);
		}
		return bpPrincipal;
	}	
	public GridPane getContentLogin(){
		if(gpLogin == null){
			gpLogin = new GridPane();
			
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
		}
		return gpLogin;
	}
	public BorderPane getContenedorPropiedadesChef(){
		if(bpPropiedadesChef == null){
			bpPropiedadesChef = new BorderPane();
				
			Image imgInicioSesion = new Image("chef4.jpg");
			ImageView imgViewInicioSesion = new ImageView(imgInicioSesion);
			
			bpPropiedadesChef.setTop(this.getContentConfiguracion());
			bpPropiedadesChef.setCenter(imgViewInicioSesion);
		}
		return bpPropiedadesChef;
	}
	public BorderPane getContenedorPropiedadesMesero(){
		if(bpPropiedadesMesero == null){
			bpPropiedadesMesero = new BorderPane();
			
			Image imgPrincipal = new Image("mes1.png");
			ImageView imgViewPrincipal = new ImageView(imgPrincipal);
			
			bpPropiedadesMesero.setTop(this.getContentConfiguracion());
			bpPropiedadesMesero.setCenter(imgViewPrincipal);
		}
		return bpPropiedadesMesero;
	}
	public HBox getContentConfiguracion(){
		String nombreIngresado = usuarioInstancia.getUsuarioIngresado().getNombre();
		
		hbConfiguracion = new HBox();
		
		btnCerrarSesion = new Button("SALIR");
		BorderPane.setMargin(btnCerrarSesion, new Insets(0,10,0,10));
		btnCerrarSesion.addEventHandler(ActionEvent.ACTION, this);
		
		lbBienvenida = new Label("BIENVENIDO, has ingresado como "+nombreIngresado);
		BorderPane.setMargin(lbBienvenida, new Insets(0,10,0,10));
		
		btnEditarPerfil = new Button("Editar PERFIL");
		BorderPane.setMargin(btnEditarPerfil, new Insets(10,0,10,0));
		btnEditarPerfil.addEventHandler(ActionEvent.ACTION, this);
		
		hbConfiguracion.getChildren().addAll(lbBienvenida, btnCerrarSesion, btnEditarPerfil);
		
		return hbConfiguracion;
	}
	public BorderPane getContenedorEditarPerfil(Usuario usuario){
		if(bpEditarPerfil == null){
			gpEditarPerfil = new GridPane();
			
			
			tfNombre = new TextField();
			tfNombre.setText(usuario.getNombre());
			tfNombre.addEventHandler(KeyEvent.KEY_RELEASED, this);
			
			tfNick = new TextField();
			tfNick.setText(usuario.getNick());
			tfNick.addEventHandler(KeyEvent.KEY_RELEASED, this);
			
			tfCorreo = new TextField();
			tfCorreo.setText(usuario.getCorreo());
			tfCorreo.addEventHandler(KeyEvent.KEY_RELEASED, this);
			
			tfEdad = new TextField();
			tfEdad.setText(String.valueOf(usuario.getEdad()));
			tfEdad.addEventHandler(KeyEvent.KEY_RELEASED, this);
			
			tfTelefono = new TextField();
			tfTelefono.setText(String.valueOf(usuario.getTelefono()));
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
	
	public Button getBtnHome(){
		if(btnHome == null){
			btnHome = new Button("Regresar");
			btnHome.addEventHandler(ActionEvent.ACTION, this);
			GridPane.setMargin(btnHome, new Insets(50,3,3,25));
		}
		return btnHome;
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
	
	public void handle(Event event){
		if(event instanceof ActionEvent){
			tpPrincipal.getTabs().clear();
			if(event.getSource().equals(btnLogin)){
				tpPrincipal.getTabs().add(this.getTabLogin());
			}else if(event.getSource().equals(btnHome)){
				tpPrincipal.getTabs().add(this.getTabPrincipal());
			}else if(event.getSource().equals(btnEditarPerfil)){
				if(!getTabPane().getTabs().contains(this.getTabEditarPerfil())){
					System.out.println(usuarioInstancia.getUsuarioIngresado().getNombre());
					System.out.println(usuarioInstancia.getUsuarioIngresado().getNick());
					tpPrincipal.getTabs().add(this.getTabEditarPerfil());
				}
			}else if(event.getSource().equals(btnCerrarSesion)){
				usuarioInstancia.salirDeSesion();
				tpPrincipal.getTabs().add(this.getTabPrincipal());
			}
		}else if(event instanceof KeyEvent){
			KeyEvent keyEvent = (KeyEvent)event;
			if(keyEvent.getCode() == KeyCode.ENTER){
				tpPrincipal.getTabs().clear();
				if(event.getSource().equals(tfNombreLog) || event.getSource().equals(pfContraseniaLog)){
					if(!tfNombreLog.getText().trim().equals(null) && !pfContraseniaLog.getText().trim().equals(null)){
						if(usuarioInstancia.comprobar(tfNombreLog.getText().trim(), pfContraseniaLog.getText().trim()) != false){
							if(usuarioInstancia.getUsuarioIngresado().getIdModulo() == 1){
								tpPrincipal.getTabs().add(this.getTabPrincipalMesero());								
							}else{
								tpPrincipal.getTabs().add(this.getTabPrincipalChef());
							}
						}else{
							tpPrincipal.getTabs().add(this.getTabUsuarioNoEncontrado());
						}
					}
				}else if(event.getSource().equals(tfNombre) || event.getSource().equals(tfNick) || event.getSource().equals(tfCorreo) || event.getSource().equals(tfEdad) || event.getSource().equals(tfTelefono)){
					if(!tfNombre.getText().equals(null) && !tfNick.getText().equals(null) && !tfCorreo.getText().equals(null) && !tfEdad.getText().equals(null) && !tfTelefono.getText().equals(null)){
						Usuario usuario = new Usuario();
						usuario.setNombre(tfNombre.getText());
						usuario.setNick(tfNombre.getText());
						usuario.setCorreo(tfNombre.getText());
						usuario.setEdad(Integer.parseInt(tfEdad.getText()));
						usuario.setTelefono(Integer.parseInt(tfTelefono.getText()));
						usuarioInstancia.modificarUsuario(usuario);
					}				
					if(usuarioInstancia.getUsuarioIngresado().getIdModulo() == 1){
						tpPrincipal.getTabs().add(this.getTabPrincipalMesero());								
					}else{
						tpPrincipal.getTabs().add(this.getTabPrincipalChef());
					}
				}			
			}
		}
	}
}
