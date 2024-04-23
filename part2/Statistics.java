import javax.swing.*;
import java.awt.*;


public class Statistics {
    private Storage storage;
    private int widthPanel;
    private int heightPanel;
    private JPanel panel = new JPanel();

    public Statistics(Storage storage) {
        this.storage = storage;
        this.widthPanel = storage.getWidthOfFrame()/6;
        this.heightPanel = storage.getHeightOfFrame()*4/5;
    }

    public JPanel displayStatistics() {
        Color backgroundColour = new Color(255, 242, 215);
        Color foregroundColour = new Color(249, 136, 102);
        Font font = new Font("Arial", Font.BOLD, 12);

        panel.removeAll();
        panel.setLayout(new GridLayout(4,1));
        panel.setPreferredSize(new Dimension(widthPanel, heightPanel));
        panel.setBackground(backgroundColour);
        
        for (int i = 0; i < storage.getNoOfHorses(); i++) {

            JPanel stats = new JPanel();
            JLabel name = new JLabel("   Horse " + (i + 1) + ": " + storage.getHorses(i).getName());
            JLabel confidence = new JLabel("   Confidence: " + storage.getHorses(i).getConfidence());
            JLabel odds = new JLabel("   Odds: " + storage.getHorses(i).getOddsOfWinning() + ":1");
            

            name.setHorizontalAlignment(JLabel.LEFT);
            name.setFont(font);
            name.setBackground(backgroundColour);
            name.setForeground(foregroundColour);
            confidence.setHorizontalAlignment(JLabel.LEFT);
            confidence.setFont(font);
            confidence.setBackground(backgroundColour);
            confidence.setForeground(foregroundColour);
            odds.setHorizontalAlignment(JLabel.LEFT);
            odds.setFont(font);
            odds.setBackground(backgroundColour);
            odds.setForeground(foregroundColour);
            stats.setLayout(new GridLayout(4,1));
            stats.setBackground(backgroundColour);

            stats.add(name);
            stats.add(confidence);
            stats.add(odds);
            stats.setPreferredSize(new Dimension(widthPanel, heightPanel/4));
            panel.add(stats);
        }

        return panel;
    }
}
