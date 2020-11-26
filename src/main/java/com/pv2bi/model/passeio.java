package com.pv2bi.model;

public class passeio extends automovel{

	private int qtd_passageiros;
	
	public passeio(int id, String nome, String eletrico) {
		super(id, nome, eletrico);
		// TODO Auto-generated constructor stub
	}

	public int getQtd_passageiros() {
		return qtd_passageiros;
	}

	public void setQtd_passageiros(int qtd_passageiros) {
		this.qtd_passageiros = qtd_passageiros;
	}

	@Override
	public String toString() {
		return "passeio [qtd_passageiros=" + qtd_passageiros + "]";
	}

	public passeio(int id, String nome, String eletrico, int qtd_passageiros) {
		super(id, nome, eletrico);
		this.qtd_passageiros = qtd_passageiros;
	}
	
	public passeio(String nome, String eletrico, int qtd_passageiros) {
		super(nome, eletrico);
		this.qtd_passageiros = qtd_passageiros;
	}
	

	public passeio() {
		// TODO Auto-generated constructor stub
		super();
	}
}
