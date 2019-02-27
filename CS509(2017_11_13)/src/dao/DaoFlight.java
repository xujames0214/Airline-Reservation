/**
 * 
 */
package dao;

import java.io.IOException;
import java.io.StringReader;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import airport.Airport;
import airport.Airports;
import flight.Flight;
import flight.Flights;
import time.TimeConversion;

/**
 * @author blake
 *
 */
public class DaoFlight {
	
	public static Flights addAll (String xmlFlights) throws NullPointerException {
		Flights flights = new Flights();
		
		// Load the XML string into a DOM tree for ease of processing
		// then iterate over all nodes adding each airport to our collection
		Document docFlights = buildDomDoc (xmlFlights);
		NodeList nodesFlights = docFlights.getElementsByTagName("Flight");
		
		for (int i = 0; i < nodesFlights.getLength(); i++) {
			Element elementFlight = (Element) nodesFlights.item(i);
			Flight flight = buildFlight(elementFlight);
			flights.add(flight);
		}
		
		return flights;
	}

	static private Flight buildFlight (Node nodeFlight) {
		
		Flight flight = new Flight();
		
		String airplane;
		double flightTime;
		int number;
		String departureCode;
		Date departureTime;
		String arrivalCode;
		Date arrivalTime;
		double firstClassPrice;
		int numOfFirstClassReserved;
		double coachPrice;
		int numOfCoachReserved;
		
		String departureTimeString;
		String arrivalTimeString;
		
		Element elementFlight = (Element) nodeFlight;
		airplane = elementFlight.getAttributeNode("Airplane").getValue();
		flightTime = Double.parseDouble(elementFlight.getAttributeNode("FlightTime").getValue());
		number = Integer.parseInt(elementFlight.getAttributeNode("Number").getValue());
		
		Element elementDeparture = (Element)elementFlight.getElementsByTagName("Departure").item(0);
		Element elementDepartureCode = (Element)elementDeparture.getElementsByTagName("Code").item(0);
		Element elementDepartureTime = (Element)elementDeparture.getElementsByTagName("Time").item(0);
		departureCode = getCharacterDataFromElement(elementDepartureCode);
		departureTimeString = getCharacterDataFromElement(elementDepartureTime);
		
		Element elementArrival = (Element)elementFlight.getElementsByTagName("Arrival").item(0);
		Element elementArrivalCode = (Element)elementArrival.getElementsByTagName("Code").item(0);
		Element elementArrivalTime = (Element)elementArrival.getElementsByTagName("Time").item(0);
		arrivalCode = getCharacterDataFromElement(elementArrivalCode);
		arrivalTimeString = getCharacterDataFromElement(elementArrivalTime);
		
		Element elementSeating = (Element)elementFlight.getElementsByTagName("Seating").item(0);
		Element elementFirstClass = (Element)elementSeating.getElementsByTagName("FirstClass").item(0);
		Element elementCoach = (Element)elementSeating.getElementsByTagName("Coach").item(0);
		
		firstClassPrice = Double.parseDouble(elementFirstClass.getAttribute("Price").substring(1).replace(",",""));
		numOfFirstClassReserved = Integer.parseInt(getCharacterDataFromElement(elementFirstClass));
		coachPrice = Double.parseDouble(elementCoach.getAttribute("Price").substring(1).replace(",", ""));
		numOfCoachReserved = Integer.parseInt(getCharacterDataFromElement(elementCoach));
		
		departureTime = TimeConversion.parseDate(departureTimeString);
		arrivalTime = TimeConversion.parseDate(arrivalTimeString);
		
		flight.setAirplane(airplane);
		flight.setFlightTime(flightTime);
		flight.setNumber(number);
		flight.setDepartureCode(departureCode);
		flight.setDepartureTimeString(departureTimeString);
		flight.setArrivalCode(arrivalCode);
		flight.setArrivalTimeString(arrivalTimeString);
		flight.setFirstClassPrice(firstClassPrice);
		flight.setNumOfFirstClassReserved(numOfFirstClassReserved);
		flight.setCoachPrice(coachPrice);
		flight.setNumOfCoachReserved(numOfCoachReserved);
		flight.setDepartureTime(departureTime);
		flight.setArrivalTime(arrivalTime);
		return flight;
	}

	/**
	 * Builds a DOM tree from an XML string
	 * 
	 * Parses the XML file and returns a DOM tree that can be processed
	 * 
	 * @param xmlString XML String containing set of objects
	 * @return DOM tree from parsed XML or null if exception is caught
	 */
	static private Document buildDomDoc (String xmlString) {
		/**
		 * load the xml string into a DOM document and return the Document
		 */
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			InputSource inputSource = new InputSource();
			inputSource.setCharacterStream(new StringReader(xmlString));
			
			return docBuilder.parse(inputSource);
		}
		catch (ParserConfigurationException e) {
			e.printStackTrace();
			return null;
		}
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		catch (SAXException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Retrieve character data from an element if it exists
	 * 
	 * @param e is the DOM Element to retrieve character data from
	 * @return the character data as String [possibly empty String]
	 */
	private static String getCharacterDataFromElement (Element e) {
		Node child = e.getFirstChild();
	    if (child instanceof CharacterData) {
	        CharacterData cd = (CharacterData) child;
	        return cd.getData();
	      }
	      return "";
	}
}
