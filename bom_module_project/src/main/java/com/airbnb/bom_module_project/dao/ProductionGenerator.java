package com.airbnb.bom_module_project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class ProductionGenerator  implements IdentifierGenerator{

	@Override
	public Object generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.mysql.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bom_module_project", "root", "root");
			String ql="SELECT product_name FROM production_table";
			Statement  stmt =con.createStatement();
			ResultSet rs=stmt.executeQuery(ql);
			int count=0; 
			String name="";
			if (rs.next()) {
				name=rs.getString(1);
				count++; 
			}
			return name+count;
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
