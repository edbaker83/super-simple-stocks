package com.assignment.market;

public class MarketFactory {
	
	private static final MarketFactory INSTANCE = new MarketFactory();
	
	private MarketFactory() {
	}
	
	public static MarketFactory getInstance() {
		return INSTANCE;
	}

	public Market createMarket(final String name){
		return new DefaultMarket(name);
	}
}
