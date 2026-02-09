package gui;

import model.CompetitorList; // Changed from AuthManager
import javax.swing.*;
import java.awt.*;

public class RegisterGUI extends JFrame {
    private JTextField txtFirst, txtMiddle, txtLast, txtCountry;
    private JPasswordField txtPass;

    public RegisterGUI() {
        setTitle("Create Account");
        setSize(400, 500);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        // 1. First Name
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("First Name:"), gbc);

        gbc.gridy = 1;
        txtFirst = new JTextField(15);
        txtFirst.setPreferredSize(new Dimension(200, 30));
        add(txtFirst, gbc);

        // 2. Middle Name
        gbc.gridy = 2;
        add(new JLabel("Middle Name (Optional):"), gbc);

        gbc.gridy = 3;
        txtMiddle = new JTextField(15);
        txtMiddle.setPreferredSize(new Dimension(200, 30));
        add(txtMiddle, gbc);

        // 3. Last Name
        gbc.gridy = 4;
        add(new JLabel("Last Name:"), gbc);

        gbc.gridy = 5;
        txtLast = new JTextField(15);
        txtLast.setPreferredSize(new Dimension(200, 30));
        add(txtLast, gbc);

        // 4. Country
        gbc.gridy = 6;
        add(new JLabel("Country:"), gbc);

        gbc.gridy = 7;
        txtCountry = new JTextField(15);
        txtCountry.setPreferredSize(new Dimension(200, 30));
        add(txtCountry, gbc);

        // 5. Password
        gbc.gridy = 8;
        add(new JLabel("Password:"), gbc);

        gbc.gridy = 9;
        txtPass = new JPasswordField(15);
        txtPass.setPreferredSize(new Dimension(200, 30));
        add(txtPass, gbc);

        // 6. Save Button
        gbc.gridy = 10;
        gbc.insets = new Insets(20, 5, 5, 5);
        JButton btnSave = new JButton("Create Account");
        btnSave.setPreferredSize(new Dimension(150, 35));

        btnSave.addActionListener(e -> {
            String f = txtFirst.getText().trim();
            String m = txtMiddle.getText().trim();
            String l = txtLast.getText().trim();
            String c = txtCountry.getText().trim();
            String p = new String(txtPass.getPassword());

            if(f.isEmpty() || p.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill required fields (First Name, Password).");
                return;
            }

            String combinedFirstName = m.isEmpty() ? f : f + " " + m;

            // --- REFACTORED: Use CompetitorList ---
            CompetitorList list = new CompetitorList();
            int newId = list.addCompetitor(combinedFirstName, l, c, p); // Changed to addCompetitor()

            if (newId != -1) {
                JOptionPane.showMessageDialog(this,
                        "Account Created!\n\nIMPORTANT: Your User ID is: " + newId +
                                "\n\nPlease login with this ID.");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error creating account.");
            }
        });

        add(btnSave, gbc);
        setVisible(true);
    }
}