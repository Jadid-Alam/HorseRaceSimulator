class HorseRaceProgram
{
    public static void main(String[] args)
    {
        // initialised horses and sets the distance
        char symbol1 = 'B';
        char symbol2 = 'G';
        char symbol3 = 'H';
        int distance = 50;

        Horse horse1 = new Horse(symbol1,"barry",0.5);
        Horse horse2 = new Horse(symbol2,"garry",0.5);
        Horse horse3 = new Horse(symbol3,"harry", 0.5);

        Race race = new Race(distance);

        race.addHorse(horse1, 1);
        race.addHorse(horse2, 2);
        race.addHorse(horse3, 3);

        race.startRace();
    }
}