
public class Main {
	public static void main(String[] args) throws Exception {
		Graph g = new Graph("./graphe_NOM_PRENOM/flight.xml");
		g.bfs(g.getAirport("JFK"));
	}
}
