package com.pv2bi.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pv2bi.ConexaoHSQLDB;
import com.pv2bi.model.promocao;
import com.pv2bi.model.utilitario;

public class utilitarioDAO extends ConexaoHSQLDB implements promocao{
	final String Insert_SQL_Utilitario = " INSERT INTO TB_UTILITARIO (nome, eletrico, transporte, valor_loc) VALUES (?, ?, ?, ?) ";
	final String SQL_SELECT = " SELECT * FROM TB_UTILITARIO ";
	final String SQL_SELECT_UTILITARIO_ID = " SELECT * FROM TB_UTILITARIO WHERE ID =? ";
	final String SQL_ALTERA_UTILITARIO = " UPDATE TB_UTILITARIO SET NOME = ?, ELETRICO =?, TRANSPORTE =?, VALOR_LOC =? WHERE ID =? ";
	final String SQL_DELETA_SQL_UTILITARIO = "DELETE FROM TB_UTILITARIO WHERE ID = ?";

	public void inserirUtilitario(utilitario index) {

		try (Connection connection = this.connectar();
				PreparedStatement pst = connection.prepareStatement(Insert_SQL_Utilitario);) {
			pst.setString(1, index.getNome());
			pst.setString(2, index.getEletrico());
			pst.setString(3, index.getTransporte());
			pst.setDouble(4, taxaSobreLocacao(index.getValor_loc()));
			pst.executeUpdate();
			System.out.println("Cadastrado com Sucesso!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean alterarUtilitario(utilitario index) {
		try (Connection connection = this.connectar();
				PreparedStatement pst = connection.prepareStatement(SQL_ALTERA_UTILITARIO);){
			pst.setString(1, index.getNome());
			pst.setString(2, index.getEletrico());
			pst.setString(3, index.getTransporte());
			pst.setDouble(4, index.getValor_loc());
			
			pst.setInt(5, index.getId());
			pst.executeUpdate();
			System.out.println("Editado com sucesso!");

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public boolean removeUtilitario(int index) {
		try (Connection connection = this.connectar();
				PreparedStatement pst = connection.prepareStatement(SQL_DELETA_SQL_UTILITARIO);) {
			pst.setInt(1, index);
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return true;
	}
	
	public List<utilitario> listarUtilitarios() {
		List<utilitario> listaUti = new ArrayList<utilitario>();
		try (Connection connection = this.connectar();
				PreparedStatement pst = connection.prepareStatement(SQL_SELECT);) {
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				utilitario index = new utilitario();
				index.setId(rs.getInt("ID"));
				index.setNome(rs.getString("NOME"));
				index.setEletrico(rs.getString("ELETRICO"));
				index.setTransporte(rs.getString("TRANSPORTE"));
				index.setValor_loc(rs.getDouble("VALOR_LOC"));
				listaUti.add(index);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaUti;
	}
	
	public utilitario pesquisar(int id) {
		utilitario uti = null;
		try (Connection connection = connectar();
				PreparedStatement pst = connection.prepareStatement(SQL_SELECT_UTILITARIO_ID);) {
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				uti = new utilitario();
				uti.setId(rs.getInt("ID"));
				uti.setNome(rs.getString("NOME"));
				uti.setEletrico(rs.getString("ELETRICO"));
				uti.setTransporte(rs.getString("TRANSPORTE"));
				uti.setValor_loc(rs.getDouble("VALOR_LOC"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uti;
	}

	@Override
	public double taxaSobreLocacao(double valor) {
		double custoFinal = valor * 0.20;
		return custoFinal + valor;
	}
	
	
	
}
