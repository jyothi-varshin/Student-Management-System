import javax.swing.*;
import java.awt.*;

public class StudentDetails extends JFrame {

    private JButton admissionButton, marksButton, attendanceButton, exitButton;

    public StudentDetails() {
        setTitle("Student Management System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 10, 15, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        int row = 0;

        JLabel title = new JLabel("Student Management System");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        gbc = gbcAt(0, row++, gbc);
        panel.add(title, gbc);

        // Admission Details Button
        admissionButton = new JButton("Admission Details");
        admissionButton.addActionListener(e -> {
            dispose();
            new AdmissionDetails().setVisible(true);
        });
        gbc = gbcAt(0, row++, gbc);
        panel.add(admissionButton, gbc);

        // Marks Button
        marksButton = new JButton("Marks");
        marksButton.addActionListener(e -> {
            dispose();
            new Marks().setVisible(true);
        });
        gbc = gbcAt(0, row++, gbc);
        panel.add(marksButton, gbc);

        // Attendance Button
        attendanceButton = new JButton("Attendance");
        attendanceButton.addActionListener(e -> {
            dispose();
            new Attendence().setVisible(true);
        });
        gbc = gbcAt(0, row++, gbc);
        panel.add(attendanceButton, gbc);

        // Exit Button
        exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        gbc = gbcAt(0, row++, gbc);
        panel.add(exitButton, gbc);

        setContentPane(panel);
    }

    private GridBagConstraints gbcAt(int x, int y, GridBagConstraints base) {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = base.insets;
        c.anchor = base.anchor;
        c.gridx = x;
        c.gridy = y;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        return c;
    }

    public static void main(String[] args) {
        new StudentDetails().setVisible(true);
    }
}