package it.polito.tdp.lab04.model;

public class Studente {
	
	private String matricola;
	private String nome;
	private String cognome;
	private String cds;
	public Studente(String matricola, String nome, String cognome, String cds) {
		
		this.matricola = matricola;
		this.nome = nome;
		this.cognome = cognome;
		this.cds = cds;
	}
	public String getMatricola() {
		return matricola;
	}
	public String getNome() {
		return nome;
	}
	public String getCognome() {
		return cognome;
	}
	public String getCds() {
		return cds;
	}
	@Override
	public String toString() {
		return String.format("Studente [matricola=%s, nome=%s, cognome=%s, cds=%s]", matricola, nome, cognome, cds);
	}
	
	
	
	
	
}

