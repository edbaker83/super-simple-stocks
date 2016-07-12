package com.assignment.stock;

public interface Stock {

	public String getSymbol();
	
	public enum StockType {
		COMMON, PREFERRED
	}
	
	public StockType getType();
	
	public int getLastDividend();
	
	public float getFixedDividend();
	
	public int getParValue();
	
}
