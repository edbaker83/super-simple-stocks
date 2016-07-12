package com.assignment.trade;

import java.util.Date;

import com.assignment.stock.Stock;

class DefaultTrade implements Trade {
	
	private final long timestamp;
	private final Stock stock;
	private final int quantity;
	private final TradeType type;
	private final int price;
	
	public DefaultTrade(final long timestamp, final Stock stock, final int quantity, final TradeType type, final int price) {
		if (stock == null) {
			throw new IllegalArgumentException("Stock must not be null");
		}
		if (quantity <= 0) {
			throw new IllegalArgumentException("Trade quantity must be a positive integer");
		}
		if (price <= 0) {
			throw new IllegalArgumentException("Trade price must be a positive integer");
		}
		this.timestamp = timestamp;
		this.stock = stock;
		this.quantity = quantity;
		this.type = type;
		this.price = price;
	}

	@Override
	public long getTimestamp() {
		return timestamp;
	}

	@Override
	public Stock getStock() {
		return stock;
	}

	@Override
	public int getQuantity() {
		return quantity;
	}

	@Override
	public TradeType getTradeType() {
		return type;
	}

	@Override
	public int getPrice() {
		return price;
	}
	
	@Override
	public String toString() {
		return "Trade stock: " + getStock().getSymbol() + ", Timestamp: " + new Date(getTimestamp()) + ", Type: " + getTradeType() + ", Price: " + getPrice() + ", Quantity: " + getQuantity();
	}

}
