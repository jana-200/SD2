import java.util.*;

public class Trees {

	// *******************************************************
	// Un premier exemple: le nombre de feuilles d'un arbre
	// *******************************************************
	public static int nbrLeaves(Tree t) {
		int r;
		if (t.isLeaf()) {
			r = 1;
		} else {
			r = 0;
			for (Tree child : t) {
				r += nbrLeaves(child);
			}
		}
		return r;
	}

	// *******************************************************
	// Un deuxième exemple: aplanir un arbre
	// *******************************************************
	public static Tree[] flattenLeaves(Tree t) {
		int nl = nbrLeaves(t);
		Tree[] r = new Tree[nl];
		flattenLeaves(t, r, 0);
		return r;
	}

	private static int flattenLeaves(Tree t, Tree[] a, int idx) {
		int r;
		if (t.isLeaf()) {
			a[idx] = t;
			r = 1;
		} else {
			r = 0;
			for (Tree child : t) {
				r += flattenLeaves(child, a, idx + r);
			}
		}
		return r;
	}

	// *******************************************************
	// Un troisième exemple:
	// tous les algorithmes ne sont pas récursifs
	// *******************************************************
	public static void pathV1(Tree t) {
		System.out.println(t.getValue());
		if (t.getParent() != null) {
			pathV1(t.getParent());
		}
	}

	public static void pathV2(Tree t) {
		while (t != null) {
			System.out.println(t.getValue());
			t = t.getParent();
		}
	}

	// *******************************************************
	// Exercices 1
	// *******************************************************

	// 1.1)

	public static int nbrNode(Tree t) {
		if(t==null) return 0;
		int nbrNoeud=1;
		for(Tree child: t.getChildren()){
			nbrNoeud+=nbrNode(child);
		}
		return nbrNoeud;
	}


	// 1.2)
	public static int min(Tree t) {
		if(t==null) return Integer.MAX_VALUE;
		int min= t.getValue();
		for(Tree child: t.getChildren()){
			int childMin = min(child);
			if(min>childMin){
				min=childMin;
			}
		}
		return min;
	}

	// 1.3)
	public static int sum(Tree t) {
		if(t==null) return 0;
		int sum=t.getValue();
		for(Tree child: t.getChildren()){
			sum+=sum(child);
		}
		return sum;
	}

	// 1.4)
	public static boolean equals(Tree t1, Tree t2) {
		if(t1==null || t2==null) return t1 == t2;
		if(t1.getValue() != t2.getValue()) return false;
		Tree[] c1 = t1.getChildren();
		Tree[] c2 = t2.getChildren();
		if(c1.length!=c2.length) return false;
		for(int i=0; i<c1.length; i++){
			if(!equals(c1[i], c2[i])) return false;
		}
		return true;
	}

	// 1.5)
	public static int depth(Tree n) {
		if (n == null || n.getParent() == null) return 0;
		return 1 + depth(n.getParent());
	}

	// 1.6)
	public static boolean sameOne(Tree n1, Tree n2) {
		if(n1.getParent()==null && n2.getParent()!=null){
			return sameOne(n1, n2.getParent());
		}
		if(n1.getParent()!=null && n2.getParent()==null){
			return sameOne(n1.getParent(),n2);
		}
		if(n1.getParent()==null && n2.getParent()==null){
			if(n1==n2) return true;
			else return false;
		}
		return sameOne(n1.getParent(),n2.getParent());
	}

	// 1.7)
	public static void dfsPrint(Tree t) {
		System.out.println(t.getValue());
		Tree[] children = t.getChildren();
		for (Tree child : children) {
			dfsPrint(child);
		}
	}

	// 1.8)
	public static void bfsPrint(Tree t) {
		if (t == null) return;
		Deque<Tree> q = new LinkedList<Tree>();
		q.add(t);
		bfsPrintBis(q);
	}

	private static void bfsPrintBis(Queue<Tree> q) {
		if (q.isEmpty()) return;
		Tree current=q.poll();
		System.out.println(current.getValue());
		for (Tree child : current.getChildren()) {
			q.add(child);
		}
		bfsPrintBis(q);
	}


	// *******************************************************
	// Exercices 2
	// *******************************************************

	// 2.1)
	static void printPathV1(Tree node) {
		if (node == null) return;
		printPathV1(node.getParent());
		System.out.println(node.getValue());
	}

	// 2.2)
	static void printPathV2(Tree node) {
		Deque<Tree> pile = new ArrayDeque<>();
		Tree current = node;

		while (current != null) {
			pile.push(current);
			current = current.getParent();
		}

		while (!pile.isEmpty()) {
			System.out.println(pile.pop().getValue());
		}
	}

	// 2.3
	static void printPathV3(Tree t, int v) {
		//todo
	}

	// *******************************************************
	// Exercices 3
	// *******************************************************

	// 3.1
	public static int[][] toArray(Tree t) {
		int n = nbrLeaves(t);
		int[][] mat = new int[3][n+1];
		fillMatrix(t, mat, 0);
		return mat;
	}

	private static void fillMatrix(Tree t, int[][] mat, int index) {
		int currentIndex = index;
		mat[0][currentIndex] = t.getValue();
		index++;
		int leftIndex = -1;
		int rightIndex = -1;
		if (t.getChildren().length > 0) {
			leftIndex = index;
			fillMatrix(t.getChildren()[0], mat, index);
		}

		if (t.getChildren().length > 1) {
			rightIndex = index;
			fillMatrix(t.getChildren()[1], mat, index);
		}
		mat[1][currentIndex] = leftIndex;
		mat[2][currentIndex] = rightIndex;

	}


	// 3.2
	public static Tree toTree(int[][] t) {
		return null;
	}

	// *******************************************************
	// Exercices 4
	// *******************************************************

	public static Tree lca(Tree n1, Tree n2) {
		return null;
	}
}