
import cs_1c.*;

import java.util.*;



public class FHlazySearchTree<E extends Comparable< ? super E > >
   implements Cloneable
{
   protected int mSize;
   protected int mSizeHard;
   protected FHlazySTNode<E> mRoot;
   
   public FHlazySearchTree() { clear(); }
   public boolean empty() { return (mSize == 0); }
   public int size() { return mSize; }
   public void clear() { mSize = 0; mRoot = null; }
   public int showHeight() { return findHeight(mRoot, -1); }
   
   public E findMin() 
   {
      if (mRoot == null)
         throw new NoSuchElementException();
      return findMin(mRoot).data;
   }
   
   public E findMax() 
   {
      if (mRoot == null)
         throw new NoSuchElementException();
      return findMax(mRoot).data;
   }
   
   public E find( E x )
   {
      FHlazySTNode<E> resultNode;
      resultNode = find(mRoot, x);
      if (resultNode == null)
         throw new NoSuchElementException();
      return resultNode.data;
   }
   public boolean contains(E x)  { return find(mRoot, x) != null; }
   
   public boolean insert( E x )
   {
      int oldSize = mSize;
      mRoot = insert(mRoot, x);
      mSize++;
      mSizeHard++;
      return (mSize != oldSize);
      
   }
  
   public boolean remove( E x )
   {
      int oldSize = mSize;
      remove(mRoot, x);
      return (mSize != oldSize);
   }
   
   public boolean collectGarbage()
   {
      int oldSizeHard = mSizeHard;
      mRoot = collectGarbage(mRoot); 
      return (mSizeHard != oldSizeHard);
   }
   
   public < F extends Traverser<? super E > > 
   void traverse(F func)
   {
      traverse(func, mRoot);
   }
   
   public Object clone() throws CloneNotSupportedException
   {
      FHlazySearchTree<E> newObject = (FHlazySearchTree<E>)super.clone();
      newObject.clear();  // can't point to other's data

      newObject.mRoot = cloneSubtree(mRoot);
      newObject.mSize = mSize;
      newObject.mSizeHard = mSizeHard;
      
      return newObject;
   }
   
   // private helper methods ----------------------------------------
   protected FHlazySTNode<E> findMin( FHlazySTNode<E> root ) 
   {
      if (root == null)
         return null;
      if (root.lftChild == null)
         return root;
      return findMin(root.lftChild);
   }
   
   protected FHlazySTNode<E> findMax( FHlazySTNode<E> root ) 
   {
      if (root == null)
         return null;
      if (root.rtChild == null)
         return root;
      return findMax(root.rtChild);
   }
   
   protected FHlazySTNode<E> insert( FHlazySTNode<E> root, E x )
   {
      int compareResult;  // avoid multiple calls to compareTo()
      
      if (root == null)
      {
         return new FHlazySTNode<E>(x, null, null, false);
      } 
      compareResult = x.compareTo(root.data); 
      if ( compareResult < 0 )
         root.lftChild = insert(root.lftChild, x);
      else if ( compareResult > 0 )
         root.rtChild = insert(root.rtChild, x);
      else if ( compareResult == 0)
      { 
         if (root.deleted == true)
         {root.deleted = false;
         mSizeHard--;
         return root;
         }
         mSize--;
         mSizeHard--;
         return root;
      }     
      return root;
   }
     
   protected void remove( FHlazySTNode<E> root, E x  )
   {
      int compareResult;  // avoid multiple calls to compareTo()
      
      if (root == null)
         return;
      
      compareResult = x.compareTo(root.data); 
      if ( compareResult < 0 )
         remove(root.lftChild, x);
      else if ( compareResult > 0 )
         remove(root.rtChild, x);  
      else if (root.deleted != true)
      {
         root.deleted = true;      
         mSize--;
      }
   }
   
   protected FHlazySTNode<E> collectGarbage(FHlazySTNode<E> root)
   {
      if (root == null)
       return null;
      
     root = removeHard(root);
     root.lftChild  = collectGarbage(removeHard(root.lftChild));
     root.rtChild =  collectGarbage(removeHard(root.rtChild));
         return root;
   }

   protected FHlazySTNode<E> removeHard ( FHlazySTNode<E> root)
   {
     
      if (root == null)
         return null;
      if (root.deleted == false)
         return root;

      else if (root.lftChild != null && root.rtChild != null)
      {   
         root.deleted = findMin(root.rtChild).deleted;
         remove(findMin(root.rtChild).data);
         root.data = findMin(root.rtChild).data;
         mSize++;
        
      }
      else if (root.lftChild == null && root.rtChild != null)
      {
         root = root.rtChild;
//         root.rtChild = null;
         mSizeHard--;
      }
      else if (root.rtChild == null && root.lftChild != null)
      {
         root = root.lftChild;
//         root.lftChild = null;
         mSizeHard--;
      }
      else if(root.rtChild == null && root.lftChild == null)
      {
         mSizeHard--;
         root = null;
         return null;        
      }
    return root;    
   }
   
   protected <F extends Traverser<? super E>> 
   void traverse(F func, FHlazySTNode<E> treeNode)
   {
      if (treeNode == null)
         return;

      traverse(func, treeNode.lftChild);
      if (treeNode.deleted != true) {func.visit(treeNode.data);};
      traverse(func, treeNode.rtChild);
   }
   
   protected FHlazySTNode<E> find( FHlazySTNode<E> root, E x )
   {
      int compareResult;  // avoid multiple calls to compareTo()

      if (root == null)
         return null;

      compareResult = x.compareTo(root.data); 
      if (compareResult < 0)
         return find(root.lftChild, x);
      if (compareResult > 0)
         return find(root.rtChild, x);
      return root;   // found
   }
   
   protected FHlazySTNode<E> cloneSubtree(FHlazySTNode<E> root)
   {
      FHlazySTNode<E> newNode;
      if (root == null)
         return null;

      // does not set myRoot which must be done by caller
      newNode = new FHlazySTNode<E>
      (
         root.data, 
         cloneSubtree(root.lftChild), 
         cloneSubtree(root.rtChild),
         root.deleted
      );
      return newNode;
   }
   
   protected int findHeight( FHlazySTNode<E> treeNode, int height ) 
   {
      int leftHeight, rightHeight;
      if (treeNode == null)
         return height;
      height++;
      leftHeight = findHeight(treeNode.lftChild, height);
      rightHeight = findHeight(treeNode.rtChild, height);
      return (leftHeight > rightHeight)? leftHeight : rightHeight;
   }
   

   protected int sizeHard()
   {
      return mSizeHard;
   }
}

//4B
class FHlazySTNode<E extends Comparable< ? super E > >
{
   // use public access so the tree or other classes can access members 
   public FHlazySTNode<E> lftChild, rtChild;
   public E data;
   public FHlazySTNode<E> myRoot;  // needed to test for certain error
   public boolean deleted;

   public FHlazySTNode( E d, FHlazySTNode<E> lft, FHlazySTNode<E> rt, boolean del)
   {
      lftChild = lft; 
      rtChild = rt;
      data = d;
      deleted = del;
   }
   
   public FHlazySTNode()
   {
      this(null, null, null, false);
   }
   
   // function stubs -- for use only with AVL Trees when we extend
   public int getHeight() { return 0; }
   boolean setHeight(int height) { return true; }
}

