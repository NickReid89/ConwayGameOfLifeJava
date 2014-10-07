package gol;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class GOL implements ActionListener {

    JFrame mainWindow = new JFrame();
    JPanel northPanel;
    JPanel cellPanel;
    JPanel southPanel;
    JCheckBox[][] cellGrid;
    int[][] lifeGrid;
    Random rand = new Random();
    JButton test = new JButton("Test");

    public GOL(int width, int height) {
        test.addActionListener(this);
        cellGrid = new JCheckBox[height][width];
        lifeGrid = new int[height][width];
        mainWindow.setLayout(new GridLayout(width, height));
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setTitle("Conway's Game of Life");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cellGrid[i][j] = new JCheckBox();

                if (rand.nextInt(10) % 2 == 1) {
                    cellGrid[i][j].setSelected(true);

                }

                mainWindow.add(cellGrid[i][j]);
            }
        }
        mainWindow.pack();
        mainWindow.setVisible(true);
    }

    public static void main(String[] args) {
        // TODO code application logic here
        final GOL gol = new GOL(50, 50);
        gol.test.doClick();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                ReactToEnvironment();
            }
        }, 0, 500);
    }

    public void ReactToEnvironment() {

        for (int i = 0; i < cellGrid.length; i++) {
            for (int j = 0; j < cellGrid[0].length; j++) {
                CompareCells(i, j);
            }
        }
        LifeOfCell();
    }

    public void CompareCells(int x, int y) {
        int counter = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (x == 0 && i == -1 || y == 0 && j == -1 || x == cellGrid.length - 1 && i == 1 || y == cellGrid[0].length - 1 && j == 1) {
                } else {
                    if (!(i == 0 && j == 0) && cellGrid[x + i][y + j].isSelected()) {

                        counter++;
                    }
                }
            }
        }
        if (counter == 3) {

            lifeGrid[x][y] = 1;
        }
        if (counter > 3) {
            lifeGrid[x][y] = 0;

        } else if (counter < 2) {
            lifeGrid[x][y] = 0;

        }

    }

    public void LifeOfCell() {
        for (int i = 0; i < lifeGrid.length; i++) {
            for (int j = 0; j < lifeGrid[0].length; j++) {
                if (lifeGrid[i][j] == 1) {
                    cellGrid[i][j].setSelected(true);
                    cellGrid[i][j].setBackground(Color.ORANGE);
                } else {
                    cellGrid[i][j].setSelected(false);
                    cellGrid[i][j].setBackground(UIManager.getColor("JCheckBox.background"));
                }
            }
        }
    }

}
