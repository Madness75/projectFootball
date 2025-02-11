package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Inscription extends JPanel {

    // Champs de saisie pour l'inscription
    private JTextField emailField;
    private JTextField nomField;
    private JTextField prenomField;
    private JPasswordField passwordField;

    // Référence à la fenêtre principale Login pour pouvoir y revenir
    private Login loginFrame;

    /**
     * Constructeur qui reçoit la référence de la fenêtre principale.
     * @param loginFrame l'instance de Login
     */
    public Inscription(Login loginFrame) {
        this.loginFrame = loginFrame;
        setLayout(new BorderLayout());
        setBackground(new Color(220, 225, 230));

        // Création du panel contenant le formulaire d'inscription
        JPanel signupPanel = new JPanel(new GridBagLayout());
        signupPanel.setBorder(BorderFactory.createTitledBorder("Inscription"));
        signupPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        // Label et champ pour l'adresse e-mail
        gbc.gridy = 0;
        signupPanel.add(new JLabel("Adresse e-mail"), gbc);

        gbc.gridy = 1;
        emailField = new JTextField(15);
        signupPanel.add(emailField, gbc);

        // Label et champ pour le nom
        gbc.gridy = 2;
        signupPanel.add(new JLabel("Nom"), gbc);

        gbc.gridy = 3;
        nomField = new JTextField(15);
        signupPanel.add(nomField, gbc);

        // Label et champ pour le prénom
        gbc.gridy = 4;
        signupPanel.add(new JLabel("Prénom"), gbc);

        gbc.gridy = 5;
        prenomField = new JTextField(15);
        signupPanel.add(prenomField, gbc);

        // Label et champ pour le mot de passe
        gbc.gridy = 6;
        signupPanel.add(new JLabel("Mot de passe"), gbc);

        gbc.gridy = 7;
        passwordField = new JPasswordField(15);
        signupPanel.add(passwordField, gbc);

        // Bouton pour s'inscrire
        gbc.gridy = 8;
        JButton signupButton = new JButton("S'inscrire");
        signupButton.setBackground(new Color(50, 120, 220));
        signupButton.setForeground(Color.WHITE);
        signupPanel.add(signupButton, gbc);

        // Bouton pour revenir à la page de connexion
        gbc.gridy = 9;
        JButton backButton = new JButton("Retour");
        signupPanel.add(backButton, gbc);

        // Action lors du clic sur le bouton "S'inscrire"
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupération des données saisies
                String email = emailField.getText();
                String nom = nomField.getText();
                String prenom = prenomField.getText();
                String password = new String(passwordField.getPassword());

                // Vérifie que tous les champs sont remplis
                if (email.isEmpty() || nom.isEmpty() || prenom.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(Inscription.this, "Tous les champs sont obligatoires.",
                            "Erreur d'inscription", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Appel du DAO pour enregistrer l'utilisateur
                UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
                boolean success = utilisateurDAO.inscrireUtilisateur(email, nom, prenom, password);

                if (success) {
                    JOptionPane.showMessageDialog(Inscription.this, "Inscription réussie !");
                    // Retour à la page de connexion après inscription
                    loginFrame.showLoginPanel();
                } else {
                    JOptionPane.showMessageDialog(Inscription.this, "Erreur lors de l'inscription.",
                            "Erreur d'inscription", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action pour le bouton "Retour"
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retour à la page de connexion
                loginFrame.showLoginPanel();
            }
        });

        // Ajoute le panel d'inscription au centre de ce JPanel
        add(signupPanel, BorderLayout.CENTER);
    }
}
