import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


public class Huffman {

	private static class Node implements Comparable<Node> {
		private char ch;
		private int freq;
		private final Node left, right;

		public Node(char ch, int freq, Node left, Node right)  {
			this.ch = ch;
			this.freq = freq;
			this.left = left;
			this.right = right;
		}

		public boolean isLeaf() {
			return left == null && right == null;
		}

		public int compareTo(Node other) {
			return Integer.compare(this.freq, other.freq);
		}

	}
	
	// renvoie une map qui a comme cl� les lettres de la chaine de 
	// caract�re donn�e en param�tre et comme valeur la fr�quence de 
	// ces lettres 
	public static Map<Character, Integer> computeFreq(String s) {
		Map<Character, Integer> freq = new HashMap<>();
		for (char c : s.toCharArray()) {
			if(freq.containsKey(c)) {
				freq.put(c, freq.get(c) + 1);
			}else freq.put(c, 1);
		}
		return freq;
	}	
	
	// renvoie l'arbre de Huffman obtenu � partir de la map des fr�quences des lettres 
	public static Node buildTree(Map<Character, Integer> freq) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for(Map.Entry<Character, Integer> entry : freq.entrySet()) {
			pq.add(new Node(entry.getKey(), entry.getValue(), null, null));
		}
		while(pq.size()>1) {
			Node n1=pq.poll();
			Node n2=pq.poll();
			Node parent;
			parent= new Node(' ', n1.freq+n2.freq,n1,n2);
			pq.add(parent);
		}
		return pq.poll();
	}
	
	// renvoie une map qui associe chaque lettre � son code (suite de 0 et de 1). 
	// Ce code est obtenu en parcourant l'arbre de Huffman donn� en param�tre
	public static Map<Character, String> buildCode(Node root) {
		Map<Character, String> code = new HashMap<>();
		buildCode(root,"",code);
		return code;
	}
	private static void buildCode(Node node, String path, Map<Character, String> code) {
		if (node == null) return;
		if (node.isLeaf()) {
			code.put(node.ch, path);
			return;
		}
		buildCode(node.left, path + "0", code);
		buildCode(node.right, path + "1", code);
	}

	
	// encode la chaine de caract�re prise en param�tre en une chaine de 
	// bit (0 et 1) en utilisant la map des codes codeMap
	public static String compress(String s, Map<Character, String> codeMap) {
		StringBuilder compressed=new StringBuilder();
		for(char c : s.toCharArray()) {
			if(codeMap.containsKey(c)) {
				compressed.append(codeMap.get(c));
			}
		}
		return compressed.toString();
	}
	
	// Cette m�thode d�code une chaine de 0 et de 1 cod� � l'aide de l'algorithme de Huffman
	// En param�tre, en plus de la chaine � d�coder, est sp�cifi� la racine de l'arbre de 
	// Huffman 
	public static String expand(Node root, String t) {
		StringBuilder expanded=new StringBuilder();
		Node current = root;
		for(char c : t.toCharArray()) {
			if(c=='1' && !current.isLeaf()) {
				current=current.right;
			}else if(c=='0' && !current.isLeaf()) {
				current=current.left;
			}
			if(current.isLeaf()){
				expanded.append(current.ch);
				current=root;
			}
		}
		return expanded.toString();
	}

	public static void main(String[] args) throws IOException {
		String s=HuffmanReadFile.loadFile(new File("11-0.txt"));
		Map<Character, Integer> freq = computeFreq(s);
		Node root = buildTree(freq);
		Map<Character, String> code= buildCode(root);
		String compress = compress(s, code);
		HuffmanWriteFile.write("fichier_compresse",compress);
		String texteOriginal =
				expand(root,HuffmanReadFile.read("fichier_compresse"));
		System.out.println(texteOriginal);
	}

}
