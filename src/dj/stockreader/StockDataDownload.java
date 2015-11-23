package dj.stockreader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * @author Dan
 * Used to download stock data from yahoo
 */

public class StockDataDownload {

	private String ticker = "";
	private String startDate = "";
	private String endDate = "";
	private String filePath;

	public StockDataDownload() {

	}

	/**
	 * @return filePath the path where the file (is/is to be) saved
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param ticker sets the ticker used for downloading stock data
	 */
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	/**
	 * @param startDate sets the startDate used for downloading stock data
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @param endDate sets the endDate used for downloading stock data
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * downloads a cvs file from yahoo historical stock data
	 * from date range and stock symbol/ticker selected in UI
	 */
	public void downloadStockData() {
		
		String[] startDates = startDate.split("/");
		String[] endDates = endDate.split("/");
		String url = "http://ichart.finance.yahoo.com/table.csv?s=";
		try {
			String startDateDay   = startDates[0];
			int startDateMonth = Integer.parseInt(startDates[1]) - 1; //months in yahoo start from 0, so we need to subtract 1 from month
			String startDateYear  = startDates[2];
			String endDateDay     = endDates[0];
			int endDateMonth   = Integer.parseInt(endDates[1]) - 1; //months in yahoo start from 0, so we need to subtract 1 from month
			String endDateYear    = endDates[2];
			url = url + ticker +"&d="+ 
			endDateMonth +"&e="+ endDateDay +"&f="+ endDateYear +"&g=d&a="+ 
			startDateMonth +"&b="+ startDateDay +"&c="+ startDateYear +"&ignore=.csv";
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Start date or end date is in incorrect format (correct format: dd/MM/yy");
			System.err.println("\nCaught ArrayIndexOutOfBoundsException: " + e.getMessage());
		}
		
		filePath = "C:/" + ticker + ".csv";
		FileOutputStream fos = null;
		ReadableByteChannel rbc = null; 
		try {
			URL website = new URL(url);
			rbc = Channels.newChannel(website.openStream());
			fos = new FileOutputStream(filePath);
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
			rbc.close();
		} catch (IOException e) {
			System.out.println("No internet connection, or ticker/end date/start date is incorrect");
			System.err.println("\nCaught IOException: " + e.getMessage());
		}
	}
}
