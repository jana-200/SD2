import java.util.*;

public class ChoixOptions {

	// associe le nom d'une option avec son objet Option correspondant
	private Map<String, Option> options;
	// ajouter ici les autres attributs
	private HashMap<Etudiant, ArrayList<String>> choix;
	private PriorityQueue<Etudiant> etudiantsTries;


	//constructeur prenant un entier et une suite de string en param�tres
	//ces string repr�sentent les noms des diff�rentes options possibles
	public ChoixOptions(int nbEtudiantsParOption, String... nomsOption) {
		this.options = new HashMap<String, Option>();
		if (nomsOption.length < 3)
			throw new IllegalArgumentException();
		for (int i = 0; i < nomsOption.length; i++) {
			String nomOption = nomsOption[i];
			options.put(nomOption, new Option(nomOption, nbEtudiantsParOption));
		}

		// initialiser les nouveaux attributs
		choix=new HashMap<>();
		etudiantsTries = new PriorityQueue<>((e1, e2) -> Integer.compare(e2.getMoyenne(), e1.getMoyenne()));


	}

	// cette m�thode encode les pr�f�rences des �tudiants
	// il ne faut pas v�rifier que ces choix soient valides
	public void ajouterPreferences(Etudiant etu, String choix1, String choix2, String choix3) {
		if(!choix.containsKey(etu)){
			choix.put(etu,new ArrayList<>());
			etudiantsTries.add(etu);
		}
		choix.get(etu).add(choix1);
		choix.get(etu).add(choix2);
		choix.get(etu).add(choix3);
	}

	// cette m�thode est appel�e apr�s que les �tudiants aient donn� leurs pr�f�rences
	// cette m�thode attribue les options aux �tudiants en favorisant les �tudiants
	// ayant les meilleures moyennes si il n'y a plus de place disponible dans certaines
	// options. Pour les �tudiants faibles, si les deux premi�res options sont pleines,
	// il faut recourir au troisi�me choix.
	// Cette m�thode doit faire appel � la m�thode inscrireEtudiant de la classe Option.
	public void attribuerOptions() {
		while (!etudiantsTries.isEmpty()) {
			Etudiant etu = etudiantsTries.poll();
			for (String choixOption : choix.get(etu)) {
				Option option = options.get(choixOption);
				if (option.inscrireEtudiant(etu)) {
					break;
				}
			}
		}

	}

	public String toString(){
		String s="";
		for (Option o:options.values()){
			s=s+o+"\n"+"-----------------"+"\n";
		}
		return s;
	}
}
