/**
 * Author: Daniel Nekludov
 * ID: 321984619
 * <p>
 * Class AddCompetitionDialog
 */
package Graphics;

import Animals.Base.Animal;
import Array.EnhancedLimitedArray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static System.Sys.tournamentArrayList;

/**
 * Represents a dialog for adding a competition.
 * This dialog allows the user to select a competition from a dropdown list and start it.
 */
public class AddCompetitionDialog extends JDialog {
    private JComboBox<String> competitionDropdown;
    private JButton startButton;
    private JButton exitButton;
    private JRadioButton courierButton;
    private JRadioButton regularButton;
    private JTextField teamField;
    private JTextField participantsField;
    private String[] competitionOptions = {
            "Air1", "Air2", "Air3", "Air4", "Air5",
            "Pool1", "Pool2", "Pool3", "Pool4",
            "Circle"
    }; // Dropdown options
    private ArrayList<String> activeCompetitions;

    /**
     * Constructs a new AddCompetitionDialog.
     *
     * @param parent             the parent frame of the dialog
     * @param activeCompetitions the list of currently active competitions
     */
    public AddCompetitionDialog(JFrame parent, ArrayList<String> activeCompetitions) {
        super(parent, "Add Competition", true);
        this.activeCompetitions = activeCompetitions;

        // Initialize components
        competitionDropdown = new JComboBox<>(competitionOptions);
        startButton = new JButton("Start");
        exitButton = new JButton("Exit");
        courierButton = new JRadioButton("Courier Tournament");
        regularButton = new JRadioButton("Regular Tournament");

        // Group radio buttons
        ButtonGroup group = new ButtonGroup();
        group.add(courierButton);
        group.add(regularButton);

        // Panel for radio buttons
        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new GridLayout(1, 2));
        radioPanel.add(courierButton);
        radioPanel.add(regularButton);

        regularButton.setSelected(true);

        JPanel panel = new JPanel(new GridLayout(3, 3));

        panel.add(new JLabel("Select Competition:"));
        panel.add(competitionDropdown);

        panel.add(new JLabel("Teams: "));
        teamField = new JTextField();
        teamField.setText("2");
        panel.add(teamField);

        panel.add(new JLabel("Participants: "));
        participantsField = new JTextField();
        participantsField.setText("1");
        panel.add(participantsField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(exitButton);

        // Central panel to hold both buttonPanel and radioPanel
        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new BorderLayout());
        centralPanel.add(buttonPanel, BorderLayout.SOUTH);
        centralPanel.add(radioPanel, BorderLayout.NORTH);

        // Add components to the dialog
        add(panel, BorderLayout.NORTH);
        add(centralPanel, BorderLayout.CENTER);

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
                    try {
                        int teams = Integer.parseInt(teamField.getText());
                        int participants = Integer.parseInt(participantsField.getText());
                        ArrayList<ArrayList<Animal>> array;

                        if (teams >= 2) {
                            if (courierButton.isSelected()) {
                                if (participants >= 2) {
                                    array = new ArrayList<>(teams);
                                    for (int i = 0; i < teams; i++) {
                                        array.add(new ArrayList<Animal>());
                                    }
                                    tournamentArrayList.add(new EnhancedLimitedArray(EnhancedLimitedArray.Tournament.Courier, array, convertStringToEnum((String) competitionDropdown.getSelectedItem()), teams, participants));
                                    activeCompetitions.add(selectedCompetition);
                                    JOptionPane.showMessageDialog(AddCompetitionDialog.this, "Competition started, Please add animals with the 'Add Animal' button");
                                    dispose(); // Close the dialog
                                } else {
                                    JOptionPane.showMessageDialog(AddCompetitionDialog.this, "Courier tournament cannot less than 2 participants", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            } else if (regularButton.isSelected()) {
                                if (participants == 1) {
                                    array = new ArrayList<>(teams);
                                    for (int i = 0; i < teams; i++) {
                                        array.add(new ArrayList<Animal>());
                                    }
                                    tournamentArrayList.add(new EnhancedLimitedArray(EnhancedLimitedArray.Tournament.Regular, array, convertStringToEnum((String) competitionDropdown.getSelectedItem()), teams, participants));
                                    activeCompetitions.add(selectedCompetition);
                                    JOptionPane.showMessageDialog(AddCompetitionDialog.this, "Competition started, Please add animals with the 'Add Animal' button");
                                    dispose(); // Close the dialog
                                } else {
                                    JOptionPane.showMessageDialog(AddCompetitionDialog.this, "Regular tournament cannot have teams", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(AddCompetitionDialog.this, "Competition cannot have less than 2 teams", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(AddCompetitionDialog.this, "Please enter valid numbers", "Error", JOptionPane.ERROR_MESSAGE);
                    }
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

    public Animal.Competition convertStringToEnum(String name) {
        return switch (name) {
            case "Air1", "Air2", "Air3", "Air4", "Air5",
                 "Pool1", "Pool2", "Pool3", "Pool4",
                 "Circle" -> Animal.Competition.valueOf(name);
            default -> throw new IllegalArgumentException("Unknown enum value: " + name);
        };
    }
}
