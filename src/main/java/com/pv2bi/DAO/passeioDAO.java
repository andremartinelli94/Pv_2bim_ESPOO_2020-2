package com.pv2bi.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pv2bi.ConexaoHSQLDB;
import com.pv2bi.model.passeio;
import com.pv2bi.model.promocao;

public class passeioDAO extends ConexaoHSQLDB implements promocao {
	final String Insert_SQL_Passeio = " INSERT INTO TB_PASSEIO (nome, eletrico, qtd_passageiros) VALUES (?, ?, ?) ";
	final String SQL_SELECT = " SELECT * FROM TB_PASSEIO ";
	final String SQL_SELECT_Passeio_ID = " SELECT * FROM TB_PASSEIO WHERE ID =? ";
	final String SQL_ALTERA_Passeio = " UPDATE TB_PASSEIO SET NOME = ?, ELETRICO =?, QTD_PASSAGEIROS =? WHERE ID =? ";
	final String SQL_DELETA_SQL_Passeio = "DELETE FROM TB_PASSEIO WHERE ID = ?";

	public void inserirPasseio(passeio index) {

		try (Connection connection = this.connectar();
				PreparedStatement pst = connection.prepareStatement(Insert_SQL_Passeio);) {
			pst.setString(1, index.getNome());
			pst.setString(2, index.getEletrico());
			pst.setInt(3, index.getQtd_passageiros());
			pst.executeUpdate();
			System.out.println("Cadastrado com Sucesso!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean alterarPasseio(passeio index) {
		try (Connection connection = this.connectar();
				PreparedStatement pst = connection.prepareStatement(SQL_ALTERA_Passeio);){
			pst.setString(1, index.getNome());
			pst.setString(2, index.getEletrico());
			pst.setInt(3, index.getQtd_passageiros());

			pst.setInt(4, index.getId());
			pst.executeUpdate();
			System.out.println("Editado com sucesso!");

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public boolean removePasseio(int index) {
		try (Connection connection = this.connectar();
				PreparedStatement pst = connection.prepareStatement(SQL_DELETA_SQL_Passeio);) {
			pst.setInt(1, index);
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return true;
	}
	
	public List<passeio> listarPasseio() {
		List<passeio> listaPas = new ArrayList<passeio>();
		try (Connection connection = this.connectar();
				PreparedStatement pst = connection.prepareStatement(SQL_SELECT);) {
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				passeio index = new passeio();
				index.setId(rs.getInt("ID"));
				index.setNome(rs.getString("NOME"));
				index.setEletrico(rs.getString("ELETRICO"));
				index.setQtd_passageiros(rs.getInt("QTD_PASSAGEIROS"));
				listaPas.add(index);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaPas;
	}
	
	public passeio pesquisar(int id) {
		passeio uti = null;
		try (Connection connection = connectar();
				PreparedStatement pst = connection.prepareStatement(SQL_SELECT_Passeio_ID);) {
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				uti = new passeio();
				uti.setId(rs.getInt("ID"));
				uti.setNome(rs.getString("NOME"));
				uti.setEletrico(rs.getString("ELETRICO"));
				uti.setQtd_passageiros(rs.getInt("QTD_PASSAGEIROS"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uti;
	}

	@Override
	public double taxaSobreLocacao(double valor) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
