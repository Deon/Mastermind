/* BoardPanel
 * By: Deon Hua
 * Date: 3 June 2014
 * Description: The BoardPanel, where everything but the buttons exists. The game is played here.
 */

//Import.
import java.awt.Color; 
import java.awt.Graphics; 
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner; 
import java.util.InputMismatchException; 
import javax.swing.JPanel; 
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.io.PrintWriter; 
import java.io.IOException; 
import java.io.File; 


public class BoardPanel extends JPanel
{
    //Instance Variables.
    private String[][] highscores;
    private Color currentColor;
    private Color newColor;
    private Palette palette;
    private Row covering;
    private Row generated;
    private Row[] rows;  
    private Indicator[] indicators;
    private int numRows;
    private int height;    
    private int activeRow;
    private int score;
    private long startTime;
    private Color[] colors = {Color.BLUE, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.YELLOW, Color.RED, Color.CYAN, Color.GRAY};
    
    
    //Constructor    
    public BoardPanel(int height){        
        this.height = height;
        numRows = 7;
        palette = new Palette(height);
        
        start();
        repaint();
        
        //Mouse handling
        MouseHandler handler = new MouseHandler();
        addMouseListener(handler);
        addMouseMotionListener(handler);
    }
    
    // Draws components on the screen.
    public void paintComponent(Graphics g)
    {
        
        super.paintComponent( g ); 
        generated.draw(g, true);
        if (covering != null){
            covering.draw(g, true);
        }
        palette.draw(g);

        if (activeRow == numRows){
            activeRow--;
        }
            
        for (int index = 0; index <= activeRow; index++){
            rows[index].draw(g, true);
            indicators[index].draw(g, true);
        }
        
        for (int index = activeRow + 1; index < numRows; index++){
            rows[index].draw(g, false);
            indicators[index].draw(g, false);
        }
        
        
    }
    
    //If the check button is clicked.
    public void buttonClicked(){        
        //Ensures that the row is filled and has no duplicates.
        if (activeRow < numRows && rows[activeRow].getFilled() && !rows[activeRow].containsDuplicates()){
            //Checks the row for # of correct.
            checkRow();
            //In case for board resets.
            if (rows[activeRow].getFilled()){
                activeRow += 1;     
            }
        } 
        //In case it's the last row.
        if (activeRow == numRows ){
            int correct = 0;
            
            for (int index = 0; index < 4; index++){
                if (generated.getColor(index) == rows[activeRow-1].getColor(index)){
                    correct += 1;
                }
            }
            // If all are correct, then the player wins, reset.
            if (correct == 4){
                reset(true);
            }
            // If it's not all correct, player loses, reset.
            else {
                reset(false);
            }
        }
          
    }
    
    //Checks the user-submitted row to see how many are correct.
    public void checkRow(){
        int correct = 0;
        
        //Correct colour in the correct position.
        for (int index = 0; index < 4; index++){
            if (generated.getColor(index) == rows[activeRow].getColor(index)){
                correct += 1;
            }
        }
        
        
        
        int wrongPlace = 0;
        
        //Correct colour in the wrong position.
        for (int outer = 0; outer < 4; outer++){
            for (int inner = 0; inner < 4; inner++){
                
                if (generated.getColor(outer) == rows[activeRow].getColor(inner)){
                    wrongPlace += 1;
                }
            }
        }
        wrongPlace -= correct;
        // # of wrong colours = 4 - # of correct - # of correct in the wrong spot.
        int wrong = 4 - correct - wrongPlace;
        
        //Tell the row's indicator to set its status.
        indicators[activeRow].setStatus(correct, wrongPlace, wrong);
        
        repaint();
        //If all are correct, reset the game with the condition that the player has won.
        if (correct == 4){
            reset(true);
        }
    }  
    
    // Resets the game. Takes a boolean parameter, won, which represents whether or not the user won.
    public void reset(boolean won){
        //Finds value of time now.
        long timeElapsed = System.currentTimeMillis() - startTime;
        timeElapsed /= 1000;
        
        //Uncover the top row and redraw.
        covering = null;
        repaint();
        
        //Calculate score. 100 points for each unused row, and one point for every second remaining under 4 minutes.
        score += (6 - activeRow)*100;
        if (timeElapsed < 240){
            score += (240-timeElapsed);
        }
        
        //Loads high-scores into memory.
        readHighscores();    
        
        //If the player wins:
        if (won){
            JOptionPane.showMessageDialog(null, "Congratulations, you won! Close this to start a new game.", "Winner!", JOptionPane.PLAIN_MESSAGE);
            // If their score is lower than the lowest high score, let them enter their name
            if (score > Integer.parseInt(highscores[9][1])){
                enterHighscore();
            }            
        }
        else {
            JOptionPane.showMessageDialog(null, "Better luck next time! Close this to start a new game.", "Try Again!", JOptionPane.PLAIN_MESSAGE);
        }
        //Show the highscores regardless.
        displayHighscores();
        //Reset all values for a restart of the game.
        start();
    }
        
    //Initializes everything (called from constructor and reset).
    public void start(){
        //Instantiate
        Random random = new Random();
        startTime = System.currentTimeMillis();
        
         //Generates random colours.
        generated = new Row (numRows, Color.BLACK, true, height);        
        for (int index = 0; index < 4; index++){
            do{
                newColor = colors[random.nextInt(8)];            
            } while (generated.containsColor(newColor));
                     
            generated.setColor(index, newColor);
        }
        
        // Sets values and instantiates the "covering" row for the generated colours.        
        score = 0;
        covering = new Row(numRows, Color.BLACK, true, height);
        currentColor = null;
        activeRow = 0;
        
        //Rows with their indicators.
        rows = new Row[numRows];  
        indicators = new Indicator[numRows];
            
        for (int index = 0; index < rows.length; index++){
            rows[index] = new Row(index, Color.BLACK, false, height);    
            indicators[index] = new Indicator (index, height);
        }
        
        repaint();
    }
    
    //Loads the highscores into memory.
    public void readHighscores(){         
        highscores = new String[10][2];
  
        // Try to open, read, and close the markfile. 
        try { 
            Scanner fileInput = new Scanner( new File("highscores.txt") ); 
              
            for (int index = 0; index < 10; index++) { 
                highscores[index] = fileInput.nextLine().split(" ");              
            } 
            fileInput.close(); 
        } 
        catch ( IOException ioException ) {             
        }    
        
    }
    
    //Makes a JOptionPane to display the highscores.
    public void displayHighscores(){
        String message = "Highscores:\n";
        for (int outer = 0; outer < 10; outer++){            
            message += String.format("%s - %s\n", highscores[outer][0], highscores[outer][1]);
        }
        
        JOptionPane.showMessageDialog(null, message, "Highscores!", JOptionPane.INFORMATION_MESSAGE);
    }
    
    //Allows the user to enter their name for high-score submission via JOptionPane.
    public void enterHighscore(){        
        String name = JOptionPane.showInputDialog(null, "Enter your name!", "New Highscore!", JOptionPane.PLAIN_MESSAGE);
        
        int row = 0;
        
        //Finds the location of the new score (relative to the old ones).
        for (int index = 0; index < 10; index++){
            if (score > Integer.parseInt(highscores[index][1])){
                row = index;
                System.out.println(row);
                break;                
            }  
        }
        //Shifts the old highscores down.
        for (int index = 9; index > row; index--){
            highscores[index][0] = highscores[index-1][0];
            highscores[index][1] = highscores[index-1][1];
        }
        //Loads the new one to the array.
        highscores[row][0] = name;
        highscores[row][1] = "" + score;
                
        //Write to disk.        
        try { 
            PrintWriter fileOutput = new PrintWriter( "highscores.txt" ); 
              
            for (int index = 0; index < 10; index++) { 
                fileOutput.printf("%s %s\n", highscores[index][0], highscores[index][1]); 
            }   
            fileOutput.close(); 
        } 
        catch ( IOException ioException ) { 
            
        } 
    }
    
        
    
    //Handles mouse events. 
    private class MouseHandler extends MouseAdapter  
    {
        // If the mouse is pressed down.
        public void mousePressed(MouseEvent event)
        {
            int x = event.getX();
            int y = event.getY();            
                      
            //If the mouse is clicked within the area of the palette.
            if ((x > 0 && x < 200) && (y > height - 190 && y < height - 80)){
                currentColor = palette.getColor(x,y);                
            }
            
            //If it's clicked within the area of the active row.
            if ((x > 0 && x < 200) && (y > height - 250 - 50 -50* activeRow && y < height - 250 - 50*activeRow) && currentColor != null){
                int col = x/50;                
                rows[activeRow].setColor(col, currentColor);
                repaint();
            }
        }    
    }
}


