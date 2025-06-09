
public class Athlete implements Comparable<Athlete> {
	private final String nom;
	private int nbSautEffectue = 0;
	private int[] sauts = new int[3];

	public Athlete(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void ajouterSaut(int longueur) {
		if (longueur < 0)
			throw new IllegalArgumentException();
		if (nbSautEffectue == 3)
			throw new RuntimeException();
		sauts[nbSautEffectue] = longueur;
		nbSautEffectue++;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Athlete other = (Athlete) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}
	public int getMeilleurSaut() {
		int max = 0;
		for (int i = 0; i < nbSautEffectue; i++) {
			if (sauts[i] >= max) {
				max = sauts[i];
			}
		}
		return max;
	}

	@Override
	public int compareTo(Athlete o) {

		int cmp = Integer.compare(o.getMeilleurSaut(), this.getMeilleurSaut());
		if (cmp != 0) return cmp;
		return this.nom.compareTo(o.nom);
	}
}
