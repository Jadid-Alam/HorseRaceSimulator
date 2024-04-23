/**
 * A three-horse race, each horse running in its own lane
 * for a given distance
 * 
 * @author McFarewell & Jadid Alam
 * @version 1.1
 */


import java.lang.Math;

public class Race
{
    public boolean finished = false;
    private Storage storage;
    private int raceDistance;

    /**
     * Constructor for objects of class Race
     * Initially there are no horses in the lanes
     * 
     * @param distance the length of the racetrack (in metres/yards...)
     */
    public Race(Storage storage)
    {
        // initialise instance variables
        this.storage = storage;
        raceDistance = storage.getDistance();
    }
    
    /**
     * Start the race
     * The horse are brought to the start and
     * track movement is set to 0 to reset the animation
     */
    public void startRace()
    {
        
        //reset all the lanes (all horses not fallen and back to 0). 
        storage.getHorses(0).goBackToStart();
        storage.getHorses(1).goBackToStart();
        storage.getHorses(2).goBackToStart();
        storage.getHorses(3).goBackToStart();
        storage.setTrackMovement(0);
        this.raceDistance = storage.getDistance();
                      
        finished = false;

         // increaseConfidence(finished);
        //printRace();
        //printWinner(finished);
    }
    
    /**
     * when this is called the horses will move
     * if they have not fallen.
     */
    public void doAction()
    {
        if (!finished && !(storage.getHorses(0).hasFallen() && storage.getHorses(1).hasFallen() && storage.getHorses(2).hasFallen() && storage.getHorses(3).hasFallen()))
        {
            //move each horse
            moveHorse(storage.getHorses(0));
            moveHorse(storage.getHorses(1));
            if (storage.getNoOfHorses() > 2)
            {
                moveHorse(storage.getHorses(2));
            }
            if (storage.getNoOfHorses() > 3)
            {
                moveHorse(storage.getHorses(3));
            }
            
            
            //if any of the four horses has won the race is finished
            if ( raceWonBy(storage,0) || raceWonBy(storage,1) || raceWonBy(storage,2) || raceWonBy(storage,3))
            {
                finished = true;
            }
            
            raceDistance = raceDistance - 1;
        }
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
            
            //the probability that the horse will fall is very small (max is 0.08)
            //but will also will depends exponentially on confidence 
            //so if you double the confidence, the probability that it will fall is ^2
            if (Math.random() < (0.08*theHorse.getConfidence()*theHorse.getConfidence()))
            {
                theHorse.fall();
                double newConfidence = theHorse.getConfidence() - 0.1;
                theHorse.setConfidence(Math.floor(newConfidence*10.0)/10.0);
            }
        }
    }
        
    /** 
     * Determines if a horse has won the race
     *
     * @param storage.getHorses(i) The horse we are testing
     * @return true if the horse has won and sets its index
     * to winner index, false otherwise.
     */
    private boolean raceWonBy(Storage storage, int i)
    {
        if (storage.getHorses(i) == null)
        {
            return false;
        }
        else if (storage.getHorses(i).getDistanceTravelled() >= raceDistance)
        {
            increaseConfidence(storage.getHorses(i));
            storage.setWinnerIndex(i);
            return true;
        }
        else
        {
            return false;
        }
    }
    


    /**
     * This method increases the confidence of the horse everytime it wins
     * e.g if the horse wins, the confidence increases by 0.1
     */
    public void increaseConfidence(Horse h)
    {
        double newConfidence = h.getConfidence() + 0.1;
        h.setConfidence(Math.floor(10.0*newConfidence)/10.0);
    }
   
}