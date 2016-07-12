package com.assignment.stock;

class DefaultStock implements Stock {
	
	private String symbol;
	private StockType type;
	private int lastDividend;
	private float fixedDividend;
	private int parValue;
	
	public DefaultStock(String symbol, StockType type, int lastDividend, float fixedDividend, int parValue) {
		this.symbol = symbol;
		this.type = type;
		this.lastDividend = lastDividend;
		this.fixedDividend = fixedDividend;
		this.parValue = parValue;
	}

	@Override
	public String getSymbol() {
		return symbol;
	}

	@Override
	public StockType getType() {
		return type;
	}

	@Override
	public int getLastDividend() {
		return lastDividend;
	}

	@Override
	public float getFixedDividend() {
		return fixedDividend;
	}

	@Override
	public int getParValue() {
		return parValue;
	}

	@Override
	public String toString() {
		return "Symbol: " + getSymbol() + ", Type: " + type + ", Last dividend: " + getLastDividend() + ", Fixed Dividend: " + getFixedDividend() + ", Par value: " + getParValue(); 
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getSymbol() == null) ? 0 : getSymbol().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DefaultStock other = (DefaultStock) obj;
		if (getSymbol() == null) {
			if (other.getSymbol() != null)
				return false;
		} else if (!getSymbol().equals(other.getSymbol()))
			return false;
		return true;
	}
}
