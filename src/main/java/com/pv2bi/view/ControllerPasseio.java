package com.pv2bi.view;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.pv2bi.DAO.passeioDAO;
import com.pv2bi.model.passeio;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerPasseio extends Application implements Initializable{
		private ObservableList<passeio> listaTabelaPasseio = FXCollections.observableArrayList();

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			listar();
			comEletrico.getItems().addAll(options);
		}

		@Override
		public void start(Stage stage) throws Exception {
			try {
				AnchorPane pane = FXMLLoader.load(getClass().getResource("TelaPasseio.fxml"));
				Scene sc = new Scene(pane);
				stage.setScene(sc);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	    @FXML
	    private TextField txtID;

	    @FXML
	    private TextField txtNome;

	    @FXML
	    private TextField txtQtd;

	    @FXML
	    private TextField txtBusca;
	    
	    @FXML
	    private ComboBox<String> comEletrico;

	    @FXML
	    private TableView<passeio> tabelaPasseio;

	    @FXML
	    private TableColumn<passeio, Integer> colunID;

	    @FXML
	    private TableColumn<passeio, String> colunName;

	    @FXML
	    private TableColumn<passeio, String> colunEletrico;

	    @FXML
	    private TableColumn<passeio, Integer> colunQtd;

	    @FXML
	    private Button btnCriar;

	    @FXML
	    private Button btnEditar;

	    @FXML
	    private Button btnExcluir;

	    @FXML
	    void criar(ActionEvent event) {

	    	passeio index= pegaDados();
	    	
	    	new passeioDAO().inserirPasseio(index);
	    	listar();	    	
	    }

	    @FXML
	    void editar(ActionEvent event) {

	    	passeio uti = pegaDadosId();
	    	if(String.valueOf(uti.getId()) == null || String.valueOf(uti.getId()) =="") {
	    		Alert alert = new Alert(AlertType.WARNING);
	    		alert.setTitle("Alerta");
	    		alert.setHeaderText("Automóvel Passeio não selecionado");
	    		alert.setContentText("Selecione um Automóvel Passeio para alterar");
	    	}
	    	else {
	    		Alert alert = new Alert(AlertType.CONFIRMATION);
	        	alert.setTitle("Alterar Automóvel Passeio");
	        	alert.setHeaderText("Você está prestes a alterar um Automóvel Passeio");
	        	alert.setContentText("Tem certeza que deseja altterar o Automóvel Passeio?");
	        	Optional<ButtonType> result = alert.showAndWait();
	        	if (result.get() == ButtonType.OK){	
	        		limparcampos();
	    		new passeioDAO().alterarPasseio(uti);
	    		listar();
	        	}
	    	}
	    	
	    }

	    @FXML
	    void excluir(ActionEvent event) {
	    	
	    	Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setTitle("Deletar Automóvel Passeio");
	    	alert.setHeaderText("Você está prestes a deletar um Automóvel Passeio");
	    	alert.setContentText("Tem certeza que deseja deletar o Automóvel Passeio?");
	    	Optional<ButtonType> result = alert.showAndWait();
	    	if (result.get() == ButtonType.OK){
	    		passeio uti= pegaDadosId();
	    		new passeioDAO().removePasseio(uti.getId());
	    		limparcampos();
	        	listar();
	    	}
	    }

	    @FXML
	    void pesquisar(ActionEvent event) {

	    	String idString = txtBusca.getText();
	    	passeio uti = null;
			if (!idString.equals("")) {
				try {
					int id = Integer.valueOf(idString);
					uti = new passeioDAO().pesquisar(id);
				} catch (Exception e) {
				}
				if (uti != null) {
					txtID.setText(uti.getId() + "");
					txtNome.setText(uti.getNome());
					comEletrico.setValue(uti.getEletrico());
					txtQtd.setText(uti.getQtd_passageiros()+ "");
				}
			}
			txtBusca.clear();
	    	
	    }
	    
		public passeio pegaDados() {
			return new passeio(txtNome.getText(), comEletrico.getValue(),Integer.parseInt(txtQtd.getText()));
		}
		
		public passeio pegaDadosId() {
			return new passeio(Integer.parseInt(txtID.getText()),txtNome.getText(), comEletrico.getValue(), Integer.parseInt(txtQtd.getText()));
		}
		
		public void listar() {
			if (!listaTabelaPasseio.isEmpty()) {
				listaTabelaPasseio.clear();
			}		
			List<passeio> lista = new passeioDAO().listarPasseio();
			   for (passeio obj : lista) {
				   passeio pas = new passeio(obj.getId(), obj.getNome(), obj.getEletrico(),obj.getQtd_passageiros());
				   listaTabelaPasseio.add(pas);
		        }
			   colunID.setCellValueFactory(new PropertyValueFactory<passeio, Integer>("Id"));
			   colunName.setCellValueFactory(new PropertyValueFactory<passeio, String>("Nome"));
			   colunEletrico.setCellValueFactory(new PropertyValueFactory<passeio, String>("Eletrico"));
			   colunQtd.setCellValueFactory(new PropertyValueFactory<passeio, Integer>("qtd_passageiros"));
			   tabelaPasseio.setItems(listaTabelaPasseio);
		}
		
		public void limparcampos() {
			
			txtID.clear();
			txtNome.clear();
			txtQtd.clear();
			tabelaPasseio.getItems().clear();
			
		}
		
		ObservableList<String> options = FXCollections.observableArrayList("Sim","Não");

}
