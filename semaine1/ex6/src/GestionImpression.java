import java.util.*;

public class GestionImpression {
	private LinkedList<String> users = new LinkedList<String>();
	private HashMap<String,ArrayDeque<Impression>> impressions=new HashMap<String,ArrayDeque<Impression>>();


	public void ajouterImpression(Impression impr){
		impressions.putIfAbsent(impr.getIdUtilisateur(), new ArrayDeque<>());

		impressions.get(impr.getIdUtilisateur()).add(impr);
 		users.removeFirstOccurrence(impr.getIdUtilisateur());
		users.add(impr.getIdUtilisateur());
	}

	public Impression selectionnerImpression(){
		if(users.isEmpty()) return null;

		String user=users.removeFirst();

		ArrayDeque<Impression> file=impressions.get(user);
		if(file.isEmpty()) return null;
		Impression impr=file.removeLast();
		impressions.remove(user);
		return impr;
	}

}
