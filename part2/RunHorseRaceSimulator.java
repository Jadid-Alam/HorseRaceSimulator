/**
 * A 2 to 4 horse race, each horse running in its own lane
 * for a given distance, with the ability to bet on a horse
 * and customise the track and horse colours.
 * 
 * @author Jadid Alam
 * @version 1.6
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.util.Random;


public class RunHorseRaceSimulator {

    /**
     * main method to run the horse racing simulator
     * initialises the storage class and sets up the frame and panels
     */
    public static void main(String[] args) {
        
        // initialises the storage class
        Storage storage = new Storage();
        storage.initialise();
        
        // initiaslises the horses with random confidence between 0.1 and 0.9 and stored in the storage class
        Random r = new Random();
        storage.setHorses(new Horse("barry",((double)r.nextInt(9) + 1)/10.0),0);
        storage.setHorses(new Horse("garry",((double)r.nextInt(9) + 1)/10.0),1);
        storage.setHorses(new Horse("harry",((double)r.nextInt(9) + 1)/10.0),2);
        storage.setHorses(new Horse("larry",((double)r.nextInt(9) + 1)/10.0),3);

        // initialises the race class and stores it in the storage class
        storage.setRace(new Race(storage));

        // initialises the betting system class and stores it in the storage class
        storage.setBettingSystem(new BettingSystem(storage));

        // calculates the odds for the betting system for each horse
        storage.getBettingSystem().calculateOdds();
    
        // initialises the frame and stores it in the storage class
        storage.setFrame(new JFrame("Horse Racing Game"));
        storage.getFrame().setResizable(false);
        storage.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // sets the size of the frame and sets the layout to border layout
        storage.getFrame().setSize(storage.getWidthOfFrame(), storage.getHeightOfFrame());
        storage.getFrame().setLayout(new BorderLayout());
        
        // initialises the horse animation and statistics classes and stores them in the storage class
        storage.setHorseAnimation(new HorseAnimation(storage));
        storage.setStatistics(new Statistics(storage));

        // initialises the panels for the horse animation, statistics and buttons and stores horse animation and statistics in the storage class
        storage.setHorseAnimationPanel(storage.getHorseAnimation().returnAnimationPanel());
        storage.setStatisticsPanel(storage.getStatistics().displayStatistics());
        JPanel panelButton = displayButtons(storage);
        storage.getHorseAnimationPanel().setBackground(Color.BLACK);
        storage.getStatisticsPanel().setBackground(Color.WHITE);
        panelButton.setBackground(Color.WHITE);
        
        // sets the preferred size of the panels and adds them to the frame
        storage.getHorseAnimationPanel().setPreferredSize(new Dimension(storage.getWidthOfFrame()*5/6, storage.getHeightOfFrame()*4/5));
        storage.getStatisticsPanel().setPreferredSize(new Dimension(storage.getWidthOfFrame()/6, storage.getHeightOfFrame()*4/5));
        panelButton.setPreferredSize(new Dimension(storage.getWidthOfFrame()*5/6, storage.getHeightOfFrame()/5));
        storage.getFrame().getContentPane().add(storage.getHorseAnimationPanel(), BorderLayout.CENTER);
        storage.getFrame().getContentPane().add(storage.getStatisticsPanel(), BorderLayout.EAST);
        storage.getFrame().getContentPane().add(panelButton, BorderLayout.SOUTH);
        
        storage.getFrame().revalidate();
        storage.getFrame().repaint();
        storage.getFrame().setVisible(true);

    }

    /**
     * method to create and show the GUI
     * @param storage the storage class
     */
    private static void createAndShowGUI(Storage storage)
    {
        
        // starts the race
        storage.getRace().startRace();

        // refreshes the screen animations every 0.2s
        Timer timer = new Timer(200, new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {

                // if the race hasn't finished or if all of the horses have not fallen
                if ((!storage.getRace().finished) && (storage.getHorses(0).hasFallen() == false || storage.getHorses(1).hasFallen() == false || storage.getHorses(2).hasFallen() == false || storage.getHorses(3).hasFallen() == false)) {
                    
                    // remove the horse animation and statistics panels from the frame
                    storage.getFrame().remove(storage.getHorseAnimationPanel());
                    storage.getFrame().remove(storage.getStatisticsPanel());

                    // move the horses backwards if they have fallen that way they look stationary on the track
                    if (storage.getHorses(0).hasFallen()) {
                        storage.getHorses(0).moveBackward();
                    }

                    if (storage.getHorses(1).hasFallen()) {
                        storage.getHorses(1).moveBackward();
                    }

                    if (storage.getHorses(2).hasFallen()) {
                        storage.getHorses(2).moveBackward();
                    }

                    if (storage.getHorses(3).hasFallen()) {
                        storage.getHorses(3).moveBackward();
                    }

                    // sets the horse animation and statistics panels to the updated versions
                    storage.setHorseAnimationPanel(storage.getHorseAnimation().returnAnimationPanel());
                    storage.setStatisticsPanel(storage.getStatistics().displayStatistics());
                    storage.getFrame().getContentPane().add(storage.getHorseAnimationPanel(), BorderLayout.CENTER);
                    storage.getFrame().getContentPane().add(storage.getStatisticsPanel(), BorderLayout.EAST);
                    storage.getFrame().revalidate();
                    storage.getFrame().repaint();
                    storage.getRace().doAction();
                    
                    // stopping condition in the case of 2 or 3 horses
                    if (storage.getNoOfHorses() == 2 && storage.getHorses(0).hasFallen() == true && storage.getHorses(1).hasFallen() == true)
                    {
                        ((Timer) e.getSource()).stop(); // Stop the timer
                        refreshScreenData(storage);
                        
                    }
                    else if (storage.getNoOfHorses() == 3 && storage.getHorses(0).hasFallen() == true && storage.getHorses(1).hasFallen() == true && storage.getHorses(2).hasFallen() == true)
                    {
                        ((Timer) e.getSource()).stop(); // Stop the timer
                        refreshScreenData(storage);
                    }
                } else {
                    ((Timer) e.getSource()).stop(); // Stop the timer
                    refreshScreenData(storage);
                }
            }
        });
        
        timer.start();
        
    }

    /**
     * method to refresh the statistics panel 
     * and display the winner using an alert
     * @param storage the storage class
     */
    public static void refreshScreenData(Storage storage)
    {
        // remove the statistics panels from the frame
        storage.getFrame().remove(storage.getStatisticsPanel());
        storage.setStatisticsPanel(storage.getStatistics().displayStatistics());

        // sets the horse animation and statistics panels to the updated versions
        storage.getFrame().getContentPane().add(storage.getStatisticsPanel(), BorderLayout.EAST);
        storage.getFrame().revalidate();
        storage.getFrame().repaint();
        storage.getRace().doAction();
        storage.getBettingSystem().betWonOrLost(); 

        // alert to display after the race
        String winOrLose = "";
        if (storage.getWinnerIndex() == storage.getBetOnHorseNumber())
        {
            winOrLose = "Congratulations, you won " + storage.getBetAmount() * storage.getHorses(storage.getBetOnHorseNumber()).getOddsOfWinning() + " credits!";
        }
        else if (storage.getBetAmount() == 0)
        {
            winOrLose = "";
        }
        else
        {
            winOrLose = "Unlucky, you lost " + storage.getBetAmount() + " credits. Better luck next time!";
        }
        storage.getWinsLabel().setText("   Wins: " + storage.getWins());
        storage.getLossesLabel().setText("   Losses: " + storage.getLosses());
        storage.setBetAmount(0);
        if (storage.getWinnerIndex() != -1)
        {
            JOptionPane.showMessageDialog(null, "The winner is: " + storage.getHorses(storage.getWinnerIndex()).getName() + "\n" + winOrLose + "\nFeedback: " + storage.getFeedback());
        }
        else
        {
            JOptionPane.showMessageDialog(null,"It's a draw! No winner this time.\n" + winOrLose + "\nFeedback: " + storage.getFeedback());
        }
    }

    /**
     * method to display the buttons on the screen and 
     * add functionality to the buttons
     * @param storage the storage class
     */
    public static JPanel displayButtons(Storage storage) {

        // initialising buttons panel
        JPanel panelButton = new JPanel();
        panelButton.setLayout(new GridLayout(1,5));
        panelButton.setPreferredSize(new Dimension(storage.getWidthOfFrame()*5/6, storage.getHeightOfFrame()/5));
        
        // setting backgroudn and foreground colours
        Color backgroundColour = new Color(255, 242, 215);
        Color foregroundColour = new Color(249, 136, 102);

        // creating 6 panels for all the items to go on
        JPanel betting1 = new JPanel();
        betting1.setLayout(new GridLayout(5,1));
        betting1.setBackground(backgroundColour);
        JPanel betting2 = new JPanel();
        betting2.setLayout(new GridLayout(7,1));
        betting2.setBackground(backgroundColour);
        JPanel track = new JPanel();
        track.setLayout(new GridLayout(6,1));
        track.setBackground(backgroundColour);
        JPanel horse1 = new JPanel();
        horse1.setLayout(new GridLayout(6,1));
        horse1.setBackground(backgroundColour);
        JPanel horse2 = new JPanel();
        horse2.setLayout(new GridLayout(4,1));
        horse2.setBackground(backgroundColour);

        // initialising fonts
        Font font = new Font("Arial", Font.PLAIN, 12);
        Font bold = new Font("Arial", Font.BOLD, 15);

        // making start button, setting its properties and adding functionality
        JButton startRace = new JButton("Start!");
        startRace.setBackground(backgroundColour);
        startRace.setForeground(foregroundColour);
        startRace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAndShowGUI(storage);
            }
        });
        startRace.setFont(new Font("Arial", Font.BOLD, 20));
        startRace.setMargin(new Insets(5, 5, 5, 5));

        // making current balance label, setting its properties
        storage.setBalanceLabel(new JLabel(" Balance: " + storage.getCurrentBalance()));
        storage.getBalanceLabel().setFont(font);
        storage.getBalanceLabel().setForeground(foregroundColour);
        storage.getBalanceLabel().setBackground(backgroundColour);

        // making bet amount label, setting its properties
        JLabel betAmount = new JLabel(" Bet Amount: ");
        betAmount.setFont(font);
        betAmount.setForeground(foregroundColour);
        betAmount.setBackground(backgroundColour);

        // making a place to type in bet amount and setting its properties
        JTextField betAmountField = new JTextField(storage.getBetAmount() + "");
        betAmountField.setFont(font);
        betAmountField.setForeground(foregroundColour);
        betAmountField.setBackground(backgroundColour);

        // making bet button, setting its properties and adding functionality 
        JButton betButton = new JButton(" Bet");
        betButton.setBackground(backgroundColour);
        betButton.setForeground(foregroundColour);
        betButton.setFont(bold);
        betButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int betAmount = Integer.parseInt(betAmountField.getText());
                    if (betAmount > storage.getCurrentBalance() || storage.getBetAmount() != 0) {
                        JOptionPane.showMessageDialog(null, "You do not have enough balance to bet that amount or you have already placed a bet.");
                    } else {
                        storage.setBetAmount(betAmount);
                        storage.setCurrentBalance(storage.getCurrentBalance() - betAmount);
                        storage.getBalanceLabel().setText(" Balance: " + storage.getCurrentBalance());
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number");
                }
            }
        });

        // adding all the items to the betting1 panel and formating the panel
        betting1.add(startRace);
        betting1.add(storage.getBalanceLabel());
        betting1.add(betAmount);
        betting1.add(betAmountField);
        betting1.add(betButton);
        betting1.setBorder(new EmptyBorder(2, 2, 2, 2));

        // making track length label, setting its properties
        JLabel trackLenght = new JLabel("   Track Length (km): ");
        trackLenght.setFont(font);
        trackLenght.setForeground(foregroundColour);
        trackLenght.setBackground(backgroundColour);

        // making a panel for all buttons to stay and setting its properties
        JPanel trackLengthPanel = new JPanel();
        trackLengthPanel.setBackground(backgroundColour);
        trackLengthPanel.setLayout(new GridLayout(1,4));
        trackLengthPanel.setBorder(new EmptyBorder(0, 10, 2, 10));

        // making buttons for different track lengths, setting their properties and adding functionality
        JButton trackLength1 = new JButton("1.0");
        JButton trackLength2 = new JButton("1.2");
        JButton trackLength3 = new JButton("1.4");
        JButton trackLength4 = new JButton("1.6");
        trackLength1.setFont(bold);
        trackLength2.setFont(bold);
        trackLength3.setFont(bold);
        trackLength4.setFont(bold);
        trackLength1.setMargin(new Insets(0,0,0,0));
        trackLength2.setMargin(new Insets(0,0,0,0));
        trackLength3.setMargin(new Insets(0,0,0,0));
        trackLength4.setMargin(new Insets(0,0,0,0));
        trackLength1.setBackground(backgroundColour);
        trackLength1.setForeground(foregroundColour);
        trackLength2.setBackground(backgroundColour);
        trackLength3.setBackground(backgroundColour);
        trackLength4.setBackground(backgroundColour);
        trackLength2.setForeground(foregroundColour);
        trackLength3.setForeground(foregroundColour);
        trackLength4.setForeground(foregroundColour);
        trackLength1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setDistance(20);
                storage.getRace().startRace();
                refreshScreenAnimations(storage);
            }
        });
        trackLength2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setDistance(30);
                refreshScreenAnimations(storage);
            }
        });
        trackLength3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setDistance(40);
                refreshScreenAnimations(storage);
            }
        });
        trackLength4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setDistance(50);
                refreshScreenAnimations(storage);
            }
        });

        // adding all the buttons to the trackLengthPanel
        trackLengthPanel.add(trackLength1);
        trackLengthPanel.add(trackLength2);
        trackLengthPanel.add(trackLength3);
        trackLengthPanel.add(trackLength4);

        // making a label for track conditions, setting its properties
        JLabel trackConditions = new JLabel("   Track Conditions: " + storage.getTrackConditions());
        trackConditions.setFont(font);
        trackConditions.setForeground(foregroundColour);
        trackConditions.setBackground(backgroundColour);

        // making a label for wins and losses, setting its properties
        storage.setWinsLabel(new JLabel("   Wins: " + storage.getWins()));
        storage.getWinsLabel().setBackground(backgroundColour);
        storage.getWinsLabel().setForeground(foregroundColour);
        storage.getWinsLabel().setFont(font);
        storage.setLossesLabel(new JLabel("   Losses: " + storage.getLosses()));
        storage.getLossesLabel().setBackground(backgroundColour);
        storage.getLossesLabel().setForeground(foregroundColour);
        storage.getLossesLabel().setFont(font);

        // making a label for betting on horse, setting its properties
        JLabel horseBets = new JLabel("   Bet On Horse:");
        horseBets.setForeground(foregroundColour);
        horseBets.setBackground(backgroundColour);
        horseBets.setFont(font);

        // making a panel for all the horse bet number buttons to stay and setting its properties
        JPanel horseBetsPanel = new JPanel();
        horseBetsPanel.setBackground(backgroundColour);
        horseBetsPanel.setLayout(new GridLayout(1,4));
        horseBetsPanel.setBorder(new EmptyBorder(0, 10, 2, 10));

        // making buttons for different horses to bet on, setting their properties and adding functionality
        JButton betHorse1 = new JButton("1");
        JButton betHorse2 = new JButton("2");
        JButton betHorse3 = new JButton("3");
        JButton betHorse4 = new JButton("4");
        betHorse1.setFont(bold);
        betHorse2.setFont(bold);
        betHorse3.setFont(bold);
        betHorse4.setFont(bold);
        betHorse1.setMargin(new Insets(2, 2, 2, 2));
        betHorse2.setMargin(new Insets(2, 2, 2, 2));
        betHorse3.setMargin(new Insets(2, 2, 2, 2));
        betHorse4.setMargin(new Insets(2, 2, 2, 2));
        betHorse1.setBackground(backgroundColour);
        betHorse2.setBackground(backgroundColour);
        betHorse3.setBackground(backgroundColour);
        betHorse4.setBackground(backgroundColour);
        betHorse1.setForeground(foregroundColour);
        betHorse2.setForeground(foregroundColour);
        betHorse3.setForeground(foregroundColour);
        betHorse4.setForeground(foregroundColour);
        betHorse1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setBetOnHorseNumber(0);
            }
        });
        betHorse2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setBetOnHorseNumber(1);
            }
        });
        betHorse3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (storage.getNoOfHorses() > 2) {
                    storage.setBetOnHorseNumber(2);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Cannot bet on horse 3. Not enough horses available.");
                }
            }
        });
        betHorse4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (storage.getNoOfHorses() > 3) {
                    storage.setBetOnHorseNumber(3);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Cannot bet on horse 4. Not enough horses available.");
                }
                
            }
        });

        // adding all the buttons to the horseBetsPanel
        horseBetsPanel.add(betHorse1);
        horseBetsPanel.add(betHorse2);
        horseBetsPanel.add(betHorse3);
        horseBetsPanel.add(betHorse4);

        // adding all the items to the betting2 panel
        betting2.add(trackLenght);
        betting2.add(trackLengthPanel);
        betting2.add(trackConditions);
        betting2.add(storage.getWinsLabel());
        betting2.add(storage.getLossesLabel());
        betting2.add(horseBets);
        betting2.add(horseBetsPanel);

        // making a label for number of tracks, setting its properties
        JLabel trackNumber = new JLabel("   Number of Track:");
        trackNumber.setForeground(foregroundColour);
        trackNumber.setBackground(backgroundColour);
        trackNumber.setFont(font);

        // making a panel for all the track number buttons to stay and setting its properties
        JPanel trackNumberPanel = new JPanel();
        trackNumberPanel.setBackground(backgroundColour);
        trackNumberPanel.setLayout(new GridLayout(1,3));
        trackNumberPanel.setBorder(new EmptyBorder(0, 10, 2, 10));

        // making buttons for different number of tracks, setting their properties and adding functionality
        JButton trackButton2 = new JButton("2");
        JButton trackButton3 = new JButton("3");
        JButton trackButton4 = new JButton("4");
        trackButton2.setFont(bold);
        trackButton3.setFont(bold);
        trackButton4.setFont(bold);
        trackButton2.setMargin(new Insets(2, 2, 2, 2));
        trackButton3.setMargin(new Insets(2, 2, 2, 2));
        trackButton4.setMargin(new Insets(2, 2, 2, 2));
        trackButton2.setBackground(backgroundColour);
        trackButton3.setBackground(backgroundColour);
        trackButton4.setBackground(backgroundColour);
        trackButton2.setForeground(foregroundColour);
        trackButton3.setForeground(foregroundColour);
        trackButton4.setForeground(foregroundColour);
        trackButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setNoOfHorses(2);
                refreshScreenAnimations(storage);
            }
        });
        trackButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setNoOfHorses(3);
                refreshScreenAnimations(storage);
            }
        });
        trackButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setNoOfHorses(4);
                refreshScreenAnimations(storage);
            }
        });

        // adding all the buttons to the trackNumberPanel
        trackNumberPanel.add(trackButton2);
        trackNumberPanel.add(trackButton3);
        trackNumberPanel.add(trackButton4);

        // making a label for track colour, setting its properties
        JLabel trackColour = new JLabel("   Track Colour: ");
        trackColour.setForeground(foregroundColour);
        trackColour.setBackground(backgroundColour);
        trackColour.setFont(font);

        // making a panel for all the track colour buttons to stay and setting its properties
        JPanel trackColourPanel = new JPanel();
        trackColourPanel.setBackground(backgroundColour);
        trackColourPanel.setLayout(new GridLayout(2,5));
        trackColourPanel.setBorder(new EmptyBorder(0, 10, 2, 10));

        // making buttons for different track colours, setting their properties and adding functionality
        JButton grassTrack = new JButton();
        grassTrack.setBackground(new Color(124, 252, 0));
        grassTrack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setLaneColor(new Color(124, 252, 0));
                storage.getFrame().revalidate();
                storage.getFrame().repaint();
            }
        });
        JButton sandTrack = new JButton();
        sandTrack.setBackground(new Color(236, 217, 201));
        sandTrack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setLaneColor(new Color(236, 217, 201));
                storage.getFrame().revalidate();
                storage.getFrame().repaint();
            }
        });
        JButton darkGrassTrack = new JButton();
        darkGrassTrack.setBackground(new Color(34, 139, 34));
        darkGrassTrack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setLaneColor(new Color(34, 139, 34));
                storage.getFrame().revalidate();
                storage.getFrame().repaint();
            }
        });

        // adding all the buttons to the trackColourPanel
        trackColourPanel.add(grassTrack);
        trackColourPanel.add(sandTrack);
        trackColourPanel.add(darkGrassTrack);

        // making a label for fence colour, setting its properties
        JLabel fenceColour = new JLabel("   Fence Colour: ");
        fenceColour.setForeground(foregroundColour);
        fenceColour.setBackground(backgroundColour);
        fenceColour.setFont(font);

        // making a panel for all the fence colour buttons to stay and setting its properties
        JPanel fenceColourPanel = new JPanel();
        fenceColourPanel.setBackground(backgroundColour);
        fenceColourPanel.setLayout(new GridLayout(2,5));
        fenceColourPanel.setBorder(new EmptyBorder(0, 10, 2, 10));

        // making buttons for different fence colours, setting their properties and adding functionality
        JButton whiteFence = new JButton();
        whiteFence.setBackground(Color.WHITE);
        whiteFence.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setFenceColor(Color.WHITE);
                storage.getFrame().revalidate();
                storage.getFrame().repaint();
            }
        });
        JButton greyFence = new JButton();
        greyFence.setBackground(new Color(128, 128, 128));
        greyFence.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setFenceColor(new Color(128, 128, 128));
                storage.getFrame().revalidate();
                storage.getFrame().repaint();
            }
        });
        JButton creamFence = new JButton();
        creamFence.setBackground(new Color(255, 253, 208));
        creamFence.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setFenceColor(new Color(255, 253, 208));
                storage.getFrame().revalidate();
                storage.getFrame().repaint();
            }
        });
        JButton woodFence = new JButton();
        woodFence.setBackground(new Color(139, 69, 19));
        woodFence.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setFenceColor(new Color(139, 69, 19));
                storage.getFrame().revalidate();
                storage.getFrame().repaint();
            }
        });
        JButton darkWoodFence = new JButton();
        darkWoodFence.setBackground(new Color(101, 67, 33));
        darkWoodFence.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setFenceColor(new Color(101, 67, 33));
                storage.getFrame().revalidate();
                storage.getFrame().repaint();
            }
        });

        // adding all the buttons to the fenceColourPanel
        fenceColourPanel.add(whiteFence);
        fenceColourPanel.add(greyFence);
        fenceColourPanel.add(creamFence);
        fenceColourPanel.add(woodFence);
        fenceColourPanel.add(darkWoodFence);

        // adding all the items to the track panel
        track.add(trackNumber);
        track.add(trackNumberPanel);
        track.add(trackColour);
        track.add(trackColourPanel);
        track.add(fenceColour);
        track.add(fenceColourPanel);

        // making a label for selecting the horse number, setting its properties
        JLabel horseNumber = new JLabel("   Customise Horse:");
        horseNumber.setForeground(foregroundColour);
        horseNumber.setBackground(backgroundColour);
        horseNumber.setFont(font);

        // making a panel for all the horse number buttons to stay and setting its properties
        JPanel horseNumberPanel = new JPanel();
        horseNumberPanel.setBackground(backgroundColour);
        horseNumberPanel.setLayout(new GridLayout(1,4));
        horseNumberPanel.setBorder(new EmptyBorder(0, 5, 2, 5));

        // making buttons for different horse numbers, setting their properties and adding functionality
        JButton horseButton1 = new JButton("1");
        JButton horseButton2 = new JButton("2");
        JButton horseButton3 = new JButton("3");
        JButton horseButton4 = new JButton("4");
        horseButton1.setFont(bold);
        horseButton2.setFont(bold);
        horseButton3.setFont(bold);
        horseButton4.setFont(bold);
        horseButton1.setMargin(new Insets(2, 2, 2, 2));
        horseButton2.setMargin(new Insets(2, 2, 2, 2));
        horseButton3.setMargin(new Insets(2, 2, 2, 2));
        horseButton4.setMargin(new Insets(2, 2, 2, 2));
        horseButton1.setBackground(backgroundColour);
        horseButton2.setBackground(backgroundColour);
        horseButton3.setBackground(backgroundColour);
        horseButton4.setBackground(backgroundColour);
        horseButton1.setForeground(foregroundColour);
        horseButton2.setForeground(foregroundColour);
        horseButton3.setForeground(foregroundColour);
        horseButton4.setForeground(foregroundColour);
        horseButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setCustomiseHorseNo(0);
                storage.getFrame().revalidate();
                storage.getFrame().repaint();
            }
        });
        horseButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setCustomiseHorseNo(1);
                storage.getFrame().revalidate();
                storage.getFrame().repaint();
            }
        });
        horseButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setCustomiseHorseNo(2);
                storage.getFrame().revalidate();
                storage.getFrame().repaint();
            }
        });
        horseButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setCustomiseHorseNo(3);
                storage.getFrame().revalidate();
                storage.getFrame().repaint();
            }
        });

        // adding all the buttons to the horseNumberPanel
        horseNumberPanel.add(horseButton1);
        horseNumberPanel.add(horseButton2);
        horseNumberPanel.add(horseButton3);
        horseNumberPanel.add(horseButton4);

        // making a label for horse colour, setting its properties
        JLabel horseColour = new JLabel("   Horse Colour: ");
        horseColour.setForeground(foregroundColour);
        horseColour.setBackground(backgroundColour);
        horseColour.setFont(font);

        // making a panel for all the horse colour buttons to stay and setting its properties
        JPanel horseColourPanel = new JPanel();
        horseColourPanel.setBackground(backgroundColour);
        horseColourPanel.setLayout(new GridLayout(2,7));
        horseColourPanel.setBorder(new EmptyBorder(0, 10, 2, 10));

        // making buttons for different horse colours, setting their properties and adding functionality
        JButton darkGreyHorse = new JButton();
        darkGreyHorse.setBackground(new Color(64, 64, 64));
        darkGreyHorse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setHorseColor(new Color(64, 64, 64),storage.getCustomiseHorseNo());
                storage.getFrame().revalidate();
                storage.getFrame().repaint();
            }
        });
        JButton darkCreamHorse = new JButton();
        darkCreamHorse.setBackground(new Color(205, 193, 169));
        darkCreamHorse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setHorseColor(new Color(205, 193, 169),storage.getCustomiseHorseNo());
                storage.getFrame().revalidate();
                storage.getFrame().repaint();
            }
        });
        JButton brownHorse = new JButton();
        brownHorse.setBackground(new Color(165, 42, 42));
        brownHorse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setHorseColor(new Color(165, 42, 42),storage.getCustomiseHorseNo());
                storage.getFrame().revalidate();
                storage.getFrame().repaint();
            }
        });
        JButton darkBrownHorse = new JButton();
        darkBrownHorse.setBackground(new Color(101, 67, 33));
        darkBrownHorse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setHorseColor(new Color(101, 67, 33),storage.getCustomiseHorseNo());
                storage.getFrame().revalidate();
                storage.getFrame().repaint();
            }
        });
        JButton lightGreyHorse = new JButton();
        lightGreyHorse.setBackground(new Color(211, 211, 211));
        lightGreyHorse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setHorseColor(new Color(211, 211, 211),storage.getCustomiseHorseNo());
                storage.getFrame().revalidate();
                storage.getFrame().repaint();
            }
        });
        JButton lightYellowHorse = new JButton();
        lightYellowHorse.setBackground(new Color(255, 255, 153));
        lightYellowHorse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setHorseColor(new Color(255, 255, 153),storage.getCustomiseHorseNo());
                storage.getFrame().revalidate();
                storage.getFrame().repaint();
            }
        });
        JButton greyHorse = new JButton();
        greyHorse.setBackground(new Color(128, 128, 128));
        greyHorse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setHorseColor(new Color(128, 128, 128),storage.getCustomiseHorseNo());
                storage.getFrame().revalidate();
                storage.getFrame().repaint();
            }
        });

        // adding all the buttons to the horseColourPanel
        horseColourPanel.add(darkGreyHorse);
        horseColourPanel.add(darkCreamHorse);
        horseColourPanel.add(brownHorse);
        horseColourPanel.add(darkBrownHorse);
        horseColourPanel.add(lightGreyHorse);
        horseColourPanel.add(lightYellowHorse);
        horseColourPanel.add(greyHorse);

        // making a label for breed colour, setting its properties
        JLabel breedColour = new JLabel("   Breed: ");
        breedColour.setForeground(foregroundColour);
        breedColour.setBackground(backgroundColour);
        breedColour.setFont(font);

        // making a panel for all the breed colour buttons to stay and setting its properties
        JPanel breedColourPanel = new JPanel();
        breedColourPanel.setBackground(backgroundColour);
        breedColourPanel.setLayout(new GridLayout(2,4));
        breedColourPanel.setBorder(new EmptyBorder(0, 10, 2, 10));

        // making buttons for different breed colours, setting their properties and adding functionality
        JButton darkBrownBreed = new JButton();
        darkBrownBreed.setBackground(new Color(101, 67, 33));
        darkBrownBreed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setBreedColor(new Color(101, 67, 33),storage.getCustomiseHorseNo());
                storage.getFrame().revalidate();
                storage.getFrame().repaint();
            }
        });
        JButton darkGreyBreed = new JButton();
        darkGreyBreed.setBackground(new Color(85, 85, 85));
        darkGreyBreed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setBreedColor(new Color(85, 85, 85),storage.getCustomiseHorseNo());
                storage.getFrame().revalidate();
                storage.getFrame().repaint();
            }
        });
        JButton lightGreyBreed = new JButton();
        lightGreyBreed.setBackground(new Color(211, 211, 211));
        lightGreyBreed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setBreedColor(new Color(211, 211, 211),storage.getCustomiseHorseNo());
                storage.getFrame().revalidate();
                storage.getFrame().repaint();
            }
        });
        JButton lightYellowBreed = new JButton();
        lightYellowBreed.setBackground(new Color(255, 255, 153));
        lightYellowBreed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setBreedColor(new Color(255, 255, 153),storage.getCustomiseHorseNo());
                storage.getFrame().revalidate();
                storage.getFrame().repaint();
            }
        });

        // adding all the buttons to the breedColourPanel
        breedColourPanel.add(darkBrownBreed);
        breedColourPanel.add(darkGreyBreed);
        breedColourPanel.add(lightGreyBreed);
        breedColourPanel.add(lightYellowBreed);

        // adding all the items to the horse1 panel
        horse1.add(horseNumber);
        horse1.add(horseNumberPanel);
        horse1.add(horseColour);
        horse1.add(horseColourPanel);
        horse1.add(breedColour);
        horse1.add(breedColourPanel);

        // making a label for saddle colour, setting its properties
        JLabel saddleColour = new JLabel("   Saddle Colour: ");
        saddleColour.setForeground(foregroundColour);
        saddleColour.setBackground(backgroundColour);
        saddleColour.setFont(font);

        // making a panel for all the saddle colour buttons to stay and setting its properties
        JPanel saddleColourPanel = new JPanel();
        saddleColourPanel.setBackground(backgroundColour);
        saddleColourPanel.setLayout(new GridLayout(2,4));
        saddleColourPanel.setBorder(new EmptyBorder(5, 10, 5, 10));

        // making buttons for different saddle colours, setting their properties and adding functionality
        JButton darkBrownSaddle = new JButton();
        darkBrownSaddle.setBackground(new Color(101, 67, 33));
        darkBrownSaddle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setSaddleColor(new Color(101, 67, 33),storage.getCustomiseHorseNo());
                storage.getFrame().revalidate();
                storage.getFrame().repaint();
            }
        });
        JButton darkGreySaddle = new JButton();
        darkGreySaddle.setBackground(new Color(85, 85, 85));
        darkGreySaddle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setSaddleColor(new Color(85, 85, 85),storage.getCustomiseHorseNo());
                storage.getFrame().revalidate();
                storage.getFrame().repaint();
            }
        });
        JButton blackSaddle = new JButton();
        blackSaddle.setBackground(Color.BLACK);
        blackSaddle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setSaddleColor(Color.BLACK,storage.getCustomiseHorseNo());
                storage.getFrame().revalidate();
                storage.getFrame().repaint();
            }
        });
        JButton brownSaddle = new JButton();
        brownSaddle.setBackground(new Color(165, 42, 42));
        brownSaddle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setSaddleColor(new Color(165, 42, 42),storage.getCustomiseHorseNo());
                storage.getFrame().revalidate();
                storage.getFrame().repaint();
            }
        });

        // adding all the buttons to the saddleColourPanel
        saddleColourPanel.add(darkBrownSaddle);
        saddleColourPanel.add(darkGreySaddle);
        saddleColourPanel.add(blackSaddle);
        saddleColourPanel.add(brownSaddle);

        // making a label for kit colour, setting its properties
        JLabel kitColour = new JLabel("   Kit Colour: ");
        kitColour.setForeground(foregroundColour);
        kitColour.setBackground(backgroundColour);
        kitColour.setFont(font);

        // making a panel for all the kit colour buttons to stay and setting its properties
        JPanel kitColourPanel = new JPanel();
        kitColourPanel.setBackground(backgroundColour);
        kitColourPanel.setLayout(new GridLayout(2,5));
        kitColourPanel.setBorder(new EmptyBorder(5, 10, 5, 10));

        // making buttons for different kit colours, setting their properties and adding functionality
        JButton redKit = new JButton();
        redKit.setBackground(Color.RED);
        redKit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setKitColor(Color.RED,storage.getCustomiseHorseNo());
                storage.getFrame().revalidate();
                storage.getFrame().repaint();
            }
        });
        JButton blueKit = new JButton();
        blueKit.setBackground(Color.BLUE);
        blueKit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setKitColor(Color.BLUE,storage.getCustomiseHorseNo());
                storage.getFrame().revalidate();
                storage.getFrame().repaint();
            }
        });
        JButton greenKit = new JButton();
        greenKit.setBackground(Color.GREEN);
        greenKit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setKitColor(Color.GREEN,storage.getCustomiseHorseNo());
                storage.getFrame().revalidate();
                storage.getFrame().repaint();
            }
        });
        JButton yellowKit = new JButton();
        yellowKit.setBackground(Color.YELLOW);
        yellowKit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setKitColor(Color.YELLOW,storage.getCustomiseHorseNo());
                storage.getFrame().revalidate();
                storage.getFrame().repaint();
            }
        });
        JButton purpleKit = new JButton();
        purpleKit.setBackground(new Color(128, 0, 128));
        purpleKit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storage.setKitColor(new Color(128, 0, 128),storage.getCustomiseHorseNo());
                storage.getFrame().revalidate();
                storage.getFrame().repaint();
            }
        });

        // adding all the buttons to the kitColourPanel
        kitColourPanel.add(redKit);
        kitColourPanel.add(blueKit);
        kitColourPanel.add(greenKit);
        kitColourPanel.add(yellowKit);
        kitColourPanel.add(purpleKit);

        // adding all the items to the horse2 panel
        horse2.add(saddleColour);
        horse2.add(saddleColourPanel);
        horse2.add(kitColour);
        horse2.add(kitColourPanel);

        // adding all the panels to the main panel
        panelButton.add(betting1);
        panelButton.add(betting2);
        panelButton.add(track);
        panelButton.add(horse1);
        panelButton.add(horse2);

        return panelButton;
    }

    /**
     * refreshes the horse animation screen and the statistics screen
     * @param storage the storage class
     */
    public static void refreshScreenAnimations(Storage storage){

        // resets the horses to the start
        storage.getRace().startRace();

        // reinitialises the horse animation
        storage.setHorseAnimation(new HorseAnimation(storage));

        // removes the old horse animation and adds the new one
        storage.getFrame().remove(storage.getHorseAnimationPanel());

        // restarts the animation
        storage.getHorseAnimation().restartAnimation();

        // adds the new horse animation to the frame
        storage.setHorseAnimationPanel(storage.getHorseAnimation().returnAnimationPanel());
        storage.getFrame().add(storage.getHorseAnimationPanel(), BorderLayout.CENTER);

        // removes the old statistics panel and adds the new one
        storage.getFrame().remove(storage.getStatisticsPanel());
        storage.setStatisticsPanel(storage.getStatistics().displayStatistics());
        storage.getFrame().getContentPane().add(storage.getStatisticsPanel(), BorderLayout.EAST);

        storage.getFrame().revalidate();
        storage.getFrame().repaint();
    }
}