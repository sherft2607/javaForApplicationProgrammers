import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * @author Shandon Herft
 */
public class Game {
    /** Start button for the game. */
    private JButton startButton;
    /** Text area for displaying the time left. */
    private JTextArea textArea;
    /** Text area for displaying the score. */
    private JTextArea textArea2;
    /** Array of buttons representing moles. */
    private JButton[] buttons;
    /** Current score of the game. */
    private int score = 0;
    /** Indicates whether the game is currently active. */
    private boolean isGameActive = false;
    /** Indicates whether the timer is currently running. */
    private boolean timerRunning = false;

    /**
     * Constructor for the Game class.
     * @param numLights Number of buttons (moles).
     * @param sleepTime Sleep time for the timer (unused in updated version but kept for consistency).
     */
    public Game(int numLights, long sleepTime) {
        Font font = new Font(Font.MONOSPACED, Font.BOLD, 14);

        // Create the main game window
        JFrame window = new JFrame("Whack-a-mole");
        window.setSize(650, 350);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel for the start button, timer, and score
        JPanel line = new JPanel();
        startButton = new JButton("Start");
        line.add(startButton);

        JLabel label = new JLabel("Time Left:");
        line.add(label);

        textArea = new JTextArea(1, 10);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setText("20"); // Initial time of 20 seconds
        line.add(textArea);

        JLabel label2 = new JLabel("Score:");
        line.add(label2);

        textArea2 = new JTextArea(1, 10);
        textArea2.setLineWrap(true);
        textArea2.setWrapStyleWord(true);
        textArea2.setEditable(false);
        textArea2.setText("0"); // Initial score of 0
        line.add(textArea2);

        // Panel to hold the mole buttons
        JPanel pane = new JPanel();

        // Create the mole buttons
        buttons = new JButton[numLights];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton("   "); // Initial "down" state
            buttons[i].setBackground(Color.LIGHT_GRAY);
            buttons[i].setFont(font);
            buttons[i].setOpaque(true);
            buttons[i].addActionListener(new ButtonClickListener()); // Add ActionListener to each button
            pane.add(buttons[i]);
        }

        // Set up the layout of the window
        window.setLayout(new BorderLayout());
        window.add(line, BorderLayout.NORTH);
        window.add(pane, BorderLayout.CENTER);
        window.setVisible(true);

        // Start button ActionListener
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isGameActive) {
                    isGameActive = true; // Start the game
                    score = 0; // Reset the score
                    textArea2.setText("0"); // Update score display
                    startButton.setEnabled(false); // Disable start button
                    timerRunning = true; // Set timer to running
                    new TimerThread(textArea, Game.this).start(); // Start the timer thread

                    // Start one thread for each mole button
                    for (int i = 0; i < buttons.length; i++) {
                        new MoleThread(buttons[i], Game.this).start();
                    }
                }
            }
        });
    }

    // Inner class to handle button clicks
    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();
            String currentText = clickedButton.getText();
            if (":-)".equals(currentText)) { // Check if the mole is "up"
                score++; // Increment the score
                clickedButton.setText(":-("); // Change to ":-(" state
                clickedButton.setBackground(Color.RED); // Indicate red visually
                textArea2.setText(String.valueOf(score)); // Update score display

                // Notify the mole thread to finish early if it's clicked
                synchronized (clickedButton) {
                    clickedButton.notify(); // Notify mole thread
                }
            }
        }
    }

    // Thread class to handle each mole's "up" and "down" states
    private static class MoleThread extends Thread {
        /** Button associated with the mole. */
        private final JButton button;
        /** Reference to the game instance. */
        private final Game game;
        /** Random number generator. */
        private final Random random = new Random();
        /** Indicates if the mole is currently up. */
        private boolean moleUp = false;

        /**
         * Constructor for the MoleThread class.
         * @param buttons The button associated with the mole.
         * @param games The instance of the game.
         */
        MoleThread(JButton buttons, Game games) {
            this.button = buttons;
            this.game = games;
        }

        @Override
        public void run() {
            while (game.timerRunning) { // Keep running while the game timer is active
                try {
                    // Mole is "down" for at least 2 seconds
                    Thread.sleep(2000);

                    // Change to "up" state
                    synchronized (button) {
                        if (game.timerRunning && "   ".equals(button.getText())) {
                            button.setText(":-)");
                            button.setBackground(Color.GREEN);
                            moleUp = true;

                            long upTime = 500 + random.nextInt(3500); // Random "up" time between 0.5 to 4 seconds

                            long startTime = System.currentTimeMillis();
                            while (moleUp && System.currentTimeMillis() - startTime < upTime) {
                                button.wait(upTime - (System.currentTimeMillis() - startTime)); // Wait until upTime is over or mole is hit
                            }

                            // Change back to "down" state
                            button.setText("   ");
                            button.setBackground(Color.LIGHT_GRAY);
                            moleUp = false;
                        }
                    }
                } catch (InterruptedException e) {
                    throw new AssertionError(e);
                }
            }
        }
    }

    // Thread class to handle the game timer
    private static class TimerThread extends Thread {
        /** Text area for displaying the time left. */
        private final JTextArea timeArea;
        /** Reference to the game instance. */
        private final Game game;

        /**
         * Constructor for the TimerThread class.
         * @param timeAreas The text area for displaying the time.
         * @param games The instance of the game.
         */
        TimerThread(JTextArea timeAreas, Game games) {
            this.timeArea = timeAreas;
            this.game = games;
        }

        @Override
        public void run() {
            int count = 20; // Start countdown from 20 seconds
            while (count >= 0) {
                timeArea.setText(String.valueOf(count)); // Display the countdown
                try {
                    Thread.sleep(1000); // Sleep for one second
                } catch (InterruptedException e) {
                    throw new AssertionError(e);
                }
                count--; // Decrement the countdown
            }

            // Stop the game when the timer reaches zero
            game.timerRunning = false;

            // Sleep for five more seconds before re-enabling the start button
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new AssertionError(e);
            }

            // Re-enable the start button after the game ends
            game.startButton.setEnabled(true);
            timeArea.setText("GAME OVER!"); // Display game over message
            game.isGameActive = false; // Mark game as inactive
        }
    }

    public static void main(String[] args) {
        int numLights = 63; // Number of buttons (moles)
        long sleepMillis = 20000; // Sleep time (not used in this updated version)
        new Game(numLights, sleepMillis); // Start the game
    }
}
