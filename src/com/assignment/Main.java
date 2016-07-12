package com.assignment;

import com.assignment.market.Market;
import com.assignment.market.MarketFactory;
import com.assignment.stock.Stock;
import com.assignment.stock.StockFactory;
import com.assignment.ui.CommandLineInterface;
import com.assignment.ui.UserInterface;

public class Main {

	public static void main (String [] args) {
		Market market = MarketFactory.getInstance().createMarket("Global Beverage Corporation Exchange");
		StockFactory stockFactory = StockFactory.getInstance();
		market.addStock(stockFactory.createStock("TEA", Stock.StockType.COMMON, 0, 0, 100));
		market.addStock(stockFactory.createStock("POP", Stock.StockType.COMMON, 8, 0, 100));
		market.addStock(stockFactory.createStock("ALE", Stock.StockType.COMMON, 23, 0, 60));
		market.addStock(stockFactory.createStock("GIN", Stock.StockType.PREFERRED, 8, 2, 100));
		market.addStock(stockFactory.createStock("JOE", Stock.StockType.COMMON, 13, 0, 250));
		UserInterface ui = new CommandLineInterface(market);
		ui.show();
	}
}
