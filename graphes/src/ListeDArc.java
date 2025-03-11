import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ListeDArc extends Graph{
	
	private ArrayList<Flight> flights;

	public ListeDArc() {
		super();
		flights=new ArrayList<Flight>();
	}

	@Override
	// Complexit?: ?
	protected void ajouterSommet(Airport a) {	
	}

	@Override
	// Complexit?: ?
	protected void ajouterArc(Flight f) {
		flights.add(f);
	}

	@Override
	// Complexit?: ?
	public Set<Flight> arcsSortants(Airport a) {
		Set<Flight> set=new HashSet<>();
		for (Flight f : flights) {
			if(f.getSource().equals(a))
				set.add(f);
		}
		return set;
	}

	@Override
	// Complexit?: ?
	public boolean sontAdjacents(Airport a1, Airport a2) {
		for (Flight f : flights) {
			if(f.getSource().equals(a1) && f.getDestination().equals(a2))
				return true;
		}
		return false;
	}

}
