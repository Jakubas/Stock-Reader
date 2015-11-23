package dj.stockreader;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Dan
 *	The graphical user interface for the StockReader application.
 *	This interface is displayed when the application is started.
 *	It has buttons and text fields for user input, and a text area
 *	to display columns and rows of formatted data.
 */

public class StockReaderGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTicker;
	private JTextField txtStartDate;
	private JTextField txtEndDate;
	private JTextArea txtAreaStock; 
	private StockDataDownload stockDataDownload = new StockDataDownload();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StockReaderGUI frame = new StockReaderGUI();
					frame.setTitle("Display Stock Data");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.pack();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StockReaderGUI() {
		setPreferredSize(new Dimension(1280, 720));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 1030, 660);
		contentPane.add(scrollPane);
		
		txtAreaStock = new JTextArea();
		txtAreaStock.setEditable(false);
		txtAreaStock.setFont(new Font("Courier New", Font.PLAIN, 12));
		scrollPane.setViewportView(txtAreaStock);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(202, 660));
		panel.setBounds(1052, 11, 202, 660);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtTicker = new JTextField();
		txtTicker.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				stockDataDownload.setTicker(txtTicker.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				stockDataDownload.setTicker(txtTicker.getText());
			}

		});
		txtTicker.setBounds(62, 11, 130, 20);
		panel.add(txtTicker);
		txtTicker.setColumns(10);
		
		JButton btnClosingPrices = new JButton("Display all rows");
		btnClosingPrices.setToolTipText("Shows the adjusted closing prices for the selected ticker and date range");
		btnClosingPrices.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayStockPrices();
			}
		});
		btnClosingPrices.setBounds(10, 114, 182, 23);
		panel.add(btnClosingPrices);
		
		JLabel lblTicker = new JLabel("Ticker:");
		lblTicker.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTicker.setBounds(0, 14, 61, 14);
		panel.add(lblTicker);
		
		JLabel lblStartDate = new JLabel("Start Date:");
		lblStartDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStartDate.setBounds(0, 45, 61, 14);
		panel.add(lblStartDate);
		
		JLabel lblEndDate = new JLabel("End Date:");
		lblEndDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEndDate.setBounds(0, 76, 61, 14);
		panel.add(lblEndDate);
		
		txtStartDate = new JTextField();
		txtStartDate.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				stockDataDownload.setStartDate(txtStartDate.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				stockDataDownload.setStartDate(txtStartDate.getText());
			}

		});
		txtStartDate.setToolTipText("dd/MM/yyyy format");
		txtStartDate.setColumns(10);
		txtStartDate.setBounds(62, 42, 130, 20);
		panel.add(txtStartDate);
		
		txtEndDate = new JTextField();
		txtEndDate.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				stockDataDownload.setEndDate(txtEndDate.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				stockDataDownload.setEndDate(txtEndDate.getText());
			}

		});
		txtEndDate.setToolTipText("dd/MM/yyyy format");
		txtEndDate.setColumns(10);
		txtEndDate.setBounds(62, 73, 130, 20);
		panel.add(txtEndDate);
		
		JButton btnIncreases = new JButton("Largest Price Increases");
		btnIncreases.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int n;
				try {
					n = Integer.parseInt(JOptionPane.showInputDialog(null,
							"How many rows should be display?",
							"Largest stock increases based on percentage",
							JOptionPane.QUESTION_MESSAGE));
				} catch(NumberFormatException e) {
					n = 0;
				}
				DisplayLargestIncreases(n);
			}
		});
		btnIncreases.setToolTipText("Displays the largest price increases (percentage wise)");
		btnIncreases.setBounds(10, 148, 182, 23);
		panel.add(btnIncreases);
		
		JButton btnDecreases = new JButton("Largest Price Decreases");
		btnDecreases.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int n;
				try {
					n = Integer.parseInt(JOptionPane.showInputDialog(null,
							"How many rows should be display?",
							"Largest stock decreases based on percentage",
							JOptionPane.QUESTION_MESSAGE));
				} catch(NumberFormatException e) {
					n = 0;
				}
				DisplayLargestDecreases(n);
			}
		});
		btnDecreases.setToolTipText("displays the largest price Decreases (percentage wise)");
		btnDecreases.setBounds(10, 182, 182, 23);
		panel.add(btnDecreases);
	}

	/**
	 * This method is run when the user clicks on the Display all rows button
	 * It extracts required data from a .csv file that is downloaded
	 */
	private void displayStockPrices() {

		stockDataDownload.downloadStockData();
		StockDataReader stockDataReader = new StockDataReader();
		ArrayList<StockData> stocksData = stockDataReader.getStocksData(stockDataDownload.getFilePath());
		txtAreaStock.setText(String.format("%-15s%-25s%-25s\n", "Date", "Adjusted Closing Price", "% Change"));
		String str = "";
		
		for (int i = 0; i < stocksData.size(); i++) {
			StockData stockData = stocksData.get(i);
			LocalDate date = stockData.getDate();
			double price = stockData.getAdjClosingPrice();
			
			BigDecimal percentage2;
			if (i+1 < stocksData.size()) {
				double v1 = stockData.getAdjClosingPrice();
				double v2 = stocksData.get(i+1).getAdjClosingPrice();
				double percentage = ((v1-v2)/v2) * 100;
				percentage2 = new BigDecimal(percentage).setScale(3, BigDecimal.ROUND_HALF_UP);
			} else {
				percentage2 = new BigDecimal(0.0);
			}	
			
			str = str + String.format("%-15s%-25f% .3f%-1%\n", date, price, percentage2);
		}
		
		txtAreaStock.setText(txtAreaStock.getText() + str);
	}
	
	/**
	 * Sets values for percentageChange, previousDate and previousPrice fields for stockData objects
	 * These values are required to calculate the largest percentage increases and decreases
	 * @return an ArrayList of StockData objects
	 */
	private ArrayList<StockData> setStockDataFields() {
		
		stockDataDownload.downloadStockData();
		StockDataReader stockDataReader = new StockDataReader();
		ArrayList<StockData> stocksData = stockDataReader.getStocksData(stockDataDownload.getFilePath());
		
		for (int i = 0; i < stocksData.size(); i++) {
			
			
			StockData stockData = stocksData.get(i);
			if (i+1 < stocksData.size()) {
				double v1 = stockData.getAdjClosingPrice();
				double v2 = stocksData.get(i+1).getAdjClosingPrice();
				double percentage = ((v1-v2)/v2) * 100;
				stockData.setPercentageChange(percentage); 
				
				LocalDate previousDate = stocksData.get(i+1).getDate();
				stockData.setPreviousDate(previousDate);
				
				double previousPrice = stocksData.get(i+1).getAdjClosingPrice();
				stockData.setPreviousPrice(previousPrice);
			} else {
				stockData.setPercentageChange(0.0);
				stockData.setPreviousDate(null);
				stockData.setPreviousPrice(0.0);
			}
		}
		return stocksData;
	}
	
	
	/**
	 * Displays the n first elements in stocksData in a text area
	 * @param stocksData an ArrayList of StockData objects
	 * @param n the number of elements to display
	 */
	private void DisplayN(ArrayList<StockData> stocksData, int n) {
		String str = "";
		for(int i = 0; i < n && i < stocksData.size(); i++) {
			StockData stockData = stocksData.get(i);
			if (stockData.getPreviousDate() != null) {
			str = str + String.format("%-16s %-16f %-16s %-24f % .3f%-1%\n",
					stockData.getPreviousDate(), stockData.getPreviousPrice(), 
					stockData.getDate(), stockData.getAdjClosingPrice(), 
					new BigDecimal(stockData.getPercentageChange()).setScale(3, BigDecimal.ROUND_HALF_UP));
			}
		}
		txtAreaStock.setText(txtAreaStock.getText() + str);
	}
	
	/**
	 * Displays the largest percentage increases in an 
	 * ArrayList of StockData objects
	 * @param n the number of elements to display
	 */
	private void DisplayLargestIncreases(int n) {
		ArrayList<StockData> stocksData = setStockDataFields();
		txtAreaStock.setText(String.format("%-17s%-17s%-17s%-25s%-25s\n", "Previous Date", "Previous Price", "Date", "Adjusted Closing Price", "% Change"));
		Collections.sort(stocksData);
		DisplayN(stocksData, n);
	}
	
	/**
	 * Displays the largest percentage decreases in an 
	 * ArrayList of StockData objects
	 * @param n the number of elements to display
	 */
	private void DisplayLargestDecreases(int n) {
		ArrayList<StockData> stocksData = setStockDataFields();
		txtAreaStock.setText(String.format("%-17s%-17s%-17s%-25s%-25s\n", "Previous Date", "Previous Price", "Date", "Adjusted Closing Price", "% Change"));
		Collections.sort(stocksData);
		Collections.reverse(stocksData);
		DisplayN(stocksData, n);
	}
}
