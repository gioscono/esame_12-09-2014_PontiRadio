/**
 * 
 */
package it.polito.tdp.radio.bean;

import java.util.List;

/**
 * Bean per rappresentare la tabella "citta" del database "pontiradio"
 * 
 * @author Fulvio
 *
 */
public class Citta implements Comparable<Citta>{
	
	private int idCitta ;
	private String call ;
	private String city ;
	private List<Ponte> ponti;
	
	public List<Ponte> getPonti() {
		return ponti;
	}

	public void setPonti(List<Ponte> ponti) {
		this.ponti = ponti;
	}

	public Citta(int idCitta, String call, String city) {
		super();
		this.idCitta = idCitta;
		this.call = call;
		this.city = city;
	}

	public int getIdCitta() {
		return idCitta;
	}

	public void setIdCitta(int idCitta) {
		this.idCitta = idCitta;
	}

	public String getCall() {
		return call;
	}

	public void setCall(String call) {
		this.call = call;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCitta;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Citta other = (Citta) obj;
		if (idCitta != other.idCitta)
			return false;
		return true;
	}

	@Override
	public int compareTo(Citta altra) {
		// TODO Auto-generated method stub
		return this.city.compareTo(altra.getCity());
	}

	@Override
	public String toString() {
		return this.idCitta+" "+this.city;
	}
	
	

}
