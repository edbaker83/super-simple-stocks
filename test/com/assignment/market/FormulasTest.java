package com.assignment.market;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.assignment.stock.Stock;
import com.assignment.stock.StockFactory;
import com.assignment.trade.TradeFactory;
import com.assignment.trade.Trade;
import com.assignment.trade.Trade.TradeType;
import com.assignment.stock.Stock.StockType;

public class FormulasTest {
	
	private static Stock preferredStock;
	private static Stock commonStock;
	private static Formulas formulas;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		preferredStock = StockFactory.getInstance().createStock("TESTP", StockType.PREFERRED, 10, 20, 30);
		commonStock = StockFactory.getInstance().createStock("TESTC", StockType.COMMON, 11, 22, 33);
		formulas = Formulas.getInstance();
	}

	@Test
	public void testDividendYield() {
		assertEquals(4.0, formulas.dividendYield(150, preferredStock), 0.01);
		assertEquals(0.07333333333333333, formulas.dividendYield(150, commonStock), 0.01);
		assertEquals(0, formulas.dividendYield(0, commonStock), 0.01);
	}

	@Test
	public void testPriceEarningsRatio() {
		assertEquals(12, formulas.priceEarningsRatio(120, preferredStock), 0.01);
		assertEquals(10, formulas.priceEarningsRatio(120, commonStock), 0.01);
		assertEquals(0, formulas.priceEarningsRatio(120, StockFactory.getInstance().createStock("TESTP", StockType.PREFERRED, 0, 0, 1)), 0.01);
	}

	@Test
	public void testVolumeWeightedStockPrice() {
		TradeFactory tradeFactory = TradeFactory.getInstance();
		List<Trade> trades = new ArrayList<Trade>(4);
		trades.add(tradeFactory.createTrade(1, preferredStock, 100, TradeType.BUY, 150));
		trades.add(tradeFactory.createTrade(2, preferredStock, 210, TradeType.SELL, 353));
		trades.add(tradeFactory.createTrade(3, preferredStock, 320, TradeType.BUY, 220));
		trades.add(tradeFactory.createTrade(4, preferredStock, 430, TradeType.SELL, 176));
		assertEquals(221, formulas.volumeWeightedStockPrice(trades), 0.01);
		assertEquals(0, formulas.volumeWeightedStockPrice(new ArrayList<Trade>()), 0.01);
	}

	@Test
	public void testGeometricMean() {
		List<Integer> prices = new ArrayList<Integer>(4);
		prices.add(25);
		prices.add(154);
		prices.add(202);
		prices.add(312);
		assertEquals(124.80778429179682, formulas.geometricMean(prices), 0.01);
		assertEquals(0, formulas.geometricMean(new ArrayList<Integer>()), 0.01);
	}

}
