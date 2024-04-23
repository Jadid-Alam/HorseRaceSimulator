/**
 * A class that programms a betting system for the
 * game to use. It allows the user to place a bet
 * and win or lose based on the outcome.
 * 
 * @author Jadid Alam
 * @version 1.1
 */

import java.util.Random;

public class BettingSystem {
    private Storage storage;
    int counter = 0;

    public BettingSystem(Storage storage) {
        this.storage = storage;
    }

    // place a bet on a horse with a certain amount and number of the horse
    public void bet(int horseNumber, int betAmount) {
        storage.setBetAmount(betAmount);
        storage.setBetOnHorseNumber(horseNumber);
    }

    // calculate the odds of winning for each horse
    public void calculateOdds() {
        // getting the highest confidence and multiplying it by 10 to get a whole number
        int highestNumber = 0;
        for (int i = 0; i < storage.getNoOfHorses(); i++) {
            if (storage.getHorses(i).getConfidence()*10 > highestNumber)
            {
                highestNumber = (int) (storage.getHorses(i).getConfidence()*10);
            }
        }

        /*
         * setting the odds of winning for each horse by finding the difference 
         * between the highest confidence and the horse's confidence and adding
         * a minimum number based on the track conditions
         */
        for (int i = 0; i < storage.getNoOfHorses(); i++) {
            int confidence = (int) (storage.getHorses(i).getConfidence()*10);
            int minOdds = 0;

            if (storage.getTrackConditions().equals("Muddy"))
            {
                minOdds = 4;
            }
            else if (storage.getTrackConditions().equals("Good"))
            {
                minOdds = 3;
            }
            else if (storage.getTrackConditions().equals("Sunny"))
            {
                minOdds = 2;
            }

            storage.getHorses(i).setOddsOfWinning(highestNumber - confidence + minOdds);
        }
    }

    // determine if the bet was won or lost
    public void betWonOrLost() {

        // generate random number to give random feedback
        Random random = new Random();
        counter = random.nextInt(5);

        // if the horse is the winner and the user has placed a bet
        if (storage.getWinnerIndex() == storage.getBetOnHorseNumber() && storage.getBetAmount() != 0)
        {
            // set the wins and current balance
            storage.setWins(storage.getWins() + 1);
            storage.setCurrentBalance(storage.getCurrentBalance() + storage.getBetAmount() * (storage.getHorses(storage.getBetOnHorseNumber()).getOddsOfWinning()+1));
            storage.getBalanceLabel().setText(" Balance: " + storage.getCurrentBalance());
        }
        else if (storage.getBetAmount() != 0)
        {
            // set the losses
            storage.setLosses(storage.getLosses() + 1);
        }

        // if the horse is the winner and the user has placed a bet
        if (storage.getWinnerIndex() == storage.getBetOnHorseNumber() && storage.getBetAmount() != 0)
        {
            // give feedback based on the counter value
            switch (counter) {
                case 0:
                    storage.setFeedback("Well done! Stick to your strategy and don't get greedy.");
                    break;
                case 1:
                    storage.setFeedback("Well done! Now might be a good time to stop betting for today.");
                    break;
                case 2:
                    storage.setFeedback("Well done! Being too greedy will cost you!");
                    break;
                case 3:
                    storage.setFeedback("Well done! Following your own strategy is the best course of action!");
                    break;
                default:
                    storage.setFeedback("Well done! If you are feeling confident in your strategy you could increase your bet amount.");
                    counter = 0;
                    break;
            }
        }
        else if (storage.getBetAmount() == 0)
        {
            // if the user has not placed a bet
            storage.setFeedback("You have not placed a bet. So no feedback is available.");
        }
        else
        {
            // if the horse is not the winner and the user has placed a bet give feedback based on the counter
            switch (counter) {
                case 0:
                    storage.setFeedback("Unlucky! Remember that the more confident the horse, the lower the odds.");
                    break;
                case 1:
                    storage.setFeedback("Unlucky! Remember that the more confident the horses tend to fall the most.");
                    break;
                case 2:
                    storage.setFeedback("Unlucky! Remember that the more confident the horses, tend to be faster.");
                    break;
                case 3:
                    storage.setFeedback("Unlucky! Remember to manage you losses and not be greedy!");
                    break;
                default:
                    storage.setFeedback("Unlucky! Now might be a good time to stop betting for today.");
                    counter = 0;
                    break;
            }
        }

    }

}
