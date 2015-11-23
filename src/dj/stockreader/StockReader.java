package dj.stockreader;

/**
 * @author Dan
 *	StockReader is a program that downloads stock data from yahoo historical charts
 *	The user selects the stock symbol (ticker) and the date range for the data
 *	Data can be displayed in 3 different formats (chosen by buttons in the UI):
 *		- rows listed according to descending date with three columns (Date, adjusted closing price, % change)
 *		- display n biggest price percentage increases (where user selects n)
 *		- display n biggest price percentage decreases (where user selects n)
 */

public class StockReader {

	public static void main(String[] args) {
		StockReaderGUI.main(args);
	}
}
