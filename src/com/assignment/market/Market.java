package com.assignment.market;

import java.util.Map;

import com.assignment.stock.Stock;
import com.assignment.trade.Trade;

public interface Market {

	public String getName();
	
	public void addStock(Stock stock);
	
	public void makeTrade(Trade trade);
	
	public double getDividendYield(Stock stock);
	
	public double getPriceEarningsRatio(Stock stock);
	
	public double getVolumeWeightedStockPrice(Stock stock);
	
	public double getAllShareIndex();
	
	public Stock getStock(String symbol);
	
	public int getStockPrice(Stock stock);
	
	public Map<Stock, Integer> getStockPrices();
}
