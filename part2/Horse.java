
/**
 * Write a description of class Horse here.
 * 
 * @author Jadid
 * @version 1.1
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
    
    //Other methods of class Horse
    public int getOddsOfWinning()
    {
        return this.oddsOfWinning;
    }

    public void setOddsOfWinning(int newOdds)
    {
        this.oddsOfWinning = newOdds;
    }

    public boolean isWinner()
    {
        return this.isWinner;
    }

    public void setWinner(boolean winner)
    {
        this.isWinner = winner;
    }

    public void fall()
    {
        this.hasFallen = true;
    }
    
    public double getConfidence()
    {
        return this.confidence;
    }
    
    public int getDistanceTravelled()
    {
        return this.distanceTravelled;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public void goBackToStart()
    {
        this.distanceTravelled = 0;
        this.hasFallen = false;
    }
    
    public boolean hasFallen()
    {
        return this.hasFallen;
    }

    public void moveForward()
    {
        this.distanceTravelled++;
    }

    public void moveBackward()
    {
        this.distanceTravelled--;
    }

    public void setConfidence(double newConfidence)
    {
        this.confidence = newConfidence;
    }
    
}
