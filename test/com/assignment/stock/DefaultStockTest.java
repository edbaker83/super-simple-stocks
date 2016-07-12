package com.assignment.stock;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.assignment.stock.Stock.StockType;

public class DefaultStockTest {
	
	private static DefaultStock preferredStock;
	private static DefaultStock commonStock;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		preferredStock = new DefaultStock("TESTP", StockType.PREFERRED, 10, 20, 30);
		commonStock = new DefaultStock("TESTC", StockType.COMMON, 11, 22, 33);
	}

	@Test
	public void testGetSymbol() {
		assertEquals("TESTP", preferredStock.getSymbol());
		assertEquals("TESTC", commonStock.getSymbol());
	}

	@Test
	public void testGetType() {
		assertEquals(StockType.PREFERRED, preferredStock.getType());
		assertEquals(StockType.COMMON, commonStock.getType());
	}

	@Test
	public void testGetLastDividend() {
		assertEquals(10, preferredStock.getLastDividend());
		assertEquals(11, commonStock.getLastDividend());
	}

	@Test
	public void testGetFixedDividend() {
		assertEquals(20, preferredStock.getFixedDividend(), 0.01);
		assertEquals(22, commonStock.getFixedDividend(), 0.01);
	}

	@Test
	public void testGetParValue() {
		assertEquals(30, preferredStock.getParValue());
		assertEquals(33, commonStock.getParValue());
	}

	@Test
	public void testHashcode() {
		assertEquals((new DefaultStock("TESTP", StockType.PREFERRED, 10, 20, 30)).hashCode(), preferredStock.hashCode());
		assertEquals((new DefaultStock("TESTC", StockType.COMMON, 11, 22, 33)).hashCode(), commonStock.hashCode());
	}

	@Test
	public void testEqualsObject() {
		assertFalse(preferredStock.equals(commonStock));
		assertTrue((new DefaultStock("TESTP", StockType.PREFERRED, 10, 20, 30)).equals(preferredStock));
		assertTrue((new DefaultStock("TESTC", StockType.COMMON, 11, 22, 33)).equals(commonStock));
	}

}
