import java.io.File;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Graph {

	protected Map<String, Airport> correspondanceIataAirport ;
	private ArrayList<Flight> flights= new ArrayList<Flight>();
	
	public Graph (String xmlFile) throws Exception {
		correspondanceIataAirport= new HashMap<String, Airport>();
		File xml = new File(xmlFile);
		DocumentBuilderFactory docBuildFact = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuild = docBuildFact.newDocumentBuilder();
		Document doc = docBuild.parse(xml);
		NodeList airports = doc.getElementsByTagName("airport");
		for (int i = 0; i < airports.getLength(); i++) {
			Node airport = airports.item(i);
			Element elAirport = (Element) airport;
			String iata = elAirport.getAttribute("iata");
			String name = elAirport.getAttribute("name");
			Airport a = new Airport(iata, name);
			correspondanceIataAirport.put(iata, a);
		}
		for (int i = 0; i < airports.getLength(); i++) {
			Node airport = airports.item(i);
			Element elAirport = (Element) airport;
			String iata = elAirport.getAttribute("iata");
			NodeList flights = elAirport.getElementsByTagName("flight");
			for (int j = 0; j < flights.getLength(); j++) {
				Node flight = flights.item(j);
				Element elFlight = (Element) flight;
				String dest = elFlight.getTextContent();
				String airline = elFlight.getAttribute("airline");
				Flight f = new Flight(correspondanceIataAirport.get(iata), correspondanceIataAirport.get(dest),
						airline);
				this.flights.add(f);
			}
		}
	}
	
	public Airport getAirport(String iata) {
		return correspondanceIataAirport.get(iata);
	}	
	
	//affiche a la sortie standard les codes iata des differents aeroports 
	//qu il est possible d'atteindre dans l ordre d un parcours en largeur (BFS) depuis l aeroport de depart.
	public void bfs(Airport a) {
		Queue<Airport> q = new LinkedList<Airport>();
		HashSet<Airport> visited=new HashSet<>();
		visited.add(a);
		q.add(a);
		while (!q.isEmpty()) {
			Airport curr = q.poll();
			System.out.println(curr.getIata());

			for(Flight f:flights) {
				if(f.getSource().equals(curr)) {
					if(!visited.contains(f.getDestination())) {
						visited.add(f.getDestination());
						q.add(f.getDestination());
					}
				}
			}
		}
	}

}
