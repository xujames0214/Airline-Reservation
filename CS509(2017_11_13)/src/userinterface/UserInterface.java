package userinterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import dao.ServerInterface;
import flight.Flight;
import flight.Flights;
import searchParameter.SearchParameter;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import airport.Airports;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;

public class UserInterface {
	private static final String teamName = "Xteam";
	private static Airports airports;
	
	private JFrame frame;
	private JTextField departureAirportCode;
	private JTextField departureDate;

	private Flights departingFlights;
	private JTable departingFlightsTable;
	private JScrollPane scrollPane;
	/**
	 * Launch the application.
	 */
	
	public static void init(){
		airports = ServerInterface.INSTANCE.getAirports(teamName);
	}
	
	public static void main(String[] args) {
		init();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface window = new UserInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 765, 656);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		departureAirportCode = new JTextField();
		departureAirportCode.setBounds(270, 57, 180, 24);
		frame.getContentPane().add(departureAirportCode);
		departureAirportCode.setColumns(10);
		
		departureDate = new JTextField();
		departureDate.setColumns(10);
		departureDate.setBounds(270, 108, 180, 24);
		frame.getContentPane().add(departureDate);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 210, 731, 398);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		frame.getContentPane().add(scrollPane);
		
		departingFlightsTable = new JTable();
		departingFlightsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(departingFlightsTable);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String departureAirportCodeString = departureAirportCode.getText().trim();
				String departureDateString = departureDate.getText().trim();
				
				SearchParameter searchParameter = new SearchParameter(false,departureAirportCodeString,departureDateString,null,null,true);
				if(!searchParameter.isDepartureAirportCodeValid(airports)){
					JOptionPane.showMessageDialog(null,"The Departure Airport is incorrect!", "alert", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(!searchParameter.isDepartureDateValid()){
					JOptionPane.showMessageDialog(null,"The Departure Date is incorrect!", "alert", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				departingFlights = ServerInterface.INSTANCE.getDepartingFlights(teamName, departureAirportCodeString, departureDateString);
				
				if(departingFlights.isEmpty()){
					JOptionPane.showMessageDialog(null, "No flights available!","information", JOptionPane.INFORMATION_MESSAGE); 
					return;
				}
				
				DefaultTableModel model = new DefaultTableModel();
				Vector colName = new Vector();
				colName.addElement("Flight Number");
				colName.addElement("Departure Airport");
				colName.addElement("Departure Time");
				colName.addElement("Arriving Airport");
				colName.addElement("Arriving Time");
				colName.addElement("Num of First Class Seats Booked");
				colName.addElement("Price of First Class Seat");
				colName.addElement("Num of Coach Seats Booked");
				colName.addElement("Price of Coach Seats");
				colName.addElement("Travel Time");
				
				Vector data = new Vector();
				for(Flight flight:departingFlights){
					Vector row = new Vector();
					row.addElement(flight.getNumber());
					row.addElement(flight.getDepartureCode());
					row.addElement(flight.getDepartureTimeString());
					row.addElement(flight.getArrivalCode());
					row.addElement(flight.getArrivalTimeString());
					row.addElement(flight.getNumOfFirstClassReserved());
					row.addElement(flight.getFirstClassPrice());
					row.addElement(flight.getNumOfCoachReserved());
					row.addElement(flight.getCoachPrice());
					row.addElement(flight.getFlightTime());
					data.addElement(row);
				}
				model.setDataVector(data, colName);
				departingFlightsTable.setModel(model);
				FitTableColumns(departingFlightsTable);
				
			}
		});
		btnSubmit.setBounds(23, 158, 113, 27);
		frame.getContentPane().add(btnSubmit);
		
		JLabel departureAirportLabel = new JLabel("Departure Airport Code:");
		departureAirportLabel.setBounds(23, 60, 197, 18);
		frame.getContentPane().add(departureAirportLabel);
		
		JLabel DepartureDateLabel = new JLabel("Departure Date (yyyy_MM_dd):");
		DepartureDateLabel.setBounds(23, 111, 233, 18);
		frame.getContentPane().add(DepartureDateLabel);
		
		
		
		
	}
	
	public static void FitTableColumns(JTable myTable){
		JTableHeader header = myTable.getTableHeader();
		int rowCount = myTable.getRowCount();
		Enumeration<TableColumn> columns = myTable.getColumnModel().getColumns();
		while(columns.hasMoreElements()){
			TableColumn column = (TableColumn)columns.nextElement();
		    int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
		    int width = (int)myTable.getTableHeader().getDefaultRenderer().getTableCellRendererComponent(myTable, column.getIdentifier(), false, false, -1, col).getPreferredSize().getWidth();
		    for(int row = 0; row<rowCount; row++){
		    	int preferedWidth = (int)myTable.getCellRenderer(row, col).getTableCellRendererComponent(myTable,
		        myTable.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
		        width = Math.max(width, preferedWidth);
		    }
		    header.setResizingColumn(column);
		    column.setWidth(width+myTable.getIntercellSpacing().width);
		}
	}
}
