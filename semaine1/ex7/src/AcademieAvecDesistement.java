import java.util.*;

public class AcademieAvecDesistement {
	private HashMap<String, LinkedHashSet<String>> file;

	public AcademieAvecDesistement(ArrayList<String> v){
		file = new HashMap<>();
		for(String s : v){
			file.put(s, new LinkedHashSet<>());
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
		LinkedHashSet<String> fileInstru = file.get(instrument);
		if(fileInstru.isEmpty()) return null;

		Iterator<String> it= fileInstru.iterator();
		String str=it.next();
		it.remove();
		return str;
	} 
	
	

}
