package com.assignment.market;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.assignment.stock.Stock;
import com.assignment.trade.Trade;

class DefaultMarket implements Market {
	
	private final String name;
	private final Map<String, Stock> stocksInMarket;
	private final Map<Stock, Integer> stockPrices;
	private final Map<Stock, TreeMap<Long, Trade>> trades;
	private static final int RECENT_TRADES_TIME_MS = 900000; // 15 minutes
	private final Formulas formulas;
	
	public DefaultMarket(final String name) {
		this.name = name;
		stocksInMarket = new HashMap<String, Stock>();
		stockPrices = new HashMap<Stock, Integer>();
		trades = new HashMap<Stock, TreeMap<Long, Trade>>();
		formulas = Formulas.getInstance();
	}

	@Override
	public void addStock(final Stock stock) {
		if (!stockPrices.containsKey(stock.getSymbol())) {
			stocksInMarket.put(stock.getSymbol(), stock);
			// Assume initial value of 0 for all stock prices
			stockPrices.put(stock, 0);
		}
	}

	@Override
	public void makeTrade(final Trade trade) {
		if (!stockPrices.containsKey(trade.getStock())) {
			return;
		}
		// Assume the last trade price should now be the market price
		stockPrices.put(trade.getStock(), trade.getPrice());
		if (!trades.containsKey(trade.getStock())) {
			trades.put(trade.getStock(), new TreeMap<Long, Trade>());
		}
		trades.get(trade.getStock()).put(trade.getTimestamp(), trade);
	}

	@Override
	public double getDividendYield(final Stock stock) {
		if (stockPrices.containsKey(stock)) {
			return formulas.dividendYield(stockPrices.get(stock), stock);
		}
		return 0;
	}

	@Override
	public double getPriceEarningsRatio(final Stock stock) {
		if (stockPrices.containsKey(stock)) {
			return formulas.priceEarningsRatio(stockPrices.get(stock), stock);
		}
		return 0;
	}

	@Override
	public double getVolumeWeightedStockPrice(final Stock stock) {
		if (!trades.containsKey(stock)) {
			return 0;
		}
		final long cutOffTimestamp = (new Date()).getTime() - RECENT_TRADES_TIME_MS;
		SortedMap<Long, Trade> recentTrades = trades.get(stock).tailMap(cutOffTimestamp);
		return formulas.volumeWeightedStockPrice(new ArrayList<Trade>(recentTrades.values()));
	}

	@Override
	public double getAllShareIndex() {
		final List<Integer> prices = new ArrayList<Integer>();
		// Only count stocks that have been traded and so have a market price
		for (Stock stock: trades.keySet()) {
			prices.add(stockPrices.get(stock));
		}
		return formulas.geometricMean(prices);
	}

	@Override
	public Stock getStock(final String symbol) {
		return stocksInMarket.get(symbol);
	}

	@Override
	public int getStockPrice(final Stock stock) {
		return stockPrices.get(stock);
	}
	
	@Override
	public Map<Stock, Integer> getStockPrices() {
		return new HashMap<Stock, Integer>(stockPrices);
	}

	@Override
	public String getName() {
		return name;
	}

}
