/* TestDraw
 * By: Deon Hua
 * Date: 10 May 2014
 * Description: The TestDraw class.
 */

// Import class.
import javax.swing.JFrame; 

public class TestDraw 
{ 
    //Main method. 
    public static void main( String args[] ) 
    {  
        //Sets window height.
        int height = 650;
        
        //Instantiate board.
        Board application = new Board(height);         
        
        //Sets up the window. 
        application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); 
        application.setSize( 400, height ); 
        application.setVisible( true ); 
    } // end main     
}
