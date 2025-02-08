import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static java.time.temporal.ChronoUnit.MILLIS;

public class LocationPatins {
	HashSet<Integer> libres;
	HashMap<Integer, ArrayList<Integer>> patins;
	HashMap<Integer, LocalTime> attribues;
	
	public LocationPatins(int[] casiers) {
		attribues = new HashMap<>();
		patins=new HashMap<>();
		libres=new HashSet<>();
		for (int i = 0; i < casiers.length; i++){
			if (!patins.containsKey(casiers[i])) {
				patins.put(casiers[i], new ArrayList<>());
			}
			patins.get(casiers[i]).add(i);
			libres.add(i);
		}

	}

	// date1 < date2
	private static double prix(LocalTime date1, LocalTime date2) {
		// 1 euro par milliseconde (c'est assez cher en effet)
		return MILLIS.between(date1, date2) ;
	}

	public int attribuerCasierAvecPatins(int pointure) {
		if (pointure < 33 || pointure > 48)
			throw new IllegalArgumentException();
		LocalTime l = LocalTime.now();

		if (patins.containsKey(pointure) && !patins.get(pointure).isEmpty()) {
			int numCasier = patins.get(pointure).getFirst();
			if (libres.contains(numCasier)) {
				libres.remove(numCasier);
				attribues.put(numCasier, l);
				return numCasier;
			}
		}
		return -1;

	}

	public double libererCasier(int numeroCasier) {
		libres.add(numeroCasier);
		LocalTime debut = attribues.remove(numeroCasier);
		return prix(debut, LocalTime.now());
	}

}
