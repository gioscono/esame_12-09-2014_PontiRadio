package it.polito.tdp.radio.db;

import it.polito.tdp.radio.bean.Citta;
import it.polito.tdp.radio.bean.Ponte;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO per l'accesso al database {@code pontiradio}
 * 
 * @author Fulvio
 * 
 */

public class RadioDAO {

	/**
	 * Interroga il database e restituisce la {@link Citta} che ha il campo
	 * {@code idCitta} pari a quello specificato
	 * 
	 * @param idCitta il valore dell'ID Città da ricercare
	 * @return la {@link Citta} cercata, oppure {@code null} se non esiste
	 */
	public Citta getCittaById(int idCitta) {
		
		throw new UnsupportedOperationException("Method not implemented - sorry") ;
		
		//return null;

	}

	/**
	 * Interroga il database e restituisce tutti i dati nella tabella
	 * {@code citta} sotto forma di un {@link ArrayList} di {@link Citta}.
	 * 
	 * @return la {@link ArrayList} di {@link Citta}
	 */

	public List<Citta> getAllCitta() {
		
		final String sql = "SELECT * FROM citta";

		List<Citta> citta = new ArrayList<Citta>();

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Citta c = new Citta(
						rs.getInt("idCitta"),
						rs.getString("call"),
						rs.getString("city")
						);
				citta.add(c);
			}
			return citta;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Interroga il database e restituisce tutti i dati nella tabella
	 * {@code ponte} sotto forma di un {@link ArrayList} di {@link Ponte}.
	 * 
	 * @return la {@link ArrayList} di {@link Ponte}
	 */

	public List<Ponte> getAllPonte() {
		
		final String sql = "SELECT * FROM ponte";

		List<Ponte> ponti = new ArrayList<Ponte>();

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Ponte p = new Ponte(
						rs.getInt("idPonte"),
						rs.getString("output"),
						rs.getString("call"),
						rs.getDouble("pl"),
						rs.getString("comments")
						);
				ponti.add(p);
			}
			return ponti;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public List<Ponte> getAllPonteCitta(Citta a) {
		
		final String sql = "select p.* " + 
				"from copertura as c, ponte as p " + 
				"where c.idPonte=p.idPonte and c.idCitta=? ";

		List<Ponte> ponti = new ArrayList<Ponte>();

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, a.getIdCitta());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Ponte p = new Ponte(
						rs.getInt("idPonte"),
						rs.getString("output"),
						rs.getString("call"),
						rs.getDouble("pl"),
						rs.getString("comments")
						);
				ponti.add(p);
			}
			return ponti;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public List<Ponte> getAllPontiInComune(Citta a, Citta b) {
		
		final String sql = "select p.* " + 
				"from copertura as c1, copertura as c2, ponte as p " + 
				"where c1.idPonte=c2.idPonte and c1.idCitta=? and c2.idCitta=? and c1.idPonte=p.idPonte";

		List<Ponte> ponti = new ArrayList<Ponte>();

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, a.getIdCitta());
			st.setInt(2, b.getIdCitta());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Ponte p = new Ponte(
						rs.getInt("idPonte"),
						rs.getString("output"),
						rs.getString("call"),
						rs.getDouble("pl"),
						rs.getString("comments")
						);
				ponti.add(p);
			}
			return ponti;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) {
		
		RadioDAO dao = new RadioDAO() ;
		
		List<Citta> citta = dao.getAllCitta() ;
		
		for( Citta c: citta ) {
			System.out.format("%4d %s %s\n", c.getIdCitta(), c.getCall(), c.getCity()) ;
		}
		
		List<Ponte> ponti = dao.getAllPonte() ;

		for( Ponte p : ponti ) {
			System.out.format("%4d %s %s\n", p.getIdPonte(), p.getCall(), p.getComments()) ;
		}
		
	}

}
