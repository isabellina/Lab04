package it.polito.tdp.lab04.model;

import java.util.*;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	public List<String> getNomeCorso(){
		CorsoDAO dao = new CorsoDAO();
		List<String> nomiCorsi = new LinkedList<String>();
		List<Corso> corsi = dao.getTuttiICorsi();
		
		for(Corso c: corsi) {
			nomiCorsi.add(c.getNome() + " (" + c.getCodins() + ")");
		}
		nomiCorsi.add("");
		
		return nomiCorsi;
	}
	
	public Studente getDatiPersona(String matricola) {
		StudenteDAO dao = new StudenteDAO();
		return  dao.getStudente(matricola);
	}

	public List<Studente> getIscrittiCorso(String corso) {
		CorsoDAO dao = new CorsoDAO();
		String co[] = corso.split("\\(");
		String cod = co[1].substring(0, co[1].length()-1);
		List<Corso> listaCorsi = dao.getTuttiICorsi();
		Corso c1=null;
		for(Corso c: listaCorsi) {
			if(c.getCodins().equals(cod)) {
				c1 = c;
			}
		}
		
		List<Studente> studenti = dao.getStudentiIscrittiAlCorso(c1);
		return studenti;
	}
	
	public List<Corso> getCorsi(String matricola){
		CorsoDAO dao = new CorsoDAO();
		List<Corso> listaCorsi = dao.getCorsiIscrittoStudente(matricola);
		return listaCorsi;
	}
	
	public boolean isIscritto(String matricola, String nomeCorso) {
		CorsoDAO dao = new CorsoDAO();
		String co[] = nomeCorso.split("\\(");
		String cod = co[1].substring(0, co[1].length()-1);
		List<Corso> listaCorsi = dao.getTuttiICorsi();
		Corso c1=null;
		for(Corso c: listaCorsi) {
			if(c.getCodins().equals(cod)) {
				c1 = c;
			}
		}
		StudenteDAO da = new StudenteDAO();
		Studente s = da.getStudente(matricola);
		return dao.isIscritto(s, c1);
	}
	
	public boolean iscriviStudente(String matricola,String corso) {
		CorsoDAO dao = new CorsoDAO();
		String co[] = corso.split("\\(");
		String cod = co[1].substring(0, co[1].length()-1);
		List<Corso> listaCorsi = dao.getTuttiICorsi();
		Corso c1=null;
		for(Corso c: listaCorsi) {
			if(c.getCodins().equals(cod)) {
				c1 = c;
			}
		}
		StudenteDAO da = new StudenteDAO();
		Studente s = da.getStudente(matricola);
		return dao.iscriviStudenteACorso(s, c1);
	}
}
