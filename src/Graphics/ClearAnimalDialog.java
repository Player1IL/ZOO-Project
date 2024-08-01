/**
 * Author: Daniel Nekludov
 * ID: 321984619
 * <p>
 * Class ClearAnimalDialog
 */
package Graphics;

import Animals.Base.Animal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static System.Sys.animalArrayList;
/**
 * Represents a dialog for clearing an animal from the list.
 * This dialog allows the user to select an animal from a dropdown list and remove it from the list.
 */
public class ClearAnimalDialog extends JDialog {
    private JComboBox<Animal> animalDropdown;
    private JButton clearButton;
    /**
     * Constructs a new ClearAnimalDialog.
     *
     * @param parent the parent frame of the dialog
     */
    public ClearAnimalDialog(JFrame parent) {
        super(parent, "Clear Animal", true);

        // Initialize components
        animalDropdown = new JComboBox<>(animalArrayList.toArray(new Animal[0]));
        clearButton = new JButton("Clear");

        // Layout
        setLayout(new BorderLayout());
        add(animalDropdown, BorderLayout.CENTER);
        add(clearButton, BorderLayout.SOUTH);

        // Button listener
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAnimal();
            }
        });

        // Dialog settings
        setSize(800, 100);
        setResizable(false);
        setLocationRelativeTo(parent);
    }
    /**
     * Removes the selected animal from the animal list and updates the dropdown.
     * This method is called when the "Clear" button is clicked.
     */
    private void clearAnimal() {
        Animal selectedAnimal = (Animal) animalDropdown.getSelectedItem();
        if (selectedAnimal != null) {
            animalArrayList.remove(selectedAnimal);
            animalDropdown.removeItem(selectedAnimal);
            dispose();
        }
    }
}
