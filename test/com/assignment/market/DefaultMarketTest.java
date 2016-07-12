package com.assignment.market;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.assignment.stock.StockFactory;
import com.assignment.stock.Stock;
import com.assignment.stock.Stock.StockType;
import com.assignment.trade.TradeFactory;
import com.assignment.trade.Trade;
import com.assignment.trade.Trade.TradeType;

public class DefaultMarketTest {
	
	private static DefaultMarket market;
	private static TradeFactory tradeFactory;
	private static Stock preferredStock;
	private static Stock commonStock;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		tradeFactory = TradeFactory.getInstance();
		preferredStock = StockFactory.getInstance().createStock("TESTP", StockType.PREFERRED, 10, 20, 300);
		commonStock = StockFactory.getInstance().createStock("TESTC", StockType.COMMON, 18, 22, 33);
	}
	
	@Before
	public void setUp() throws Exception {
		market = new DefaultMarket("Test Market");
	}

	@Test
	public void testAddStock() {
		assertEquals(null, market.getStock("TESTP"));
		market.addStock(preferredStock);
		assertEquals(preferredStock, market.getStock("TESTP"));
	}

	@Test
	public void testMakeTrade() {
		market.addStock(preferredStock);
		assertEquals(0, market.getStockPrice(preferredStock));
		Trade trade = tradeFactory.createTrade(1, preferredStock, 100, TradeType.BUY, 150);
		market.makeTrade(trade);
		assertEquals(150, market.getStockPrice(preferredStock));
		trade = tradeFactory.createTrade(2, preferredStock, 100, TradeType.BUY, 200);
		market.makeTrade(trade);
		assertEquals(200, market.getStockPrice(preferredStock));
	}
	
	@Test
	public void testGetDividendYield() {
		assertEquals(0, market.getDividendYield(preferredStock), 0.01);
		market.addStock(preferredStock);
		Trade trade = tradeFactory.createTrade(1, preferredStock, 100, TradeType.BUY, 150);
		market.makeTrade(trade);
		assertEquals(40.0, market.getDividendYield(preferredStock), 0.01);
		market.addStock(commonStock);
		trade = tradeFactory.createTrade(2, commonStock, 300, TradeType.BUY, 170);
		market.makeTrade(trade);
		assertEquals(0.10588235294117647, market.getDividendYield(commonStock), 0.01);
	}

	@Test
	public void testGetPriceEarningsRatio() {
		assertEquals(0, market.getPriceEarningsRatio(preferredStock), 0.01);
		market.addStock(preferredStock);
		Trade trade = tradeFactory.createTrade(1, preferredStock, 100, TradeType.BUY, 150);
		market.makeTrade(trade);
		assertEquals(15.0, market.getPriceEarningsRatio(preferredStock), 0.01);
	}
	
	@Test
	public void testGetVolumeWeightedStockPrice() {
		market.addStock(preferredStock);
		assertEquals(0, market.getVolumeWeightedStockPrice(preferredStock), 0.01);
		long currentTime = (new Date()).getTime();
		Trade trade = tradeFactory.createTrade(currentTime - 900001, preferredStock, 100, TradeType.BUY, 150);
		market.makeTrade(trade);
		trade = tradeFactory.createTrade(currentTime - 3, preferredStock, 170, TradeType.BUY, 92);
		market.makeTrade(trade);
		trade = tradeFactory.createTrade(currentTime - 2, preferredStock, 241, TradeType.BUY, 184);
		market.makeTrade(trade);
		trade = tradeFactory.createTrade(currentTime - 1, preferredStock, 323, TradeType.BUY, 212);
		market.makeTrade(trade);
		assertEquals(175, market.getVolumeWeightedStockPrice(preferredStock), 0.01);
	}

	@Test
	public void testGetStock() {
		assertEquals(null, market.getStock("TESTP"));
		market.addStock(preferredStock);
		assertEquals(preferredStock, market.getStock("TESTP"));
	}

	@Test
	public void testGetStockPrice() {
		market.addStock(preferredStock);
		assertEquals(0, market.getStockPrice(preferredStock));
		Trade trade = tradeFactory.createTrade(1, preferredStock, 100, TradeType.BUY, 150);
		market.makeTrade(trade);
		assertEquals(150, market.getStockPrice(preferredStock));
		trade = tradeFactory.createTrade(1, preferredStock, 100, TradeType.BUY, 202);
		market.makeTrade(trade);
		assertEquals(202, market.getStockPrice(preferredStock));
	}

	@Test
	public void testGetStockPrices() {
		market.addStock(preferredStock);
		market.addStock(commonStock);
		Map<Stock, Integer> stockPrices = market.getStockPrices();
		assertEquals(2, stockPrices.size());
		assertTrue(stockPrices.containsKey(preferredStock));
		assertTrue(stockPrices.containsKey(commonStock));
		assertEquals(0, stockPrices.get(preferredStock), 0.01);
		assertEquals(0, stockPrices.get(commonStock), 0.01);
		Trade trade = tradeFactory.createTrade(1, preferredStock, 100, TradeType.BUY, 150);
		market.makeTrade(trade);
		trade = tradeFactory.createTrade(1, commonStock, 100, TradeType.BUY, 179);
		market.makeTrade(trade);
		assertEquals(150, market.getStockPrice(preferredStock));
		stockPrices = market.getStockPrices();
		assertEquals(2, stockPrices.size());
		assertTrue(stockPrices.containsKey(preferredStock));
		assertTrue(stockPrices.containsKey(commonStock));
		assertEquals(150, stockPrices.get(preferredStock), 0.01);
		assertEquals(179, stockPrices.get(commonStock), 0.01);
	}

	@Test
	public void testGetName() {
		assertEquals("Test Market", market.getName());
	}

}
