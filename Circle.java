//Import
import java.awt.Color;
import java.awt.Graphics;

public class Circle extends MyBoundedShape{

    //Constructor
    public Circle(int x1, int x2, int y1, int y2, Color color, boolean filled)
    {
        super(x1, x2, y1, y2, color, filled);
             
    }

    
    //Draws the Circle.
    public void draw (Graphics g)
    {        
        g.setColor(getColor());
        if (getFilled()){            
            g.fillOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
        }
        else{
            g.drawOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
        }
    
    }

}
