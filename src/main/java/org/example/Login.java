package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*; // Bien que non utilisé directement ici, il est utile pour la connexion dans le DAO

public class Login extends JFrame {

    // On déclare ici les champs de saisie pour pouvoir y accéder dans l'action du bouton
    private JTextField emailField;
    private JPasswordField passwordField;

    public Login() {
        setTitle("Page de Connexion");
        setSize(800, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Couleur de fond de l'application
        Color backgroundColor = new Color(220, 225, 230);
        getContentPane().setBackground(backgroundColor);

        // Panel principal pour l'organisation des composants
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(backgroundColor);
        add(mainPanel, BorderLayout.CENTER);

        // Panel contenant le formulaire de connexion
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setPreferredSize(new Dimension(250, 180));
        loginPanel.setBorder(BorderFactory.createTitledBorder("Connexion"));
        loginPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Label et champ pour l'adresse e-mail
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(new JLabel("Adresse e-mail"), gbc);

        gbc.gridy = 1;
        emailField = new JTextField(15);
        loginPanel.add(emailField, gbc);

        // Label et champ pour le mot de passe
        gbc.gridy = 2;
        loginPanel.add(new JLabel("Mot de passe"), gbc);

        gbc.gridy = 3;
        passwordField = new JPasswordField(15);
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
                // Vous pouvez ici ouvrir une autre fenêtre d'inscription
            }
        });

        // Ajout du formulaire centré dans le panel principal
        mainPanel.add(loginPanel, BorderLayout.CENTER);

        // Ajout d'icônes à gauche et à droite (les images sont des placeholders)
        JLabel leftImage = new JLabel(new ImageIcon("image_placeholder.png"));
        JLabel rightImage = new JLabel(new ImageIcon("image_placeholder.png"));
        leftImage.setHorizontalAlignment(JLabel.CENTER);
        rightImage.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(leftImage, BorderLayout.WEST);
        mainPanel.add(rightImage, BorderLayout.EAST);

        // Ajout de l'action sur le bouton de validation pour vérifier les identifiants
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupération des données saisies
                String email = emailField.getText();
                // Convertir le tableau de char en String pour le mot de passe
                String password = new String(passwordField.getPassword());

                // Création d'une instance du DAO pour vérifier les identifiants
                UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
                boolean isConnected = utilisateurDAO.checkLogin(email, password);

                // Vérification du résultat
                if (isConnected) {
                    JOptionPane.showMessageDialog(Login.this, "Connexion réussie !");
                    // Vous pouvez ici ouvrir la fenêtre principale de l'application
                    // Exemple : new MainFrame().setVisible(true);
                    // Puis fermer la fenêtre de connexion : Login.this.dispose();
                } else {
                    JOptionPane.showMessageDialog(Login.this, "Email ou mot de passe incorrect.",
                            "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        // Pour respecter la sécurité des threads Swing, on utilise invokeLater
        SwingUtilities.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }
}
