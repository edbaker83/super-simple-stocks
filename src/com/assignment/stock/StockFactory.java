package com.assignment.stock;

import com.assignment.stock.Stock.StockType;

public class StockFactory {
	
	private static final StockFactory INSTANCE = new StockFactory();
	
	private StockFactory() {
	}
	
	public static StockFactory getInstance() {
		return INSTANCE;
	}

	public Stock createStock(final String symbol, final StockType type, final int lastDividend, final float fixedDividend, final int parValue){
		return new DefaultStock(symbol, type, lastDividend, fixedDividend, parValue);
	}
}
