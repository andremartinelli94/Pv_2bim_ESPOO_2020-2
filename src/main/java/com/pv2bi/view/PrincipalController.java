package com.pv2bi.view;


import java.awt.Button;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PrincipalController extends Application implements Initializable{
	
    @FXML
    private Button btnutilitario;

    @FXML
    private Button btnSair;

    @FXML
    void chamaTelaPasseio(ActionEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TelaPasseio.fxml"));
		Parent root1 = fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));
		stage.show();
    	
    }
    
    @FXML
    void chamaTelaUtilitario() throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TelaUtilitario.fxml"));
		Parent root1 = fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));
		stage.show();
    	
    }

	public void execute() {
		// TODO Auto-generated method stub
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("TelaPrincipal.fxml"));
			Scene sc = new Scene(pane);
			stage.setScene(sc);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

    @FXML
    void sair(ActionEvent event) {
	   
   	Alert alert = new Alert(AlertType.CONFIRMATION);
   	alert.setTitle("Sair");
   	alert.setHeaderText("Você está prestes a sair da aplicação");
   	alert.setContentText("Tem certeza que deseja sair?");
   	Optional<ButtonType> result = alert.showAndWait();
   	if (result.get() == ButtonType.OK){
   		System.exit(0);
   	}
    }


}
