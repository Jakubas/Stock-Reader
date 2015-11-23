package dj.stockreader;

import java.time.LocalDate;

/**
 * @author Dan
 * Used for storing each row of the csv file in one object.
 */
public class StockData implements Comparable<StockData> {

	private LocalDate date;
	private double openingPrice;
	private double highestPrice;
	private double lowestPrice;
	private double closingPrice;
	private int volume;
	private double adjClosingPrice;
	
	private double percentageChange;
	private LocalDate previousDate;
	private double previousPrice;
	
	public StockData() {
		
	}
	
	public StockData(LocalDate date, double openingPrice, double highestPrice, 
			double lowestPrice, double closingPrice, int volume, double adjClosingPrice) {
		this.date = date;
		this.openingPrice = openingPrice;
		this.highestPrice = highestPrice;
		this.lowestPrice = lowestPrice;
		this.closingPrice = closingPrice;
		this.volume = volume;
		this.adjClosingPrice = adjClosingPrice;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(StockData o) {
		if(percentageChange == o.percentageChange)
            return 0;
        return percentageChange < o.percentageChange ? 1 : -1;
	}
	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public double getOpeningPrice() {
		return openingPrice;
	}

	public void setOpeningPrice(double openingPrice) {
		this.openingPrice = openingPrice;
	}

	public double getHighestPrice() {
		return highestPrice;
	}

	public void setHighestPrice(double highestPrice) {
		this.highestPrice = highestPrice;
	}

	public double getLowestPrice() {
		return lowestPrice;
	}

	public void setLowestPrice(double lowestPrice) {
		this.lowestPrice = lowestPrice;
	}

	public double getClosingPrice() {
		return closingPrice;
	}

	public void setClosingPrice(double closingPrice) {
		this.closingPrice = closingPrice;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public double getAdjClosingPrice() {
		return adjClosingPrice;
	}

	public void setAdjClosingPrice(double adjClosingPrice) {
		this.adjClosingPrice = adjClosingPrice;
	}
	
	public double getPercentageChange() {
		return percentageChange;
	}
	
	public void setPercentageChange(double percentageChange) {
		this.percentageChange = percentageChange;
	}

	public LocalDate getPreviousDate() {
		return previousDate;
	}

	public void setPreviousDate(LocalDate date) {
		this.previousDate = date;
	}
	
	public double getPreviousPrice() {
		return previousPrice;
	}
	
	public void setPreviousPrice(double previousPrice) {
		this.previousPrice = previousPrice;
	}
}

