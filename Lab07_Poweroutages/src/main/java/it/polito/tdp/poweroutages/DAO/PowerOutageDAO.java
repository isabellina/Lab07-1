package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutages;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM Nerc";
		List<Nerc> nercList = new ArrayList<Nerc>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	
	public List<PowerOutages> getEventi(int id){
		String sql = "select id,nerc_id, customers_affected, date_event_began, date_event_finished\r\n" + 
				"from PowerOutages \r\n" + 
				"where nerc_id= ?" ;
		List<PowerOutages> listaEventi = new LinkedList<PowerOutages>();
		System.out.println(id);
		
		try{
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				PowerOutages p = new PowerOutages(res.getInt("id"), res.getInt("nerc_id"), res.getInt("customers_affected"),
						res.getDate("date_event_began"), res.getDate("date_event_finished"));
				listaEventi.add(p);
			}
			System.out.println(listaEventi);
			conn.close();
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return listaEventi;
	}
	

}
