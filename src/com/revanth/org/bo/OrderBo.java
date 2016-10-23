package com.revanth.org.bo;

import com.revanth.org.bo.exception.BOException;
import com.revanth.org.dto.Order;

public interface OrderBo {

	boolean placeOrder(Order order) throws BOException;
	
	boolean cancelOrder(int id) throws BOException;
	
	boolean deleteOrder(int id) throws BOException;
}
