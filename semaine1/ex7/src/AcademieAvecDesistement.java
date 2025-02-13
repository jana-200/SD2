import java.util.*;

public class AcademieAvecDesistement {
	private HashMap<String, LinkedList<String>> file;

	public AcademieAvecDesistement(ArrayList<String> v){
		file = new HashMap<>();
		for(String s : v){
			file.put(s, new LinkedList<>());
		}
	}
	
	public void mettreEnAttente(String instrument, String nomEleve){
		file.get(instrument).add(nomEleve);
	} 
	
	public void desistement(String instrument, String eleve){
		file.get(instrument).remove(eleve);
	}
	
	//supprime uniquement l'�l�ve de la file d'attente
	public String attribuerPlace(String instrument){
		LinkedList<String> fileInstru = file.get(instrument);
		if(fileInstru.isEmpty()) return null;

		String str = fileInstru.removeFirst();
		return str;
	} 
	
	

}
