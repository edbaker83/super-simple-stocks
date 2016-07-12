package com.assignment.ui;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

import com.assignment.market.Market;
import com.assignment.stock.Stock;
import com.assignment.trade.Trade;
import com.assignment.trade.Trade.TradeType;
import com.assignment.trade.TradeFactory;

public class CommandLineInterface implements UserInterface {
	
	private final Market market;
	private final Scanner scanner;
	private final DecimalFormat decimalFormat;
	
	public CommandLineInterface(final Market market) {
		this.market = market;
		scanner = new Scanner(System.in);
		decimalFormat = new DecimalFormat("0");
		decimalFormat.setMaximumFractionDigits(10);
	}
	
	private int promptForMenuOption(final String text, final int minValue, final int maxValue) {
		int menuOptionSelected = 0;
		while (true) {
			System.out.println(text + "\nEnter an option between " + minValue + " and " + maxValue);
			try {
				String line = scanner.nextLine();
				menuOptionSelected = Integer.parseInt(line);
				if (menuOptionSelected < minValue || menuOptionSelected > maxValue) {
					promptToContinue("Invalid input");
					continue;
				}
				break;
			} catch(NumberFormatException nfe) {
				promptToContinue("Invalid input");
			}
		}
		return menuOptionSelected;
	}
	
	private int promptForIntValue(final String text, final int minValue) {
		int intValue = 0;
		while(true) {
			System.out.println(text);
			try {
				String line = scanner.nextLine();
				intValue = Integer.parseInt(line);
				if (intValue >= minValue) {
					break;
				}
			} catch (NumberFormatException nfe) {
				promptToContinue("Invalid input");
			}
		}
		return intValue;
	}

	@Override
	public void show() {
		try {
			mainLoop: while (true) {
				int menuOption = promptForMenuOption(market.getName() +
						"\n\n1. Show all stocks in market" +
						"\n2. Make trade\n3. Exit", 1, 3);
				switch (menuOption) {
					case 1 :
						displayAllStocksInMarket();
						break;
					case 2:
						displayTradeMenu();
						break;
					case 3:
						break mainLoop;
				}
			}
		} catch (Exception e) {
			System.err.println("Exception occurred: " + e);
		} finally {
			scanner.close();
		}
	}
	
	private Stock promptForStockSymbol() {
		System.out.println("Enter stock symbol:");
		String symbol = scanner.nextLine();
		Stock stock = market.getStock(symbol);
		if (stock == null) {
			System.out.println("Stock not listed on market");
		}
		return stock;
	}
	
	private void promptToContinue() {
		promptToContinue("");
	}
	
	private void promptToContinue(String text) {
		System.out.println(text + "\nPress enter to continue");
		scanner.nextLine();
	}

	@Override
	public void displayTradeMenu() {
		final Stock stock = promptForStockSymbol();
		if (stock != null) {
			System.out.println("\nTrade stock: [" + stock + "]");
			final TradeType type = promptForMenuOption("1. Buy\n2. Sell", 1, 2) == 1 ? TradeType.BUY : TradeType.SELL;
			// Assume quantity and price can only be positive whole integers
			final int quantity = promptForIntValue("Enter quantity of shares:",1);
			final int price = promptForIntValue("Enter price per share:", 1);
			final boolean proceedWithTrade = promptForMenuOption("Confirm trade:\n1. Yes\n2. No", 1, 2) == 1;
			if (proceedWithTrade) {
				Trade trade = TradeFactory.getInstance().createTrade((new Date()).getTime(), stock, quantity, type, price);
				market.makeTrade(trade);
				System.out.println(trade);
			}
			System.out.println("Trade " + (proceedWithTrade ? "completed" : "cancelled"));
		}
		promptToContinue();
	}

	@Override
	public void displayAllStocksInMarket() {
		Map<Stock, Integer> stockPrices = market.getStockPrices();
		System.out.println("\nAll stocks in market");
		for (Stock stock: stockPrices.keySet()) {
			System.out.println("[Market price: " + stockPrices.get(stock) +
					", Dividend yield: " + decimalFormat.format(market.getDividendYield(stock)) +
					", P/E ratio: " + decimalFormat.format(market.getPriceEarningsRatio(stock)) +
					", VWSP: " + decimalFormat.format(market.getVolumeWeightedStockPrice(stock)) +
					", Stock: " + stock + "]");
		}
		System.out.println("\nAll share index: " + decimalFormat.format(market.getAllShareIndex()));
		promptToContinue();
	}

}
