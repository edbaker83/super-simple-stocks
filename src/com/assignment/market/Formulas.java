package com.assignment.market;

import java.util.List;

import com.assignment.stock.Stock;
import com.assignment.trade.Trade;

class Formulas {
	
	private static final Formulas INSTANCE = new Formulas();
	
	private Formulas() {
	}
	
	public static Formulas getInstance() {
		return INSTANCE;
	}
	
	public double dividendYield(final int marketPrice, final Stock stock) {
		if (marketPrice <= 0) {
			return 0;
		}
		double dividend;
		if (stock.getType() == Stock.StockType.COMMON) {
			dividend = stock.getLastDividend();
		} else if (stock.getType() == Stock.StockType.PREFERRED) {
			dividend = stock.getFixedDividend() * stock.getParValue();
		} else {
			throw new IllegalArgumentException("Unsupported stock type: " + stock);
		}
		return dividend / marketPrice;
	}
	
	public double priceEarningsRatio(final int marketPrice, final Stock stock) {
		if (stock.getLastDividend() <= 0) {
			return 0;
		}
		return marketPrice / stock.getLastDividend();
	}

	public double volumeWeightedStockPrice(List<Trade> trades) {
		if (trades.isEmpty()) {
			return 0;
		}
		long sumPrice = 0;
		long sumQuantity = 0;
		for (Trade trade: trades) {
			sumQuantity += trade.getQuantity();
			sumPrice += (trade.getPrice() * trade.getQuantity());
		}
		return sumPrice / sumQuantity;
	}
	
	public double geometricMean(List<Integer> prices) {
		if (prices.isEmpty()) {
			return 0;
		}
		long sumPrices = 1;
		for(int price: prices) {
			sumPrices *= price;
		}
		return Math.pow(sumPrices, 1.0 / prices.size());
	}
}
