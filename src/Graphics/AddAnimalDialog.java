package Graphics;

import Animals.*;
import Animals.Base.Animal;
import Array.EnhancedLimitedArray;
import Olympics.Medal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static System.Sys.animalArrayList;
import static System.Sys.tournamentArrayList;

/**
 * Dialog for adding a new animal to the system. The dialog allows the user to enter information about various types of animals.
 * It includes radio buttons for selecting the animal type and dynamically displays the relevant input fields based on the selection.
 * The dialog supports adding data for the following animals: Alligator, Cat, Dog, Dolphin, Eagle, Pigeon, Snake, Whale.
 */
public class AddAnimalDialog extends JDialog {
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private CompetitionFrame parent;
    private ArrayList<String> activeCompetitions;
    ArrayList<String> terrestrialFilter = new ArrayList<>(List.of(
            "Circle"
    ));
    ArrayList<String> aquaticFilter = new ArrayList<>(Arrays.asList(
            "Pool1", "Pool2", "Pool3", "Pool4"
    ));
    ArrayList<String> airFilter = new ArrayList<>(Arrays.asList(
            "Air1", "Air2", "Air3", "Air4", "Air5"
    ));
    ArrayList<String> filteredCompetitions = new ArrayList<>();

    /**
     * Constructs an `AddAnimalDialog` with the specified parent frame and list of active competitions.
     *
     * @param parent             the parent frame of the dialog
     * @param activeCompetitions the list of active competitions available for animals
     */
    public AddAnimalDialog(JFrame parent, ArrayList<String> activeCompetitions) {
        super(parent, "Add Animal", true);

        this.activeCompetitions = activeCompetitions;
        this.parent = (CompetitionFrame) parent;

        setLayout(new BorderLayout());

        // Create radio buttons for animals
        JRadioButton alligatorButton = new JRadioButton("Alligator");
        JRadioButton catButton = new JRadioButton("Cat");
        JRadioButton dogButton = new JRadioButton("Dog");
        JRadioButton dolphinButton = new JRadioButton("Dolphin");
        JRadioButton eagleButton = new JRadioButton("Eagle");
        JRadioButton pigeonButton = new JRadioButton("Pigeon");
        JRadioButton snakeButton = new JRadioButton("Snake");
        JRadioButton whaleButton = new JRadioButton("Whale");

        // Group radio buttons
        ButtonGroup group = new ButtonGroup();
        group.add(alligatorButton);
        group.add(catButton);
        group.add(dogButton);
        group.add(dolphinButton);
        group.add(eagleButton);
        group.add(pigeonButton);
        group.add(snakeButton);
        group.add(whaleButton);

        // Panel for radio buttons
        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new GridLayout(8, 1));
        radioPanel.add(alligatorButton);
        radioPanel.add(catButton);
        radioPanel.add(dogButton);
        radioPanel.add(dolphinButton);
        radioPanel.add(eagleButton);
        radioPanel.add(pigeonButton);
        radioPanel.add(snakeButton);
        radioPanel.add(whaleButton);

        add(radioPanel, BorderLayout.WEST);

        // Panel for input fields (using CardLayout)
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Adding input panels for each animal
        cardPanel.add(createAlligatorPanel(), "Alligator");
        cardPanel.add(createCatPanel(), "Cat");
        cardPanel.add(createDogPanel(), "Dog");
        cardPanel.add(createDolphinPanel(), "Dolphin");
        cardPanel.add(createEaglePanel(), "Eagle");
        cardPanel.add(createPigeonPanel(), "Pigeon");
        cardPanel.add(createSnakePanel(), "Snake");
        cardPanel.add(createWhalePanel(), "Whale");

        // Default selected
        alligatorButton.setSelected(true);

        add(cardPanel, BorderLayout.EAST);
        //add(cardPanel, BorderLayout.CENTER);

        // Add action listeners for radio buttons
        alligatorButton.addActionListener(e -> cardLayout.show(cardPanel, "Alligator"));
        catButton.addActionListener(e -> cardLayout.show(cardPanel, "Cat"));
        dogButton.addActionListener(e -> cardLayout.show(cardPanel, "Dog"));
        dolphinButton.addActionListener(e -> cardLayout.show(cardPanel, "Dolphin"));
        eagleButton.addActionListener(e -> cardLayout.show(cardPanel, "Eagle"));
        pigeonButton.addActionListener(e -> cardLayout.show(cardPanel, "Pigeon"));
        snakeButton.addActionListener(e -> cardLayout.show(cardPanel, "Snake"));
        whaleButton.addActionListener(e -> cardLayout.show(cardPanel, "Whale"));

        setPreferredSize(new Dimension(350, 250));
        pack();

        setLocationRelativeTo(parent);

        setResizable(false);
    }

    /**
     * Creates and returns a panel with input fields for adding an Alligator.
     *
     * @return a JPanel with input fields for an Alligator
     */
    private JPanel createAlligatorPanel() {
        JPanel panel = new JPanel(new GridLayout(9, 2));

        panel.add(new JLabel("Name:"));
        JTextField nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Gender:"));
        JComboBox<String> genderCombo = new JComboBox<>(new String[]{"Male", "Female", "Hermaphrodite"});
        panel.add(genderCombo);

        panel.add(new JLabel("Weight:"));
        JTextField weightField = new JTextField();
        weightField.setText("0");
        panel.add(weightField);

        panel.add(new JLabel("Area of Living:"));
        JTextField areaField = new JTextField();
        panel.add(areaField);

        panel.add(new JLabel("Max Energy:"));
        JTextField maxEnergyField = new JTextField();
        maxEnergyField.setText("0");
        panel.add(maxEnergyField);

        panel.add(new JLabel("Energy Per Meter:"));
        JTextField energyPerMeterField = new JTextField();
        energyPerMeterField.setText("0");
        panel.add(energyPerMeterField);

        ArrayList<String> combinedList = new ArrayList<>(terrestrialFilter);
        combinedList.addAll(aquaticFilter);
        filter(combinedList);

        panel.add(new JLabel("Competition Type:"));
        JComboBox<String> competitionTypeCombo = new JComboBox<>(filteredCompetitions.toArray(new String[0]));
        panel.add(competitionTypeCombo);

        panel.add(new JLabel("Team:"));
        JComboBox<Integer> tournamentTeam = new JComboBox<>();

        int numberOfTeams = 0;

        try {
            for (EnhancedLimitedArray array : tournamentArrayList) {
                if ((Animal.Competition) enumHandler((String) Objects.requireNonNull(competitionTypeCombo.getSelectedItem())) == array.getCompetition()) {
                    numberOfTeams = array.getTeamsSize();
                }
            }
        } catch (NullPointerException _) {
        }

        for (int i = 1; i <= numberOfTeams; i++) {
            tournamentTeam.addItem(i);
        }

        competitionTypeCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numberOfTeams = 0;

                try {
                    for (EnhancedLimitedArray array : tournamentArrayList) {
                        if ((Animal.Competition) enumHandler((String) Objects.requireNonNull(competitionTypeCombo.getSelectedItem())) == array.getCompetition()) {
                            numberOfTeams = array.getTeamsSize();
                        }
                    }
                } catch (NullPointerException _) {
                }
                tournamentTeam.removeAllItems(); // Clear existing items
                for (int i = 1; i <= numberOfTeams; i++) {
                    tournamentTeam.addItem(i);
                }
                panel.revalidate();
                panel.repaint();
            }
        });

        panel.add(tournamentTeam);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                String gender = (String) genderCombo.getSelectedItem();
                double weight = Double.parseDouble(weightField.getText());
                String area = areaField.getText();
                int maxEnergy = Integer.parseInt(maxEnergyField.getText());
                int energyPerMeter = Integer.parseInt(energyPerMeterField.getText());
                String competitionType = (String) competitionTypeCombo.getSelectedItem();
                ArrayList<Medal> medals = new ArrayList<>();
                Integer selected = (Integer) tournamentTeam.getSelectedItem();
                int teamNumber = selected != null ? selected : 0;

                if (name.isEmpty() || gender == null || area.isEmpty() || Objects.requireNonNull(competitionType).isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all required fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //animalArrayList.add(new Alligator(name, (Animal.Gender) enumHandler(gender), (Animal.Competition) enumHandler(competitionType), weight, maxEnergy, energyPerMeter, medals, parent, area));
                Animal.Competition compType = (Animal.Competition) enumHandler(competitionType);
                Animal animal = new Alligator(name, (Animal.Gender) enumHandler(gender), compType, weight, maxEnergy, energyPerMeter, medals, parent, area);
                if (addAnimalToTournament(animal, compType, teamNumber)) {
                    animalArrayList.add(animal);
                    // Close the dialog
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Team " + teamNumber + " is full!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(this, "No competition provided", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(submitButton);

        return panel;
    }

    /**
     * Creates and returns a panel with input fields for adding a Cat.
     *
     * @return a JPanel with input fields for a Cat
     */
    private JPanel createCatPanel() {
        JPanel panel = new JPanel(new GridLayout(9, 2));

        panel.add(new JLabel("Name:"));
        JTextField nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Gender:"));
        JComboBox<String> genderCombo = new JComboBox<>(new String[]{"Male", "Female", "Hermaphrodite"});
        panel.add(genderCombo);

        panel.add(new JLabel("Weight:"));
        JTextField weightField = new JTextField();
        weightField.setText("0");
        panel.add(weightField);

        panel.add(new JLabel("Castrated:"));
        JRadioButton yesButton = new JRadioButton("Yes");
        JRadioButton noButton = new JRadioButton("No");

        noButton.setSelected(true);

        ButtonGroup castratedGroup = new ButtonGroup();
        castratedGroup.add(yesButton);
        castratedGroup.add(noButton);

        JPanel castratedPanel = new JPanel(new GridLayout(1, 2));
        castratedPanel.add(yesButton);
        castratedPanel.add(noButton);
        panel.add(castratedPanel);

        panel.add(new JLabel("Max Energy:"));
        JTextField maxEnergyField = new JTextField();
        maxEnergyField.setText("0");
        panel.add(maxEnergyField);

        panel.add(new JLabel("Energy Per Meter:"));
        JTextField energyPerMeterField = new JTextField();
        energyPerMeterField.setText("0");
        panel.add(energyPerMeterField);

        filter(terrestrialFilter);
        panel.add(new JLabel("Competition Type:"));
        JComboBox<String> competitionTypeCombo = new JComboBox<>(filteredCompetitions.toArray(new String[0]));
        panel.add(competitionTypeCombo);

        panel.add(new JLabel("Team:"));
        JComboBox<Integer> tournamentTeam = new JComboBox<>();

        int numberOfTeams = 0;

        try {
            for (EnhancedLimitedArray array : tournamentArrayList) {
                if ((Animal.Competition) enumHandler((String) Objects.requireNonNull(competitionTypeCombo.getSelectedItem())) == array.getCompetition()) {
                    numberOfTeams = array.getTeamsSize();
                }
            }
        } catch (NullPointerException _) {
        }

        for (int i = 1; i <= numberOfTeams; i++) {
            tournamentTeam.addItem(i);
        }

        competitionTypeCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numberOfTeams = 0;

                try {
                    for (EnhancedLimitedArray array : tournamentArrayList) {
                        if ((Animal.Competition) enumHandler((String) Objects.requireNonNull(competitionTypeCombo.getSelectedItem())) == array.getCompetition()) {
                            numberOfTeams = array.getTeamsSize();
                        }
                    }
                } catch (NullPointerException _) {
                }
                tournamentTeam.removeAllItems(); // Clear existing items
                for (int i = 1; i <= numberOfTeams; i++) {
                    tournamentTeam.addItem(i);
                }
                panel.revalidate();
                panel.repaint();
            }
        });

        panel.add(tournamentTeam);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                String gender = (String) genderCombo.getSelectedItem();
                double weight = Double.parseDouble(weightField.getText());
                boolean castrated = yesButton.isSelected();
                int maxEnergy = Integer.parseInt(maxEnergyField.getText());
                int energyPerMeter = Integer.parseInt(energyPerMeterField.getText());
                String competitionType = (String) competitionTypeCombo.getSelectedItem();
                ArrayList<Medal> medals = new ArrayList<>();
                Integer selected = (Integer) tournamentTeam.getSelectedItem();
                int teamNumber = selected != null ? selected : 0;

                if (name.isEmpty() || gender == null || Objects.requireNonNull(competitionType).isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all required fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //animalArrayList.add(new Cat(name, (Animal.Gender) enumHandler(gender), (Animal.Competition) enumHandler(competitionType), weight, maxEnergy, energyPerMeter, medals, parent, castrated));
                Animal.Competition compType = (Animal.Competition) enumHandler(competitionType);
                Animal animal = new Cat(name, (Animal.Gender) enumHandler(gender), compType, weight, maxEnergy, energyPerMeter, medals, parent, castrated);
                if (addAnimalToTournament(animal, compType, teamNumber)) {
                    animalArrayList.add(animal);
                    // Close the dialog
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Team " + teamNumber + " is full!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(this, "No competition provided", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });
        panel.add(submitButton);

        return panel;
    }

    /**
     * Creates and returns a panel with input fields for adding a Dog.
     *
     * @return a JPanel with input fields for a Dog
     */
    private JPanel createDogPanel() {
        JPanel panel = new JPanel(new GridLayout(9, 2));

        panel.add(new JLabel("Name:"));
        JTextField nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Gender:"));
        JComboBox<String> genderCombo = new JComboBox<>(new String[]{"Male", "Female", "Hermaphrodite"});
        panel.add(genderCombo);

        panel.add(new JLabel("Weight:"));
        JTextField weightField = new JTextField();
        weightField.setText("0");
        panel.add(weightField);

        panel.add(new JLabel("Breed:"));
        JTextField breedField = new JTextField();
        panel.add(breedField);

        panel.add(new JLabel("Max Energy:"));
        JTextField maxEnergyField = new JTextField();
        maxEnergyField.setText("0");
        panel.add(maxEnergyField);

        panel.add(new JLabel("Energy Per Meter:"));
        JTextField energyPerMeterField = new JTextField();
        energyPerMeterField.setText("0");
        panel.add(energyPerMeterField);

        filter(terrestrialFilter);
        panel.add(new JLabel("Competition Type:"));
        JComboBox<String> competitionTypeCombo = new JComboBox<>(filteredCompetitions.toArray(new String[0]));
        panel.add(competitionTypeCombo);

        panel.add(new JLabel("Team:"));
        JComboBox<Integer> tournamentTeam = new JComboBox<>();

        int numberOfTeams = 0;

        try {
            for (EnhancedLimitedArray array : tournamentArrayList) {
                if ((Animal.Competition) enumHandler((String) Objects.requireNonNull(competitionTypeCombo.getSelectedItem())) == array.getCompetition()) {
                    numberOfTeams = array.getTeamsSize();
                }
            }
        } catch (NullPointerException _) {
        }

        for (int i = 1; i <= numberOfTeams; i++) {
            tournamentTeam.addItem(i);
        }

        competitionTypeCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numberOfTeams = 0;

                try {
                    for (EnhancedLimitedArray array : tournamentArrayList) {
                        if ((Animal.Competition) enumHandler((String) Objects.requireNonNull(competitionTypeCombo.getSelectedItem())) == array.getCompetition()) {
                            numberOfTeams = array.getTeamsSize();
                        }
                    }
                } catch (NullPointerException _) {
                }
                tournamentTeam.removeAllItems(); // Clear existing items
                for (int i = 1; i <= numberOfTeams; i++) {
                    tournamentTeam.addItem(i);
                }
                panel.revalidate();
                panel.repaint();
            }
        });

        panel.add(tournamentTeam);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                String gender = (String) genderCombo.getSelectedItem();
                double weight = Double.parseDouble(weightField.getText());
                String breed = breedField.getText();
                int maxEnergy = Integer.parseInt(maxEnergyField.getText());
                int energyPerMeter = Integer.parseInt(energyPerMeterField.getText());
                String competitionType = (String) competitionTypeCombo.getSelectedItem();
                ArrayList<Medal> medals = new ArrayList<>();
                Integer selected = (Integer) tournamentTeam.getSelectedItem();
                int teamNumber = selected != null ? selected : 0;

                if (name.isEmpty() || gender == null || breed.isEmpty() || Objects.requireNonNull(competitionType).isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all required fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //animalArrayList.add(new Dog(name, (Animal.Gender) enumHandler(gender), (Animal.Competition) enumHandler(competitionType), weight, maxEnergy, energyPerMeter, medals, parent, breed));
                Animal.Competition compType = (Animal.Competition) enumHandler(competitionType);
                Animal animal = new Dog(name, (Animal.Gender) enumHandler(gender), compType, weight, maxEnergy, energyPerMeter, medals, parent, breed);
                if (addAnimalToTournament(animal, compType, teamNumber)) {
                    animalArrayList.add(animal);
                    // Close the dialog
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Team " + teamNumber + " is full!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(this, "No competition provided", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(submitButton);

        // Close the dialog
        return panel;
    }

    /**
     * Creates and returns a panel with input fields for adding a Dolphin.
     *
     * @return a JPanel with input fields for a Dolphin
     */
    private JPanel createDolphinPanel() {
        JPanel panel = new JPanel(new GridLayout(9, 2));

        panel.add(new JLabel("Name:"));
        JTextField nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Gender:"));
        JComboBox<String> genderCombo = new JComboBox<>(new String[]{"Male", "Female", "Hermaphrodite"});
        panel.add(genderCombo);

        panel.add(new JLabel("Weight:"));
        JTextField weightField = new JTextField();
        weightField.setText("0");
        panel.add(weightField);

        panel.add(new JLabel("Water Type:"));
        JComboBox<String> waterTypeCombo = new JComboBox<>(new String[]{"Sea", "Sweet"});
        panel.add(waterTypeCombo);

        panel.add(new JLabel("Max Energy:"));
        JTextField maxEnergyField = new JTextField();
        maxEnergyField.setText("0");
        panel.add(maxEnergyField);

        panel.add(new JLabel("Energy Per Meter:"));
        JTextField energyPerMeterField = new JTextField();
        energyPerMeterField.setText("0");
        panel.add(energyPerMeterField);

        filter(aquaticFilter);
        panel.add(new JLabel("Competition Type:"));
        JComboBox<String> competitionTypeCombo = new JComboBox<>(filteredCompetitions.toArray(new String[0]));
        panel.add(competitionTypeCombo);

        panel.add(new JLabel("Team:"));
        JComboBox<Integer> tournamentTeam = new JComboBox<>();

        int numberOfTeams = 0;

        try {
            for (EnhancedLimitedArray array : tournamentArrayList) {
                if ((Animal.Competition) enumHandler((String) Objects.requireNonNull(competitionTypeCombo.getSelectedItem())) == array.getCompetition()) {
                    numberOfTeams = array.getTeamsSize();
                }
            }
        } catch (NullPointerException _) {
        }

        for (int i = 1; i <= numberOfTeams; i++) {
            tournamentTeam.addItem(i);
        }

        competitionTypeCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numberOfTeams = 0;

                try {
                    for (EnhancedLimitedArray array : tournamentArrayList) {
                        if ((Animal.Competition) enumHandler((String) Objects.requireNonNull(competitionTypeCombo.getSelectedItem())) == array.getCompetition()) {
                            numberOfTeams = array.getTeamsSize();
                        }
                    }
                } catch (NullPointerException _) {
                }
                tournamentTeam.removeAllItems(); // Clear existing items
                for (int i = 1; i <= numberOfTeams; i++) {
                    tournamentTeam.addItem(i);
                }
                panel.revalidate();
                panel.repaint();
            }
        });

        panel.add(tournamentTeam);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                String gender = (String) genderCombo.getSelectedItem();
                double weight = Double.parseDouble(weightField.getText());
                String waterType = (String) waterTypeCombo.getSelectedItem();
                int maxEnergy = Integer.parseInt(maxEnergyField.getText());
                int energyPerMeter = Integer.parseInt(energyPerMeterField.getText());
                String competitionType = (String) competitionTypeCombo.getSelectedItem();
                ArrayList<Medal> medals = new ArrayList<>();
                Integer selected = (Integer) tournamentTeam.getSelectedItem();
                int teamNumber = selected != null ? selected : 0;

                if (name.isEmpty() || gender == null || Objects.requireNonNull(waterType).isEmpty() || Objects.requireNonNull(competitionType).isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all required fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //animalArrayList.add(new Dolphin(name, (Animal.Gender) enumHandler(gender), (Animal.Competition) enumHandler(competitionType), weight, maxEnergy, energyPerMeter, medals, parent, (Dolphin.WaterType) enumHandler(waterType)));
                Animal.Competition compType = (Animal.Competition) enumHandler(competitionType);
                Animal animal = new Dolphin(name, (Animal.Gender) enumHandler(gender), compType, weight, maxEnergy, energyPerMeter, medals, parent, (Dolphin.WaterType) enumHandler(waterType));
                if (addAnimalToTournament(animal, compType, teamNumber)) {
                    animalArrayList.add(animal);
                    // Close the dialog
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Team " + teamNumber + " is full!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(this, "No competition provided", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });
        panel.add(submitButton);

        return panel;
    }

    /**
     * Creates and returns a panel with input fields for adding an Eagle.
     *
     * @return a JPanel with input fields for an Eagle
     */
    private JPanel createEaglePanel() {
        JPanel panel = new JPanel(new GridLayout(9, 2));

        panel.add(new JLabel("Name:"));
        JTextField nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Gender:"));
        JComboBox<String> genderCombo = new JComboBox<>(new String[]{"Male", "Female", "Hermaphrodite"});
        panel.add(genderCombo);

        panel.add(new JLabel("Weight:"));
        JTextField weightField = new JTextField();
        weightField.setText("0");
        panel.add(weightField);

        panel.add(new JLabel("Wingspan:"));
        JTextField wingspanField = new JTextField();
        wingspanField.setText("0");
        panel.add(wingspanField);

        panel.add(new JLabel("Max Energy:"));
        JTextField maxEnergyField = new JTextField();
        maxEnergyField.setText("0");
        panel.add(maxEnergyField);

        panel.add(new JLabel("Energy Per Meter:"));
        JTextField energyPerMeterField = new JTextField();
        energyPerMeterField.setText("0");
        panel.add(energyPerMeterField);

        filter(airFilter);
        panel.add(new JLabel("Competition Type:"));
        JComboBox<String> competitionTypeCombo = new JComboBox<>(filteredCompetitions.toArray(new String[0]));
        panel.add(competitionTypeCombo);

        panel.add(new JLabel("Team:"));
        JComboBox<Integer> tournamentTeam = new JComboBox<>();

        int numberOfTeams = 0;

        try {
            for (EnhancedLimitedArray array : tournamentArrayList) {
                if ((Animal.Competition) enumHandler((String) Objects.requireNonNull(competitionTypeCombo.getSelectedItem())) == array.getCompetition()) {
                    numberOfTeams = array.getTeamsSize();
                }
            }
        } catch (NullPointerException _) {
        }

        for (int i = 1; i <= numberOfTeams; i++) {
            tournamentTeam.addItem(i);
        }

        competitionTypeCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numberOfTeams = 0;

                try {
                    for (EnhancedLimitedArray array : tournamentArrayList) {
                        if ((Animal.Competition) enumHandler((String) Objects.requireNonNull(competitionTypeCombo.getSelectedItem())) == array.getCompetition()) {
                            numberOfTeams = array.getTeamsSize();
                        }
                    }
                } catch (NullPointerException _) {
                }
                tournamentTeam.removeAllItems(); // Clear existing items
                for (int i = 1; i <= numberOfTeams; i++) {
                    tournamentTeam.addItem(i);
                }
                panel.revalidate();
                panel.repaint();
            }
        });

        panel.add(tournamentTeam);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                String gender = (String) genderCombo.getSelectedItem();
                double weight = Double.parseDouble(weightField.getText());
                double wingspan = Double.parseDouble(wingspanField.getText());
                int maxEnergy = Integer.parseInt(maxEnergyField.getText());
                int energyPerMeter = Integer.parseInt(energyPerMeterField.getText());
                String competitionType = (String) competitionTypeCombo.getSelectedItem();
                ArrayList<Medal> medals = new ArrayList<>();
                Integer selected = (Integer) tournamentTeam.getSelectedItem();
                int teamNumber = selected != null ? selected : 0;

                if (name.isEmpty() || gender == null || Objects.requireNonNull(competitionType).isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all required fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //animalArrayList.add(new Eagle(name, (Animal.Gender) enumHandler(gender), (Animal.Competition) enumHandler(competitionType), weight, maxEnergy, energyPerMeter, medals, wingspan, parent, 0));
                Animal.Competition compType = (Animal.Competition) enumHandler(competitionType);
                Animal animal = new Eagle(name, (Animal.Gender) enumHandler(gender), compType, weight, maxEnergy, energyPerMeter, medals, wingspan, parent, 0);
                if (addAnimalToTournament(animal, compType, teamNumber)) {
                    animalArrayList.add(animal);
                    // Close the dialog
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Team " + teamNumber + " is full!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(this, "No competition provided", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });
        panel.add(submitButton);

        return panel;
    }

    /**
     * Creates and returns a panel with input fields for adding a Pigeon.
     *
     * @return a JPanel with input fields for a Pigeon
     */
    private JPanel createPigeonPanel() {
        JPanel panel = new JPanel(new GridLayout(10, 2));

        panel.add(new JLabel("Name:"));
        JTextField nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Gender:"));
        JComboBox<String> genderCombo = new JComboBox<>(new String[]{"Male", "Female", "Hermaphrodite"});
        panel.add(genderCombo);

        panel.add(new JLabel("Weight:"));
        JTextField weightField = new JTextField();
        weightField.setText("0");
        panel.add(weightField);

        panel.add(new JLabel("Wingspan:"));
        JTextField wingspanField = new JTextField();
        wingspanField.setText("0");
        panel.add(wingspanField);

        panel.add(new JLabel("Family:"));
        JTextField familyField = new JTextField();
        panel.add(familyField);

        panel.add(new JLabel("Max Energy:"));
        JTextField maxEnergyField = new JTextField();
        maxEnergyField.setText("0");
        panel.add(maxEnergyField);

        panel.add(new JLabel("Energy Per Meter:"));
        JTextField energyPerMeterField = new JTextField();
        energyPerMeterField.setText("0");
        panel.add(energyPerMeterField);

        filter(airFilter);
        panel.add(new JLabel("Competition Type:"));
        JComboBox<String> competitionTypeCombo = new JComboBox<>(filteredCompetitions.toArray(new String[0]));
        panel.add(competitionTypeCombo);

        panel.add(new JLabel("Team:"));
        JComboBox<Integer> tournamentTeam = new JComboBox<>();

        int numberOfTeams = 0;

        try {
            for (EnhancedLimitedArray array : tournamentArrayList) {
                if ((Animal.Competition) enumHandler((String) Objects.requireNonNull(competitionTypeCombo.getSelectedItem())) == array.getCompetition()) {
                    numberOfTeams = array.getTeamsSize();
                }
            }
        } catch (NullPointerException _) {
        }

        for (int i = 1; i <= numberOfTeams; i++) {
            tournamentTeam.addItem(i);
        }

        competitionTypeCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numberOfTeams = 0;

                try {
                    for (EnhancedLimitedArray array : tournamentArrayList) {
                        if ((Animal.Competition) enumHandler((String) Objects.requireNonNull(competitionTypeCombo.getSelectedItem())) == array.getCompetition()) {
                            numberOfTeams = array.getTeamsSize();
                        }
                    }
                } catch (NullPointerException _) {
                }
                tournamentTeam.removeAllItems(); // Clear existing items
                for (int i = 1; i <= numberOfTeams; i++) {
                    tournamentTeam.addItem(i);
                }
                panel.revalidate();
                panel.repaint();
            }
        });

        panel.add(tournamentTeam);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                String gender = (String) genderCombo.getSelectedItem();
                double weight = Double.parseDouble(weightField.getText());
                double wingspan = Double.parseDouble(wingspanField.getText());
                String family = familyField.getText();
                int maxEnergy = Integer.parseInt(maxEnergyField.getText());
                int energyPerMeter = Integer.parseInt(energyPerMeterField.getText());
                String competitionType = (String) competitionTypeCombo.getSelectedItem();
                ArrayList<Medal> medals = new ArrayList<>();
                Integer selected = (Integer) tournamentTeam.getSelectedItem();
                int teamNumber = selected != null ? selected : 0;

                if (name.isEmpty() || gender == null || family.isEmpty() || Objects.requireNonNull(competitionType).isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all required fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //animalArrayList.add(new Pigeon(name, (Animal.Gender) enumHandler(gender), (Animal.Competition) enumHandler(competitionType), weight, maxEnergy, energyPerMeter, medals, wingspan, parent, family));
                Animal.Competition compType = (Animal.Competition) enumHandler(competitionType);
                Animal animal = new Pigeon(name, (Animal.Gender) enumHandler(gender), compType, weight, maxEnergy, energyPerMeter, medals, wingspan, parent, family);
                if (addAnimalToTournament(animal, compType, teamNumber)) {
                    animalArrayList.add(animal);
                    // Close the dialog
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Team " + teamNumber + " is full!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(this, "No competition provided", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });
        panel.add(submitButton);

        return panel;
    }

    /**
     * Creates and returns a panel with input fields for adding a Snake.
     *
     * @return a JPanel with input fields for a Snake
     */
    private JPanel createSnakePanel() {
        JPanel panel = new JPanel(new GridLayout(9, 2));

        panel.add(new JLabel("Name:"));
        JTextField nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Gender:"));
        JComboBox<String> genderCombo = new JComboBox<>(new String[]{"Male", "Female", "Hermaphrodite"});
        panel.add(genderCombo);

        panel.add(new JLabel("Weight:"));
        JTextField weightField = new JTextField();
        weightField.setText("0");
        panel.add(weightField);

        panel.add(new JLabel("Poisonous Level:"));
        JComboBox<String> poisonousLevelCombo = new JComboBox<>(new String[]{"Low", "Medium", "High"});
        panel.add(poisonousLevelCombo);

        panel.add(new JLabel("Max Energy:"));
        JTextField maxEnergyField = new JTextField();
        maxEnergyField.setText("0");
        panel.add(maxEnergyField);

        panel.add(new JLabel("Energy Per Meter:"));
        JTextField energyPerMeterField = new JTextField();
        energyPerMeterField.setText("0");
        panel.add(energyPerMeterField);

        filter(terrestrialFilter);
        panel.add(new JLabel("Competition Type:"));
        JComboBox<String> competitionTypeCombo = new JComboBox<>(filteredCompetitions.toArray(new String[0]));
        panel.add(competitionTypeCombo);

        panel.add(new JLabel("Team:"));
        JComboBox<Integer> tournamentTeam = new JComboBox<>();

        int numberOfTeams = 0;

        try {
            for (EnhancedLimitedArray array : tournamentArrayList) {
                if ((Animal.Competition) enumHandler((String) Objects.requireNonNull(competitionTypeCombo.getSelectedItem())) == array.getCompetition()) {
                    numberOfTeams = array.getTeamsSize();
                }
            }
        } catch (NullPointerException _) {
        }

        for (int i = 1; i <= numberOfTeams; i++) {
            tournamentTeam.addItem(i);
        }

        competitionTypeCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numberOfTeams = 0;

                try {
                    for (EnhancedLimitedArray array : tournamentArrayList) {
                        if ((Animal.Competition) enumHandler((String) Objects.requireNonNull(competitionTypeCombo.getSelectedItem())) == array.getCompetition()) {
                            numberOfTeams = array.getTeamsSize();
                        }
                    }
                } catch (NullPointerException _) {
                }
                tournamentTeam.removeAllItems(); // Clear existing items
                for (int i = 1; i <= numberOfTeams; i++) {
                    tournamentTeam.addItem(i);
                }
                panel.revalidate();
                panel.repaint();
            }
        });

        panel.add(tournamentTeam);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                String gender = (String) genderCombo.getSelectedItem();
                double weight = Double.parseDouble(weightField.getText());
                String poisonousLevel = (String) poisonousLevelCombo.getSelectedItem();
                int maxEnergy = Integer.parseInt(maxEnergyField.getText());
                int energyPerMeter = Integer.parseInt(energyPerMeterField.getText());
                String competitionType = (String) competitionTypeCombo.getSelectedItem();
                ArrayList<Medal> medals = new ArrayList<>();
                Integer selected = (Integer) tournamentTeam.getSelectedItem();
                int teamNumber = selected != null ? selected : 0;

                if (name.isEmpty() || gender == null || poisonousLevel == null || Objects.requireNonNull(competitionType).isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all required fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //animalArrayList.add(new Snake(name, (Animal.Gender) enumHandler(gender), (Animal.Competition) enumHandler(competitionType), weight, maxEnergy, energyPerMeter, medals, parent, (Snake.Poisonous) enumHandler(poisonousLevel)));
                Animal.Competition compType = (Animal.Competition) enumHandler(competitionType);
                Animal animal = new Snake(name, (Animal.Gender) enumHandler(gender), compType, weight, maxEnergy, energyPerMeter, medals, parent, (Snake.Poisonous) enumHandler(poisonousLevel));
                if (addAnimalToTournament(animal, compType, teamNumber)) {
                    animalArrayList.add(animal);
                    // Close the dialog
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Team " + teamNumber + " is full!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(this, "No competition provided", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });
        panel.add(submitButton);

        return panel;
    }

    /**
     * Creates and returns a panel with input fields for adding a Whale.
     *
     * @return a JPanel with input fields for a Whale
     */
    private JPanel createWhalePanel() {
        JPanel panel = new JPanel(new GridLayout(9, 2));

        panel.add(new JLabel("Name:"));
        JTextField nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Gender:"));
        JComboBox<String> genderCombo = new JComboBox<>(new String[]{"Male", "Female", "Hermaphrodite"});
        panel.add(genderCombo);

        panel.add(new JLabel("Weight:"));
        JTextField weightField = new JTextField();
        weightField.setText("0");
        panel.add(weightField);

        panel.add(new JLabel("Food Type:"));
        JTextField foodTypeField = new JTextField();
        panel.add(foodTypeField);

        panel.add(new JLabel("Max Energy:"));
        JTextField maxEnergyField = new JTextField();
        maxEnergyField.setText("0");
        panel.add(maxEnergyField);

        panel.add(new JLabel("Energy Per Meter:"));
        JTextField energyPerMeterField = new JTextField();
        energyPerMeterField.setText("0");
        panel.add(energyPerMeterField);

        filter(aquaticFilter);
        panel.add(new JLabel("Competition Type:"));
        JComboBox<String> competitionTypeCombo = new JComboBox<>(filteredCompetitions.toArray(new String[0]));
        panel.add(competitionTypeCombo);

        panel.add(new JLabel("Team:"));
        JComboBox<Integer> tournamentTeam = new JComboBox<>();

        int numberOfTeams = 0;

        try {
            for (EnhancedLimitedArray array : tournamentArrayList) {
                if ((Animal.Competition) enumHandler((String) Objects.requireNonNull(competitionTypeCombo.getSelectedItem())) == array.getCompetition()) {
                    numberOfTeams = array.getTeamsSize();
                }
            }
        } catch (NullPointerException _) {
        }

        for (int i = 1; i <= numberOfTeams; i++) {
            tournamentTeam.addItem(i);
        }

        competitionTypeCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numberOfTeams = 0;

                try {
                    for (EnhancedLimitedArray array : tournamentArrayList) {
                        if ((Animal.Competition) enumHandler((String) Objects.requireNonNull(competitionTypeCombo.getSelectedItem())) == array.getCompetition()) {
                            numberOfTeams = array.getTeamsSize();
                        }
                    }
                } catch (NullPointerException _) {
                }
                tournamentTeam.removeAllItems(); // Clear existing items
                for (int i = 1; i <= numberOfTeams; i++) {
                    tournamentTeam.addItem(i);
                }
                panel.revalidate();
                panel.repaint();
            }
        });

        panel.add(tournamentTeam);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                String gender = (String) genderCombo.getSelectedItem();
                double weight = Double.parseDouble(weightField.getText());
                String foodType = foodTypeField.getText();
                int maxEnergy = Integer.parseInt(maxEnergyField.getText());
                int energyPerMeter = Integer.parseInt(energyPerMeterField.getText());
                String competitionType = (String) competitionTypeCombo.getSelectedItem();
                ArrayList<Medal> medals = new ArrayList<>();
                Integer selected = (Integer) tournamentTeam.getSelectedItem();
                int teamNumber = selected != null ? selected : 0;

                if (name.isEmpty() || gender == null || foodType.isEmpty() || Objects.requireNonNull(competitionType).isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all required fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //animalArrayList.add(new Whale(name, (Animal.Gender) enumHandler(gender), (Animal.Competition) enumHandler(competitionType), weight, maxEnergy, energyPerMeter, medals, parent, foodType));
                Animal.Competition compType = (Animal.Competition) enumHandler(competitionType);
                Animal animal = new Whale(name, (Animal.Gender) enumHandler(gender), compType, weight, maxEnergy, energyPerMeter, medals, parent, foodType);
                if (addAnimalToTournament(animal, compType, teamNumber)) {
                    animalArrayList.add(animal);
                    // Close the dialog
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Team " + teamNumber + " is full!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(this, "No competition provided", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });
        panel.add(submitButton);

        return panel;
    }

    /**
     * Converts a string to the corresponding enumeration value.
     *
     * @param value the string representation of the enum
     * @return the corresponding enum value
     */
    public static Object enumHandler(String value) {
        return switch (value) {
            case "Male", "Female", "Hermaphrodite" -> Animal.Gender.valueOf(value);
            case "Low", "Medium", "High" -> Snake.Poisonous.valueOf(value);
            case "Sea", "Sweet" -> Dolphin.WaterType.valueOf(value);
            case "Air1", "Air2", "Air3", "Air4", "Air5",
                 "Pool1", "Pool2", "Pool3", "Pool4",
                 "Circle" -> Animal.Competition.valueOf(value);
            default -> throw new IllegalArgumentException("Unknown enum value: " + value);
        };
    }

    /**
     * Filters the competitions based on the given list of filters.
     */
    public void filter(ArrayList<String> array) {
        filteredCompetitions.clear();
        for (String competition : this.activeCompetitions) {
            if (array.contains(competition) && !filteredCompetitions.contains(competition)) {
                filteredCompetitions.add(competition);
            }
        }
    }

    public boolean addAnimalToTournament(Animal animal, Animal.Competition competition, int team) {
        for (EnhancedLimitedArray array : tournamentArrayList) {
            if (array.getCompetition() == competition) {
                if (array.getArray().get(team - 1).size() < array.getParticipantsSize()) {
                    array.getArray().get(team - 1).add(animal);
                    return true;
                }
            }
        }
        return false;
    }
}