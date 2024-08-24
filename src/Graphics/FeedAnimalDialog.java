package Graphics;

import Animals.Base.Animal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static System.Sys.animalArrayList;
/**
 * Represents a dialog for feeding an animal.
 * Allows the user to select an animal and input energy to be fed to the selected animal.
 */
public class FeedAnimalDialog extends JDialog {
    private JComboBox<Animal> animalDropdown;
    private JTextField energyInputField;
    private JButton feedButton;
    private JButton exitButton;
    /**
     * Constructs a new FeedAnimalDialog.
     * Initializes the dialog with components for selecting an animal, entering energy, and buttons for feeding or exiting.
     *
     * @param parent the parent frame of this dialog
     */
    public FeedAnimalDialog(JFrame parent) {
        super(parent, "Feed Animal", true);

        // Initialize components
        animalDropdown = new JComboBox<>(animalArrayList.toArray(new Animal[0]));
        energyInputField = new JTextField(10);
        feedButton = new JButton("Feed");
        exitButton = new JButton("Exit");

        // Layout
        JPanel inputPanel = new JPanel();

        inputPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        inputPanel.setLayout(new GridLayout(4,1));

        inputPanel.add(new JLabel("Select Animal:"));
        inputPanel.add(animalDropdown);
        inputPanel.add(new JLabel("Energy:"));
        inputPanel.add(energyInputField);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(feedButton);
        buttonPanel.add(exitButton);

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button listeners
        feedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                feedAnimal();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Closes the dialog
            }
        });
        // Dialog settings
        pack();
        setSize(500, 180);
        setResizable(false);
        setLocationRelativeTo(parent);
    }
    /**
     * Feeds the selected animal with the specified amount of energy.
     * Retrieves the selected animal from the dropdown and parses the energy input.
     * Displays an error message if the input is invalid or insufficient energy is provided.
     *
     * @throws NumberFormatException if the energy input is not a valid integer
     */
    private void feedAnimal() {
        Animal selectedAnimal = (Animal) animalDropdown.getSelectedItem();
        String energyText = energyInputField.getText();
        try {
            int energy = Integer.parseInt(energyText);
            if (selectedAnimal != null) {
                boolean result = selectedAnimal.eat(energy);

                if (!result) {
                    JOptionPane.showMessageDialog(this, "Invalid input or insufficient energy.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Animal was fed, current energy: " + selectedAnimal.getEnergyAmount(), "Info", JOptionPane.INFORMATION_MESSAGE);
                    // Here temporarily
                    // selectedAnimal.race();
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}