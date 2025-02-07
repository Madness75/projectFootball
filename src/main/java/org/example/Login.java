package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame {
    public Login() {
        setTitle("Page de Connexion");
        setSize(800, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Couleur de fond de l'application
        Color backgroundColor = new Color(220, 225, 230);
        getContentPane().setBackground(backgroundColor);

        // Panel principal avec BoxLayout (pour aligner verticalement)
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(backgroundColor);
        add(mainPanel, BorderLayout.CENTER);

        // Panel contenant le formulaire
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setPreferredSize(new Dimension(250, 180));
        loginPanel.setBorder(BorderFactory.createTitledBorder("Connexion"));
        loginPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Label et champ email
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(new JLabel("Adresse e-mail"), gbc);

        gbc.gridy = 1;
        JTextField emailField = new JTextField(15);
        loginPanel.add(emailField, gbc);

        // Label et champ mot de passe
        gbc.gridy = 2;
        loginPanel.add(new JLabel("Mot de passe"), gbc);

        gbc.gridy = 3;
        JPasswordField passwordField = new JPasswordField(15);
        loginPanel.add(passwordField, gbc);

        // Bouton de validation
        gbc.gridy = 4;
        JButton loginButton = new JButton("Valider");
        loginButton.setBackground(new Color(50, 120, 220));
        loginButton.setForeground(Color.WHITE);
        loginPanel.add(loginButton, gbc);

        // Lien "Vous enregistrer ?"
        gbc.gridy = 5;
        JLabel registerLabel = new JLabel("<html><a href='#'>Vous enregistrer ?</a></html>");
        registerLabel.setForeground(Color.BLUE);
        registerLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginPanel.add(registerLabel, gbc);

        // Action lorsqu'on clique sur "Vous enregistrer ?"
        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(Login.this, "Redirection vers l'inscription...");
            }
        });

        // Ajout du formulaire centré
        mainPanel.add(loginPanel, BorderLayout.CENTER);

        // Ajout des icônes à gauche et à droite
        JLabel leftImage = new JLabel(new ImageIcon("image_placeholder.png"));
        JLabel rightImage = new JLabel(new ImageIcon("image_placeholder.png"));

        leftImage.setHorizontalAlignment(JLabel.CENTER);
        rightImage.setHorizontalAlignment(JLabel.CENTER);

        mainPanel.add(leftImage, BorderLayout.WEST);
        mainPanel.add(rightImage, BorderLayout.EAST);
    }

    public static void main(String[] args) {
        // Pour respecter la sécurité des threads Swing, on utilise invokeLater
        SwingUtilities.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }
}
