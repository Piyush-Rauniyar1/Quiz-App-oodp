package gui;

import model.CompetitorList; // Changed from AuthManager
import model.PiyCompetitor;

import javax.swing.*;
import java.awt.*;

public class LoginGUI extends JFrame {

    private JTextField txtUser;
    private JPasswordField txtPass;

    public LoginGUI() {
        setTitle("Login");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Use GridBagLayout to keep components centered
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        // 1. User ID Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("User ID:"), gbc);

        // 2. User ID Text Field
        gbc.gridy = 1;
        txtUser = new JTextField(15);
        txtUser.setPreferredSize(new Dimension(200, 30));
        add(txtUser, gbc);

        // 3. Password Label
        gbc.gridy = 2;
        add(new JLabel("Password:"), gbc);

        // 4. Password Field
        gbc.gridy = 3;
        txtPass = new JPasswordField(15);
        txtPass.setPreferredSize(new Dimension(200, 30));
        add(txtPass, gbc);

        // 5. Login Button
        gbc.gridy = 4;
        gbc.insets = new Insets(20, 5, 5, 5);
        JButton btnLogin = new JButton("Login");
        btnLogin.setPreferredSize(new Dimension(150, 35));

        btnLogin.addActionListener(e -> {
            String idStr = txtUser.getText();
            String pass = new String(txtPass.getPassword());

            try {
                int id = Integer.parseInt(idStr);

                // --- REFACTORED: Use CompetitorList instead of AuthManager ---
                CompetitorList list = new CompetitorList();
                PiyCompetitor user = list.getCompetitor(id, pass); // Changed method to getCompetitor()

                if (user != null) {
                    dispose();
                    new PlayerDashboardGUI(user);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid ID or Password.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "User ID must be a number!");
            }
        });

        add(btnLogin, gbc);

        // 6. Register Button
        gbc.gridy = 5;
        gbc.insets = new Insets(5, 5, 5, 5);
        JButton btnRegister = new JButton("Create New Account");
        btnRegister.setPreferredSize(new Dimension(200, 35));
        btnRegister.addActionListener(e -> new RegisterGUI());
        add(btnRegister, gbc);

        setVisible(true);
    }
}