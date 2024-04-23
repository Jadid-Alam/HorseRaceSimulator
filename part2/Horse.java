/**
 * Write a description of class Horse here.
 * 
 * @author Jadid
 * @version 1.2
 */
public class Horse
{
    //Fields of class Horse
    private String name;
    private double confidence;
    private int distanceTravelled;
    private boolean hasFallen;
    private int oddsOfWinning;
    private boolean isWinner;
    
      
    //Constructor of class Horse
    /**
     * Constructor for objects of class Horse
     */
    public Horse(String horseName, double horseConfidence)
    {
        this.name = horseName;
        this.confidence = horseConfidence;
    }
    
    // return the odds of winning for the betting system
    public int getOddsOfWinning()
    {
        return this.oddsOfWinning;
    }

    // set the odds of winning for the betting system
    public void setOddsOfWinning(int newOdds)
    {
        this.oddsOfWinning = newOdds;
    }

    // return whether the horse is the winner
    public boolean isWinner()
    {
        return this.isWinner;
    }

    // set whether the horse is the winner
    public void setWinner(boolean winner)
    {
        this.isWinner = winner;
    }

    // sets the hasFallen field to true
    public void fall()
    {
        this.hasFallen = true;
    }
    
    // returns the confidence of the horse
    public double getConfidence()
    {
        return this.confidence;
    }
    
    // returns the distance travelled by the horse
    public int getDistanceTravelled()
    {
        return this.distanceTravelled;
    }
    
    // returns the name of the horse
    public String getName()
    {
        return this.name;
    }
    
    // sets the distance travelled by the horse to 0 and hasFallen to false
    public void goBackToStart()
    {
        this.distanceTravelled = 0;
        this.hasFallen = false;
    }
    
    // returns whether the horse has fallen
    public boolean hasFallen()
    {
        return this.hasFallen;
    }

    // moves the horse forward by 1
    public void moveForward()
    {
        this.distanceTravelled++;
    }

    // moves the horse backward by 1
    public void moveBackward()
    {
        this.distanceTravelled--;
    }

    // sets the confidence of the horse
    public void setConfidence(double newConfidence)
    {
        this.confidence = newConfidence;
    }
    
}
