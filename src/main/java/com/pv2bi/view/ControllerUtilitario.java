package com.pv2bi.view;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.pv2bi.DAO.utilitarioDAO;
import com.pv2bi.model.utilitario;

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

public class ControllerUtilitario extends Application implements Initializable{

	private ObservableList<utilitario> listTabelaUtilitario = FXCollections.observableArrayList();
	
    @FXML
    private TextField txtNome;

    @FXML
    private ComboBox<String> comEletrico;

    @FXML
    private TextField txtTransporte;

    @FXML
    private TextField txtID;
    
    @FXML
    private TextField txtBusca;
    
    @FXML
    private TextField txtValor;

    @FXML
    private Button btnCriar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableView<utilitario> tabelaUtilitario;

    @FXML
    private TableColumn<utilitario, Integer> colunID;

    @FXML
    private TableColumn<utilitario, String> colunName;

    @FXML
    private TableColumn<utilitario, String> colunEletrico;

    @FXML
    private TableColumn<utilitario, String> colunTransporte;
    
    @FXML
    private TableColumn<utilitario, Double> colunValor;

    @FXML
    void criar(ActionEvent event) {
    	utilitario index= pegaDados();
    	
    	new utilitarioDAO().inserirUtilitario(index);
    	listar();
     }

    @FXML
    void pesquisar(ActionEvent event) {
    	String idString = txtBusca.getText();
    	utilitario uti = null;
		if (!idString.equals("")) {
			try {
				int id = Integer.valueOf(idString);
				uti = new utilitarioDAO().pesquisar(id);
			} catch (Exception e) {
			}
			if (uti != null) {
				txtID.setText(uti.getId() + "");
				txtNome.setText(uti.getNome());
				comEletrico.setValue(uti.getEletrico());
				txtTransporte.setText(uti.getTransporte());
				txtValor.setText(uti.getValor_loc()+ "");
			}
		}
		txtBusca.clear();
    }
    
    @FXML
    void editar(ActionEvent event) {
    	utilitario uti = pegaDadosId();
    	if(String.valueOf(uti.getId()) == null || String.valueOf(uti.getId()) =="") {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Alerta");
    		alert.setHeaderText("Utilitario não selecionado");
    		alert.setContentText("Selecione um Utilitario para alterar");
    	}
    	else {
    		Alert alert = new Alert(AlertType.CONFIRMATION);
        	alert.setTitle("Alterar Utilitario");
        	alert.setHeaderText("Você está prestes a alterar um Utilitario");
        	alert.setContentText("Tem certeza que deseja altterar o Utilitario?");
        	Optional<ButtonType> result = alert.showAndWait();
        	if (result.get() == ButtonType.OK){	
        		limparcampos();
    		new utilitarioDAO().alterarUtilitario(uti);
    		listar();
        	}
    	}
    }
    
    @FXML
    void excluir(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Deletar Utilitario");
    	alert.setHeaderText("Você está prestes a deletar um Utilitario");
    	alert.setContentText("Tem certeza que deseja deletar o Utilitario?");
    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    		utilitario uti= pegaDadosId();
    		new utilitarioDAO().removeUtilitario(uti.getId());
    		limparcampos();
        	listar();
    	}
    }

    @FXML
    void voltar(ActionEvent event) {
    	
    }

	@Override
	public void start(Stage stage) throws Exception {
		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("TelaUtilitario.fxml"));
			Scene sc = new Scene(pane);
			stage.setScene(sc);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		comEletrico.getItems().addAll(options);
		listar();
	}

	public utilitario pegaDados() {
		return new utilitario(txtNome.getText(), comEletrico.getValue(), txtTransporte.getText(), Double.valueOf(txtValor.getText()));
	}
	
	public utilitario pegaDadosId() {
		return new utilitario(Integer.parseInt(txtID.getText()),txtNome.getText(), comEletrico.getValue(), txtTransporte.getText(), Double.valueOf(txtValor.getText()));
	}
	
	public void listar() {
		if (!listTabelaUtilitario.isEmpty()) {
			listTabelaUtilitario.clear();
		}		
		List<utilitario> lista = new utilitarioDAO().listarUtilitarios();
		   for (utilitario util : lista) {
			   utilitario uti = new utilitario(util.getId(), util.getNome(), util.getEletrico(),util.getTransporte(), util.getValor_loc());
			   listTabelaUtilitario.add(uti);
	        }
		   colunID.setCellValueFactory(new PropertyValueFactory<utilitario, Integer>("Id"));
		   colunName.setCellValueFactory(new PropertyValueFactory<utilitario, String>("Nome"));
		   colunEletrico.setCellValueFactory(new PropertyValueFactory<utilitario, String>("Eletrico"));
		   colunTransporte.setCellValueFactory(new PropertyValueFactory<utilitario, String>("Transporte"));
		   colunValor.setCellValueFactory(new PropertyValueFactory<utilitario, Double>("valor_loc"));
		   tabelaUtilitario.setItems(listTabelaUtilitario);
	}
	
	public void limparcampos() {
		
		txtID.clear();
		txtNome.clear();
		txtTransporte.clear();
		txtValor.clear();
		tabelaUtilitario.getItems().clear();
		
	}
	
	ObservableList<String> options = FXCollections.observableArrayList("Sim","Não");

}
