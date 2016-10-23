package com.revanth.org;

import java.sql.SQLException;

import com.revanth.org.dto.Order;

public interface OrderDao {
	
	int create(Order order) throws SQLException;
	
	Order read(int id) throws SQLException;
	
	int update(Order order) throws SQLException;
	
	int delete(int id) throws SQLException;
}
