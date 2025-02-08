import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

public class Ordonnancement {
	public static final int NIVEAU_PRIORITE_MAX=5;
	private HashMap<Integer, ArrayDeque<Tache>> taches;

	public Ordonnancement(){
		taches=new HashMap<>();
		for(int i=1;i<=NIVEAU_PRIORITE_MAX;i++){
			taches.put(i, new ArrayDeque<>());
		}
	}

	public void ajouterTache (String descriptif, int priorite){
		taches.get(priorite).add(new Tache(descriptif, priorite));
	}
	
	//renvoie la tache prioritaire
	//renvoie null si plus de tache presente
	public Tache attribuerTache(){
		Tache tache;
		for(int i=NIVEAU_PRIORITE_MAX;i>=1;i--){
			if(!taches.get(i).isEmpty()){
				tache=taches.get(i).removeFirst();
				return tache;
			}
		}
		return null;
	}
}
