import java.util.*;


public class ProgrammesEtudiants {

	private Set<Etudiant> etudiants = new HashSet<Etudiant>();
	private HashMap<Etudiant, List<UniteEnseignement>> valides;
	private PriorityQueue<Etudiant> queue;
	
	public ProgrammesEtudiants(Etudiant... etudiants) {
		for (Etudiant etudiant : etudiants) {
			this.etudiants.add(etudiant);
		}
		valides = new HashMap<>();
		queue=new PriorityQueue<>();

	}

	// A COMPLETER	
	// Enregistre la validation de l'unit� d'enseignement par l'�tudiant et met �
	// jour le nombre d'ects valid� par l'�tudiant.
	// Si l'unit� d'enseignement a d�j� �t� valid�e par l'�tudiant, la m�thode 
	// lance une RuntimeException avec le message 'ue d�j� valid�e'
	public void valider(Etudiant e, UniteEnseignement ue) {
		if(valides.containsKey(e)) {
			if(valides.get(e).contains(ue)) throw new RuntimeException("ue déjà validée");
			else valides.get(e).add(ue);
		}
		else{
			List<UniteEnseignement> ues = new ArrayList<>();
			ues.add(ue);
			valides.put(e, ues);
		}
		e.setNbEctsValides(e.getNbEctsValides()+ue.getNbEcts());

	}
	
	// A COMPLETER
	// affiche la liste de tous les �tudiants (pr�nom, nom et nombre d'ects valid�s)
	// tri�s par le nombre d'ects valid�s
	// Voici un exemple de sortie attendue:
	// Alain Delcourt 10 ects
	// Pol Durant 8 ects
	// Jean Michel 0 ects
	// Si deux �tudiants ont le meme nombre d'ects valid�s, on affiche 
	// les deux �tudiants dans n'importe quel sens.
	public void afficherEtudiantsTriesParEcts() {
        queue.addAll(etudiants);
		while(!queue.isEmpty()) {
			Etudiant etudiant = queue.poll();
			System.out.println(etudiant.getNom()+" "+ etudiant.getPrenom()+" "+etudiant.getNbEctsValides());
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Etudiant e1= new Etudiant(123456, "Durant", "Pol");
		Etudiant e2= new Etudiant(123453, "Delcourt", "Alain");
		Etudiant e3= new Etudiant(123452, "Michel", "Jean");
		ProgrammesEtudiants p= new ProgrammesEtudiants(e1,e2,e3);
		UniteEnseignement sd2= new UniteEnseignement("SD2", 4);
		UniteEnseignement bd2= new UniteEnseignement("BD2", 6);
		UniteEnseignement mobile= new UniteEnseignement("Mobile", 4);
		p.valider(e1, sd2);
		p.valider(e1, mobile);
		p.valider(e2, bd2);
		p.valider(e2, mobile);
		p.afficherEtudiantsTriesParEcts();
		Thread.sleep(50); //cette ligne est uniquement presente pour l'affichage
		p.valider(e1, mobile);
	}
}
