package it.polito.tdp.radio.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polito.tdp.radio.db.RadioDAO;

public class Model {
	
	private RadioDAO dao;
	private List<Citta> citta;
	
	
	public Model(){
		dao = new RadioDAO();
	}
	
	public List<Citta> getAllCitta(){
		if(citta == null){
			citta = dao.getAllCitta();
		}
		Collections.sort(citta);
		return citta;
	}
	
	public List<Ponte> getPontiInComune(Citta a, Citta b){
		return dao.getAllPontiInComune(a, b);
	}

	public List<Ponte> avviaRicorsione(Citta a, Citta b, Citta c) {
		
		List<Ponte> pontia = new ArrayList<>(dao.getAllPonteCitta(a));
		a.setPonti(pontia);
		List<Ponte> pontib = new ArrayList<>(dao.getAllPonteCitta(b));
		b.setPonti(pontib);
		List<Ponte> pontic = new ArrayList<>(dao.getAllPonteCitta(c));
		c.setPonti(pontic);
		
		Set<Ponte> ponti = new HashSet<>();
		ponti.addAll(pontia);
		ponti.addAll(pontib);
		ponti.addAll(pontic);
		
		List<Ponte> parziale = new ArrayList<>();
		List<Ponte> finale = new ArrayList<>();
		ricorsione(parziale, finale, a, b, c, ponti);
		return finale;
		
	}

	private void ricorsione(List<Ponte> parziale, List<Ponte> finale, Citta a, Citta b, Citta c, Set<Ponte> ponti) {

		System.out.println(parziale);
		if(parziale.size()<=3){
			if(controllaCopertura(a, b, c, parziale)){
				if(finale.isEmpty() || finale.size()>parziale.size()){
					finale.clear();
					finale.addAll(parziale);
				}
			}
			
		}
		for(Ponte p : ponti){
			if(parziale.isEmpty() || p.compareTo(parziale.get(parziale.size()-1))>0){
				parziale.add(p);
				
				ricorsione(parziale, finale, a,b,c,ponti);
				
				parziale.remove(p);
			}
		}
	}

	private boolean controllaCopertura(Citta a, Citta b, Citta c, List<Ponte> parziale) {

		boolean copertoA = false;
		boolean copertoB = false;
		boolean copertoC = false;
		
		for(Ponte p : parziale){
			if(a.getPonti().contains(p))
				copertoA = true;
			if(b.getPonti().contains(p))
				copertoB = true;
			if(c.getPonti().contains(p))
				copertoC = true;
		}
		return copertoA && copertoB && copertoC;
	}
	

}
