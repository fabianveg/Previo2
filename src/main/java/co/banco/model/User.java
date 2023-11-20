package co.banco.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

	
	private int id;
	private String userName;
	private String pass;
	private String email;
	private List<Bill> bills;
	
	public User(String userName, String pass, String email, List<Bill> bills) {
		
		this.userName=userName;
		this.pass=pass;
		this.email=email;
		this.bills = bills;
	}

	public User(int id, String userName, String pass, String email) {
		
		this.id = id;
		this.userName = userName;
		this.pass = pass;
		this.email = email;
	}
	
	
	
	
}
