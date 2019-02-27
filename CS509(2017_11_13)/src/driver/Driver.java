/**
 * 
 */
package driver;

import java.util.Collections;

import airport.Airport;
import airport.Airports;
import dao.ServerInterface;
import flight.Flight;
import flight.Flights;
import searchParameter.SearchParameter;
import searchTravel.SearchTravel;
import travel.Travel;
import travel.Travels;

/**
 * @author blake
 *
 */
public class Driver {

	/**
	 * Entry point for CS509 sample code driver
	 * 
	 * This driver will retrieve the list of airports from the CS509 server and print the list 
	 * to the console sorted by 3 character airport code
	 * 
	 * @param args is the arguments passed to java vm in format of "CS509.sample teamName" where teamName is a valid team
	 */
	public static void main(String[] args) {
		/*
		if (args.length != 1) {
			System.err.println("usage: CS509.sample teamName");
			System.exit(-1);
			return;
		}
		*/
		//String teamName = args[0];
		String teamName = "Xteam";
		// Try to get a list of airports
		Airports airports = ServerInterface.INSTANCE.getAirports(teamName);
		Collections.sort(airports);
		/*
		for (Airport airport : airports) {
			System.out.println(airport.toString());
		}
		*/
		
		/*
		String airportCode = "BOS";
		String departingDate = "2017_12_10";
		Flights flights = ServerInterface.INSTANCE.getDepartingFlights(teamName, airportCode, departingDate);
		
		
		for(Flight flight : flights){
			System.out.println(flight.toString());
		}
		*/
		
		SearchParameter searchParameter = new SearchParameter(true,"BOS","2017_12_10","JFK","2017_12_15",true);
		System.out.println("isAirportCodeValid:" + searchParameter.isAirportCodesValid(airports));
		System.out.println("isTravelDateValid:" + searchParameter.isTravelDatesValid());
		SearchTravel searchTravel = new SearchTravel(searchParameter);
		Travels firstTravels = searchTravel.getFistTravels();
		Travels secondTravels = searchTravel.getSecondTravels();
		System.out.println("First Travel:");
		for(Travel travel : firstTravels){
			System.out.println(travel);
		}
		
		System.out.println("Second Travel:");
		for(Travel travel : secondTravels){
			System.out.println(travel);
		}
		
	}
}
