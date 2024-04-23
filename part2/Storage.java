/**
 * An association class to link all other classes together.
 * This class is used to store all the data that is used 
 * in the game.
 * 
 * @author Jadid Alam
 * @version 1.5
 */

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Storage {

    // initialises the storage so that the game can be played with default values
    public void initialise()
    {
        this.setNoOfHorses(4);
        this.setDistance(50);
        this.setWidthOfFrame(800);
        this.setHeightOfFrame(1000);
        this.setBetAmount(0);
        this.setBetOnHorseNumber(0);
        this.setTrackConditions();
        this.setCurrentBalance(1000);
        this.setCustomiseHorseNo(0);
        this.setHorseColor(new Color(176, 141, 87),0);
        this.setHorseColor(new Color(92, 54, 34),1);
        this.setHorseColor(new Color(138, 115, 75),2);
        this.setHorseColor(new Color(255, 253, 208),3);
        this.setLaneColor(Color.ORANGE);
        this.setSaddleColor(Color.GRAY,0);
        this.setSaddleColor(Color.GRAY,1);
        this.setSaddleColor(Color.GRAY,2);
        this.setSaddleColor(Color.GRAY,3);
        this.setKitColor(Color.BLACK,0);
        this.setKitColor(Color.BLACK,1);
        this.setKitColor(Color.BLACK,2);
        this.setKitColor(Color.BLACK,3);
        this.setFenceColor(Color.WHITE);
        this.setBreedColor(new Color(255, 255, 153),0);
        this.setBreedColor(new Color(101, 67, 33),1);
        this.setBreedColor(new Color(211, 211, 211),2);
        this.setBreedColor(new Color(85, 85, 85),3);
        
    }


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

    // Horse animation storage
    private HorseAnimation horseAnimation;
    private JPanel horseAnimationPanel;

    private int customiseHorseNo;

    private int trackMovement;

    private Color horseColor1;
    private Color horseColor2;
    private Color horseColor3;
    private Color horseColor4;

    private Color laneColor;

    private Color saddleColor1;
    private Color saddleColor2;
    private Color saddleColor3;
    private Color saddleColor4;

    private Color breedColor1;
    private Color breedColor2;
    private Color breedColor3;
    private Color breedColor4;

    private Color kitColor1;
    private Color kitColor2;
    private Color kitColor3;
    private Color kitColor4;

    private Color fenceColor;

    
    public JPanel getHorseAnimationPanel() {
        return horseAnimationPanel;
    }

    public void setHorseAnimationPanel(JPanel horseAnimationPanel) {
        this.horseAnimationPanel = horseAnimationPanel;
    }

    public HorseAnimation getHorseAnimation() {
        return horseAnimation;
    }

    public void setHorseAnimation(HorseAnimation horseAnimation) {
        this.horseAnimation = horseAnimation;
    }

    public int getCustomiseHorseNo() {
        return customiseHorseNo;
    }

    public void setCustomiseHorseNo(int customiseHorseNo) {
        this.customiseHorseNo = customiseHorseNo;
    }

    public Color getHorseColor( int i ) {
        switch (i) {
            case 0:
                return horseColor1;
            case 1:
                return horseColor2;
            case 2:
                return horseColor3;
            case 3:
                return horseColor4;
            default:
                return Color.BLACK;
        }
    }

    public int getTrackMovement() {
        return trackMovement;
    }

    public void setTrackMovement(int trackMovement) {
        this.trackMovement = trackMovement;
    }


    public void setHorseColor(Color horseColor, int i) {
        switch (i) {
            case 0:
                this.horseColor1 = horseColor;
                break;
            case 1:
                this.horseColor2 = horseColor;
                break;
            case 2:
                this.horseColor3 = horseColor;
                break;
            case 3:
                this.horseColor4 = horseColor;
                break;
            default:
                break;
        }
    }

    public Color getLaneColor() {
        return laneColor;
    }

    public void setLaneColor(Color laneColor1) {
        this.laneColor = laneColor1;
    }

    public Color getSaddleColor(int i) {
        switch (i) {
            case 0:
                return saddleColor1;
            case 1:
                return saddleColor2;
            case 2:
                return saddleColor3;
            case 3:
                return saddleColor4;
            default:
                return Color.BLACK;
        }
    }

    public void setSaddleColor(Color saddleColor, int i) {
        switch (i) {
            case 0:
                this.saddleColor1 = saddleColor;
                break;
            case 1:
                this.saddleColor2 = saddleColor;
                break;
            case 2:
                this.saddleColor3 = saddleColor;
                break;
            case 3:
                this.saddleColor4 = saddleColor;
                break;
            default:
                break;
        }
    }

    public Color getBreedColor(int i) {
        switch (i) {
            case 0:
                return breedColor1;
            case 1:
                return breedColor2;
            case 2:
                return breedColor3;
            case 3:
                return breedColor4;
            default:
                return Color.BLACK;
        }
    }

    public void setBreedColor(Color breedColor, int i) {
        switch (i) {
            case 0:
                this.breedColor1 = breedColor;
                break;
            case 1:
                this.breedColor2 = breedColor;
                break;
            case 2:
                this.breedColor3 = breedColor;
                break;
            case 3:
                this.breedColor4 = breedColor;
                break;
            default:
                break;
        }
    }

    public Color getKitColor(int i) {
        switch (i) {
            case 0:
                return kitColor1;
            case 1:
                return kitColor2;
            case 2:
                return kitColor3;
            case 3:
                return kitColor4;
            default:
                return Color.BLACK;
        }
    }

    public void setKitColor(Color kitColor, int i) {
        switch (i) {
            case 0:
                this.kitColor1 = kitColor;
                break;
            case 1:
                this.kitColor2 = kitColor;
                break;
            case 2:
                this.kitColor3 = kitColor;
                break;
            case 3:
                this.kitColor4 = kitColor;
                break;
            default:
                break;
        }
    }

    public Color getFenceColor() {
        return fenceColor;
    }

    public void setFenceColor(Color fenceColor1) {
        this.fenceColor = fenceColor1;
    }

    private int noOfHorses;
    private int distance;

    public int getNoOfHorses() {
        return noOfHorses;
    }

    public void setNoOfHorses(int noOfHorses) {
        this.noOfHorses = noOfHorses;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    // statistics panel storages
    private Statistics statistics;
    private JPanel statisticsPanel;

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }
    
    public JPanel getStatisticsPanel() {
        return statisticsPanel;
    }

    public void setStatisticsPanel(JPanel statisticsPanel2) {
        this.statisticsPanel = statisticsPanel2;
    }

    // frame storage
    private JFrame  frame;
    private int widthOfFrame;
    private int heightOfFrame;

    public int getWidthOfFrame() {
        return widthOfFrame;
    }

    public void setWidthOfFrame(int widthOfFrame) {
        this.widthOfFrame = widthOfFrame;
    }

    public int getHeightOfFrame() {
        return heightOfFrame;
    }

    public void setHeightOfFrame(int heightOfFrame) {
        this.heightOfFrame = heightOfFrame;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
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