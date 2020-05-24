package it.polito.tdp.poweroutages.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	private int maxPersone;
	private List<PowerOutages> listaSoluzione ;
	private int ore;
	private int anni;
	private List<PowerOutages> listaEvent = new LinkedList<PowerOutages>();
	
	 
	
	public Model() {
		this.podao = new PowerOutageDAO();
		
	}
	
	public List<Nerc> getNercList() {
		List<Nerc> nerc = new LinkedList<Nerc>(this.podao.getNercList());
		
		return nerc ;
	}
	
	public List<PowerOutages> getEvent(int id){
		List<PowerOutages> listaEventi = new LinkedList<PowerOutages>(this.podao.getEventi(id));
		this.listaEvent = new LinkedList<PowerOutages>(listaEventi);
		return listaEventi;
	}
	
	
	public List<PowerOutages> calcolaSottoinsiemeBlackout(int ore, int anni) {
		this.anni = anni;
		this.ore = ore;
		this.maxPersone = 0;
		this.listaSoluzione = new LinkedList<PowerOutages>();
		List<PowerOutages> parziale = new LinkedList<PowerOutages>();
		cerca(parziale,0, 0,0,0);
		return this.listaSoluzione;
		
	}
	
	private void cerca(List<PowerOutages> parziale,int livello, int oreT, int anniT, int sommaPersone ) {
		if(anniT>anni || oreT<ore) {
			return;
		}
		
		if(sommaPersone>=this.maxPersone) {
			this.maxPersone = sommaPersone;
			this.listaSoluzione = new LinkedList<PowerOutages>(parziale);
			
			
		}
		
		parziale.add(listaEvent.get(livello));
		cerca(parziale, livello+1, )
		parziale.remove(listaEvent.get(livello));
		
		
		
	
		
	}
	
	
	private int differenza(Date prima, Date seconda) {
		long diff = Math.abs(prima.getTime()-seconda.getTime());
		int d = (int) TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS);
		return d;
		
	}
	
	
	private int getAnni(Date prima, Date seconda) {
		int diff = Math.abs(prima.getYear()-seconda.getYear());
		return diff;
	}	

}
