package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public Studente getStudente(String matricola) {
		
		String sql = "SELECT * FROM studente WHERE matricola=?";
		Studente s = null;

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, matricola);
			
			
			ResultSet rs = st.executeQuery();
			
			if(rs.next()!=false) {
				 s = new Studente(rs.getString("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("CDS"));
				
			}
		 return s;
		 
			
	   }
		catch(SQLException e) {
			e.printStackTrace();
		}
	return s;
	}
	
	
}