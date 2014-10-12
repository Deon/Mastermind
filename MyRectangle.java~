/* MyRectangle
 * By: Deon Hua
 * Date: 11 February 2014
 * Description: MyRectangle class. This inherits from MyBoundedShape which inherits from MyShape.
 */

// Import classes.
import java.awt.Color; 
import java.awt.Graphics; 

public class MyRectangle extends MyBoundedShape
{   
    // Default constructor, calls the superclass's constructor that sets it to default values. 
    public MyRectangle ()
    {
        super();
    }
    
    // Constructor given input. Takes in integers x1, x2, y1, y2, Color object color, and boolean filled.
    public MyRectangle (int x1, int x2, int y1, int y2, Color color, boolean filled)
    {
        super(x1, x2, y1, y2, color, filled);
    }
    
    // Draws the rectangle given a Graphics object, g. Returns nothing. 
    public void draw (Graphics g)
    {
        g.setColor( getColor() ); 
        
        if (getFilled()){            
            g.fillRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
        }
        else{
            g.drawRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
        }
    }
    
}