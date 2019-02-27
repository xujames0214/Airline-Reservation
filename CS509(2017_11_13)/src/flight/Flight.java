package flight;

import java.util.Date;

/**
 * This class holds values pertaining to a single Flight. Class member attributes
 * are the same as defined by the CS509 server API and store values after conversion from
 * XML received from the server to Java primitives. Attributes are accessed via getter and 
 * setter methods.
 * 
 * @author Jishen Xu
 * @version 1.2
 * @since 2017-10-20
 * 
 * 
 *
 */
public class Flight {
	/**
	 * Flight attributes as defined by the CS509 server interface XML
	 */
	private String airplane; 	// Name of airplane of this flight.
	private double flightTime;	// Duration of this flight.
	private int number;			// Flight Number.
	private String departureCode;	// Departure Airport Code
	private Date departureTime;		// Departure Time
	private String arrivalCode;		// Arrival Airport Code
	private Date arrivalTime;		// Arrival Time
	private double firstClassPrice;		//Price of first-class seat
	private int numOfFirstClassReserved;	// Number of first-class seats reserved
	private double coachPrice;			//Price of coach seat
	private int numOfCoachReserved;			// Number of coach seats reserved
	private String departureTimeString;	//Departure time in String format
	private String arrivalTimeString; //Arrival time in String format
	
	/**
	 * Default constructor
	 * 
	 * Constructor without params. Requires object fields to be explicitly
	 * set using setter methods
	 * 
	 * @pre None
	 * @post member attributes are initialized to invalid default values
	 */	
	public Flight(){
		departureCode = "";
		arrivalCode = "";
		departureTimeString = "";
		arrivalTimeString = "";
	}

	/**Set the airplane name
	 * @param airplane The name of this flight's airplane
	 */
	public void setAirplane(String airplane) {
		this.airplane = airplane;
	}

	/**Set flight time
	 * @param flightTime The duration of this flight
	 */
	public void setFlightTime(double flightTime) {
		this.flightTime = flightTime;
	}

	/**Set flight number
	 * @param number The flight number
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**Set departure airport code
	 * @param departureCode The code of departure airport of this flight
	 */
	public void setDepartureCode(String departureCode) {
		this.departureCode = departureCode;
	}

	/**Set departure time
	 * @param departureTime The departure time of this flight
	 */
	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	/**Set arrival airport code
	 * @param arrivalCode The code of arrival airport of this flight
	 */
	public void setArrivalCode(String arrivalCode) {
		this.arrivalCode = arrivalCode;
	}

	/**Set arrival time
	 * @param arrivalTime The arrival time of this flight
	 */
	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	/**Set price of first-class seats
	 * @param firstClassPrice The price of first-class seats
	 */
	public void setFirstClassPrice(double firstClassPrice) {
		this.firstClassPrice = firstClassPrice;
	}

	/**Set number of first-class seats reserved
	 * @param numOfFirstClassReserved The number of first-class seats reserved
	 */
	public void setNumOfFirstClassReserved(int numOfFirstClassReserved) {
		this.numOfFirstClassReserved = numOfFirstClassReserved;
	}

	/**Set price of coach seats
	 * @param coachPrice The price of coach seats
	 */
	public void setCoachPrice(double coachPrice) {
		this.coachPrice = coachPrice;
	}

	/**Set number of coach seats reserved
	 * @param numOfCoachReserved The number of coach seats reserved
	 */
	public void setNumOfCoachReserved(int numOfCoachReserved) {
		this.numOfCoachReserved = numOfCoachReserved;
	}

	/**Set departure time in String format
	 * @param departureTimeString Departure time in String format
	 */
	public void setDepartureTimeString(String departureTimeString) {
		this.departureTimeString = departureTimeString;
	}

	/**Set arrival time in String format
	 * @param arrivalTimeString Arrival time in String format
	 */
	public void setArrivalTimeString(String arrivalTimeString) {
		this.arrivalTimeString = arrivalTimeString;
	}

	/**
	 * Convert object to printable string of format
	 * 
	 * @return the object formatted as String to display
	 */
	public String toString() {
		return "Flight [airplane=" + airplane + ", flightTime=" + flightTime + ", number=" + number + ", departureCode="
				+ departureCode + ", departureTime=" + departureTime + ", arrivalCode=" + arrivalCode + ", arrivalTime="
				+ arrivalTime + ", firstClassPrice=" + firstClassPrice + ", numOfFirstClassReserved="
				+ numOfFirstClassReserved + ", coachPrice=" + coachPrice + ", numOfCoachReserved=" + numOfCoachReserved
				+ ", departureTimeString=" + departureTimeString + ", arrivalTimeString=" + arrivalTimeString + "]";
	}

	/**Get airplane name
	 * @return airplane name
	 */
	public String getAirplane() {
		return airplane;
	}

	/**Get duration of this flight
	 * @return duration of this flight
	 */
	public double getFlightTime() {
		return flightTime;
	}

	/**Get flight number
	 * @return flight number
	 */
	public int getNumber() {
		return number;
	}

	/**Get departure airport code
	 * @return departure airport code
	 */
	public String getDepartureCode() {
		return departureCode;
	}

	/**Get departure time
	 * @return departure time
	 */
	public Date getDepartureTime() {
		return departureTime;
	}

	/**Get arrival airport code
	 * @return arrival airport code
	 */
	public String getArrivalCode() {
		return arrivalCode;
	}
	
	/**Get arrival time
	 * @return arrival time
	 */
	public Date getArrivalTime() {
		return arrivalTime;
	}

	/**Get price of first-class seats
	 * @return price of first-class seats
	 */
	public double getFirstClassPrice() {
		return firstClassPrice;
	}

	/**Get number of first-class seats reserved
	 * @return number of first-class seats reserved
	 */
	public int getNumOfFirstClassReserved() {
		return numOfFirstClassReserved;
	}

	/**Get price of coach seats
	 * @return price of coach seats
	 */
	public double getCoachPrice() {
		return coachPrice;
	}

	/**Get number of coach seats reserved
	 * @return number of coach seats reserved
	 */
	public int getNumOfCoachReserved() {
		return numOfCoachReserved;
	}

	/**Get departure time in String format
	 * @return departure time in String format
	 */
	public String getDepartureTimeString() {
		return departureTimeString;
	}

	/**Get arrival time in String format
	 * @return arrival time in String format
	 */
	public String getArrivalTimeString() {
		return arrivalTimeString;
	}
	
	
	
	
}
