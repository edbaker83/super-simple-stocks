package com.assignment.trade;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.assignment.stock.Stock;
import com.assignment.stock.Stock.StockType;
import com.assignment.stock.StockFactory;
import com.assignment.trade.Trade.TradeType;

public class DefaultTradeTest {
	
	private static DefaultTrade buyTrade;
	private static DefaultTrade sellTrade;
	private static Stock stock;
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		stock = StockFactory.getInstance().createStock("TESTP", StockType.PREFERRED, 10, 20, 30);
		buyTrade = new DefaultTrade(1, stock, 2, TradeType.BUY, 3);
		sellTrade = new DefaultTrade(4, stock, 5, TradeType.SELL, 6);
	}

	@Test
	public void testDefaultTradeNullStock() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Stock must not be null");
		new DefaultTrade(1, null, 1, TradeType.BUY, 1);
	}
	
	@Test
	public void testDefaultTradeInvalidQuantity() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Trade quantity must be a positive integer");
		new DefaultTrade(1, stock, 0, TradeType.BUY, 1);
	}
	
	@Test
	public void testDefaultTradeInvalidPrice() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Trade price must be a positive integer");
		new DefaultTrade(1, stock, 1, TradeType.BUY, 0);
	}
	
	@Test
	public void testGetTimestamp() {
		assertEquals(1, buyTrade.getTimestamp());
		assertEquals(4, sellTrade.getTimestamp());
	}

	@Test
	public void testGetStock() {
		assertEquals(stock, buyTrade.getStock());
		assertEquals(stock, sellTrade.getStock());
	}

	@Test
	public void testGetQuantity() {
		assertEquals(2, buyTrade.getQuantity());
		assertEquals(5, sellTrade.getQuantity());
	}

	@Test
	public void testGetTradeType() {
		assertEquals(TradeType.BUY, buyTrade.getTradeType());
		assertEquals(TradeType.SELL, sellTrade.getTradeType());
	}

	@Test
	public void testGetPrice() {
		assertEquals(3, buyTrade.getPrice());
		assertEquals(6, sellTrade.getPrice());
	}

}
