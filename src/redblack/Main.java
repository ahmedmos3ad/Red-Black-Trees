package redblack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {            
    	int counter=0;
    	Scanner scan = new Scanner(System.in);
       /* Creating object of RedBlack Tree */
       RBTree rbt = new RBTree(String.valueOf(0)); 
       System.out.println("Red Black Tree Test\n");          
       char ch;
       /*  Perform tree operations  */
       do    
       {
           System.out.println("\nRed Black Tree Operations\n");
           System.out.println("1. insert ");
           System.out.println("2. search");
           System.out.println("3. count nodes");
           System.out.println("4. check empty");
           System.out.println("5. clear tree");
           System.out.println("6. insert Dictionary");

           int choice = scan.nextInt();            
           switch (choice)
           {
           case 1 : 
               System.out.println("Enter integer element to insert");
               rbt.insert(scan.next());                     
               break;                          
           case 2 : 
               System.out.println("Enter integer element to search");
               System.out.println("Search result : "+ rbt.search(scan.next()));
               break;                                          
           case 3 : 
               System.out.println("Nodes = "+ rbt.countNodes());
               break;     
           case 4 : 
               System.out.println("Empty status = "+ rbt.isEmpty());
               break;     
           case 5 : 
               System.out.println("\nTree Cleared");
               rbt.makeEmpty();
               break;
           case 6 :
           		try {
           			Scanner reader=new Scanner(new File("Dictionary.txt"));
           			reader.useDelimiter("[\n]");
           			String word;
           			while (reader.hasNext()) 
           				{
           				word=reader.next().trim();
           				rbt.insert(word);
           				counter++;
           				}
           			reader.close();
           			//System.out.println(counter);
           			} 
           		catch (IOException e) {
           		e.printStackTrace();}
           		break;
           default : 
               System.out.println("Wrong Entry \n ");
               break;    
           }
           /* Display tree
           System.out.print("\nPost order(Left-Right-Root): ");
           rbt.postorder();
           System.out.print("\nPre order(Root-Left-Right): ");
           rbt.preorder();
           System.out.print("\nIn order(Left-Root-Right): ");
           rbt.inorder();
           */
           System.out.print("\nIn order(Left-Root-Right):\n");
           rbt.printTree();
           System.out.println("Nodes = "+ rbt.countNodes());
           System.out.println("Tree Height = "+ rbt.maxDepth());
           System.out.println("Dictionary Words = "+counter);
           System.out.println("\nDo you want to continue (Type y or n) \n");
           ch = scan.next().charAt(0);                        
       } while (ch == 'Y'|| ch == 'y');               
    }
}