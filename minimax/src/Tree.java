// Cette classe repr�sente l'arbre du jeu.
public class Tree {

  // L'�tat du jeu correspondant � un noeud de l'arbre.
  private State state;

  // La valeur Minimax dans cet �tat.
  private Triplet minimaxValue;

  // null si le noeud courant est une feuille, le fils de gauche sinon.
  private Tree leftChild;

  // null si le noeud courant est une feuille, le fils de droite sinon.
  private Tree rightChild;

  // Ce constructeur construit l'arbre du jeu � partir de l'�tat state.
  // Notez que les valeurs Minimax seront calcul�e dans la m�thode computeMinimaxValues
  // et non dans ce constructeur.
  public Tree(State state) {
    this.state=state;
    if(state.getLeftBar()!= state.getRightBar()){
      this.leftChild=new Tree(state.playLeft());
      this.rightChild=new Tree(state.playRight());
    }
  }

  // Renvoie la valeur Minimax du joueur bleu en fonction des valeurs Minimax de
  // ses fils.
  private static Triplet minBlue(Triplet leftRes, Triplet rightRes) {
    Triplet t;
    if(leftRes.getMinBlue()>=rightRes.getMinBlue()){
      t= new Triplet(true, leftRes.getMinBlue(), leftRes.getMinOrange());
    }
    else t= new Triplet(false, rightRes.getMinBlue(), rightRes.getMinOrange());
    return t;
  }

  // Renvoie la valeur Minimax du joueur orange en fonction des valeurs Minimax de
  // ses fils.
  private static Triplet minOrange(Triplet leftRes, Triplet rightRes) {
    Triplet t;
    if(leftRes.getMinBlue() <= rightRes.getMinBlue()){
      t= new Triplet(true, leftRes.getMinBlue(), leftRes.getMinOrange());
    }
    else t= new Triplet(false, rightRes.getMinBlue(), rightRes.getMinOrange());
    return t;
  }

  // Calcule les valeurs Minimax de tout l'arbre.
  // En pratique, cette m�thode calcule pour chaque noeud de l'arbre un nouveau
  // Triplet repr�sentant les valeurs Minimax de chaque noeud.
  public void computeMinimaxValues() {
    if (isLeaf()) {
      minimaxValue = new Triplet(true, state.getBluePoints(), state.getOrangePoints());
    } else {
      leftChild.computeMinimaxValues();
      rightChild.computeMinimaxValues();

      Triplet leftRes = leftChild.getMinimaxValue();
      Triplet rightRes = rightChild.getMinimaxValue();

      if (state.isBlueToPlay()) {
        minimaxValue = minBlue(leftRes, rightRes);
      } else {
        minimaxValue = minOrange(leftRes, rightRes);
      }
    }
  }


  // Renvoie true si le noeud est une feuille, false sinon.
  public boolean isLeaf() {
    return leftChild == null;
  }

  // Getter de la valeur Minimax
  public Triplet getMinimaxValue() {
    return minimaxValue;
  }

  // Getter de l'�tat courant
  public State getState() {
    return state;
  }

  // Getter du fils de gauche
  public Tree getLeftChild() {
    return leftChild;
  }

  // Getter du fils de droite
  public Tree getRightChild() {
    return rightChild;
  }

  public int nbrNode() {
    int res = 1;
    if (!isLeaf()) {
      res += leftChild.nbrNode() + rightChild.nbrNode();
    }
    return res;
  }
}
