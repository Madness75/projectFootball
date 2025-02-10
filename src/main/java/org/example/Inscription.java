package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Inscription extends JFrame {

    private JTextField emailField;
    private JTextField nomField;
    private JTextField prenomField;
    private JPasswordField passwordField;

    public Inscription() {
        setTitle("Page d'Inscription");
        setSize(800, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Couleur de fond de l'application
        Color backgroundColor = new Color(220, 225, 230);
        getContentPane().setBackground(backgroundColor);

        // Panel principal avec BoxLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(backgroundColor);
        add(mainPanel, BorderLayout.CENTER);

        // Panel contenant le formulaire
        JPanel signupPanel = new JPanel();
        signupPanel.setLayout(new GridBagLayout());
        signupPanel.setPreferredSize(new Dimension(250, 200));
        signupPanel.setBorder(BorderFactory.createTitledBorder("Inscription"));
        signupPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Label et champ email
        gbc.gridx = 0;
        gbc.gridy = 0;
        signupPanel.add(new JLabel("Adresse e-mail"), gbc);

        gbc.gridy = 1;
        emailField = new JTextField(15);
        signupPanel.add(emailField, gbc);

        // Label et champ nom
        gbc.gridy = 2;
        signupPanel.add(new JLabel("Nom"), gbc);

        gbc.gridy = 3;
        nomField = new JTextField(15);
        signupPanel.add(nomField, gbc);

        // Label et champ prénom
        gbc.gridy = 4;
        signupPanel.add(new JLabel("Prénom"), gbc);

        gbc.gridy = 5;
        prenomField = new JTextField(15);
        signupPanel.add(prenomField, gbc);

        // Label et champ mot de passe
        gbc.gridy = 6;
        signupPanel.add(new JLabel("Mot de passe"), gbc);

        gbc.gridy = 7;
        passwordField = new JPasswordField(15);
        signupPanel.add(passwordField, gbc);

        // Bouton de validation
        gbc.gridy = 8;
        JButton signupButton = new JButton("S'inscrire");
        signupButton.setBackground(new Color(50, 120, 220));
        signupButton.setForeground(Color.WHITE);
        signupPanel.add(signupButton, gbc);

        // Action lorsqu'on clique sur "S'inscrire"
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupération des données saisies
                String email = emailField.getText();
                String nom = nomField.getText();
                String prenom = prenomField.getText();
                String password = new String(passwordField.getPassword());

                // Vérification de l'inscription
                if (email.isEmpty() || nom.isEmpty() || prenom.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(Inscription.this, "Tous les champs sont obligatoires.",
                            "Erreur d'inscription", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Création de l'instance UtilisateurDAO pour enregistrer l'utilisateur
                UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
                boolean isSuccess = utilisateurDAO.inscrireUtilisateur(email, nom, prenom, password);

                // Vérification du résultat
                if (isSuccess) {
                    JOptionPane.showMessageDialog(Inscription.this, "Inscription réussie !");
                    // Rediriger vers la page de connexion ou fermer cette fenêtre et ouvrir le login
                    // Exemple : new Login().setVisible(true);
                    // Inscription.this.dispose(); // Ferme la fenêtre d'inscription
                } else {
                    JOptionPane.showMessageDialog(Inscription.this, "Erreur lors de l'inscription. Vérifiez vos informations.",
                            "Erreur d'inscription", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ajout du formulaire centré
        mainPanel.add(signupPanel, BorderLayout.CENTER);

        // Ajout d'icônes à gauche et à droite (optionnel)
        JLabel leftImage = new JLabel(new ImageIcon("image_placeholder.png"));
        JLabel rightImage = new JLabel(new ImageIcon("image_placeholder.png"));
        leftImage.setHorizontalAlignment(JLabel.CENTER);
        rightImage.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(leftImage, BorderLayout.WEST);
        mainPanel.add(rightImage, BorderLayout.EAST);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Inscription().setVisible(true);
        });
    }
}
