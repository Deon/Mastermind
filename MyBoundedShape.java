/* MyBoundedShape
 * By: Deon Hua
 * Date: 11 February 2014
 * Description: MyBoundedShape class - MyOval and MyRectangle inherit from this class. This class inherits from MyShape.
 */

// Import classes.
import java.lang.Math;
import java.awt.Color;

abstract class MyBoundedShape extends MyShape
{
    //Instance variable to keep track of if the shape is filled or not. 
    private boolean filled;
    
    // Default constructor, sets values to default (filled as false). 
    public MyBoundedShape()
    {
        super();
        filled = false;
    }
        
    // Constructor given input. Takes in integers getX1(), getX2(), getY1(), getY2(), Color object color, and boolean filled.
    public MyBoundedShape(int x1, int x2, int y1, int y2, Color color, boolean filled)
    {
        super(x1, x2, y1, y2, color);
        this.filled = filled;
    }
    
    // Sets the value for filled. Takes a boolean value, filled. It returns nothing.
    protected void setFilled (boolean filled)
    {
        this.filled = filled;
    }
    
    // Returns the value of filled as a boolean.
    protected boolean getFilled()
    {
        return filled;
    }
    
    // Returns the value of the upper-left X coordinate. Has no parameters.
    protected int getUpperLeftX()
    {
        if (getX1() < getX2()){
            return getX1();
        }
        else{
            return getX2();
        }
    }
    
    // Returns the value of the upper-left Y coordinate. Has no parameters. 
    protected int getUpperLeftY()
    {
        if (getY1() < getY2()){
            return getY1();
        }
        else{
            return getY2();
        }
    }
    
    // Returns the width of the shape. Has no parameters.
    protected int getWidth()
    {
        return Math.abs(getX2() - getX1());
    }
    
    // Returns the height of the shape. Has no parameters. 
    protected int getHeight()
    {
        return Math.abs(getY2()-getY1());
    }
    
    protected boolean contains(int x, int y){
        if (((y > getY1() && y < getY2()) || (y < getY1() && y > getY2())) && ((x > getX1() && x < getX2()) || (x < getX1() && x > getX2()))){
            return true;
        }
        else {
            return false;
        }
    }
}
