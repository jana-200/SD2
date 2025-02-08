import java.util.*;

public class Doodle {
	private PlageHoraire[] plages;
	private HashMap<PlageHoraire, ArrayList<String>> dispo;
	private PriorityQueue<PlageHoraire> tri;

	public Doodle(PlageHoraire... plages) {
		this.plages = plages;
		this.dispo=new HashMap<>();
		for (PlageHoraire plage: plages) {
			this.dispo.put(plage, new ArrayList<>());
		}
		tri=new PriorityQueue<>((p2,p1)->Integer.compare(dispo.get(p1).size(), dispo.get(p2).size()));
	}

	// ajoute les disponibilit�s d'un participant sous forme d'un tableau de booleen.
	// les indices du tableau correspondent aux indices du tableau plages
	// true � l'indice i veut dire que le participant est disponible pour la plage � l'indice i du tableau plages
	// false � l'indice i veut dire que le participant n'est pas disponible pour la plage � l'indice i du tableau plages
	public void ajouterDisponibilites(String participant, boolean[] disponibilites) {
		if (disponibilites.length != plages.length)
			throw new IllegalArgumentException();

		for (int i = 0; i < disponibilites.length; i++) {
			if(disponibilites[i]){
				PlageHoraire plage = plages[i];
				plage.setNbParticipantPresent(plage.getNbParticipantPresent()+1);
				dispo.get(plage).add(participant);

			}
		}
	}
	
	// Hypoth�se: la PlageHoraire plage en param�tre fait bien partie du tableau plages
	// renvoie vrai si le participant est disponible pour cette plage horaire
	// renvoie faux si le participant n'est pas disponible ou s'il n'a pas rempli le
	// sondage doodle
	public boolean estDisponible(String participant, PlageHoraire plage) {
		return dispo.get(plage).contains(participant);
	}

	// renvoie une des plages horaires qui a le maximum de participants pr�vus
	// cette m�thode est appel�e apr�s que les participants aient rempli leurs disponibilit�s
	public PlageHoraire trouverPlageQuiConvientLeMieux() {
		tri.addAll(Arrays.asList(plages));
		return tri.peek();
	}

}
