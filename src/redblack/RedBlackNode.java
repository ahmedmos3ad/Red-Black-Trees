package redblack;

class RedBlackNode
{    
    RedBlackNode left, right;
    String data;
    int color;

    /* Constructor */
    public RedBlackNode(String data)
    {
        this(data,null,null);
    } 
    /* Constructor */
    public RedBlackNode(String data,RedBlackNode left,RedBlackNode right)
    {
        this.left = left;
        this.right = right;
        this.data = data;
        this.color = 1;
    }    
}
	