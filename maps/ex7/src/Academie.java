import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

public class Academie {

	private HashMap<String, ArrayDeque<String>> file;
	public Academie(ArrayList<String> v){
		file = new HashMap<>();
		for(String s : v){
			file.put(s, new ArrayDeque<>());
		}
	}
	
	public void mettreEnAttente(String instrument, String nomEleve){
		file.get(instrument).add(nomEleve);
	} 
	
	// supprime uniquement l'�l�ve de la file d'attente et le renvoie
	// renvoie null s�il n�y a pas d��l�ve en attente pour cet instrument	
	public String attribuerPlace(String instrument){
		if(file.get(instrument).isEmpty())return null;
		return file.get(instrument).removeFirst();
	} 

}
