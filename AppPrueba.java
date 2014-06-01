// package org.bryampaniagua.sistema.AppPrueba;

// import javafx.application.Application;
// import javafx.stage.Stage;
// import javafx.scene.Scene;
// import javafx.scene.control.TabPane;
// import javafx.scene.control.Tab;
// import javafx.scene.layout.GridPane;
// import javafx.scene.layout.BorderPane;
// import javafx.scene.control.Label;
// import javafx.scene.control.TextField;
// import javafx.scene.control.PasswordField;
// import javafx.scene.control.Button;
// import javafx.scene.control.ToolBar;
// import javafx.scene.image.Image;
// import javafx.event.Event;
// import javafx.event.EventHandler;
// import javafx.event.ActionEvent;
// import javafx.scene.input.KeyEvent;
// import javafx.stage.Modality;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.input.KeyCode;

// import org.bryampaniagua.database.Conexion;
// import org.bryampaniagua.manejadores.ManejadorMesero;
// import org.bryampaniagua.manejadores.ManejadorChef;

// public class AppPrueba extends Application implements EventHandler<Event>{
	// private Stage primaryStage;
	// private Scene primaryScene;
	// private TabPane tpPrincipal;
	// private Tab tbLogin, tabInicial, tbDBoard;
	// private GridPane gpLogin;
	// private Label lbNombreLog, lbContraseniaLog;
	// private TextField tfNombreLog;
	// private PasswordField pfContraseniaLog;
	// private Button btnLogin, btnHome;
	// private Image imgLogin, imgHome;
	// private BorderPane bpPrincipal, bpDBoard;
	// private ToolBar tbInicial;
	// private Image imgInicial;
	// private ImageView imgvwInicial;
	
	// private Conexion cnxConexion;
	// private ManejadorMesero meseroInstancia;
	// private ManejadorChef chefInstancia;

	// public void start(Stage primaryStage){
		// this.primaryStage = primaryStage;
		
		// setManejadorMesero(new ManejadorMesero(cnxConexion));
		// setManejadorChef(new ManejadorChef(cnxConexion));
		
		
		// primaryScene = new Scene(this.getTabPane());
		
		// primaryStage.setTitle("RESTAURANTE"); 
		// primaryStage.setScene(primaryScene);
		// primaryStage.setResizable(true); 
		// primaryStage.show();
	// }
	
	// public TabPane getTabPane(){
		// if(tpPrincipal == null){
			// tpPrincipal = new TabPane();
			// tpPrincipal.setStyle("-fx-background-color: #80BFFF;");
			// tpPrincipal.setStyle("-fx-background-image: url("+"HFrancesa.jpg"+");");
			
			// tpPrincipal.getTabs().add(this.getTabLogin());
		// }
		// return tpPrincipal;
	// }
	
	// public Tab getTabLogin(){
		// if(tbLogin == null){
			// tbLogin = new Tab("LOGIN");
			
			// imgLogin = new Image("Imagenes/log.jpg");
			
			// imgView 
			
			// tbLogin.setContent(this.getContentLogin());
		// }
		// return tbLogin;
	// }
	// public Tab getTabPrincipal(){
		// if(tbDBoard == null){
			// tbDBoard= new Tab();
			
			// bpDBoard = new BorderPane();
			
			// String nombreIngresado = null;
			// if(meseroInstancia.getUsuarioIngresado() != null){
				// nombreIngresado = meseroInstancia.getUsuarioIngresado().getNombre();
			// }else{
				// nombreIngresado = chefInstancia.getUsuarioIngresado().getNombre();
			// }
			
			// bpDBoard.setCenter(new Label("BIENVENIDO, has ingresado como "+nombreIngresado));
			
			// tbDBoard.setContent(bpDBoard);
		// }
		// return tbDBoard;
	// }
	
	// public GridPane getContentLogin(){
		// if(gpLogin == null){
			// gpLogin = new GridPane();
			
			// lbNombreLog = new Label("e-mail");
			// lbContraseniaLog = new Label("Contrasenia ");
			
			// tfNombreLog = new TextField();
			// tfNombreLog.setPromptText("ej. 'asad@ha.com'");
			// tfNombreLog.addEventHandler(KeyEvent.KEY_RELEASED, this);
			
			// pfContraseniaLog = new PasswordField();
			// pfContraseniaLog.setPromptText("Contrasenia");
			// pfContraseniaLog.addEventHandler(KeyEvent.KEY_RELEASED, this);
			
			// Image img = new Image("home.jpg");
			// ImageView imgV = new ImageView(img);
			// imgV.setFitWidth(25);
			// imgV.setPreserveRatio(true);
			// imgV.setSmooth(true);
			// imgV.setCache(true);
			
			// btnHome = new Button(" ", imgV);
			// btnHome.addEventHandler(ActionEvent.ACTION, this);
			// btnHome.setStyle("-fx-background-color: #FFFFFF;");
			
			// gpLogin.add(lbNombreLog, 0, 0);
			// gpLogin.add(lbContraseniaLog, 0, 1);
			// gpLogin.add(tfNombreLog, 1, 0);
			// gpLogin.add(pfContraseniaLog, 1, 1);
			// gpLogin.add(btnHome, 1, 3, 2, 1);
		// }else{
			// tfNombreLog.clear();
			// pfContraseniaLog.clear();
		// }
		// return gpLogin;
	// }
	
	// public void setManejadorMesero(ManejadorMesero instancia){
		// if(meseroInstancia == null){
			// meseroInstancia = new ManejadorMesero(cnxConexion);
		// }
		// this.meseroInstancia = instancia;
	// }
	// public void setManejadorChef(ManejadorChef instancia){
		// if(chefInstancia == null){
			// chefInstancia = new ManejadorChef(cnxConexion);
		// }
		// this.chefInstancia = instancia;
	// }
	
	// public void handle(Event event){
		// if(event instanceof ActionEvent){
			// tpPrincipal.getTabs().clear();
			// if(event.getSource().equals(btnLogin)){
				
				// tpPrincipal.getTabs().add(this.getTabLogin());
			// }
			// if(event.getSource().equals(btnHome)){
				
				// tpPrincipal.getTabs().add(this.getTabLogin());
			// }
		// }else if(event instanceof KeyEvent){
			// KeyEvent keyEvent = (KeyEvent)event;
			// if(keyEvent.getCode()==KeyCode.ENTER){
				// if(event.getSource().equals(tfNombreLog) || event.getSource().equals(pfContraseniaLog)){
					// if(!tfNombreLog.getText().trim().equals(null) && !pfContraseniaLog.getText().trim().equals(null)){
						// tpPrincipal.getTabs().clear();
						// if(meseroInstancia.comprobar(tfNombreLog.getText().trim(), pfContraseniaLog.getText().trim()) != false){
							// tpPrincipal.getTabs().add(this.getTabPrincipal());
						// }else{
							// if(chefInstancia.comprobar(tfNombreLog.getText(), pfContraseniaLog.getText())){
								// tpPrincipal.getTabs().add(this.getTabPrincipal());
							// } else{
								// tpPrincipal.getTabs().add(this.getTabLogin());
							// }
						// }
					// }
				// }			
			// }
		// }
	// }
// }