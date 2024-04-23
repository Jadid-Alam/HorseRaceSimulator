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
        this.screenHeight = storage.getHeightOfFrame()*4 / 5;
        this.screenWidth = storage.getWidthOfFrame()*5 / 6;
        this.NoOfTracks = storage.getNoOfHorses();
        this.distance = storage.getDistance();
        this.trackHeight = this.screenHeight / 4; 
        this.blockSize = this.trackHeight / blockHeight;
        
        final Color blck = Color.BLACK;
        final Color yell = new Color(255, 255, 51);
        final Color reds = Color.RED;

        Color colr = new Color(255, 255, 200);
        Color bred = new Color(255, 255, 199);
        Color prsn = new Color(255, 255, 198);
        Color sadl = new Color(255, 255, 197);
        Color kits = new Color(255, 255, 196);
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

    public JPanel returnAnimationPanel() {
      
        horseStage++; // need to put it here other wise it wouldnt work properly since painComponent is called afterwards
        
        if (fenceStage == 0)
        {
            fenceStage = 1;
        }
        else
        {
            fenceStage = 0;
        }


        this.mainScreen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Draw(g, 0, storage, 0);
                Draw(g, 30, storage, 1);
                if (NoOfTracks > 2)
                {
                    Draw(g, 60, storage, 2);
                    if (NoOfTracks > 3)
                    {
                        Draw(g, 90, storage, 3);
                    }
                }
            }
        };
        
        storage.setTrackMovement(storage.getTrackMovement() + 1);
        this.mainScreen.setPreferredSize(new Dimension(screenWidth, screenHeight));

        return this.mainScreen;
     
    }

    private void Draw(Graphics g, int y, Storage storage, int i)
    {
        for (int row1 = 0; row1 < screenHeight/blockSize - 1; row1++) {
            for (int col = 0; col < screenWidth/blockSize; col++) {

                Color c1 = null;

                if ((col >= 0 && col < 25 - storage.getTrackMovement()) || col > distance+ 75) {
                    c1 = Color.GREEN;
                }
                else if (row1 == 0 || row1 == 25)
                {
                    c1 = storage.getFenceColor();
                }
                else if (col%2 == fenceStage && ((row1 >= 0 && row1 < 4) || (row1 >= 25 && row1 < 29)))
                {
                    c1 = storage.getFenceColor();
                }
                else if ((row1 >= 3 && row1 < 5) || (row1 >= 28))
                {
                    c1 = Color.GREEN;
                }
                else if (col == 50 - storage.getTrackMovement())
                {
                    c1 = Color.WHITE;
                }
                else if (col == 49 + distance - storage.getTrackMovement())
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
                else if (col == 50 + distance - storage.getTrackMovement())
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
                else 
                {
                    c1 = storage.getLaneColor();
                }

                if (c1 != null) {
                    g.setColor(c1);
                    g.fillRect((col) * blockSize, (row1+y) * blockSize, blockSize, blockSize);
                }
                
                
            }
        }

        // Draw the grid with colors
        for (int row = 0; row < 20; row++) {
            for (int col = 0; col < 25; col++) {

                Color c;

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

                Color colr = new Color(255, 255, 200);
                Color bred = new Color(255, 255, 199);
                Color prsn = new Color(255, 255, 198);
                Color sadl = new Color(255, 255, 197);
                Color kits = new Color(255, 255, 196);

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

                if (c != null) {
                    g.setColor(c);
                    g.fillRect((col+25 + storage.getHorses(i).getDistanceTravelled()) * blockSize, (row + 5+y) * blockSize, blockSize, blockSize);
                }
            
            }
        }

    }

    public void restartAnimation() {
        horseStage = 0;
        fenceStage = 0;
    }

    public static boolean colorsEqual(Color color1, Color color2) {
        if (color1 == null || color2 == null) {
            return false;
        }
        return color1.getRed() == color2.getRed() && color1.getGreen() == color2.getGreen() && color1.getBlue() == color2.getBlue();
    }
}
