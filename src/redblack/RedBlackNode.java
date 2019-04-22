package redblack;

class RedBlackNode
{    
    RedBlackNode left, right,parent;
    String data;
    int color;

    /* Constructor */
    public RedBlackNode(String data)
    {
        this(data,null,null,null);
    } 
    /* Constructor */
    public RedBlackNode(String data,RedBlackNode left,RedBlackNode right,RedBlackNode parent)
    {
        this.left = left;
        this.right = right;
        this.data = data;
        this.color = 1;
        this.parent=parent;
    }    
}
	