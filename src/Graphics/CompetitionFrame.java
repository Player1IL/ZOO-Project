/**
 * Author: Daniel Nekludov
 * ID: 321984619
 * <p>
 * Class CompetitionFrame
 */
package Graphics;

import Animals.*;
import Animals.Base.AirAnimal;
import Animals.Base.Animal;
import Animals.Base.AquaticAnimal;
import Animals.Base.TerrestrialAnimals;
import Array.EnhancedLimitedArray;
import Tournaments.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import static System.Sys.animalArrayList;
import static System.Sys.tournamentArrayList;

/**
 * Represents the main frame of the competition application.
 * This frame contains menus, buttons, and displays information about animals participating in competitions.
 */
public class CompetitionFrame extends JFrame {

    private static boolean infoTableStatus = false;
    private static boolean infoTournamentTableStatus = false;

    private Object[][] dataAnimal;
    private Object[][] dataTournament;

    private int dataAnimalArraySize;
    private int dataTournamentArraySize;

    private int deleted = 0;
    private BufferedImage backgroundImage;
    private ArrayList<String> activeCompetitions = new ArrayList<>();
    private ArrayList<String> activeTournaments = new ArrayList<>();
    private ArrayList<Tournament> allTournaments = new ArrayList<>();

    /**
     * Constructs a new CompetitionFrame.
     * Initializes the frame with a menu bar, buttons, background image, and layout.
     */
    public CompetitionFrame() {
        // Set title
        super("Competition");

        setSize(1700, 1200);

        // Set layout
        setLayout(new BorderLayout());

        // Create menu bar
        JMenuBar menuBar = new JMenuBar();

        // File menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        // Help menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem helpItem = new JMenuItem("Help");
        helpItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Home Work 2\nGUI");
            }
        });
        helpMenu.add(helpItem);
        menuBar.add(helpMenu);

        // Set menu bar
        setJMenuBar(menuBar);

        // Load the background image
        /*
        ImageIcon backgroundIcon = new ImageIcon(BACKGROUND_PATH);
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setPreferredSize(new Dimension(backgroundIcon.getIconWidth(), backgroundIcon.getIconHeight()));

        // Set background layout
        backgroundLabel.setLayout(new GridLayout(1, 1));
        add(backgroundLabel, BorderLayout.CENTER);
        */
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setLayout(new GridLayout(1, 1));
        try {
            String BACKGROUND_PATH = "src/Graphics/graphics2/competitionBackground.png";
            backgroundImage = ImageIO.read(new File(BACKGROUND_PATH));
        } catch (IOException e) {
            System.out.println("Cannot load background image");
        }
        backgroundLabel.setLayout(new GridLayout(1, 1));
        add(new DrawingPanel(), BorderLayout.CENTER);

        // Create panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 8));

        // Add buttons to the panel
        JButton addCompetitionButton = new JButton("Add Competition");
        addCompetitionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddCompetitionDialog dialog = new AddCompetitionDialog(CompetitionFrame.this, activeCompetitions);
                dialog.setVisible(true);
            }
        });
        JButton addAnimalButton = new JButton("Add Animal");
        addAnimalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddAnimalDialog dialog = new AddAnimalDialog(CompetitionFrame.this, activeCompetitions);
                dialog.setVisible(true);
                repaint();
            }
        });
        JButton addClearButton = new JButton("Clear");
        addClearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClearAnimalDialog dialog = new ClearAnimalDialog(CompetitionFrame.this);
                dialog.setVisible(true);
                repaint();
            }
        });
        JButton addEatButton = new JButton("Eat");
        addEatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FeedAnimalDialog dialog = new FeedAnimalDialog(CompetitionFrame.this);
                dialog.setVisible(true);
            }
        });
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        JButton animalInfoButton = new JButton("Animal Info");
        animalInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int columns = 8; // 7

                if (dataAnimal == null) {
                    dataAnimalArraySize = animalArrayList.size();
                    dataAnimal = new Object[dataAnimalArraySize][columns];

                    createAnimalInfoArrayFirstTime();
                    openInfoTable(dataAnimal);
                } else {
                    if (dataAnimalArraySize == animalArrayList.size() + deleted)
                        openInfoTable(dataAnimal);
                    else {
                        dataAnimal = increaseInfoTableArray(dataAnimalArraySize, animalArrayList.size() + deleted, columns);
                        updateInfoTableArray();
                        openInfoTable(dataAnimal);
                    }
                }
            }
        });
        JButton startTournamentButton = new JButton("Start Tournament");
        startTournamentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // StartTournamentDialog dialog = new StartTournamentDialog(CompetitionFrame.this, activeTournaments, allTournaments);
                // dialog.setVisible(true);
                new Thread(() -> {
                    StartTournamentDialog dialog = new StartTournamentDialog(CompetitionFrame.this, activeTournaments, allTournaments);
                    dialog.setVisible(true);
                }).start();
            }
        });
        JButton tournamentInfoButton = new JButton("Tournament Info");
        tournamentInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int columns = 5; // 4

                dataTournamentArraySize = getJTableTournamentInfoSize();
                dataTournament = new Object[dataTournamentArraySize][columns];

                createTournamentInfoArrayFirstTime();
                openTournamentInfo(dataTournament);

            }
        });
        buttonPanel.add(addCompetitionButton);
        buttonPanel.add(addAnimalButton);
        buttonPanel.add(addClearButton);
        buttonPanel.add(addEatButton);
        buttonPanel.add(startTournamentButton);
        buttonPanel.add(tournamentInfoButton);
        buttonPanel.add(animalInfoButton);
        buttonPanel.add(exitButton);

        // Add button panel to the frame
        add(buttonPanel, BorderLayout.SOUTH);

        // Pack the frame to fit the preferred size of its components
        //pack();

        // Disable window resizing
        setResizable(false);

        // Set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Opens a table displaying information about animals.
     *
     * @param data a 2D array containing the data to be displayed in the table
     */
    private void openInfoTable(Object[][] data) {
        if (!infoTableStatus) {
            infoTableStatus = true;
            // Column headers for the table
            String[] columns = {
                    //    NAME       TYPE    CLS NAME
                    "Animal", "Category", "Type", "Speed", "Energy Amount", "Distance", "Energy Consumption"
            };
            // Create the table with the data and column headers
            JTable table = new JTable(data, columns);

            // Create Scroll
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            // Center value
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            table.setDefaultRenderer(Object.class, centerRenderer);

            // Create a new frame to display the table
            JFrame tableFrame = new JFrame("Animal Info");
            tableFrame.setLayout(new BorderLayout());
            tableFrame.add(scrollPane);
            tableFrame.setSize(1400, 600);
            tableFrame.setLocationRelativeTo(null);
            tableFrame.setResizable(false);
            tableFrame.setVisible(true);

            tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            tableFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    infoTableStatus = false;
                }
            });
        }
    }

    /**
     * Creates the initial array of data for the information table.
     * Populates the table with information from the current list of animals.
     */
    public void createAnimalInfoArrayFirstTime() {
        for (int i = 0; i < animalArrayList.size(); ++i) {
            Animal animal = animalArrayList.get(i);
            updateAnimalInfoTableRow(animal, i);
        }
    }

    /**
     * Increases the size of the information table array.
     *
     * @param oldSizeRows the current number of rows in the array
     * @param newSizeRows the new number of rows in the array
     * @param columns     the number of columns in the array
     * @return a new 2D array with the increased size
     */
    public Object[][] increaseInfoTableArray(int oldSizeRows, int newSizeRows, int columns) {
        Object[][] newData = new Object[newSizeRows][columns];
        for (int i = 0; i < oldSizeRows; ++i) {
            System.arraycopy(dataAnimal[i], 0, newData[i], 0, dataAnimal[i].length);
        }
        dataAnimalArraySize = newSizeRows;
        return newData;
    }

    /**
     * Updates the information table array with the current list of animals.
     * Refreshes the data displayed in the table.
     */
    public void updateInfoTableArray() {
        for (Animal animal : animalArrayList) {
            boolean isOld = false;

            for (int j = 0; j < dataAnimalArraySize; ++j) {
                Object value = dataAnimal[j][7];
                if (value != null) {
                    if (value instanceof Integer uid) {
                        if (uid == animal.getUID()) {
                            dataAnimal[j][3] = animal.getSpeed();
                            dataAnimal[j][4] = animal.getEnergyAmount();
                            dataAnimal[j][5] = animal.getDistance();
                            dataAnimal[j][6] = animal.getConsumedEnergy();
                            dataAnimal[j][7] = animal.getUID();
                            isOld = true;
                            break;
                        }
                    }
                }
            }
            if (!isOld) {
                for (int k = 0; k < dataAnimalArraySize; ++k) {
                    Object value = dataAnimal[k][7];
                    if (value == null) {
                        updateAnimalInfoTableRow(animal, k);
                    }
                }
            }
        }
    }

    /**
     * Updates a specific row in the information table with data from a given animal.
     *
     * @param animal the animal whose data is to be updated
     * @param i      the row index in the table
     */
    public void updateAnimalInfoTableRow(Animal animal, int i) {
        dataAnimal[i][0] = animal.getAnimaleName();
        switch (animal) {
            case TerrestrialAnimals terrestrialAnimal -> {
                dataAnimal[i][1] = terrestrialAnimal.getClass().getSuperclass().getSimpleName();
                switch (terrestrialAnimal) {
                    case Cat cat -> dataAnimal[i][2] = cat.getClass().getSimpleName();
                    case Dog dog -> dataAnimal[i][2] = dog.getClass().getSimpleName();
                    case Snake snake -> dataAnimal[i][2] = snake.getClass().getSimpleName();
                    default -> {
                    }
                }
            }
            case AquaticAnimal aquaticAnimal -> {
                if (aquaticAnimal instanceof Alligator alligator) {
                    dataAnimal[i][1] = aquaticAnimal.getClass().getSuperclass().getSimpleName() + " & " + alligator.terrestrialSide.getClass().getSimpleName();
                    dataAnimal[i][2] = alligator.getClass().getSimpleName();
                } else {
                    dataAnimal[i][1] = aquaticAnimal.getClass().getSuperclass().getSimpleName();
                    if (aquaticAnimal instanceof Dolphin dolphin) {
                        dataAnimal[i][2] = dolphin.getClass().getSimpleName();
                    } else if (aquaticAnimal instanceof Whale whale) {
                        dataAnimal[i][2] = whale.getClass().getSimpleName();
                    }
                }
            }
            case AirAnimal airAnimal -> {
                dataAnimal[i][1] = airAnimal.getClass().getSimpleName();
                if (airAnimal instanceof Eagle eagle) {
                    dataAnimal[i][2] = eagle.getClass().getSimpleName();
                } else if (airAnimal instanceof Pigeon pigeon) {
                    dataAnimal[i][2] = pigeon.getClass().getSimpleName();
                }
            }
            default -> {
            }
        }
        dataAnimal[i][3] = animal.getSpeed();
        dataAnimal[i][4] = animal.getEnergyAmount();
        dataAnimal[i][5] = animal.getDistance();
        dataAnimal[i][6] = animal.getConsumedEnergy();
        dataAnimal[i][7] = animal.getUID();
    }

    /**
     * Represents a custom JPanel for drawing the background image and animals.
     */
    private class DrawingPanel extends JPanel {
        /**
         * Paints the component by drawing the background image and animals.
         *
         * @param g the Graphics object used for painting
         */
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Draw the background image
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                //System.out.println("W: " + backgroundImage.getWidth() + " H: " + backgroundImage.getHeight());
            }
            // Draw each animal
            for (Animal animal : animalArrayList) {
                animal.drawObject(g);
            }
        }
    }

    private void openTournamentInfo(Object[][] data) {
        if (!infoTournamentTableStatus) {
            infoTournamentTableStatus = true;
            // Column headers for the table
            String[] columns = {
                    //    NAME       TYPE    CLS NAME
                    "Area", "Tournament", "Animal name", "Date"
            };
            // Create the table with the data and column headers
            JTable table = new JTable(data, columns);

            // Create Scroll
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            // Center value
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            table.setDefaultRenderer(Object.class, centerRenderer);

            // Create a new frame to display the table
            JFrame tableFrame = new JFrame("Tournament Info");
            tableFrame.setLayout(new BorderLayout());
            tableFrame.add(scrollPane);
            tableFrame.setSize(1400, 600);
            tableFrame.setLocationRelativeTo(null);
            tableFrame.setResizable(false);
            tableFrame.setVisible(true);

            tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            tableFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    infoTournamentTableStatus = false;
                }
            });
        }
    }

    public void createTournamentInfoArrayFirstTime() {
        for (int i = 0, j = 0; i < allTournaments.size(); ++i) {
            Tournament tournament = allTournaments.get(i);
            switch (tournament) {
                case RegularTournament regularTournament -> {
                    ArrayList<Scores> scores = regularTournament.getThread().returnScores();
                    for (Scores score : scores) {
                        synchronized (score.getAll()) {
                            for (Map.Entry<String, Date> entry : score.getAll().entrySet()) {
                                dataTournament[j][0] = regularTournament.returnCompetitionArea();
                                dataTournament[j][1] = regularTournament.returnTournamentType();
                                dataTournament[j][2] = entry.getKey();
                                dataTournament[j][3] = entry.getValue();
                                ++j;
                            }
                        }
                    }
                }
                case CourierTournament courierTournament -> {
                    ArrayList<Scores> scores = courierTournament.getThread().returnScores();
                    for (Scores score : scores) {
                        synchronized (score.getAll()) {
                            for (Map.Entry<String, Date> entry : score.getAll().entrySet()) {
                                dataTournament[j][0] = courierTournament.returnCompetitionArea();
                                dataTournament[j][1] = courierTournament.returnTournamentType();
                                dataTournament[j][2] = entry.getKey();
                                dataTournament[j][3] = entry.getValue();
                                ++j;
                            }
                        }
                    }
                }
                default -> throw new IllegalStateException("Unexpected value: " + tournament);
            }

        }
    }

    public int getJTableTournamentInfoSize() {
        int count = 0;

        for (Tournament tournament : allTournaments) {
            switch (tournament) {
                case RegularTournament regularTournament -> {
                    ArrayList<Scores> scores = regularTournament.getThread().returnScores();
                    for (Scores score : scores) {
                        synchronized (score.getAll()) {
                            for (Map.Entry<String, Date> entry : score.getAll().entrySet()) {
                                ++count;
                            }
                        }
                    }
                }
                case CourierTournament courierTournament -> {
                    ArrayList<Scores> scores = courierTournament.getThread().returnScores();
                    for (Scores score : scores) {
                        synchronized (score.getAll()) {
                            for (Map.Entry<String, Date> entry : score.getAll().entrySet()) {
                                ++count;
                            }
                        }
                    }

                }
                default -> throw new IllegalStateException("Unexpected value: " + tournament);
            }
        }
        return count;
    }
}