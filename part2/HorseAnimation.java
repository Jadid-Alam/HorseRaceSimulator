/**
 * A class to make the animation of the horses moving
 * this also includes the tracks and the fences
 * 
 * @author Jadid Alam
 * @version 1.1
 */

import java.awt.*;
import javax.swing.*;

public class HorseAnimation extends JPanel {

    private JPanel mainScreen;
    private int horseStage = 0;
    private int fenceStage = 0;
    private int blockSize;
    private int screenHeight;
    private int screenWidth;
    private int NoOfTracks;
    private int trackHeight; // 200, 160
    private int distance;
    private final int blockHeight = 30;
    Color[][] restingHC;
    Color[][] startingHC;
    Color[][] runningHC;
    Color[][] landingHC;
    Color[][] fallenHC;
    Storage storage;
    

    public HorseAnimation(Storage storage) {

        this.storage = storage;

        // sets the size of the screen
        this.screenHeight = storage.getHeightOfFrame()*4 / 5;
        this.screenWidth = storage.getWidthOfFrame()*5 / 6;
        this.NoOfTracks = storage.getNoOfHorses();
        this.distance = storage.getDistance();

        // canculates the size of the blocks and track height for the animation drawing
        this.trackHeight = this.screenHeight / 4; 
        this.blockSize = this.trackHeight / blockHeight;
        
        final Color blck = Color.BLACK;
        final Color yell = new Color(255, 255, 51);
        final Color reds = Color.RED;

        // sets the default colors for the horses
        Color colr = new Color(255, 255, 200);
        Color bred = new Color(255, 255, 199);
        Color prsn = new Color(255, 255, 198);
        Color sadl = new Color(255, 255, 197);
        Color kits = new Color(255, 255, 196);

        // making a 2D array of colors to draw the resting horse
        Color[][] restingHC1 = 
        {
            {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,colr,null,colr,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,bred,colr,bred,bred,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,null,kits,kits,null,null,bred,colr,colr,colr,colr,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,null,prsn,prsn,null,null,bred,colr,blck,colr,sadl,colr,colr,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,null,kits,null,null,bred,bred,colr,colr,sadl,sadl,colr,colr,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,kits,null,kits,sadl,sadl,sadl,sadl,sadl,colr,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,kits,null,null,null,bred,bred,bred,colr,colr,colr,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,bred,bred,sadl,kits,sadl,sadl,colr,colr,colr,colr,colr,colr,null,null,null,null},
            {null,null,null,null,null,null,null,null,bred,bred,colr,colr,sadl,kits,colr,colr,colr,colr,colr,colr,colr,null,null,null,null},
            {null,null,null,null,null,null,null,null,bred,bred,colr,colr,kits,colr,colr,colr,colr,colr,colr,colr,colr,null,null,null,null},
            {null,null,null,null,null,null,null,bred,bred,null,colr,colr,colr,colr,colr,colr,colr,colr,colr,colr,colr,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,colr,null,colr,null,null,null,null,null,colr,null,colr,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,colr,null,colr,null,null,null,null,null,colr,null,colr,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,colr,null,colr,null,null,null,null,null,colr,null,colr,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,blck,null,blck,null,null,null,null,null,blck,null,blck,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null}
        };
        this.restingHC = restingHC1;  
        
        // making a 2D array of colors to draw the starting horse
        Color[][] startingHC1 = 
        {
            {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,colr,null,colr,null,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,colr,colr,bred,bred,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,kits,kits,null,null,null,bred,colr,colr,colr,colr,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,prsn,prsn,null,null,null,bred,colr,blck,colr,sadl,colr,colr,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,kits,null,null,null,bred,bred,colr,colr,sadl,colr,colr,colr,null},
            {null,null,null,bred,bred,bred,null,null,null,null,null,kits,null,kits,sadl,sadl,sadl,sadl,sadl,sadl,colr,null,null,null,null},
            {null,null,bred,bred,bred,bred,bred,null,null,null,kits,null,null,null,bred,bred,bred,colr,colr,colr,colr,null,null,null,null},
            {null,null,bred,null,null,bred,bred,bred,bred,bred,sadl,kits,sadl,sadl,colr,colr,colr,colr,colr,colr,null,null,null,null,null},
            {null,null,null,null,null,null,bred,bred,null,colr,colr,sadl,kits,colr,colr,colr,colr,colr,colr,colr,null,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,colr,colr,kits,colr,colr,colr,colr,colr,colr,colr,colr,null,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,colr,colr,colr,colr,colr,colr,colr,colr,colr,colr,colr,colr,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,colr,null,colr,null,null,null,null,null,null,colr,null,colr,null,null,null,null},
            {null,null,null,null,null,null,null,null,colr,colr,colr,colr,null,null,null,null,null,null,colr,null,colr,null,null,null,null},
            {null,null,null,null,null,null,null,null,colr,null,colr,null,null,null,null,null,null,null,colr,null,blck,null,null,null,null},
            {null,null,null,null,null,null,null,null,blck,null,blck,null,null,null,null,null,null,null,blck,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null}
        };
        this.startingHC = startingHC1;

        // making a 2D array of colors to draw the running horse
        Color[][] runningHC1 = 
        {
            {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,colr,null,colr,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,bred,colr,bred,bred,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,null,kits,kits,null,null,bred,colr,colr,colr,colr,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,null,prsn,prsn,null,null,bred,colr,blck,colr,sadl,colr,colr,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,null,kits,null,null,bred,bred,colr,colr,sadl,sadl,colr,colr,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,kits,null,kits,sadl,sadl,sadl,sadl,sadl,colr,null,null,null,null},
            {null,null,null,null,null,null,null,bred,bred,bred,null,kits,null,null,null,bred,bred,bred,colr,colr,colr,null,null,null,null},
            {null,null,null,null,null,null,bred,bred,bred,bred,bred,sadl,kits,sadl,sadl,colr,colr,colr,colr,colr,colr,null,null,null,null},
            {null,null,null,null,null,null,bred,bred,bred,null,colr,colr,sadl,kits,colr,colr,colr,colr,colr,colr,colr,null,null,null,null},
            {null,null,null,null,bred,null,null,bred,bred,null,colr,colr,kits,colr,colr,colr,colr,colr,colr,colr,colr,null,null,null,null},
            {null,null,null,null,null,bred,bred,bred,null,null,colr,colr,colr,colr,colr,colr,colr,colr,colr,colr,colr,colr,colr,colr,null},
            {null,null,null,null,null,null,null,null,null,null,colr,colr,colr,colr,null,null,null,null,colr,colr,colr,null,null,colr,null},
            {null,null,null,null,null,null,null,null,null,null,colr,null,colr,colr,null,null,null,null,null,null,colr,null,blck,null,null},
            {null,null,null,null,null,null,null,null,null,colr,null,null,colr,null,null,null,null,null,null,blck,null,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,colr,null,blck,null,null,null,null,null,null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null,blck,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null}
        };
        this.runningHC = runningHC1;

        // making a 2D array of colors to draw the landing horse
        Color[][] landingHC1 = 
        {
            {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,colr,null,colr,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,bred,colr,bred,bred,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,null,kits,kits,null,null,bred,colr,colr,colr,colr,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,null,prsn,prsn,null,null,bred,colr,blck,colr,sadl,colr,colr,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,null,kits,null,null,bred,bred,colr,colr,sadl,sadl,colr,colr,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,kits,null,kits,sadl,sadl,sadl,sadl,sadl,colr,null,null,null,null},
            {null,null,null,null,null,null,null,null,bred,bred,bred,kits,null,null,null,bred,bred,bred,colr,colr,colr,null,null,null,null},
            {null,null,null,null,null,null,null,bred,bred,bred,bred,sadl,kits,sadl,sadl,colr,colr,colr,colr,colr,colr,null,null,null,null},
            {null,null,null,null,bred,null,null,bred,bred,null,colr,colr,sadl,kits,colr,colr,colr,colr,colr,colr,colr,null,null,null,null},
            {null,null,null,null,bred,bred,bred,bred,null,null,colr,colr,kits,colr,colr,colr,colr,colr,colr,colr,colr,null,null,null,null},
            {null,null,null,null,null,bred,bred,null,null,null,colr,colr,colr,colr,colr,colr,colr,colr,colr,colr,colr,colr,null,null,null},
            {null,null,null,null,null,null,null,null,null,colr,colr,colr,colr,colr,null,null,null,null,colr,null,null,colr,colr,null,null},
            {null,null,null,null,null,null,null,null,colr,null,null,null,colr,null,null,null,null,null,null,colr,null,null,colr,null,null},
            {null,null,null,null,null,null,null,null,colr,colr,null,null,colr,null,null,null,null,null,null,null,colr,null,blck,null,null},
            {null,null,null,null,null,null,null,null,null,null,blck,null,null,blck,null,null,null,null,null,null,null,colr,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,blck,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null}
        };
        this.landingHC = landingHC1;

        // making a 2D array of colors to draw the fallen horse
        Color[][] fallenHC1 = 
        {
            {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,colr,null,colr,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,bred,yell,bred,yell,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,kits,kits,null,null,null,yell,colr,yell,colr,yell,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,prsn,prsn,null,null,null,bred,colr,colr,colr,sadl,colr,colr,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,kits,null,null,null,bred,bred,colr,colr,sadl,sadl,colr,colr,reds},
            {null,null,null,null,null,null,null,null,null,null,null,null,kits,kits,null,null,sadl,sadl,sadl,sadl,colr,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,bred,bred,null,kits,null,kits,bred,bred,bred,colr,colr,colr,null,null,null,null},
            {null,null,null,null,null,null,null,null,bred,bred,bred,sadl,kits,sadl,sadl,colr,colr,colr,colr,colr,colr,null,null,null,null},
            {null,null,null,null,null,null,null,null,bred,bred,colr,colr,sadl,kits,colr,colr,colr,colr,colr,colr,colr,null,null,null,null},
            {null,null,null,null,null,null,null,null,bred,bred,colr,colr,kits,colr,colr,colr,colr,colr,colr,colr,colr,null,null,null,null},
            {null,null,null,null,null,null,null,bred,bred,bred,colr,colr,colr,colr,colr,colr,colr,colr,colr,colr,colr,colr,colr,colr,blck},
            {null,null,null,null,null,null,null,bred,bred,colr,colr,colr,colr,colr,null,null,null,null,colr,null,null,null,null,null,null},
            {null,null,null,null,null,null,blck,bred,colr,null,null,null,colr,null,null,null,null,null,null,colr,colr,null,null,null,null},
            {null,null,null,null,null,null,bred,null,null,null,colr,colr,null,null,null,null,null,null,null,null,null,colr,blck,null,null},
            {null,null,null,null,null,null,null,null,null,blck,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null}
        };
        this.fallenHC = fallenHC1;

    }

    /*
     * this method paints the animation of the horses moving
     * with the tracks and the fences and returns the JPanel
     */
    public JPanel returnAnimationPanel() {
      
        /*
         * increment the horse stage to move the horse to the next stage
         * increment the fence stage to move the fence to the next stage
         * at the start due to the paintComponent method being called at
         * the end due to its nature
         */
        horseStage++; 
        
        if (fenceStage == 0)
        {
            fenceStage = 1;
        }
        else
        {
            fenceStage = 0;
        }

        // creates a new JPanel to draw the animation
        this.mainScreen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                // Draw the tracks for horse 1 and 2
                Draw(g, 0, storage, 0);
                Draw(g, 30, storage, 1);

                // Draw the tracks for horse 3 if no of tracks is greater than 2
                if (NoOfTracks > 2)
                {
                    Draw(g, 60, storage, 2);

                    // Draw the tracks for horse 4 if no of tracks is greater than 3
                    if (NoOfTracks > 3)
                    {
                        Draw(g, 90, storage, 3);
                    }
                }
            }
        };
        
        // increment the track movement to cause track movement
        storage.setTrackMovement(storage.getTrackMovement() + 1);

        // set the size of the JPanel
        this.mainScreen.setPreferredSize(new Dimension(screenWidth, screenHeight));

        return this.mainScreen;
     
    }

    /*
     * this method draws the tracks and the fences
     * and the horses on the tracks
     */
    private void Draw(Graphics g, int y, Storage storage, int i)
    {
        // conditions for the drawing, rows and columns represent height and weight of the track
        for (int row1 = 0; row1 < screenHeight/blockSize - 1; row1++) {
            for (int col = 0; col < screenWidth/blockSize; col++) {

                Color c1 = null;

                // deciding which colour to draw in each block of the grid
                // 25 - storage.getTrackMovement() to make the track start at the horses position
                if ((col >= 0 && col < 25 - storage.getTrackMovement()) || col > distance+ 75) { // 75 + distance to make the track longer and not end abruptly
                    c1 = Color.GREEN;
                }
                else if (row1 == 0 || row1 == 25)
                {
                    c1 = storage.getFenceColor(); // color of the fence
                }
                else if (col%2 == fenceStage && ((row1 >= 0 && row1 < 4) || (row1 >= 25 && row1 < 29))) // usage of fence stage to make the fence move
                {
                    c1 = storage.getFenceColor();
                }
                else if ((row1 >= 3 && row1 < 5) || (row1 >= 28)) // setting the position of the grass
                {
                    c1 = Color.GREEN;
                }
                else if (col == 50 - storage.getTrackMovement()) // moving the statring line with the track
                {
                    c1 = Color.WHITE;
                }
                else if (col == 49 + distance - storage.getTrackMovement()) // moving the finishing line with the track
                {
                    if (row1%2 == 0)
                    {
                        c1 = Color.BLACK;
                    }
                    else 
                    {
                        c1 = Color.WHITE;
                    }
                }
                else if (col == 50 + distance - storage.getTrackMovement()) // moving the finishing line with the track but making sure its checkered
                {
                    if (row1%2 == 1)
                    {
                        c1 = Color.BLACK;
                    }
                    else 
                    {
                        c1 = Color.WHITE;
                    }
                }
                else  // setting the color of the track
                {
                    c1 = storage.getLaneColor();
                }

                if (c1 != null) {
                    g.setColor(c1);
                    g.fillRect((col) * blockSize, (row1+y) * blockSize, blockSize, blockSize);
                }
                
                
            }
        }

        // Draw horse on the track
        for (int row = 0; row < 20; row++) {
            for (int col = 0; col < 25; col++) {

                Color c;

                // deciding which stage of the horse to draw
                if (storage.getHorses(i).hasFallen() == true)
                {
                    c = fallenHC[row][col];
                }
                else if (horseStage == 1) {
                    c = restingHC[row][col];
                } else if (horseStage == 2) {
                    c = startingHC[row][col];
                } else if (horseStage == 3) {
                    c = runningHC[row][col];
                } else if (horseStage == 4) {
                    c = landingHC[row][col];
                } else {
                    c = restingHC[row][col];
                    horseStage = 1;
                } 

                // initializing the existing colors that are on the horse drawing
                Color colr = new Color(255, 255, 200);
                Color bred = new Color(255, 255, 199);
                Color prsn = new Color(255, 255, 198);
                Color sadl = new Color(255, 255, 197);
                Color kits = new Color(255, 255, 196);

                // using existing colors to change the colors of the horse to the colours choses by the user
                if (colorsEqual(c,colr))
                {
                    c = storage.getHorseColor(i);
                }
                else if (colorsEqual(c,bred))
                {
                    c = storage.getBreedColor(i);
                }
                else if (colorsEqual(c,prsn))
                {
                    c = Color.PINK;
                }
                else if (colorsEqual(c,sadl))
                {
                    c = storage.getSaddleColor(i);
                }
                else if (colorsEqual(c,kits))
                {
                    c = storage.getKitColor(i);
                }

                // drawing the horse
                if (c != null) {
                    g.setColor(c);
                    g.fillRect((col+25 + storage.getHorses(i).getDistanceTravelled()) * blockSize, (row + 5+y) * blockSize, blockSize, blockSize);
                }
            
            }
        }

    }

    /*
     * this method restarts the animation
     */
    public void restartAnimation() {
        horseStage = 0;
        fenceStage = 0;
    }

    /*
     * this method checks if the colors are equal
     * and returns a boolean
     * @param color1, color2 the colors to be compared
     */
    public static boolean colorsEqual(Color color1, Color color2) {
        if (color1 == null || color2 == null) {
            return false;
        }
        return color1.getRed() == color2.getRed() && color1.getGreen() == color2.getGreen() && color1.getBlue() == color2.getBlue();
    }
}
