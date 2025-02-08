import java.util.*;

public class GestionImpression {
	private LinkedHashSet<String> users = new LinkedHashSet<>();
	private HashMap<String,ArrayDeque<Impression>> impressions=new HashMap<>();


	public void ajouterImpression(Impression impr){
		impressions.putIfAbsent(impr.getIdUtilisateur(), new ArrayDeque<>());

		impressions.get(impr.getIdUtilisateur()).add(impr);
		users.remove(impr.getIdUtilisateur());
		users.add(impr.getIdUtilisateur());
	}

	public Impression selectionnerImpression(){
		if(users.isEmpty()) return null;

		// Récupère et supprime le premier utilisateur en file
		Iterator<String> it = users.iterator();
		String user = it.next();
		it.remove();

		ArrayDeque<Impression> file=impressions.get(user);
		if(file.isEmpty()) return null;
		Impression impr=file.removeLast();
		impressions.remove(user);
		return impr;
	}

}
