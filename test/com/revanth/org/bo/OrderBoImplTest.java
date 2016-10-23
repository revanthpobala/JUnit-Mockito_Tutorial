package com.revanth.org.bo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.sql.SQLException;

import com.revanth.org.OrderDao;
import com.revanth.org.bo.exception.BOException;
import com.revanth.org.dto.Order;

public class OrderBoImplTest {
	
	private static final int ORDER_ID = 123;
	@Mock
	OrderDao dao;
	private OrderBoImpl bo;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		bo = new OrderBoImpl();
		bo.setDao(dao);
	}
	
	@Test
	public void placeorder_should_create_order() throws SQLException, BOException {
		Order order = new Order();
		when(dao.create(any(Order.class))).thenReturn(new Integer(1));
		boolean result = bo.placeOrder(order);
		assertTrue(result);
		verify(dao).create(order);
		
	}
	
	@Test
	public void placeorder_should_not_create_order() throws SQLException, BOException {
		Order order = new Order();
		when(dao.create(order)).thenReturn(new Integer(0));
		boolean result = bo.placeOrder(order);
		assertFalse(result);
		verify(dao).create(order);
		
	}
	
	@Test(expected = BOException.class)
	public void placeorder_should_throw_exception() throws SQLException, BOException{
		Order order = new Order();
		when(dao.create(order)).thenThrow(SQLException.class);
		boolean result = bo.placeOrder(order);
	}
	
	@Test
	public void cancelorder_should_cancel_the_order() throws SQLException, BOException{
		Order order = new Order();
		when(dao.read(anyInt())).thenReturn(order);
		when(dao.update(order)).thenReturn(1);
		boolean result = bo.cancelOrder(ORDER_ID);
		
		assertTrue(result);
		verify(dao, times(1)).read(ORDER_ID);
		verify(dao).update(order);
		
	}
	
	@Test
	public void cancelorder_should_not_cancel_the_order() throws SQLException, BOException{
		Order order = new Order();
		when(dao.read(ORDER_ID)).thenReturn(order);
		when(dao.update(order)).thenReturn(0);
		boolean result = bo.cancelOrder(ORDER_ID);
		
		assertFalse(result);
		verify(dao).read(ORDER_ID);
		verify(dao).update(order);
		
	}
	
	@Test(expected = BOException.class)
	public void cancel_order_should_throw_BOException() throws SQLException, BOException{
		when(dao.read(ORDER_ID)).thenThrow(SQLException.class);
		bo.cancelOrder(ORDER_ID);
		
	}
	
	@Test(expected = BOException.class)
	public void cancelorder_should_throw_B_OException() throws SQLException, BOException{
		Order order = new Order();
		when(dao.read(ORDER_ID)).thenReturn(order);
		when(dao.update(order)).thenThrow(SQLException.class);
		bo.cancelOrder(ORDER_ID);
		
	}
	
	@Test
	public void delete_order_delete_the_order() throws SQLException, BOException{
		when(dao.delete(ORDER_ID)).thenReturn(1);
		boolean result = bo.deleteOrder(ORDER_ID);
		assertTrue(result);
		verify(dao).delete(ORDER_ID);
	}

	
	
}















