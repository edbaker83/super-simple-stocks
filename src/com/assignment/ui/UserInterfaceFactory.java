package com.assignment.ui;

import com.assignment.market.Market;

public class UserInterfaceFactory {
	
	private static final UserInterfaceFactory INSTANCE = new UserInterfaceFactory();
	
	private UserInterfaceFactory() {
	}
	
	public static UserInterfaceFactory getInstance() {
		return INSTANCE;
	}

	public UserInterface createUserInterface(final Market market){
		return new CommandLineInterface(market);
	}
}
