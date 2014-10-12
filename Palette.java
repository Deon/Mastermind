//Import
import java.awt.Color;
import java.awt.Graphics;


public class Palette{
    
    //Instance Variables
    private Circle[][] circles;
    private Rectangle rectangle;
    private Color[] colors = {Color.BLUE, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.YELLOW, Color.RED, Color.CYAN, Color.GRAY};
      
    //Constructor given the height of the window.
    public Palette (int height) {
        circles = new Circle[2][4];
        rectangle = new Rectangle(0, 200, height - 200, height - 70, Color.BLACK, false);
        
        for (int index = 0; index < 4; index++){
            circles[0][index] = new Circle(50*index, 50 + 50*index, height-190, height-140, colors[index], true);
            circles[1][index] = new Circle(50*index, 50 + 50*index, height-130, height-80, colors[index + 4], true);
        }
    }
     
    //Draws the Palette given a Graphics object g. Returns nothing.
    public void draw (Graphics g)
    {        
        rectangle.draw(g);
        for (int outer = 0; outer < 2; outer++){
            for (int inner = 0; inner < 4; inner++){
                circles[outer][inner].draw(g);
            }                       
        }
    }
    
    //Given two parameters, the x and y coordinates, this method returns the color at that coordinate - or null if there's none.
    public Color getColor (int x, int y)
    {
        for (int outer = 0; outer < 2; outer++){
            for (int inner = 0; inner < 4; inner++){
                if (circles[outer][inner].contains(x, y)){
                    return circles[outer][inner].getColor();
                }
            }                       
        }
        return null;
    }
}