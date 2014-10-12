/* MyShape
 * By: Deon Hua
 * Date: 11 February 2014
 * Description: MyShape class.
 */

// Import classes.
import java.awt.Color; 
import java.awt.Graphics; 
import java.util.Random; 

abstract class MyShape
{
    private int x1; // x coordinate of first endpoint 
    private int y1; // y coordinate of first endpoint 
    private int x2; // x coordinate of second endpoint 
    private int y2; // y coordinate of second endpoint 
    private Color myColor; // color of this shape 
    
    // "Default" constructor. Instantiates everything to default values of 0 or black.
    public MyShape()
    {
        x1 = 0;
        x2 = 0;
        y1 = 0;
        y2 = 0;
        myColor = Color.BLACK;
    }
    
    // Constructor given input. Takes in integers x1, x2, y1, y2, and Color object color.
    public MyShape (int x1, int x2, int y1, int y2, Color color)
    {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        myColor = color;
    }
    
    // Abstract draw method, takes input of a Graphics object, g. 
    public abstract void draw(Graphics g);    
    
    // Sets the value of x1, takes an integer x1 for input, returns nothing.
    protected void setX1(int x1){
        this.x1 = x1;
    }
    
    // Sets the value of x2, takes an integer x2 for input, returns nothing.
    protected void setX2 (int x2){
        this.x2 = x2;
    }
    
    // Sets the value of y1, takes an integer y1 for input, returns nothing.
    protected void setY1 (int y1){
        this.y1 = y1;
    }
    
    // Sets the value of y2, takes an integer y2 for input, returns nothing.
    protected void setY2 (int y2){
        this.y2 = y2;
    }
    
    // Sets the value of myColor, takes an Color object color, returns nothing.
    protected void setColor (Color color){
        myColor = color;
    }
    
    //Returns the value of x1, an integer. Takes no parameters.
    protected int getX1(){
        return x1;
    }
    
    //Returns the value of x2, an integer. Takes no parameters.
    protected int getX2(){
        return x2;
    }
    
    //Returns the value of y1, an integer. Takes no parameters.
    protected int getY1(){
        return y1;
    }
    
    //Returns the value of y2, an integer. Takes no parameters.
    protected int getY2(){
        return y2;
    }
    
    //Returns the value of myColor, a Color object. Takes no parameters.
    protected Color getColor(){
        return myColor;
    } 
}
