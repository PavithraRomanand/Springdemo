package com.gavs.springboot.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gavs.springboot.model.Employee;

public class DAOWrapper {
	private static String MYSQL_DRIVERNAME = "com.mysql.cj.jdbc.Driver";
	private static String MYSQL_CONNECTION_URL = "jdbc:mysql://localhost:3306/sampledb";

	public static Connection getConnection() {
		Connection con = null;
		String strUserName = "root";
		String strPassword = "admin";
		try {
			java.util.Properties p = new java.util.Properties();
			p.put("user", strUserName);
			p.put("password", strPassword);
			String driverName = MYSQL_DRIVERNAME;
			Class.forName(driverName);
			String url = MYSQL_CONNECTION_URL;
			con = DriverManager.getConnection(url, p);
			
		} catch (SQLException sqe) {
			System.out.println("SQLException:  " + sqe.getMessage());
		} catch (Exception e2) {
			System.out.println("Exception:  " + e2.getMessage());
		} finally {

		}
		return con;
	}
	
	public String add(Employee employee) {
    	try {
    		
    		Connection connection = getConnection();
    	
    		PreparedStatement pst = connection.prepareStatement("INSERT INTO employeetb VALUES(?,?)");
    		pst.setInt(1, employee.getId());
    		pst.setString(2, employee.getName());
    		pst.executeUpdate();
    	}
    	catch(SQLException sqe)
    	{
    		sqe.printStackTrace();
    	}
    	finally {
    		//connection.close();
    	}
    	return "Successfully Inserted...";
    }
    
    public String delete(int delId) {
    	Connection conn = getConnection();
    	try {
    		
    		String sql= "DELETE FROM employeetb  WHERE id=?";
			PreparedStatement stmt= conn.prepareStatement(sql);
			stmt.setInt(1, delId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Successfully Deleted...";
    }
    
    public Employee getEmployee(int id) {
    	Connection con = getConnection();
    	Employee emp = new Employee();
    	ResultSet rs = null;
    	PreparedStatement st = null;
    	try {
			st = con.prepareStatement("select * from employeetb where id = ?");
			st.setInt(1, id);
			System.out.println(id);
			rs = st.executeQuery();
			rs.next();
			emp.setId(rs.getInt(1));
			emp.setName(rs.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	 return emp;
    }
    
    public String updateEmployee(Employee employee)
    {
    	Connection con = getConnection();
    	try {
    		PreparedStatement st = con.prepareStatement("update employeetb set id = ?,name = ? where id = ?");
    		st.setInt(1, employee.getId());
    		st.setString(2, employee.getName());
    		st.setInt(3, employee.getId());
    		st.execute();
    	}catch(SQLException sqe) {
    		sqe.printStackTrace();
    	}
    	return "Updated Successfull...";
    }
}
   
    
