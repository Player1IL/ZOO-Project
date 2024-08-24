/**
 * Author: Daniel Nekludov
 * ID: 321984619
 * <p>
 * Class AddCompetitionDialog
 */
package Graphics;

import Animals.Base.Animal;
import Array.EnhancedLimitedArray;
import Tournaments.CourierTournament;
import Tournaments.RegularTournament;
import Tournaments.Tournament;

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
public class StartTournamentDialog extends JDialog {
    private JComboBox<String> tournamentDropdown;
    private JButton startButton;
    private JButton exitButton;
    private String[] tournamentOptions = {
            "Air1", "Air2", "Air3", "Air4", "Air5",
            "Pool1", "Pool2", "Pool3", "Pool4",
            "Circle"
    }; // Dropdown options
    private ArrayList<String> activeTournaments;
    private ArrayList<Tournament> allTournaments;

    /**
     * Constructs a new AddCompetitionDialog.
     *
     * @param parent            the parent frame of the dialog
     * @param activeTournaments the list of currently active tournaments
     */
    public StartTournamentDialog(JFrame parent, ArrayList<String> activeTournaments, ArrayList<Tournament> allTournaments) {
        super(parent, "Add Competition", true);
        this.allTournaments = allTournaments;
        this.activeTournaments = activeTournaments;

        // Initialize components
        tournamentDropdown = new JComboBox<>(tournamentOptions);
        startButton = new JButton("Start");
        exitButton = new JButton("Exit");

        // Set up the layout
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.add(new JLabel("Select Tournament:"));
        panel.add(tournamentDropdown);
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
            String selectedCompetition = (String) tournamentDropdown.getSelectedItem();
            boolean flag = false;
            if (selectedCompetition != null) {
                if (activeTournaments.contains(selectedCompetition)) {
                    JOptionPane.showMessageDialog(StartTournamentDialog.this, "Tournament already underway");
                } else {
                    for (EnhancedLimitedArray array : tournamentArrayList) {
                        if (array.getCompetition() == Animal.Competition.valueOf(selectedCompetition)) {
                            flag = true;
                            for (int i = 0; i < array.getTeamsSize(); ++i) {
                                if (array.getArray().get(i).size() != array.getParticipantsSize()) {
                                    JOptionPane.showMessageDialog(StartTournamentDialog.this, "In Tournament " + selectedCompetition + ": Team " + (i + 1) + " missing " + (array.getParticipantsSize() - array.getArray().get(i).size()) + " participants!", "Error", JOptionPane.ERROR_MESSAGE);
                                    dispose();
                                    return;
                                }
                            }
                            if (array.getTournamentType() == EnhancedLimitedArray.Tournament.Regular) {
                                JOptionPane.showMessageDialog(StartTournamentDialog.this, "Tournament has started!");
                                dispose();
                                RegularTournament tournament = new RegularTournament(
                                        array.getArray(),
                                        Animal.Competition.valueOf(selectedCompetition),
                                        EnhancedLimitedArray.Tournament.Regular);
                                allTournaments.add(tournament);
                                activeTournaments.add(selectedCompetition);
                                Thread tournamentThread = new Thread(tournament.getThread());
                                tournamentThread.start();

                            } else if (array.getTournamentType() == EnhancedLimitedArray.Tournament.Courier) {
                                JOptionPane.showMessageDialog(StartTournamentDialog.this, "Tournament has started!");
                                dispose();
                                CourierTournament tournament = new CourierTournament(
                                        array.getArray(),
                                        Animal.Competition.valueOf(selectedCompetition),
                                        EnhancedLimitedArray.Tournament.Courier);
                                allTournaments.add(tournament);
                                activeTournaments.add(selectedCompetition);
                                Thread tournamentThread = new Thread(tournament.getThread());
                                tournamentThread.start();
                            }
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(StartTournamentDialog.this, "Tournament hasn't been created yet!", "Error", JOptionPane.ERROR_MESSAGE);
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