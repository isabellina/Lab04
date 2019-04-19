package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {

	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

			Corso c = new Corso(	 rs.getString("codins"),
				rs.getInt("crediti"),
				 rs.getString("nome"),
				 rs.getInt("pd"));
			
			corsi.add(c);

				//System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
			}

			return corsi;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(String corso) {
		
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		final String sql = "SELECT studente.matricola,studente.nome,studente.cognome,studente.CDS "
				+ "FROM studente "
				+ "INNER JOIN iscrizione "
				+ "ON studente.matricola = iscrizione.matricola "
				+ "WHERE  iscrizione.codins=?;" ;
		
		List<Studente> lstud = new LinkedList<Studente>();
 
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			  st.setString(1, corso.getCodins());

			ResultSet rs = st.executeQuery();
			
			Studente s;

			while (rs.next()) {

				 s = new Studente(rs.getString("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("CDS"));
			lstud.add(s);
		
	}
			
			
}
	 catch (SQLException e) {
		e.printStackTrace();
		//throw new RuntimeException("Errore Db");
	}
		return lstud;
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean iscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		// ritorna true se l'iscrizione e' avvenuta con successo
		
		if(!this.isIscritto(studente, corso)) {
			final String sql = "INSERT INTO iscrizione(matricola,codins) "
					+ "VALUES('" + studente.getMatricola() + "','" + corso.getCodins() + "') ";
			try {
				Connection conn = ConnectDB.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				/*st.setString(1, studente.getMatricola());
				st.setString(2, corso.getCodins());*/
				int rs = st.executeUpdate(sql);
				return true;
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	
	//L'HO AGGIUNTO IO 
	public List<Corso> getCorsiIscrittoStudente(String matricola){
		final String sql = "SELECT c.codins,c.crediti,c.nome,c.pd \n" + 
				"FROM corso as c ,iscrizione as i  \n" + 
				"WHERE matricola=? AND c.codins=i.codins" ;
		
		List<Corso> lcorsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			  st.setString(1, matricola);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

			Corso c = new Corso(rs.getString("codins"),
				rs.getInt("crediti"),
				 rs.getString("nome"),
				 rs.getInt("pd"));
			
			lcorsi.add(c);
	}
			return lcorsi;
			
}
	 catch (SQLException e) {
		// e.printStackTrace();
		throw new RuntimeException("Errore Db");
	}
 }
	public boolean isIscritto(Studente studente, Corso corso) {
		final String sql="SELECT 1 "
				+ "FROM iscrizione "
				+ "WHERE iscrizione.matricola=? AND iscrizione.codins=? ";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, studente.getMatricola());
			st.setString(2, corso.getCodins());
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				return true;
			}
			else {
				return false;
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;		
		
	}
	
}	
