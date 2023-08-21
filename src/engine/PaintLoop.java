package engine;

import Swing.MainPanel;

public class PaintLoop extends Thread {
    private MainPanel mainPanel;

    public PaintLoop(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        mainPanel.paintThread = this ;
    }

    @Override
    public void run() {
        while (true) {
        	if (mainPanel.EscFrameShow == false) {
        		mainPanel.moveGame();
                mainPanel.repaint();	
        	}
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}