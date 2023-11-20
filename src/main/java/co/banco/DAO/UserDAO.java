package co.banco.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.banco.model.Bill;
import co.banco.model.User;
import co.banco.util.Conexion;

public class UserDAO {

	private Conexion conexion;

	private static final String insert = "INSERT INTO users (username,pass,email) VALUES (?,?,?);";
	private static final String delete = "DELETE FROM users where id= ?;";

	private static final String selectByID= "SELECT * FROM users WHERE id=?;";
	private static final String selectAll= "SELECT * FROM users;";
	
	public UserDAO() {
		this.conexion = conexion.getConexion();
	}
	
	public void insert(User user) throws SQLException{
		System.out.println(user.getUserName());
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(insert);
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getPass());
			preparedStatement.setString(3, user.getEmail());
			conexion.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int id) throws SQLException{
		
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(delete);
			preparedStatement.setInt(1, id);
			conexion.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public List<User> selectAll(){
		List<User> usuarios = new ArrayList<>();
		
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(selectAll);
			ResultSet rs = conexion.query();
			while(rs.next()) {
				int id = rs.getInt("id");
				String userName = rs.getString("username");
				String email = rs.getString("email");
				String pass= rs.getString("pass");
				usuarios.add(new User(id,userName,email,pass));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usuarios;
	}
	
	public User selectByID(int id) {
		User user = new User();

		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(selectByID);
			preparedStatement.setInt(1, id);
			ResultSet rs = conexion.query();
			while (rs.next()) {
		
				String userName = rs.getString("username");
				String email = rs.getString("email");
				String pass = rs.getString("pass");
				user =new User(id, userName, email, pass);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	
	
	

}
