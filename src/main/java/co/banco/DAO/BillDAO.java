package co.banco.DAO;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.banco.model.Bill;
import co.banco.model.User;
import co.banco.util.Conexion;

public class BillDAO {

	private Conexion conexion;

	private static final String insert = "INSERT INTO bill (date_bill, value, type, observation) VALUES (?,?,?,?);";
	private static final String delete = "DELETE FROM bill WHERE id=?;";
	private static final String update =  "UPDATE bill SET date_bill=?, value=?, type=?, observation=? WHERE id=?;";
	private static final String selectByID = "SELECT * FROM bill WHERE id=?;";
	private static final String selectAll = "SELECT * FROM bill WHERE user_id=?;";

	
	public BillDAO() {
		this.conexion = conexion.getConexion();
	}
	
	public void insert(Bill bill) throws SQLException{
		
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(insert);
			preparedStatement.setString(1, bill.getDate());
			preparedStatement.setInt(2, bill.getValue());
			preparedStatement.setShort(3, bill.getType());
			preparedStatement.setString(4, bill.getObservation());
			
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
	
	
	
	public List<Bill> selectAll(int user_id){
		List<Bill> bills= new ArrayList<>();
		
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(selectAll);
			preparedStatement.setInt(1, user_id);
			ResultSet rs = conexion.query();
			while(rs.next()) {
				
				String date = rs.getString("date_bill");
				
				String description = rs.getString("obsecvation");
				int value= rs.getInt("value");
				short type = rs.getShort("type");
				bills.add(new Bill(date,user_id,value,type,description));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bills;
	}
	
	public Bill selectByID(int id) {
		Bill bill = new Bill();

		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(selectByID);
			preparedStatement.setInt(1, id);
			ResultSet rs = conexion.query();
			while (rs.next()) {
		
				String date = rs.getString("date");
				int value = rs.getInt("value");
				String observation = rs.getString("observation");
				short type = rs.getShort("type");
				bill =new Bill(date,value,observation,type);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bill;
	}
	
public void update(Bill bill) throws SQLException{
		
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(update);
			preparedStatement.setString(1, bill.getDate());
			preparedStatement.setInt(2, bill.getValue());
			preparedStatement.setShort(3, bill.getType());
			preparedStatement.setString(4, bill.getObservation());
			preparedStatement.setInt(5, bill.getId());
			conexion.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
