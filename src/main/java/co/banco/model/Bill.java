package co.banco.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill implements Serializable {
	
	private int id;
	private String date;
	private int idUser;
	private int value;
	private short type;
	private String observation;
	
	public Bill(String date, int value, String observation, short type) {
		
		this.date = date;
		this.value = value;
		this.observation = observation;
		this.type = type;
	}

	public Bill(String date, int idUser, int value, short type, String observation) {
		
		this.date = date;
		this.idUser = idUser;
		this.value = value;
		this.type = type;
		this.observation = observation;
	}

	public Bill(int id, String date, int value, short type, String observation) {
		super();
		this.id = id;
		this.date = date;
		this.value = value;
		this.type = type;
		this.observation = observation;
	}


	

	
	
}
