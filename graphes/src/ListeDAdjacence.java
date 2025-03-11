import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ListeDAdjacence extends Graph{
	
	private Map<Airport,Set<Flight>> outputFlights;

	public ListeDAdjacence(){
		super();
		outputFlights=new HashMap<Airport,Set<Flight>>();

	}

	@Override
	// Complexit?: O(1)
	protected void ajouterSommet(Airport a) {
		outputFlights.putIfAbsent(a,new HashSet<>());
	}

	@Override
	// Complexit?:
	protected void ajouterArc(Flight f) {
		Airport source=f.getSource();
		outputFlights.get(source).add(f);
	}

	@Override
	// Complexit?: ?
	public Set<Flight> arcsSortants(Airport a) {
		return outputFlights.get(a);
	}

	@Override
	// Complexit?: ?
	public boolean sontAdjacents(Airport a1, Airport a2) {
		for(Flight f : outputFlights.get(a1)){
			if(f.getDestination().equals(a2)){
				return true;
			}
		}
		return false;
	}

}
