import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class Tree implements Iterable<Tree> {

	private int value;

	private Tree parent;

	private Tree[] children;

	// *******************************************************
	// CONSTRUCTEURS
	// *******************************************************
	public Tree(int v, Tree[] chd) {
		value = v;
		children = chd;

		for (Tree child : chd) {
			child.parent = this;
		}
	}

	public Tree(int v) {
		this(v, new Tree[0]);
	}

	// *******************************************************
	// GETTERS
	// *******************************************************
	public int getValue() {
		return value;
	}

	public Tree getParent() {
		return parent;
	}

	public Tree[] getChildren() {
		return children;
	}

	// *******************************************************
	// ITERATEUR
	// *******************************************************
	public Iterator<Tree> iterator() {
		return Arrays.asList(children).iterator();
	}

	public int nbrChildren() {
		return children.length;
	}

	public boolean isLeaf() {
		return children.length == 0;
	}

	// renvoie vrai si l'entier est contenu dans l'arbre, faux sinon
	public boolean contains(int x) {
		return false;
	}
	

	// renvoie une map dont les cl�s sont les entiers pr�sents dans l'arbre 
	// et les valeurs sont le nombre de fois qu'apparaissent ces entiers dans l'arbre
	public HashMap<Integer,Integer> toMap(){
		HashMap<Integer,Integer> map = new HashMap<>();
		map.put(this.value,1);
		toMapBis(map);
		return map;
	}
	private void toMapBis(HashMap<Integer,Integer> map){
		if(children.length == 0){return;}
		for(Tree child : children){
			if(map.containsKey(child.value)){
				map.put(child.value,map.get(child.value)+1);
			}else map.put(child.value,1);

			child.toMapBis(map);
		}
	}

	public static void main(String[] args) {
		Tree l6 = new Tree(6);
		Tree l1 = new Tree(1);
		Tree t9 = new Tree(9, new Tree[] { l6, l1 });
		Tree l3 = new Tree(3);
		Tree l8 = new Tree(8);
		Tree t8 = new Tree(8, new Tree[] { l3, l8 });
		Tree l4 = new Tree(4);
		Tree t4 = new Tree(4, new Tree[] { t8, t9, l4});
		System.out.println(t4.contains(6));
		System.out.println(t4.toMap());
	}
}

