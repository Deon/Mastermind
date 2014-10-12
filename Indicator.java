//Import
import java.awt.Color;
import java.awt.Graphics;

//The Indicator class inherits from Row.
public class Indicator extends Row
{
    //Constructor
    public Indicator (int number, int height){ 
        super(number);  
        rectangle = new Rectangle(200, 300, height - 300 - 50*number, height - 250 - 50*number, Color.BLACK, false);
        for (int index = 0; index < 4; index++){
            circles[index] = new Circle(200 + 25*index, 225 + 25*index, height - 287 -50*number, height - 262 - 50*number, Color.BLACK, false);            
        }       
    }
    
    //Sets the status of the indicator - takes three parameters, all integers. They describe the # of correct, wrong place, and wrong guesses.
    //No calculations are made in this method, this just sets the colours.
    public void setStatus (int correct, int wrongPlace, int wrong){
        for (int index = 0; index < 4; index++){
            circles[index].setFilled(true);
        }
        
        for (int index = 0; index < correct; index++){
            circles[index].setColor(Color.GREEN);
        }
        
        for (int index = correct; index < correct + wrongPlace; index++){
            circles[index].setColor(Color.BLUE);
        }
        
        for (int index = correct + wrongPlace; index < 4; index++){
            circles[index].setColor(Color.RED);
        }
          
    }
    
}
