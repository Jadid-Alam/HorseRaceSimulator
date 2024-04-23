/**
 * Java Porgram to set up and simulate the horse race
 * 
 * @author Jadid Alam
 * @version 1.2
 */

import java.util.Random;
import java.util.Scanner;

class startRace
{
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);

        // initialised horses and sets the distance
        char symbol1 = 'B';
        char symbol2 = 'G';
        char symbol3 = 'H';
        
        // getting the distance from the user and making sure it is a valid number greater than 0 and smaller than 50
        int distance = 0;
        while (distance <= 0 || distance > 50)
        {
            System.out.println("Enter the distance:");
            try {
                distance = Integer.parseInt(s.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number bwtween 1 and 50");
            }
        }
        
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