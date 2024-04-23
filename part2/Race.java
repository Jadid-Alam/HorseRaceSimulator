import java.util.concurrent.TimeUnit;
import java.lang.Math;

/**
 * A three-horse race, each horse running in its own lane
 * for a given distance
 * 
 * @author McFarewell & Jadid Alam
 * @version 1.1
 */
public class Race
{
    private int raceLength;
    private Horse lane1Horse;
    private Horse lane2Horse;
    private Horse lane3Horse;

    /**
     * Constructor for objects of class Race
     * Initially there are no horses in the lanes
     * 
     * @param distance the length of the racetrack (in metres/yards...)
     */
    public Race(int distance)
    {
        // initialise instance variables
        raceLength = distance;
        lane1Horse = null;
        lane2Horse = null;
        lane3Horse = null;
    }
    
    /**
     * Adds a horse to the race in a given lane
     * 
     * @param theHorse the horse to be added to the race
     * @param laneNumber the lane that the horse will be added to
     */
    public void addHorse(Horse theHorse, int laneNumber)
    {
        if (laneNumber == 1)
        {
            lane1Horse = theHorse;
        }
        else if (laneNumber == 2)
        {
            lane2Horse = theHorse;
        }
        else if (laneNumber == 3)
        {
            lane3Horse = theHorse;
        }
        else
        {
            System.out.println("Cannot add horse to lane " + laneNumber + " because there is no such lane");
        }
    }
    
    /**
     * Start the race
     * The horse are brought to the start and
     * then repeatedly moved forward until the 
     * race is finished
     */
    public void startRace()
    {
        //declare a local variable to tell us when the race is finished
        boolean finished = false;
        
        //reset all the lanes (all horses not fallen and back to 0). 
        lane1Horse.goBackToStart();
        lane2Horse.goBackToStart();
        lane3Horse.goBackToStart();
                      
        while (!finished && !(lane1Horse.hasFallen() && lane2Horse.hasFallen() && lane3Horse.hasFallen()))
        {
            //move each horse
            moveHorse(lane1Horse);
            moveHorse(lane2Horse);
            moveHorse(lane3Horse);
                        
            //print the race positions
            printRace();
            
            //if any of the three horses has won the race is finished
            if ( raceWonBy(lane1Horse) || raceWonBy(lane2Horse) || raceWonBy(lane3Horse) )
            {
                finished = true;
            }
            
            //wait for 100 milliseconds
            try{ 
                TimeUnit.MILLISECONDS.sleep(100);
            }catch(Exception e){}
        }

        increaseConfidence(finished);
        keepConfidenceInRange();
        printRace();
        printWinner(finished);
    }
    
    /**
     * Randomly make a horse move forward or fall depending
     * on its confidence rating
     * A fallen horse cannot move
     * 
     * @param theHorse the horse to be moved
     */
    private void moveHorse(Horse theHorse)
    {
        //if the horse has fallen it cannot move, 
        //so only run if it has not fallen
        
        if  (!theHorse.hasFallen())
        {
            //the probability that the horse will move forward depends on the confidence;
            if (Math.random() < theHorse.getConfidence())
            {
               theHorse.moveForward();
            }
            
            //the probability that the horse will fall is very small (max is 0.1)
            //but will also will depends exponentially on confidence 
            //so if you double the confidence, the probability that it will fall is *2
            if (Math.random() < (0.1*theHorse.getConfidence()*theHorse.getConfidence()))
            {
                theHorse.fall();
                double newConfidence = theHorse.getConfidence() - 0.1;
                theHorse.setConfidence(Math.floor(10.0*newConfidence)/10.0);
            }
        }
    }
        
    /** 
     * Determines if a horse has won the race
     *
     * @param theHorse The horse we are testing
     * @return true if the horse has won, false otherwise.
     */
    private boolean raceWonBy(Horse theHorse)
    {
        return theHorse.getDistanceTravelled() == raceLength;
    }
    
    /***
     * Print the race on the terminal
     */
    private void printRace()
    {
        multiplePrintln("", 20);;  //clear the terminal window
        
        multiplePrint('=',raceLength+3); //top edge of track
        System.out.println();
        
        printLane(lane1Horse);
        printHorseDetails(lane1Horse);
        System.out.println();
        
        printLane(lane2Horse);
        printHorseDetails(lane2Horse);
        System.out.println();
        
        printLane(lane3Horse);
        printHorseDetails(lane3Horse);
        
        multiplePrint('=',raceLength+3); //bottom edge of track
        System.out.println();    
    }
    
    /**
     * print a horse's lane during the race
     * for example
     * |           X                      |
     * to show how far the horse has run
     */
    private void printLane(Horse theHorse)
    {
        //calculate how many spaces are needed before
        //and after the horse
        int spacesBefore = theHorse.getDistanceTravelled();
        int spacesAfter = raceLength - theHorse.getDistanceTravelled();
        
        //print a | for the beginning of the lane
        System.out.print('|');
        
        //print the spaces before the horse
        multiplePrint(' ',spacesBefore);
        
        //if the horse has fallen then print dead
        //else print the horse's symbol
        if(theHorse.hasFallen())
        {
            System.out.print('X');
        }
        else
        {
            System.out.print(theHorse.getSymbol());
        }
        
        //print the spaces after the horse
        multiplePrint(' ',spacesAfter);
        
        //print the | for the end of the track
        System.out.print('|');
    }
        
    
    /***
     * print a character a given number of times.
     * e.g. printmany('x',5) will print: xxxxx
     * 
     * @param aChar the character to Print
     */
    private void multiplePrint(char aChar, int times)
    {
        for  (int i = 0; i < times;i++)
        {
            System.out.print(aChar);
        }
    }


    /**
     * This method increases the confidence of the horse everytime it wins
     * e.g if the horse wins, the confidence increases by 0.1
     */
    public void increaseConfidence(boolean finished)
    {
        if (raceWonBy(lane1Horse) == true && finished == true)
        {
            double newConfidence = lane1Horse.getConfidence() + 0.1;
            lane1Horse.setConfidence(Math.floor(10.0*newConfidence)/10.0);
        }
        else if (raceWonBy(lane2Horse) == true && finished == true)
        {
            double newConfidence = lane2Horse.getConfidence() + 0.1;
            lane2Horse.setConfidence(Math.floor(10.0*newConfidence)/10.0);
        }
        else if (raceWonBy(lane3Horse) == true && finished == true)
        {
            double newConfidence = lane3Horse.getConfidence() + 0.1;
            lane3Horse.setConfidence(Math.floor(10.0*newConfidence)/10.0);
        }
    }

    /**
     * This method prints the horse's name and confidence
     * e.g. BARRY (Current confidence 0.5)
     */
    public void printHorseDetails(Horse h)
    {
        System.out.println(h.getName().toUpperCase() + " (Current confidence " + h.getConfidence() + ")");
    }

    /**
     * This method prints the winner 
     * e.g And the winner is BARRY
     */
    public void printWinner(boolean finished)
    {
        if (raceWonBy(lane1Horse) == true)
        {
            System.out.println("And the winner is " + lane1Horse.getName().toUpperCase());
        }
        else if (raceWonBy(lane2Horse) == true)
        {
            System.out.println("And the winner is " + lane2Horse.getName().toUpperCase());
        }
        else if (raceWonBy(lane3Horse) == true)
        {
            System.out.println("And the winner is " + lane3Horse.getName().toUpperCase());
        }
        else if (finished == false)
        {
            System.out.println("All horses have fallen, so no winners!");
        }
    }

    /**
     * This method prints the line a given number of times
     * e.g multiplePrintln("Hello", 3) will print:
     * Hello
     * Hello
     * Hello
     */
    private void multiplePrintln(String a, int times)
    {
        for  (int i = 0; i < times;i++)
        {
            System.out.println(a);
        }
    }

    /**
     * This method make sure to not let the horse's confidence go below 0.1
     * i.e. if a horse with 0.1 confidence falls then it keeps it on 0.1
     */
    private void keepConfidenceInRange()
    {
        if (lane1Horse.getConfidence() < 0.1)
        {
            lane1Horse.setConfidence(0.1);
        }

        if (lane2Horse.getConfidence() < 0.1)
        {
            lane2Horse.setConfidence(0.1);
        }

        if (lane3Horse.getConfidence() < 0.1)
        {
            lane3Horse.setConfidence(0.1);
        }
    }
}