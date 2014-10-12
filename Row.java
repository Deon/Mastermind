import java.awt.Color;
import java.awt.Graphics;

public class Row
{
    protected Circle[] circles;
    protected Rectangle rectangle;
    
    //Constructor for subclasses (Indicator)
    protected Row (int number){
        circles = new Circle[4];
    }    
    
    //Public constructor with no "filled" boolean parameter. Assigns false automatically.
    public Row (int number, Color color, int height){
        this(number, color, false, height);        
    }
    //Public constructor.
    public Row (int number, Color color, boolean filled, int height){
        circles = new Circle[4];
        rectangle = new Rectangle(0, 200, height-300 - 50*number, height-250 - 50*number, Color.BLACK, false);
        for (int index = 0; index < 4; index++){
            circles[index] = new Circle(50*index, 50+50*index, height-300 -50*number, height-250 - 50*number, color, filled);            
        }           
    }
    
    //Sets one of the colours in Row given a column and colour.
    public void setColor(int column, Color color){
        circles[column].setColor(color);
        circles[column].setFilled(true);
    }
    
    //Draws the row.
    public void draw (Graphics g, boolean active){
        rectangle.draw(g);
        // If it's "active", draw the circles as well.
        if (active == true){            
            for (int index = 0; index < 4; index++){
                circles[index].draw(g);
            }   
        }
    }
    
    // True if all circles are filled, false if otherwise. 
    public boolean getFilled (){
        for (int index = 0; index < 4; index++){
            if (circles[index].getFilled() == false){
                return false;
            }
        }
        return true;
    }
    
    //Checks to see if the Row contains a certain colour.
    public boolean containsColor(Color color){
        for (int index = 0; index < 4; index++){
            if (circles[index].getColor() == color){
                return true;
            }
        }
        return false;
    }
    
    //Checks to see if the Row has duplicates.
    public boolean containsDuplicates(){
        for (int outer = 0; outer < 4; outer++){
            
            for (int index = 0; index < 4; index++){
                if (index == outer){
                    continue;
                }
                if (circles[index].getColor() == circles[outer].getColor()){
                    return true;
                }
            }
        }
        return false;
    }       
    
    //Returns the colour at a certain "column" of the Row.
    public Color getColor(int index){
        return circles[index].getColor();
    }
}

        
        
        
    
    