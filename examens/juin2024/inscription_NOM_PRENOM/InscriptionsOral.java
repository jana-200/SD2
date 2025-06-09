import java.util.*;

public class InscriptionsOral {
	
	HashMap<PlageHoraire, List<Etudiant>> map;
	HashMap<Etudiant, PlageHoraire> etudiants;
	HashSet<Etudiant> set;
	PriorityQueue<PlageHoraire> pq;
	
	public InscriptionsOral() {
		map = new HashMap<>();
		set = new HashSet<>();
		pq = new PriorityQueue<>();
		etudiants = new HashMap<>();
	}
	
	// Inscrit l'?tudiant ? une plage horaire. Plusieurs ?tudiants peuvent s'inscrire ? la m?me plage horaire.
	// Cette m?thode v?rifie si l'?tudiant est d?j? inscrit ? une autre plage horaire.
    // Si oui, une RuntimeException est lanc?e
	public void inscrireEtudiant(Etudiant e, PlageHoraire p) {
		if(set.contains(e)) throw new RuntimeException();
		if(!pq.contains(p)) pq.add(p);
		set.add(e);
		etudiants.put(e, p);
		if(map.containsKey(p)) {
			map.get(p).add(e);
		}else{
			List<Etudiant> list = new ArrayList<>();
			list.add(e);
			map.put(p, list);
		}
	}
	
	// Affiche l'horaire des examens en ordre chronologique.
    // Elle affiche les ?tudiants inscrits pour chaque plage horaire. 
	// L'ordre des ?tudiants au sein d'une plage n'a pas d'importance
	public void afficherHoraire() {

		for(PlageHoraire p : pq) {
			System.out.println(p.toString() + " : "+map.get(p).toString());
		}
	}
	
	// Echange les plages horaires de deux ?tudiants
    // Si l'un des deux ?tudiants n'est pas inscrit, une RuntimeException est lanc?e
	public void echanger(Etudiant e1, Etudiant e2) {
		PlageHoraire p1=etudiants.get(e1);
		PlageHoraire p2=etudiants.get(e2);

		map.get(p1).remove(e1);
		map.get(p1).add(e2);
		map.get(p2).remove(e2);
		map.get(p2).add(e1);
	}
	
}
