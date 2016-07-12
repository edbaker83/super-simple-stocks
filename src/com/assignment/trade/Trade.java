package com.assignment.trade;

import com.assignment.stock.Stock;

public interface Trade {
	
	public long getTimestamp();
	
	public Stock getStock();
	
	public int getQuantity();
	
	public enum TradeType {
		BUY, SELL
	}
	
	public TradeType getTradeType();
	
	public int getPrice();

}
