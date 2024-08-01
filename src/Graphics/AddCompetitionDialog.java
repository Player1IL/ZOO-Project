/**
 * Author: Daniel Nekludov
 * ID: 321984619
 * <p>
 * Class AddCompetitionDialog
 */
package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
 * Represents a dialog for adding a competition.
 * This dialog allows the user to select a competition from a dropdown list and start it.
 */
public class AddCompetitionDialog extends JDialog {
    private JComboBox<String> competitionDropdown;
    private JButton startButton;
    private JButton exitButton;
    private String[] competitionOptions = {
            "Air1", "Air2", "Air3", "Air4", "Air5",
            "Pool1", "Pool2", "Pool3", "Pool4",
            "Circle"
    }; // Dropdown options
    private ArrayList<String> activeCompetitions;
    /**
     * Constructs a new AddCompetitionDialog.
     *
     * @param parent the parent frame of the dialog
     * @param activeCompetitions the list of currently active competitions
     */
    public AddCompetitionDialog(JFrame parent, ArrayList<String> activeCompetitions) {
        super(parent, "Add Competition", true);
        this.activeCompetitions = activeCompetitions;

        // Initialize components
        competitionDropdown = new JComboBox<>(competitionOptions);
        startButton = new JButton("Start");
        exitButton = new JButton("Exit");

        // Set up the layout
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.add(new JLabel("Select Competition:"));
        panel.add(competitionDropdown);
        add(panel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        startButton.addActionListener(new StartButtonListener());
        exitButton.addActionListener(new ExitButtonListener());

        pack(); // Adjusts size to fit components
        setResizable(false);
        setLocationRelativeTo(parent); // Centers the dialog
    }
    /**
     * Listener for the "Start" button.
     * Checks if the selected competition is already active. If not, it adds the competition to the list.
     */
    private class StartButtonListener implements ActionListener {
        /**
         * Handles the action event when the "Start" button is clicked.
         * Adds the selected competition to the active competitions list if not already present.
         * Displays a message dialog indicating whether the competition was started or already open.
         *
         * @param e the action event
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedCompetition = (String) competitionDropdown.getSelectedItem();
            if (selectedCompetition != null) {
                if (activeCompetitions.contains(selectedCompetition)) {
                    JOptionPane.showMessageDialog(AddCompetitionDialog.this, "Competition already open");
                } else {
                    activeCompetitions.add(selectedCompetition);
                    JOptionPane.showMessageDialog(AddCompetitionDialog.this, "Competition started");
                    dispose(); // Close the dialog
                }
            }
        }
    }
    /**
     * Listener for the "Exit" button.
     * Closes the dialog without making any changes.
     */
    private class ExitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose(); // Close the dialog
        }
    }
}
