

import cs_1c.*;

class PrintObject<E> implements Traverser<E>
{
   public void visit(E x)
   {
      System.out.print( x + " ");
   }
};

//------------------------------------------------------
public class Foothill
{
// -------  main --------------
public static void main(String[] args) throws Exception
{
   int k;
   FHlazySearchTree<Integer> searchTree = new FHlazySearchTree<Integer>();
   PrintObject<Integer> intPrinter = new PrintObject<Integer>();

   searchTree.traverse(intPrinter);

   System.out.println( "\ninitial size: " + searchTree.size() );
   searchTree.insert(50);
   searchTree.insert(20);
   searchTree.insert(61);
   searchTree.insert(30);
   searchTree.insert(59);
   searchTree.insert(10);
   searchTree.insert(9);
   searchTree.insert(60);
   searchTree.insert(70);
   searchTree.insert(62);

  
   //TRAVERSE
   System.out.println( "\nTRAVERSE after populating: ");
   searchTree.traverse(intPrinter);
   System.out.println( "\ntree 1 size: " + searchTree.size() 
      + "  Hard size: " + searchTree.sizeHard() );
   
   //SOFT REMOVAL
   System.out.println( "\n59 (one child) soft removal: ");
   if (searchTree.remove(59))
      System.out.println( "removed " + 59);
   searchTree.traverse(intPrinter);
   System.out.println( "\ntree 1 size: " + searchTree.size() 
      + "  Hard size: " + searchTree.sizeHard() );
   
   //SOFT REMOVAL
   System.out.println( "\n20 (two children) soft removal: ");
   if (searchTree.remove(20))
      System.out.println( "removed " + 20 );
   searchTree.traverse(intPrinter);
   System.out.println( "\ntree 1 size: " + searchTree.size() 
      + "  Hard size: " + searchTree.sizeHard() );
   
   //SOFT REMOVAL
   System.out.println( "\n62 (leaf) soft removal: ");
   if (searchTree.remove(62))
      System.out.println( "removed " + 62 );
   searchTree.traverse(intPrinter);
   System.out.println( "\ntree 1 size: " + searchTree.size() 
      + "  Hard size: " + searchTree.sizeHard() );
  
   //GARBAGE COLLECTION
   System.out.println( "\nCollecting garbage after soft remove of 3 items.\n"
         + "Size should be 7 and Hard Size should be 7. " );
   searchTree.collectGarbage();
   searchTree.traverse(intPrinter);
   System.out.println( "\ntree 1 size: " + searchTree.size() 
      + "  Hard size: " + searchTree.sizeHard() );
   
   //GARBAGE COLLECTION
   System.out.println( "\nCollecting garbage REPEAT. Should be no change" );
   searchTree.collectGarbage();
   searchTree.traverse(intPrinter);
   System.out.println( "\ntree 1 size: " + searchTree.size() 
      + "  Hard size: " + searchTree.sizeHard() );
   
 //GARBAGE COLLECTION
   System.out.println( "\nCollecting garbage REPEAT. Should be no change" );
   searchTree.collectGarbage();
   searchTree.traverse(intPrinter);
   System.out.println( "\ntree 1 size: " + searchTree.size() 
      + "  Hard size: " + searchTree.sizeHard() );
   
   System.out.println("\n\nREINSERTING AFTER A SOFT REMOVAL");
   
   //SOFT REMOVAL
   System.out.println( "\nAttempting 9 soft removal: ");
   searchTree.remove(9);
   System.out.println( "removed " + 9 );
   System.out.println( "tree 1 size: " + searchTree.size() 
      + "  Hard size: " + searchTree.sizeHard() );
 
   System.out.println( "\nReinsert 9 which is in a soft delete state.\n"
      + "Should only increment the size not the hard size.");  
   searchTree.insert(9);
   System.out.println( "\ntree 1 size: " + searchTree.size() 
      + "  Hard size: " + searchTree.sizeHard() );
   
   System.out.println( "\ninsert 9 again.\n");  
      searchTree.insert(9);
      System.out.println( "\ntree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );
   
    // test assignment operator
   System.out.println("\nClone Search Tree (ST) into SEarch Tree 2 (ST2)\n");
   FHlazySearchTree<Integer> searchTree2 
      = (FHlazySearchTree<Integer>)searchTree.clone();
    
   //TRAVERSE
   System.out.println( "\nSearch Tree (ST) now: ");
   searchTree.traverse(intPrinter);
   System.out.println( "\ntree 1 size: " + searchTree.size() 
      + "  Hard size: " + searchTree.sizeHard() );
 
   //TRAVERSE
   System.out.println( "\nSearch Tree 2 (ST2), a product of clone: ");
   searchTree2.traverse(intPrinter);
   System.out.println( "\ntree 1 size: " + searchTree2.size() 
      + "  Hard size: " + searchTree2.sizeHard() );
    
   System.out.println("Six insertions into ST2\n");
   searchTree2.insert(500);
   searchTree2.insert(200);
   searchTree2.insert(300);
   searchTree2.insert(700);
   searchTree2.insert(100);
   searchTree2.insert(600);
    
 //TRAVERSE
   System.out.println( "\nST2 now: ");
   searchTree2.traverse(intPrinter);
   System.out.println( "\ntree 1 size: " + searchTree2.size() 
       + "  Hard size: " + searchTree2.sizeHard() );
   
   //TRAVERSE
   System.out.println( "\nST now: ");
   searchTree.traverse(intPrinter);
   System.out.println( "\ntree 1 size: " + searchTree.size() 
      + "  Hard size: " + searchTree.sizeHard() );
  
   //SOFT REMOVAL 60 FROM ST
   System.out.println( "\n60 soft removal from ST: ");
   if (searchTree.remove(60))
   System.out.println( "removed " + 60);
   searchTree.traverse(intPrinter);
   System.out.println( "\nST size: " + searchTree.size() 
      + "  Hard size: " + searchTree.sizeHard() );
   
   //SOFT REMOVAL 100 FROM ST2
   System.out.println( "\n100 soft removal from ST2: ");
   if (searchTree2.remove(100))
   System.out.println( "soft removed " + 100);
   
   System.out.println( "\nST2 now: ");
   searchTree2.traverse(intPrinter);
   System.out.println( "\nST2 size: " + searchTree2.size() 
      + "  Hard size: " + searchTree2.sizeHard() );
    
   // test soft insertion and deletion:
   
   // INSERT
   System.out.println( "\nST size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );
   System.out.println( "\nAdd 22 to ST " );
   searchTree.insert(22);
   searchTree.traverse(intPrinter);
   System.out.println( "\nST size: " + searchTree.size() 
      + "  Hard size: " + searchTree.sizeHard() );
   
   
   System.out.println( "\nST after soft removal 22. " );
   searchTree.remove(22);
   searchTree.traverse(intPrinter);
   System.out.println( "\ntree 1 size: " + searchTree.size() 
      + "  Hard size: " + searchTree.sizeHard() );

   System.out.println( "\nST repeating soft removal 22. Should see no "
         + "change. " );
   searchTree.remove(22);
   searchTree.traverse(intPrinter);
   System.out.println( "\ntree 1 size: " + searchTree.size() 
      + "  Hard size: " + searchTree.sizeHard() );

   System.out.println( "\nSoft insertion. Hard size should not change. " );
   searchTree.insert(22);
   searchTree.traverse(intPrinter);
   System.out.println( "\ntree 1 size: " + searchTree.size() 
      + "  Hard size: " + searchTree.sizeHard() );

   System.out.println( "\nAttempting 1 removal: ");
   if (searchTree.remove(22))
      System.out.println( "removed " + 22 );
   searchTree.traverse(intPrinter);
   System.out.println( "tree 1 size: " + searchTree.size() 
      + "  Hard size: " + searchTree.sizeHard() );

   System.out.println( "\nCollecting Garbage - should clean 2 items. " );
   searchTree.collectGarbage();
   System.out.println( "tree 1 size: " + searchTree.size() 
      + "  Hard size: " + searchTree.sizeHard() );

   System.out.println( "\nCollecting Garbage again - no change expected. " );
   searchTree.collectGarbage();
   System.out.println( "tree 1 size: " + searchTree.size() 
      + "  Hard size: " + searchTree.sizeHard() );

   // test soft insertion and deletion:

   System.out.println( "\nAdding 'hard' 22 - should see new sizes. " );
   searchTree.insert(22);
   searchTree.traverse(intPrinter);
   System.out.println( "\ntree 1 size: " + searchTree.size() 
      + "  Hard size: " + searchTree.sizeHard() );

   System.out.println( "\nAfter soft removal. " );
   searchTree.remove(22);
   searchTree.traverse(intPrinter);
   System.out.println( "\ntree 1 size: " + searchTree.size() 
      + "  Hard size: " + searchTree.sizeHard() );

   System.out.println( "\nRepeating soft removal. Should see no change. " );
   searchTree.remove(22);
   searchTree.traverse(intPrinter);
   System.out.println( "\ntree 1 size: " + searchTree.size() 
      + "  Hard size: " + searchTree.sizeHard() );

   System.out.println( "\nSoft insertion. Hard size should not change. " );
   searchTree.insert(22);
   searchTree.traverse(intPrinter);
   System.out.println( "\ntree 1 size: " + searchTree.size() 
      + "  Hard size: " + searchTree.sizeHard() );

   System.out.println( "\n\nAttempting 100 removals: " );
   for (k = 100; k > 0; k--)
   {
      if (searchTree.remove(k))
         System.out.println( "removed " + k );
   }
   
   System.out.println("\nGARBAGE COLLECTION");
   searchTree.collectGarbage();
   searchTree.traverse(intPrinter);
   System.out.println( "\ntree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );
   
 //--------------------------Run-------------------------------------
// Arggh! I wrote the program three times and still can't get it to perform 
// correctly. The "k loop test" is diabolical! What core concept (or two) am
// I missing? Thanks DG
// 
   
//   System.out.println("\nGARBAGE COLLECTION");
//   searchTree.collectGarbage();
//   searchTree.traverse(intPrinter);
//   System.out.println( "\ntree 1 size: " + searchTree.size() 
//         + "  Hard size: " + searchTree.sizeHard() );
//   
//   System.out.println( "\nAfter soft removal 9 " );
//   searchTree.remove(9);
//   searchTree.traverse(intPrinter);
//   System.out.println( "\ntree 1 size: " + searchTree.size() 
//      + "  Hard size: " + searchTree.sizeHard() );
//   
//   System.out.println("\nGARBAGE COLLECTION");
//   searchTree.collectGarbage();
//   searchTree.traverse(intPrinter);
//   System.out.println( "\ntree 1 size: " + searchTree.size() 
//         + "  Hard size: " + searchTree.sizeHard() );
//   
//   System.out.println( "\nAfter soft removal 10 " );
//   searchTree.remove(10);
//   searchTree.traverse(intPrinter);
//   System.out.println( "\ntree 1 size: " + searchTree.size() 
//      + "  Hard size: " + searchTree.sizeHard() );
//   
//   System.out.println("\nGARBAGE COLLECTION");
//   searchTree.collectGarbage();
//   searchTree.traverse(intPrinter);
//   System.out.println( "\ntree 1 size: " + searchTree.size() 
//         + "  Hard size: " + searchTree.sizeHard() );
//   
//   System.out.println( "\nAfter soft removal 22 " );
//   searchTree.remove(22);
//   searchTree.traverse(intPrinter);
//   System.out.println( "\ntree 1 size: " + searchTree.size() 
//      + "  Hard size: " + searchTree.sizeHard() );
//   
//   System.out.println("\nGARBAGE COLLECTION");
//   searchTree.collectGarbage();
//   searchTree.traverse(intPrinter);
//   System.out.println( "\ntree 1 size: " + searchTree.size() 
//         + "  Hard size: " + searchTree.sizeHard() );
//   
//   System.out.println( "\nAfter soft removal 30" );
//   searchTree.remove(30);
//   searchTree.traverse(intPrinter);
//   System.out.println( "\ntree 1 size: " + searchTree.size() 
//      + "  Hard size: " + searchTree.sizeHard() );
//   
//   System.out.println( "\nAfter soft removal 50 " );
//   searchTree.remove(50);
//   searchTree.traverse(intPrinter);
//   System.out.println( "\ntree 1 size: " + searchTree.size() 
//      + "  Hard size: " + searchTree.sizeHard() );
//   
//   System.out.println( "\nAfter soft removal 61 " );
//   searchTree.remove(61);
//   searchTree.traverse(intPrinter);
//   System.out.println( "\ntree 1 size: " + searchTree.size() 
//      + "  Hard size: " + searchTree.sizeHard() );
//   
//   System.out.println( "\nAfter soft removal 70 " );
//   searchTree.remove(70);
//   searchTree.traverse(intPrinter);
//   System.out.println( "\ntree 1 size: " + searchTree.size() 
//      + "  Hard size: " + searchTree.sizeHard() );
//  
//   System.out.println("\nPRINT");
//   searchTree.traverse(intPrinter);
//   System.out.println("\nGARBAGE COLLECTION 1");
//   searchTree.collectGarbage();
//   searchTree.traverse(intPrinter);
//   System.out.println( "\ntree 1 size: " + searchTree.size() 
//         + "  Hard size: " + searchTree.sizeHard() );
//  
//   System.out.println("\nGARBAGE COLLECTION 2");
//   searchTree.collectGarbage();
//   System.out.println( "\ntree 1 size: " + searchTree.size() 
//         + "  Hard size: " + searchTree.sizeHard() );
//   searchTree.traverse(intPrinter);
//   System.out.println("\nGARBAGE COLLECTION 3");
//   searchTree.collectGarbage();
//   System.out.println( "\ntree 1 size: " + searchTree.size() 
//         + "  Hard size: " + searchTree.sizeHard() );
//   searchTree.traverse(intPrinter);
//   System.out.println("\nGARBAGE COLLECTION 4");
//   System.out.println( "\ntree 1 size: " + searchTree.size() 
//      + "  Hard size: " + searchTree.sizeHard() );
//
//   System.out.println("\nFIND MAX" + searchTree.findMax());
//   System.out.println("\nFIND MIN" + searchTree.findMin());
//   
//   System.out.println( "\nRemove 50. " );
//   searchTree.remove(50);
//   searchTree.traverse(intPrinter);
//   System.out.println( "\ntree 1 size: " + searchTree.size() 
//      + "  Hard size: " + searchTree.sizeHard() );
//
//   System.out.println( "\nCollecting Garbage after remove 50. " );
//   searchTree.collectGarbage();
//   searchTree.traverse(intPrinter);
//   searchTree.remove(61);
//   searchTree.collectGarbage();

   }

}
