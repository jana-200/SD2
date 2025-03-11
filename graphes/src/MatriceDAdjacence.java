import java.util.*;

public class MatriceDAdjacence extends Graph{
	
	private Map<Integer, Airport>  correspondanceIndiceAirport;
	private Map<Airport, Integer>  correspondanceAirportIndice;
	private Flight[][] matrice= new Flight[0][0];
	private int nbAirport=0;

	public MatriceDAdjacence() {
		super();
		correspondanceAirportIndice= new HashMap<Airport,Integer>();
		correspondanceIndiceAirport= new HashMap<Integer,Airport>();
	}

	@Override
	// Complexit?: ?
	protected void ajouterSommet(Airport a) {	
		correspondanceAirportIndice.put(a,nbAirport);
		correspondanceIndiceAirport.put(nbAirport,a);
		Flight[][] mat= new Flight[nbAirport+1][nbAirport+1];
		for (int x=0;x<nbAirport;x++){
			for (int y=0;y<nbAirport;y++){
				mat[x][y]= matrice[x][y];
			}
		}
		matrice=mat;
		nbAirport++;
	}
	@Override
	// Complexit?: ?
	protected void ajouterArc(Flight f) {
		int source =  correspondanceAirportIndice.get(f.getSource());
		int arrivee= correspondanceAirportIndice.get(f.getDestination());
		matrice[source][arrivee]= f;
	}

	@Override
	// Complexit?: ?
	public Set<Flight> arcsSortants(Airport a) {
		Set<Flight> result= new HashSet<Flight>();
		int source =  correspondanceAirportIndice.get(a);
		for (Flight f: matrice[source]){
			if(f!=null) result.add(f);
		}
		return result;
	}

	@Override
	// Complexit?: ?
	public boolean sontAdjacents(Airport a1, Airport a2) {
		int ind1= correspondanceAirportIndice.get(a1);
		int ind2= correspondanceAirportIndice.get(a2);
		if(matrice[ind1][ind2]==null){
			return false;
		}
		return true;
	}
	
	

}
