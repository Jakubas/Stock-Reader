package dj.stockreader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

/**
 * @author Dan
 * This class is used to read data with a reader and parse it into an appropriate data container.
 */
public class StockDataReader {

	public StockDataReader() {
	}
	
	/**
	 * Reads the stock data and saves each row as an StockData object in an ArrayList.
	 * @param filePath the path where the file to be read is saved
	 * @return An ArrayList of StockData objects
	 */
	public ArrayList<StockData> getStocksData(String filePath) {
		
		ArrayList<StockData> stocksData = new ArrayList<StockData>();
		String csvFile = filePath;
		BufferedReader reader = null;
		String line = "";
		String splitBy = ",";
	
		try {
			
			reader = new BufferedReader(new FileReader(csvFile));
			reader.readLine();
			while ((line = reader.readLine()) != null) {
			    
				int i = 0;
				String[] stockInfo = line.split(splitBy);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
				StockData stockData = new StockData(LocalDate.parse(stockInfo[i++], formatter), Double.parseDouble(stockInfo[i++]), 
						Double.parseDouble(stockInfo[i++]), Double.parseDouble(stockInfo[i++]), Double.parseDouble(stockInfo[i++]), 
						Integer.parseInt(stockInfo[i++]), Double.parseDouble(stockInfo[i++]));
			
				stocksData.add(stockData);
				
			}
		} catch (FileNotFoundException e) {
			System.out.println("File was not downloaded");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();	
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return stocksData;
	 }
}
