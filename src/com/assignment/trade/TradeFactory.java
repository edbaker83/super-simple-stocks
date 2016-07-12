package com.assignment.trade;

import com.assignment.stock.Stock;
import com.assignment.trade.Trade.TradeType;

public class TradeFactory {
	
	private static final TradeFactory INSTANCE = new TradeFactory();
	
	private TradeFactory() {
	}
	
	public static TradeFactory getInstance() {
		return INSTANCE;
	}

	public Trade createTrade(final long timestamp, final Stock stock, final int quantity, final TradeType type, final int price){
		return new DefaultTrade(timestamp, stock, quantity, type, price);
	}
}
