package searchTravel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import dao.ServerInterface;
import flight.Flight;
import flight.Flights;
import searchParameter.SearchParameter;
import travel.Travel;
import travel.Travels;
import utils.Saps;

public class SearchTravel {
	private static final long THIRTY_MINUTES_MILLISECONDS = 1000 * 60 * 30;
	private static final long FOUR_HOURS_MILLISECONDS = 1000 * 60 * 60 * 4;
	private static final int FIRST_TRAVEL = 0;
	private static final int SECOND_TRAVEL = 1;
	
	private SearchParameter searchParameter;

	public SearchTravel(SearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}
	
	public Travels getFistTravels(){
		return getTravels(FIRST_TRAVEL);
	}
	
	public Travels getSecondTravels(){
		if(searchParameter.isRoundTrip()) return getTravels(SECOND_TRAVEL);
		else return null;
	}
	
	private Travels getTravels(int flag){
		Travels travels = new Travels();
		String departureAirportCode = null;
		String arrivalAirportCode = null;
		String departureDateString = null;
		String nextDayofDepartureDateString = null;
		if(flag == FIRST_TRAVEL){
			departureAirportCode = searchParameter.getDepartureAirportCode();
			arrivalAirportCode = searchParameter.getArrivalAirportCode();
			departureDateString = searchParameter.getDepartureDateString();
			nextDayofDepartureDateString = nextDayofDate(departureDateString);
		}else if(flag == SECOND_TRAVEL){
			departureAirportCode = searchParameter.getArrivalAirportCode();
			arrivalAirportCode = searchParameter.getDepartureAirportCode();
			departureDateString = searchParameter.getReturnDateString();
			nextDayofDepartureDateString = nextDayofDate(departureDateString);
		}
		//One-flight travel;
		Flights departingFlights = ServerInterface.INSTANCE.getDepartingFlights(Saps.teamName, departureAirportCode, departureDateString);
		for(Flight flight : departingFlights){
			if(flight.getArrivalCode().equals(arrivalAirportCode)){
				travels.add(new Travel(flight));
			}
		}
		
		//Two-flight travel;
		Flights arrivingFlights1 = ServerInterface.INSTANCE.getArrivingFlights(Saps.teamName, arrivalAirportCode, departureDateString);
		Flights arrivingFlights2 = ServerInterface.INSTANCE.getArrivingFlights(Saps.teamName, arrivalAirportCode, nextDayofDepartureDateString);
		Flights arrivingFlights = new Flights();
		arrivingFlights.addAll(arrivingFlights1);
		arrivingFlights.addAll(arrivingFlights2);
		HashMap<String,Flight> arrivingFlightsMap = new HashMap<String,Flight>();
		
		for(Flight flight : arrivingFlights){
			arrivingFlightsMap.put(flight.getDepartureCode(), flight);
		}
		
		for(Flight departingFlight : departingFlights){
			if(arrivingFlightsMap.containsKey(departingFlight.getArrivalCode())){
				Flight arrivingFlight = arrivingFlightsMap.get(departingFlight.getArrivalCode());
				if(isTimeIntervalOK(departingFlight.getArrivalTime(),arrivingFlight.getDepartureTime())){
					travels.add(new Travel(departingFlight,arrivingFlight));
				}
			}
		}
		
		//Three-flight travel
		for(Flight departingFlight : departingFlights){
			Flights middleFlights1 = ServerInterface.INSTANCE.getDepartingFlights(Saps.teamName, departingFlight.getArrivalCode(), convertDate(departingFlight.getArrivalTime()));
			Flights middleFlights2 = ServerInterface.INSTANCE.getDepartingFlights(Saps.teamName, departingFlight.getArrivalCode(), nextDayofDate(convertDate(departingFlight.getArrivalTime())));
			Flights middleFlights = new Flights();
			middleFlights.addAll(middleFlights1);
			middleFlights.addAll(middleFlights2);
			for(Flight middleFlight : middleFlights){
				if(!middleFlight.getArrivalCode().equals(departingFlight.getDepartureCode()) && isTimeIntervalOK(departingFlight.getArrivalTime(),middleFlight.getDepartureTime()) && arrivingFlightsMap.containsKey(middleFlight.getArrivalCode())){
					Flight arrivingFlight = arrivingFlightsMap.get(middleFlight.getArrivalCode());
					if(isTimeIntervalOK(middleFlight.getArrivalTime(),arrivingFlight.getDepartureTime())){
						travels.add(new Travel(departingFlight,middleFlight,arrivingFlight));
					}
				}
			}
		
		}
		
		return travels;
	}
	
	private boolean isTimeIntervalOK(Date date1, Date date2){
		long time1 = date1.getTime();
		long time2 = date2.getTime();
		long interval = time2 - time1;
		return interval >= THIRTY_MINUTES_MILLISECONDS && interval <= FOUR_HOURS_MILLISECONDS;
	}
	
	private String nextDayofDate(String dateString){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
		Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		return sdf.format(cal.getTime());
	}
	
	private String convertDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
		return sdf.format(date);
	}
}
