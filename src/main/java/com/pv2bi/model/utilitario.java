package com.pv2bi.model;

public class utilitario extends automovel{
	
	public utilitario(int id, String nome, String eletrico) {
		super(id, nome, eletrico);
		// TODO Auto-generated constructor stub
	}

	private String Transporte;
	private double valor_loc;

	public double getValor_loc() {
		return valor_loc;
	}

	public void setValor_loc(double valor_loc) {
		this.valor_loc = valor_loc;
	}

	public String getTransporte() {
		return Transporte;
	}

	public void setTransporte(String transporte) {
		Transporte = transporte;
	}

	@Override
	public String toString() {
		return "utilitario [Transporte=" + Transporte + "]";
	}
	
	public utilitario(String nome, String eletrico, String Transporte,double valor) {
		// TODO Auto-generated constructor stub
		super(nome, eletrico);
		this.Transporte = Transporte;
		this.valor_loc = valor;
	}
	
	public utilitario(int Id,String nome, String eletrico, String Transporte,double valor) {
		// TODO Auto-generated constructor stub
		super(Id,nome, eletrico);
		this.Transporte = Transporte;
		this.valor_loc = valor;
	}

	public utilitario() {
		// TODO Auto-generated constructor stub
		super();
	}

}
