package it.polito.tdp.poweroutages.model;

import java.util.Date;

public class PowerOutages {
	
	
	private int id;
	private int nerc_id;
	private int customers_affected;
	private Date inizio_evento;
	private Date fine_evento;
	
	
	public PowerOutages(int id,int nerc_id, int customers_affected, Date inizio_evento, Date fine_evento) {
		this.id=id;
		this.nerc_id = nerc_id;
		this.customers_affected = customers_affected;
		this.inizio_evento = inizio_evento;
		this.fine_evento = fine_evento;
	}


	public int getNerc_id() {
		return nerc_id;
	}


	public void setNerc_id(int nerc_id) {
		this.nerc_id = nerc_id;
	}


	public int getCustomers_affected() {
		return customers_affected;
	}


	public void setCustomers_affected(int customers_affected) {
		this.customers_affected = customers_affected;
	}


	public Date getInizio_evento() {
		return inizio_evento;
	}


	public void setInizio_evento(Date inizio_evento) {
		this.inizio_evento = inizio_evento;
	}


	public Date getFine_evento() {
		return fine_evento;
	}


	public void setFine_evento(Date fine_evento) {
		this.fine_evento = fine_evento;
	}
	
	


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "PowerOutages [nerc_id=" + nerc_id + ", customers_affected=" + customers_affected + ", inizio_evento="
				+ inizio_evento + ", fine_evento=" + fine_evento + "]";
	}
	
	
	
	
	
	

}
