/**
 * Java Porgram to set up and simulate the horse race
 * 
 * @author Jadid Alam
 * @version 1.1
 */

import java.util.Random;
import java.util.Scanner;

class RunHorseRaceSimulator
{
    public static void main(String[] args)
    {
        // initialised horses and sets the distance
        char symbol1 = 'B';
        char symbol2 = 'G';
        char symbol3 = 'H';
        int distance = 50;

        Random random = new Random();

        // initialised the horses
        Horse horse1 = new Horse(symbol1,"barry",(random.nextInt(8)+1)/10.0);
        Horse horse2 = new Horse(symbol2,"garry",(random.nextInt(8)+1)/10.0);
        Horse horse3 = new Horse(symbol3,"harry", (random.nextInt(8)+1)/10.0);

        // initialised the race
        Race race = new Race(distance);

        // adding horse to the race
        race.addHorse(horse1, 1);
        race.addHorse(horse2, 2);
        race.addHorse(horse3, 3);

        Scanner s = new Scanner(System.in);

        // displaying the race on the terminal and giving an option to start another race
        boolean reapetRace = true;
        while (reapetRace)
        {
            race.startRace();

            System.out.println("Start another race? (y/n)");
            reapetRace = s.nextLine().toLowerCase().charAt(0) == 'y';
        }
    }
}