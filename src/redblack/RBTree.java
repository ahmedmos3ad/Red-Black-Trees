package redblack;

public class RBTree
{
    private RedBlackNode current;
    private RedBlackNode parent;
    private RedBlackNode grand;
    private RedBlackNode great;
    private RedBlackNode header;    
    private static RedBlackNode nullNode;
    /* static initializer for nullNode */
    static 
    {
        nullNode = new RedBlackNode(null);
        nullNode.left = nullNode;
        nullNode.right = nullNode;
    }
    /* Black - 1  RED - 0 */
    static final int BLACK=1;    
    static final int RED=0;

    /* Constructor */
    public RBTree()
    {
        header = new RedBlackNode(String.valueOf(0));
        header.left = nullNode;
        header.right = nullNode;
    }
    /* Function to check if tree is empty */
    public boolean isEmpty()
    {
        return header.right.equals(nullNode);
    }
    /* Make the tree logically empty */
    public void makeEmpty()
    {
    	header.right = nullNode;
    }
    /* Function to insert item */
    public void insert(String item)
    {
        current = parent = grand = header;
        nullNode.data = item;

        while(current.data.compareTo(item)!=0)//current.data.compareTo(item)!=0
        {
            great = grand; grand = parent; parent = current;
            current = item.compareTo(current.data)<0 ? current.left : current.right;

                // Check if two red children; fix if so
            if(current.left.color == RED && current.right.color==RED)
                 handleReorient(item);
        }
            // Insertion fails if already present
        if(current!=nullNode)
            return;
        current = new RedBlackNode(item, nullNode, nullNode,nullNode);

            // Attach to parent
        if(item.compareTo(parent.data)<0)
            parent.left = current;
        else
            parent.right = current;
        handleReorient( item );
    }

    private void handleReorient(String item)
    {
            // Do the color flip
        current.color = RED;
        current.left.color = BLACK;
        current.right.color = BLACK;

        if(parent.color == RED)   // Have to rotate
        {
            grand.color = RED;
            if((item.compareTo(grand.data)<0) != (item.compareTo(parent.data)<0))
                parent = rotate( item, grand );  // Start dbl rotate
            current = rotate( item, great );
            current.color = BLACK;
        }
        header.right.color = BLACK; // Make root black
    }
    private RedBlackNode rotate(String item, RedBlackNode parent )
    {
        if( item.compareTo( parent.data)<0)
            return parent.left = item.compareTo( parent.left.data) < 0 ?
                rotateWithLeftChild( parent.left )  :  // LL
                rotateWithRightChild( parent.left ) ;  // LR
        else
            return parent.right = item.compareTo( parent.right.data) < 0 ?
                rotateWithLeftChild( parent.right ) :  // RL
                rotateWithRightChild( parent.right );  // RR
    }
            
    
    /* Rotate binary tree node with left child */
    private RedBlackNode rotateWithLeftChild(RedBlackNode k2)
    {
        RedBlackNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        return k1;
    }
    /* Rotate binary tree node with right child */
    private RedBlackNode rotateWithRightChild(RedBlackNode k1)
    {
        RedBlackNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        return k2;
    }
    /* Functions to count number of nodes */
    public int countNodes()
    {
        return countNodes(header.right);
    }
    private int countNodes(RedBlackNode r)
    {
        if (r == nullNode)
            return 0;
        else
        {
            int l = 1;
            l += countNodes(r.left);
            l += countNodes(r.right);
            return l;
        }
    }
    public int maxDepth() {
    	return maxDepth(header.right);
    }
    private int maxDepth(RedBlackNode r)  
    { 
        if (r == nullNode) 
            return 0; 
        else 
        { 
            /* compute the depth of each subtree */
            int lDepth = maxDepth(r.left); 
            int rDepth = maxDepth(r.right); 
   
            /* use the larger one */
            if (lDepth > rDepth) 
                return (lDepth + 1); 
             else 
                return (rDepth + 1);
        } 
    } 
    /* Functions to search for an element */
    public boolean search(String val)
    {
        return search(header.right, val);
    }
    private boolean search(RedBlackNode r, String val)
    {
        boolean found = false;
        while ((r != nullNode) && !found)
        {
            String rval = r.data;
            if (val.compareTo(rval)>0)	
                r = r.right;
            else if (val.compareTo(rval)<0)
                r = r.left;
            else
            {
                found = true;
                break;
            }
            found = search(r, val);
        }
        return found;
    }
       
    public void printTree()
    {
        if(isEmpty())
            System.out.println("Empty tree");
        else
            printTree(header.right);
    }
    private void printTree(RedBlackNode t)
    {
        if( t != nullNode)
        {
            char c='B';
        	printTree(t.left);
        	if (t.color==0)
        		c='R';
            System.out.println(t.data+"-"+c);
            printTree(t.right);
        }
    }

    private RedBlackNode findNode(RedBlackNode findNode, RedBlackNode node) {
        if (header.right == nullNode) {
            return null;
        }

        if (findNode.data.compareTo(node.data)<0) {
            if (node.left != nullNode) {
                return findNode(findNode, node.left);
            }
        } else if (findNode.data.compareTo(node.data)>0) {
            if (node.right != nullNode) {
                return findNode(findNode, node.right);
            }
        } else if (findNode.data.equals(node.data)) {
            return node;
        }
        return null;
    }
    void transplant(RedBlackNode target, RedBlackNode with){ 
        if(target.parent == nullNode){
            header.right = with;
        }else if(target == target.parent.left){
            target.parent.left = with;
        }else
            target.parent.right = with;
        with.parent = target.parent;
  }
  
  boolean delete(RedBlackNode z){
      if((z = findNode(z, header.right))==null)return false;
      RedBlackNode x;
      RedBlackNode y = z; // temporary reference y
      int y_original_color = y.color;
      
      if(z.left == nullNode){
          x = z.right;  
          transplant(z, z.right);  
      }else if(z.right == nullNode){
          x = z.left;
          transplant(z, z.left); 
      }else{
          y = treeMinimum(z.right);
          y_original_color = y.color;
          x = y.right;
          if(y.parent == z)
              x.parent = y;
          else{
              transplant(y, y.right);
              y.right = z.right;
              y.right.parent = y;
          }
          transplant(z, y);
          y.left = z.left;
          y.left.parent = y;
          y.color = z.color; 
      }
      if(y_original_color==BLACK)
          deleteFixup(x);  
      return true;
  }
  
  void deleteFixup(RedBlackNode x){
      while(x!=header.right && x.color == BLACK){ 
          if(x == x.parent.left){
              RedBlackNode w = x.parent.right;
              if(w.color == RED){
                  w.color = BLACK;
                  x.parent.color = RED;
                  rotateWithLeftChild(x.parent);
                  w = x.parent.right;
              }
              if(w.left.color == BLACK && w.right.color == BLACK){
                  w.color = RED;
                  x = x.parent;
                  continue;
              }
              else if(w.right.color == BLACK){
                  w.left.color = BLACK;
                  w.color = RED;
                  rotateWithRightChild(w);
                  w = x.parent.right;
              }
              if(w.right.color == RED){
                  w.color = x.parent.color;
                  x.parent.color = BLACK;
                  w.right.color = BLACK;
                  rotateWithLeftChild(x.parent);
                  x = header.right;
              }
          }else{
              RedBlackNode w = x.parent.left;
              if(w.color == RED){
                  w.color = BLACK;
                  x.parent.color = RED;
                  rotateWithRightChild(x.parent);
                  w = x.parent.left;
              }
              if(w.right.color == BLACK && w.left.color == BLACK){
                  w.color = RED;
                  x = x.parent;
                  continue;
              }
              else if(w.left.color == BLACK){
                  w.right.color = BLACK;
                  w.color = RED;
                  rotateWithLeftChild(w);
                  w = x.parent.left;
              }
              if(w.left.color == RED){
                  w.color = x.parent.color;
                  x.parent.color = BLACK;
                  w.left.color = BLACK;
                  rotateWithRightChild(x.parent);
                  x = header.right;
              }
          }
      }
      x.color = BLACK; 
  }
  
  RedBlackNode treeMinimum(RedBlackNode subTreeRoot){
      while(subTreeRoot.left!=nullNode){
          subTreeRoot = subTreeRoot.left;
      }
      return subTreeRoot;
  }
}