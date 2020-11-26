package com.pv2bi.model;

public abstract class automovel {
	
    private int id;
    private String Nome;
    private String Eletrico;
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getEletrico() {
		return Eletrico;
	}
	public void setEletrico(String eletrico) {
		Eletrico = eletrico;
	}
	
	public automovel(int id, String nome, String eletrico) {
		super();
		this.id = id;
		Nome = nome;
		Eletrico = eletrico;
	}
	
	@Override
	public String toString() {
		return "automovel [id=" + id + ", Nome=" + Nome + ", Eletrico=" + Eletrico + "]";
	}

    public automovel() {
		// TODO Auto-generated constructor stub
	}
    

	public automovel(String nome, String eletrico) {
		super();
		Nome = nome;
		Eletrico = eletrico;
	}
}
