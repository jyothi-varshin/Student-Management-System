import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Attendence extends JFrame implements ActionListener {

    private JTextField admissionIdField;
    private JComboBox<String> classBox, sectionBox, rollBox;
    private JRadioButton present, absent;
    private JButton submitButton, backButton, nextButton;
    private ButtonGroup attendanceGroup;

    public Attendence() {
        setTitle("Attendance");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 400);
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

        // Class
        panel.add(new JLabel("Class:"), gbcAt(0, row, gbc));
        String[] classOptions = {"I","II","III","IV","V","VI","VII","VIII","IX","X"};
        classBox = new JComboBox<>(classOptions);
        panel.add(classBox, gbcAt(1, row++, gbc));

        // Section
        panel.add(new JLabel("Section:"), gbcAt(0, row, gbc));
        String[] sectionOptions = {"A","B","C","D"};
        sectionBox = new JComboBox<>(sectionOptions);
        panel.add(sectionBox, gbcAt(1, row++, gbc));

        // Roll No
        panel.add(new JLabel("Roll No:"), gbcAt(0, row, gbc));
        String[] rollOptions = new String[20];
        for (int i = 0; i < 20; i++) rollOptions[i] = String.valueOf(i + 1);
        rollBox = new JComboBox<>(rollOptions);
        panel.add(rollBox, gbcAt(1, row++, gbc));

        // Attendance (Radio Buttons)
        panel.add(new JLabel("Attendance:"), gbcAt(0, row, gbc));
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        present = new JRadioButton("Present");
        absent = new JRadioButton("Absent");
        attendanceGroup = new ButtonGroup();
        attendanceGroup.add(present);
        attendanceGroup.add(absent);
        radioPanel.add(present);
        radioPanel.add(absent);
        panel.add(radioPanel, gbcAt(1, row++, gbc));

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
            new Marks().setVisible(true);
        });

        nextButton.addActionListener(e -> {
            dispose();
            new StudentDetails().setVisible(true);
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
            String admissionId = admissionIdField.getText().trim();
            String selectedClass = (String) classBox.getSelectedItem();
            String selectedSection = (String) sectionBox.getSelectedItem();
            String selectedRollNo = (String) rollBox.getSelectedItem();
            String attendance = present.isSelected() ? "Present" : (absent.isSelected() ? "Absent" : "Not Selected");

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("attendance.txt", true))) {
                writer.write(admissionId + "," + selectedClass + "," + selectedSection + "," +
                             selectedRollNo + "," + attendance);
                writer.newLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            JOptionPane.showMessageDialog(this, "Attendance Recorded!");
            clearFields();
        }
    }

    private void clearFields() {
        admissionIdField.setText("");
        attendanceGroup.clearSelection();
    }

    public static void main(String[] args) {
        new Attendence().setVisible(true);
    }
}