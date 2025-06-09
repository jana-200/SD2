import java.util.Arrays;

public class Main {
  public static void main(String[] args){
    Tree l1 = new Tree(1);
    Tree l3 = new Tree(3);
    Tree l5 = new Tree(5);
    Tree l7 = new Tree(7);
    
    Tree t2 = new Tree(2, new Tree[]{l1, l3});
    Tree t3 = new Tree(2, new Tree[]{l1,l3,l5});
    Tree t6 = new Tree(6, new Tree[]{l7});
    
    Tree t4 = new Tree(4, new Tree[]{t2, l5, t6});

    // 4 2 5 6 1 3 7

    System.out.println(Trees.nbrLeaves(t4));
    
    
    Tree[] ls = Trees.flattenLeaves(t4);
    System.out.println("Les " + ls.length + " feuilles");
    int i = 0;
    while(i != ls.length) {
      System.out.println(ls[i].getValue());
      i++;
    }

    System.out.println("Path V1");    
    Trees.pathV1(l7);

    System.out.println("Path V2");    
    Trees.pathV2(l7);

    System.out.println("test nombre de noeud");
    System.out.println(Trees.nbrNode(t2));

    System.out.println("test trouver le min");
    System.out.println(Trees.min(t4));

    System.out.println("test trouver la somme");
    System.out.println(Trees.sum(t2));

    System.out.println("test si les arbres sont egaux");
    System.out.println(Trees.equals(t3,t3));

    System.out.println("test la profondeur");
    System.out.println(Trees.depth(l7));

    System.out.println("test mm arbre ou pas");
    System.out.println(Trees.sameOne(t2,l3));

    System.out.println("test dfs");
    Trees.dfsPrint(t4);

    System.out.println("test bfs");
    Tree t1 = new Tree(1, new Tree[]{new Tree(8)});
    Tree t2b = new Tree(2, new Tree[]{t1, l3});
    Tree t4b = new Tree(4, new Tree[]{t2b, l5, t6});
    Trees.bfsPrint(t4b);

    System.out.println("test print Path v1");
    Trees.printPathV1(l7);

    System.out.println("test print Path v2");
    Trees.printPathV2(l7);

    System.out.println("test print Path v3");
    Trees.printPathV3(t3, 5);

    System.out.println("test toArray :");
    int[][] mat = Trees.toArray(t4);
    System.out.println(Arrays.toString(mat[0]));
    System.out.println(Arrays.toString(mat[1]));
    System.out.println(Arrays.toString(mat[2]));
  }
}
