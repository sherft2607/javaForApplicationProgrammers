import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.util.List;
import java.util.Scanner;

/**
 * @author Shandon Herft
 */
public class DirectoryDriver {
    /**directory.*/
    private Directory directory;
    /**frame.*/
    private JFrame frame;
    /**first name.*/
    private JTextField firstNameField;
    /**last name.*/
    private JTextField lastNameField;
    /**andrew id.*/
    private JTextField andrewIdField;
    /**phone number.*/
    private JTextField phoneNumberField;
    /**display area.*/
    private JTextArea displayArea;

    public static void main(String[] args) {
        DirectoryDriver app = new DirectoryDriver();
        app.setupGUI();
        if (args.length > 0) {
            app.loadDataFromCSV(args[0]);
        }
        
    }

    public DirectoryDriver() {
        directory = new Directory();
    }

    private void setupGUI() {
        frame = new JFrame("Student Directory");
        frame.setSize(520, 420);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        firstNameField = createTextField(inputPanel, "First Name:");
        lastNameField = createTextField(inputPanel, "Last Name:");
        andrewIdField = createTextField(inputPanel, "Andrew ID:");
        phoneNumberField = createTextField(inputPanel, "Phone Number:");

        JPanel buttonPanel = new JPanel();
        createButton(buttonPanel, "Add Student", e -> addStudent());
        createButton(buttonPanel, "Search By Andrew ID", e -> searchByAndrewId());
        createButton(buttonPanel, "Search By First Name", e -> searchByFirstName());
        createButton(buttonPanel, "Search By Last Name", e -> searchByLastName());
        createButton(buttonPanel, "Delete", e -> deleteStudent());
        displayArea = new JTextArea(13, 40);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private JTextField createTextField(JPanel panel, String labelText) {
        panel.add(new JLabel(labelText));
        JTextField textField = new JTextField();
        panel.add(textField);
        return textField;
    }

    private void createButton(JPanel panel, String buttonText, ActionListener action) {
        JButton button = new JButton(buttonText);
        button.addActionListener(action);
        panel.add(button);
    }

    private void addStudent() {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String andrewId = andrewIdField.getText().trim();
        String phoneNumber = phoneNumberField.getText().trim();


        try {
            Student student = new Student(andrewId);
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setPhoneNumber(phoneNumber);
            directory.addStudent(student);

            clearInputFields();
            displayArea.append("Added: " + student.toString() + "\n");
        } catch (IllegalArgumentException ex) {
            displayError(ex.getMessage());
        }
    }

    private void searchByAndrewId() {
        String andrewId = andrewIdField.getText().trim();
        if (andrewId.isEmpty()) {
            displayError("Please enter an Andrew ID to search.");
            return;
        }

        Student student = directory.searchByAndrewId(andrewId);
        if (student == null) {
            displayArea.append("No matches found for Andrew ID: " + andrewId + "\n");
        } else {
            String phoneNumber = student.getPhoneNumber() != null ? student.getPhoneNumber() : "";
            displayArea.append(student.getFirstName() + " " + student.getLastName()
                    + " (Andrew ID: " + student.getAndrewId() + ", Phone Number: " + phoneNumber + ")\n");
            clearInputFields();
        }
    }

    private void searchByFirstName() {
        String firstName = firstNameField.getText().trim();
        if (firstName.isEmpty()) {
            displayError("Please enter a first name to search.");
            return;
        }

        List<Student> students = directory.searchByFirstName(firstName);
        displayResults(students, "first name", firstName);
    }

    private void searchByLastName() {
        String lastName = lastNameField.getText().trim();
        if (lastName.isEmpty()) {
            displayError("Please enter a last name to search.");
            return;
        }

        List<Student> students = directory.searchByLastName(lastName);
        displayResults(students, "last name", lastName);
    }

    private void displayResults(List<Student> students, String searchType, String searchKey) {
        if (students.isEmpty()) {
            displayArea.append("No matches found for " + searchType + ": " + searchKey + "\n");
        } else {
            StringBuilder result = new StringBuilder();
            for (Student student : students) {
                String phoneNumber = student.getPhoneNumber() != null ? student.getPhoneNumber() : "";
                result.append(student.getFirstName()).append(" ").append(student.getLastName())
                      .append(" (Andrew ID: ").append(student.getAndrewId())
                      .append(", Phone Number: ").append(phoneNumber).append(")\n");
            }
            displayArea.append(result.toString());
            clearInputFields();
        }
    }

    private void deleteStudent() {
        String andrewId = andrewIdField.getText().trim();
        if (andrewId.isEmpty()) {
            displayError("Please enter an Andrew ID to delete.");
            return;
        }

        try {
            Student student = directory.searchByAndrewId(andrewId);
            if (student == null) {
                displayArea.append("No matches found for Andrew ID: " + andrewId + "\n");
            } else {
                directory.deleteStudent(andrewId);
                String phoneNumber = student.getPhoneNumber() != null ? student.getPhoneNumber() : "";
                displayArea.append("Deleted: " + student.getFirstName() + " " + student.getLastName()
                        + " (Andrew ID: " + student.getAndrewId() + ", Phone Number: " + phoneNumber + ")\n");
                clearInputFields();
            }
        } catch (IllegalArgumentException ex) {
            displayError(ex.getMessage());
        }
    }

    private void clearInputFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        andrewIdField.setText("");
        phoneNumberField.setText("");
    }

    private void displayError(String message) {
        displayArea.setText("Error: " + message);
    }

    private void loadDataFromCSV(String csvFilePath) {
        try (Scanner scanner = new Scanner(new File(csvFilePath))) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.replace("\"", "").split(",");

                Student student = new Student(values[2]);
                student.setFirstName(values[0]);
                student.setLastName(values[1]);
                if (values.length > 3) {
                    student.setPhoneNumber(values[3]);
                }
                directory.addStudent(student);
            }
            displayArea.append("Data loaded successfully from CSV.\n");
        } catch (FileNotFoundException e) {
            displayError("File not found: " + csvFilePath);
        }
    }
}
