package co.banco.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	
	private int id;
	private String userName;
	private String pass;
	private String email;
	
	public User(String userName, String pass, String email) {
		
		this.userName=userName;
		this.pass=pass;
		this.email=email;
	}
	
}
