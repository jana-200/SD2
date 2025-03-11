import java.util.*;

public class AcademieAvecDesistement {
	private HashMap<String, ArrayDeque<String>> file;
	private Set<String> desistement;

	public AcademieAvecDesistement(List<String> instruments) {
		file = new HashMap<>(instruments.size());
		desistement = new HashSet<>();
		for (String instrument : instruments) {
			file.put(instrument, new ArrayDeque<>());
		}
	}

	public void mettreEnAttente(String instrument, String nomEleve) {
		Queue<String> queue = file.get(instrument);
		if (queue != null) {
			queue.add(nomEleve);
		}
	}

	public void desistement(String instrument, String eleve) {
		desistement.add(eleve);

		ArrayDeque<String> queue = file.get(instrument);
		if (queue != null) {
			queue.remove(eleve);
		}
	}

	public String attribuerPlace(String instrument) {
		ArrayDeque<String> queue = file.get(instrument);
		if (queue == null || queue.isEmpty()) return null;

		while (!queue.isEmpty() && desistement.contains(queue.getFirst())) {
			desistement.remove(queue.removeFirst());
		}

		return queue.poll();
	}
}
