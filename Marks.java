import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Marks extends JFrame implements ActionListener {

    private JTextField admissionIdField, teluguField, hindiField, englishField,
            mathsField, physicsField, chemistryField, socialField, totalField;
    private JButton submitButton, backButton, nextButton;

    public Marks() {
        setTitle("Marks");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null); // center the window
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.anchor = GridBagConstraints.WEST;

        int row = 0;

        // Admission ID
        panel.add(new JLabel("Admission ID:"), gbcAt(0, row, gbc));
        admissionIdField = new JTextField(15);
        addDigitOnlyValidation(admissionIdField);
        panel.add(admissionIdField, gbcAt(1, row++, gbc));

        // Subjects
        teluguField = addSubjectField(panel, "Telugu:", row++, gbc);
        hindiField = addSubjectField(panel, "Hindi:", row++, gbc);
        englishField = addSubjectField(panel, "English:", row++, gbc);
        mathsField = addSubjectField(panel, "Maths:", row++, gbc);
        physicsField = addSubjectField(panel, "Physics:", row++, gbc);
        chemistryField = addSubjectField(panel, "Chemistry:", row++, gbc);
        socialField = addSubjectField(panel, "Social:", row++, gbc);

        // Total field (non-editable)
        panel.add(new JLabel("Total:"), gbcAt(0, row, gbc));
        totalField = new JTextField(15);
        totalField.setEditable(false);
        panel.add(totalField, gbcAt(1, row++, gbc));

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
            new AdmissionDetails().setVisible(true);
        });
        nextButton.addActionListener(e -> {
            dispose();
            new Attendence().setVisible(true);
        });

        gbc = gbcAt(0, row, gbc);
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(navPanel, gbc);

        // Add auto total calculator
        addAutoTotalUpdater();

        setContentPane(panel);
    }

    // Method to add subject fields
    private JTextField addSubjectField(JPanel panel, String label, int row, GridBagConstraints gbc) {
        panel.add(new JLabel(label), gbcAt(0, row, gbc));
        JTextField field = new JTextField(15);
        addDigitOnlyValidation(field);
        panel.add(field, gbcAt(1, row, gbc));
        return field;
    }

    // Restrict input to digits only
    private void addDigitOnlyValidation(JTextField field) {
        field.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    e.consume(); // ignore non-digit input
                }
            }
        });
    }

    // Auto update total when marks are changed
    private void addAutoTotalUpdater() {
        DocumentListener listener = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { updateTotal(); }
            public void removeUpdate(DocumentEvent e) { updateTotal(); }
            public void changedUpdate(DocumentEvent e) { updateTotal(); }
        };

        teluguField.getDocument().addDocumentListener(listener);
        hindiField.getDocument().addDocumentListener(listener);
        englishField.getDocument().addDocumentListener(listener);
        mathsField.getDocument().addDocumentListener(listener);
        physicsField.getDocument().addDocumentListener(listener);
        chemistryField.getDocument().addDocumentListener(listener);
        socialField.getDocument().addDocumentListener(listener);
    }

    private void updateTotal() {
        try {
            int total = 0;
            total += parseField(teluguField);
            total += parseField(hindiField);
            total += parseField(englishField);
            total += parseField(mathsField);
            total += parseField(physicsField);
            total += parseField(chemistryField);
            total += parseField(socialField);

            totalField.setText(String.valueOf(total));
        } catch (NumberFormatException e) {
            totalField.setText("");
        }
    }

    private int parseField(JTextField field) {
        String text = field.getText().trim();
        return text.isEmpty() ? 0 : Integer.parseInt(text);
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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String admissionId = admissionIdField.getText();
            String telugu = teluguField.getText();
            String hindi = hindiField.getText();
            String english = englishField.getText();
            String maths = mathsField.getText();
            String physics = physicsField.getText();
            String chemistry = chemistryField.getText();
            String social = socialField.getText();
            String total = totalField.getText();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("marks.txt", true))) {
                writer.write(admissionId + "," + telugu + "," + hindi + "," + english + "," +
                             maths + "," + physics + "," + chemistry + "," + social + "," + total);
                writer.newLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            JOptionPane.showMessageDialog(this, "Marks Submitted!");
        }
    }

    public static void main(String[] args) {
        new Marks().setVisible(true);
    }
}
