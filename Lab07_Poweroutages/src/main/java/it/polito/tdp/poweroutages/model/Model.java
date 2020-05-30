package it.polito.tdp.poweroutages.model;

import java.util.Calendar;
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
	private Date annoVecchio = null ;
	
	 
	
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
		System.out.println(listaEventi);
		return listaEventi;
	}
	
	
	public List<PowerOutages> calcolaSottoinsiemeBlackout(int ore, int anni) {
		this.anni = anni;
		this.ore = ore;
		this.maxPersone = 0;
		this.listaSoluzione = new LinkedList<PowerOutages>();
		List<PowerOutages> parziale = new LinkedList<PowerOutages>();
		if(this.listaEvent.size()==0) {
			return listaEvent;
		}
		cerca(parziale,0, 0,0,0);
		System.out.println(listaSoluzione);
		return this.listaSoluzione;
		
	}
	
	private void cerca(List<PowerOutages> parziale,int livello, int oreT, int anniT, int sommaPersone ) {
		if(anniT>anni || oreT>ore) {
			return;
		}
		
		if(sommaPersone>=this.maxPersone) {
			this.maxPersone = sommaPersone;
		
			this.listaSoluzione = new LinkedList<PowerOutages>(parziale);
			
			
			
		}
		
		if(livello>=listaEvent.size()-1) {
			return;
		}
		
		PowerOutages e = this.listaEvent.get(livello);
		
		if(annoVecchio==null) {
			
			this.annoVecchio = e.getInizio_evento();
		}
		else {
			if(this.annoVecchio.compareTo(e.getInizio_evento())<0) {
				this.annoVecchio = e.getInizio_evento();
			}
		}
		
		parziale.add(e);		
		
		int o= this.differenza(e.getFine_evento(), e.getInizio_evento()) + oreT;
		int a = this.getAnni(annoVecchio, e.getInizio_evento() );
		sommaPersone +=e.getCustomers_affected();
		cerca(parziale, livello+1,o, a, sommaPersone );
		sommaPersone-=e.getCustomers_affected();
		parziale.remove(e);
		
		
		
		cerca(parziale, livello+1,oreT, anniT, sommaPersone );
	
		
	}
	
	
	private int differenza(Date prima, Date seconda) {
		long diff = Math.abs(prima.getTime()-seconda.getTime());
		int d = (int) TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS);
		System .out.println("inizio" + " " +prima.toString());
		System .out.println("fine" + " " +seconda.toString());
		System.out.println(d);
		return d;
		
	}
	
	
	private int getAnni(Date prima, Date seconda) {
		int diff = Math.abs(prima.getYear()-seconda.getYear());
		return diff;
	}
	

}
