package it.polito.tdp.lab04.model;

public class Corso {

	private String codins;
	private int crediti;
	private String nome;
	private int pd;
	
	
	public Corso(String codins, int crediti, String nome, int pd) {
		super();
		this.codins = codins;
		this.crediti = crediti;
		this.nome = nome;
		this.pd = pd;
	}
	public String getCodins() {
		return codins;
	}
	public int getCrediti() {
		return crediti;
	}
	public String getNome() {
		return nome;
	}
	public int getPd() {
		return pd;
	}
	@Override
	public String toString() {
		return String.format("Corso [codins=%s, crediti=%s, nome=%s, pd=%s]", codins, crediti, nome, pd);
	}
	
	
	
}
