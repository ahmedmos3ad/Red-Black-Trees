package redblack;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {            
    	int counter=0;
    	@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
       /* Creating object of RedBlack Tree */
       RBTree rbt = new RBTree(); 
       System.out.println("Red Black Tree Test\n");          
       char ch;
       String Deletethis;
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
           System.out.println("7. Print Tree");
           System.out.println("8. Delete");
           

           int choice = scan.nextInt();            
           switch (choice)
           {
           case 1 : 
               System.out.println("Enter String element to insert");
               rbt.insert(scan.next());                     
               break;                          
           case 2 : 
               System.out.println("Enter String element to search");
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
           			Scanner reader=new Scanner(new File("test.txt"));
           			reader.useDelimiter("[\n]");
           			String word;
           			while (reader.hasNext()) 
           				{
           				word=reader.next().trim();
           				rbt.insert(word);
           				counter++;
           				}
           			reader.close();
           			} 
           		catch (IOException e) {
           		e.printStackTrace();}
           		break;
           case 7 :
        	   System.out.print("\nIn order(Left-Root-Right):\n");
               rbt.printTree();
               break;
           case 8 : 
               System.out.println("Enter String to delete");
               Deletethis=scan.next();
               RedBlackNode node = new RedBlackNode(Deletethis);
               System.out.print("\nDeleting item " + Deletethis);
               if (rbt.delete(node)) {
                   System.out.print(": deleted!\n");
               } else {
                   System.out.print(": does not exist!\n");
               }
               break;
           default : 
               System.out.println("Wrong Entry \n ");
               break;    
           }
           System.out.println("Nodes = "+ rbt.countNodes());
           System.out.println("Tree Height = "+ rbt.maxDepth());
           System.out.println("Dictionary Words = "+counter);
           System.out.println("\nDo you want to continue (Type y or n) \n");
           ch = scan.next().charAt(0);                        
       } while (ch == 'Y'|| ch == 'y');               
    }
}