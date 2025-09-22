import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AdmissionDetails extends JFrame implements ActionListener {

    private JTextField admissionIdField, studentNameField, motherNameField, fatherNameField, dobField, phoneField, addressField;
    private JButton submitButton, backButton, nextButton;

    public AdmissionDetails() {
        setTitle("Admission Details");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.anchor = GridBagConstraints.WEST;

        int row = 0;

        // Admission ID
        panel.add(new JLabel("Admission ID:"), gbcAt(0, row, gbc));
        admissionIdField = new JTextField(15);
        panel.add(admissionIdField, gbcAt(1, row++, gbc));

        // Student Name
        panel.add(new JLabel("Student Name:"), gbcAt(0, row, gbc));
        studentNameField = new JTextField(15);
        panel.add(studentNameField, gbcAt(1, row++, gbc));

        // Mother Name
        panel.add(new JLabel("Mother Name:"), gbcAt(0, row, gbc));
        motherNameField = new JTextField(15);
        panel.add(motherNameField, gbcAt(1, row++, gbc));

        // Father Name
        panel.add(new JLabel("Father Name:"), gbcAt(0, row, gbc));
        fatherNameField = new JTextField(15);
        panel.add(fatherNameField, gbcAt(1, row++, gbc));

        // DOB
        panel.add(new JLabel("Date of Birth (DD/MM/YYYY):"), gbcAt(0, row, gbc));
        dobField = new JTextField(15);
        panel.add(dobField, gbcAt(1, row++, gbc));

        // Phone
        panel.add(new JLabel("Phone:"), gbcAt(0, row, gbc));
        phoneField = new JTextField(15);
        panel.add(phoneField, gbcAt(1, row++, gbc));

        // Address
        panel.add(new JLabel("Address:"), gbcAt(0, row, gbc));
        addressField = new JTextField(15);
        panel.add(addressField, gbcAt(1, row++, gbc));

        // Submit button
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        gbc = gbcAt(1, row++, gbc);
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(submitButton, gbc);

        // Navigation buttons
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        backButton = new JButton("Back");
        nextButton = new JButton("Next");
        navPanel.add(backButton);
        navPanel.add(nextButton);

        backButton.addActionListener(e -> {
            dispose();
            new StudentDetails().setVisible(true);
        });

        nextButton.addActionListener(e -> {
            dispose();
            new Marks().setVisible(true);
        });

        gbc = gbcAt(0, row, gbc);
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(navPanel, gbc);

        setContentPane(panel);
    }

    private GridBagConstraints gbcAt(int x, int y, GridBagConstraints base) {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = base.insets;
        c.anchor = base.anchor;
        c.gridx = x;
        c.gridy = y;
        c.fill = (x == 1 ? GridBagConstraints.HORIZONTAL : GridBagConstraints.NONE);
        c.weightx = (x == 1 ? 1.0 : 0.0);
        return c;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String admissionId = admissionIdField.getText();
            String studentName = studentNameField.getText();
            String motherName = motherNameField.getText();
            String fatherName = fatherNameField.getText();
            String dob = dobField.getText();
            String phone = phoneField.getText();
            String address = addressField.getText();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt", true))) {
                writer.write(admissionId + "," + studentName + "," + motherName + "," +
                             fatherName + "," + dob + "," + phone + "," + address);
                writer.newLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            JOptionPane.showMessageDialog(this, "Admission Details Submitted!");
            clearFields();
        }
    }

    private void clearFields() {
        admissionIdField.setText("");
        studentNameField.setText("");
        motherNameField.setText("");
        fatherNameField.setText("");
        dobField.setText("");
        phoneField.setText("");
        addressField.setText("");
    }

    public static void main(String[] args) {
        new AdmissionDetails().setVisible(true);
    }
}