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
    public RBTree(String negInf)
    {
        header = new RedBlackNode(negInf);
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
    public void insert( String item )
    {
        current = parent = grand = header;
        nullNode.data = item;

        while( current.data.compareTo( item ) != 0 )
        {
            great = grand; grand = parent; parent = current;
            current = item.compareTo( current.data ) < 0 ?
                         current.left : current.right;

                // Check if two red children; fix if so
            if( current.left.color == RED && current.right.color == RED )
                 handleReorient( item );
        }

            // Insertion fails if already present
        if( current != nullNode )
            return;
        current = new RedBlackNode( item, nullNode, nullNode,nullNode);

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

        if(parent.color == RED )   // Have to rotate
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
        if( item.compareTo( parent.data) < 0 )
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
    /* Function for inorder traversal */ 
    public void inorder()
    {
        if(isEmpty())
        	System.out.println("Empty tree");
        else inorder(header.right);
    }
    private void inorder(RedBlackNode r)
    {
        if (r != nullNode)
        {
            inorder(r.left);
            char c = 'B';
            if (r.color == 0)
                c = 'R';
            System.out.print(r.data +""+c+" ");
            inorder(r.right);
        }
    }
    /* Function for preorder traversal */
    public void preorder()
    {	if(isEmpty())
    		System.out.println("Empty tree");
    	else preorder(header.right);
    }
    private void preorder(RedBlackNode r)
    {
        if (r != nullNode)
        {
            char c = 'B';
            if (r.color == 0)
                c = 'R';
            System.out.print(r.data +""+c+" ");
            preorder(r.left);             
            preorder(r.right);
        }
    }
    /* Function for postorder traversal */
    public void postorder()
    {	
    	if (isEmpty())
    		System.out.println("Empty tree");
    	else postorder(header.right);
    }
    private void postorder(RedBlackNode r)
    {
        if (r != nullNode)
        {
            postorder(r.left);             
            postorder(r.right);
            char c = 'B';
            if (r.color == 0)
                c = 'R';
            System.out.print(r.data +""+c+" ");
        }
    }     
    public void printTree( )
    {
        if( isEmpty())
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
    public void remove(String v){

		current = searchdelete(v);

		// Declare variables
		RedBlackNode x = nullNode;
		RedBlackNode y = nullNode;

		// if either one of z's children is nil, then we must remove z
		if (current.left==nullNode || current.right==nullNode)
			y = current;

		// else we must remove the successor of z
		else y = treeSuccessor(current);

		// Let x be the left or right child of y (y can only have one child)
		if (y.left!=nullNode)
			x = y.left;
		else
			x = y.right;

		// link x's parent to y's parent
		x.parent = y.parent;

		// If y's parent is nil, then x is the root
		if (y.parent==nullNode)
			header.right = x;

		// else if y is a left child, set x to be y's left sibling
		else if (y.parent.left!=nullNode && y.parent.left == y)
			y.parent.left = x;

		// else if y is a right child, set x to be y's right sibling
		else if (y.parent.right!=nullNode && y.parent.right == y)
			y.parent.right = x;

		// if y != z, trasfer y's satellite data into z.
		if (y != current){
			current.data = y.data;
		}

		// If y's color is black, it is a violation of the
		// RedBlackTree properties so call removeFixup()
		if (y.color == BLACK)
			removeFixup(x);
	}
    private void removeFixup(RedBlackNode x){

		RedBlackNode w;

		// While we haven't fixed the tree completely...
		while (x != header.right && x.color == BLACK){

			// if x is it's parent's left child
			if (x == x.parent.left){

				// set w = x's sibling
				w = x.parent.right;

				// Case 1, w's color is red.
				if (w.color == RED){
					w.color = BLACK;
					x.parent.color = RED;
					leftRotate(x.parent);
					//rotateWithLeftChild(x.parent);
					w = x.parent.right;
				}

				// Case 2, both of w's children are black
				if (w.left.color == BLACK &&
							w.right.color == BLACK){
					w.color = RED;
					x = x.parent;
				}
				// Case 3 / Case 4
				else{
					// Case 3, w's right child is black
					if (w.right.color == BLACK){
						w.left.color = BLACK;
						w.color = RED;
						rightRotate(w);
						//rotateWithRightChild(w);
						w = x.parent.right;
					}
					// Case 4, w = black, w.right = red
					w.color = x.parent.color;
					x.parent.color = BLACK;
					w.right.color = BLACK;
					leftRotate(x.parent);
					//rotateWithLeftChild(x.parent);
					x = header.right;
				}
			}
			// if x is it's parent's right child
			else{

				// set w to x's sibling
				w = x.parent.left;

				// Case 1, w's color is red
				if (w.color == RED){
					w.color = BLACK;
					x.parent.color = RED;
					rightRotate(x.parent);
					//rotateWithRightChild(x.parent);
					w = x.parent.left;
				}

				// Case 2, both of w's children are black
				if (w.right.color == BLACK &&
							w.left.color == BLACK){
					w.color = RED;
					x = x.parent;
				}

				// Case 3 / Case 4
				else{
					// Case 3, w's left child is black
					 if (w.left.color == BLACK){
						w.right.color = BLACK;
						w.color = RED;
						leftRotate(w);
						//rotateWithLeftChild(w);
						w = x.parent.left;
					}

					// Case 4, w = black, and w.left = red
					w.color = x.parent.color;
					x.parent.color = BLACK;
					w.left.color = BLACK;
					rightRotate(x.parent);
					//rotateWithRightChild(x.parent);
					x = header.right;
				}
			}
		}// end while

		// set x to black to ensure there is no violation of
		// RedBlack tree Properties
		x.color = BLACK;
	}
    public RedBlackNode searchdelete(String key){

		// Initialize a pointer to the root to traverse the tree
		RedBlackNode current = header.right;

		// While we haven't reached the end of the tree
		while (current!=nullNode){

			// If we have found a node with a key equal to key
			if (current.data.equals(key))

				// return that node and exit search(int)
				return current;

			// go left or right based on value of current and key
			else if (current.data.compareTo(key) < 0)
				current = current.right;

			// go left or right based on value of current and key
			else
				current = current.left;
		}

		// we have not found a node whose key is "key"
		return null;
    }
    public RedBlackNode treeSuccessor(RedBlackNode x){

		// if x.left is not nil, call treeMinimum(x.right) and
		// return it's value
		if (x.left!=nullNode)
			return treeMinimum(x.right);

		RedBlackNode y = x.parent;

		// while x is it's parent's right child...
		while (y!=nullNode && x == y.right){
			// Keep moving up in the tree
			x = y;
			y = y.parent;
		}
		// Return successor
		return y;
    }
    public RedBlackNode treeMinimum(RedBlackNode node){

		// while there is a smaller key, keep going left
		while (node.left!=nullNode)
			node = node.left;
		return node;
	}// end treeMinimum(RedBlackNode node)
    private void leftRotate(RedBlackNode x){

		// Call leftRotateFixup() which updates the numLeft
		// and numRight values.

		// Perform the left rotate as described in the algorithm
		// in the course text.
		RedBlackNode y;
		y = x.right;
		x.right = y.left;

		// Check for existence of y.left and make pointer changes
		if (y.left!=nullNode)
			y.left.parent = x;
		y.parent = x.parent;

		// x's parent is nul
		if (x.parent==nullNode)
			header.right = y;

		// x is the left child of it's parent
		else if (x.parent.left == x)
			x.parent.left = y;

		// x is the right child of it's parent.
		else
			x.parent.right = y;

		// Finish of the leftRotate
		y.left = x;
		x.parent = y;
	}
    private void rightRotate(RedBlackNode y){


        // Perform the rotate as described in the course text.
        RedBlackNode x = y.left;
        y.left = x.right;

        // Check for existence of x.right
        if (x.right!=nullNode)
            x.right.parent = y;
        x.parent = y.parent;

        // y.parent is nil
        if (y.parent==nullNode)
            header.right = x;

        // y is a right child of it's parent.
        else if (y.parent.right == y)
            y.parent.right = x;

        // y is a left child of it's parent.
        else
            y.parent.left = x;
        x.right = y;

        y.parent = x;

	}
}