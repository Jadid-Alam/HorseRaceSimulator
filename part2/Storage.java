import javax.swing.*;

import java.util.Random;

public class Storage {

    // race storage

    private Race race;
    private int winnerIndex = -1;

    public int getWinnerIndex() {
        return winnerIndex;
    }

    public void setWinnerIndex(int winnerIndex) {
        this.winnerIndex = winnerIndex;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    // horse storage
    private Horse[] horses = new Horse[4];

    public Horse getHorses(int i) {
        return horses[i];
    }

    public void setHorses(Horse horse, int i) {
        this.horses[i] = horse;
    }


    // betting storage
    private BettingSystem bettingSystem;
    private int currentBalance;
    private JLabel balanceLabel;
    private String trackConditions;
    private String feedback;
    private int wins;
    private int losses;
    private int betAmount;
    private int betOnHorseNumber;
    private JLabel winsLabel;
    private JLabel lossesLabel;

    
    public BettingSystem getBettingSystem() {
        return bettingSystem;
    }

    public void setBettingSystem(BettingSystem bettingSystem) {
        this.bettingSystem = bettingSystem;
    }


    public JLabel getWinsLabel() {
        return winsLabel;
    }

    public void setWinsLabel(JLabel winsLabel) {
        this.winsLabel = winsLabel;
    }

    public JLabel getLossesLabel() {
        return lossesLabel;
    }

    public void setLossesLabel(JLabel lossesLabel) {
        this.lossesLabel = lossesLabel;
    }

    public JLabel getBalanceLabel() {
        return balanceLabel;
    }

    public void setBalanceLabel(JLabel balanceLabel) {
        this.balanceLabel = balanceLabel;
    }
    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
    
    public int getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public String getTrackConditions() {
        return trackConditions;
    }

    public void setTrackConditions() {
        Random random = new Random();
        int randomNumber = random.nextInt(3);

        switch (randomNumber) {
            case 0:
                trackConditions = "Muddy";
                break;
            case 1:
                trackConditions = "Good";
                break;
            case 2:
                trackConditions = "Sunny";
                break;
            default:
                trackConditions = "Error";
                break;
        }
    }

    public int getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(int betAmount) {
        this.betAmount = betAmount;
    }

    public int getBetOnHorseNumber() {
        return betOnHorseNumber;
    }

    public void setBetOnHorseNumber(int betOnHorseNumber) {
        this.betOnHorseNumber = betOnHorseNumber;
    }

    
}