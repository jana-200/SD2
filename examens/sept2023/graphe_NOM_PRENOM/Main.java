import java.io.File;

public class Main {

  public static void main(String[] args) {
    try {
      File lignes = new File("./graphe_NOM_PRENOM/lignes.txt");
      File troncons = new File("./graphe_NOM_PRENOM/troncons.txt");
      Graph g = new Graph(lignes, troncons);
      g.bfs(g.getStation("ALMA"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
