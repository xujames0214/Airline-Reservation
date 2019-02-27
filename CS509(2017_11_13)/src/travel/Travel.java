package travel;

import flight.Flight;

public class Travel {
	int numOfFlights;
	Flight[] flights;
	public Travel(Flight flight){
		numOfFlights = 1;
		flights = new Flight[numOfFlights];
		flights[0] = flight;
	}
	
	public Travel(Flight flight1, Flight flight2){
		numOfFlights = 2;
		flights = new Flight[numOfFlights];
		flights[0] = flight1;
		flights[1] = flight2;
	}
	
	public Travel(Flight flight1, Flight flight2, Flight flight3){
		numOfFlights = 3;
		flights = new Flight[numOfFlights];
		flights[0] = flight1;
		flights[1] = flight2;
		flights[2] = flight3;
	}
	
	public String toString(){
		String str = "";
		for(int i = 0;i < numOfFlights;i++){
			str += "("+ i + ")" + flights[i].toString() + "\n";
		}
		return str;
	}
}
