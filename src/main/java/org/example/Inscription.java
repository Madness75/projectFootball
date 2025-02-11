package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Inscription extends JPanel {

    // Champs de saisie pour l'inscription
    private JTextField emailField;
    private JTextField nomField;
    private JTextField prenomField;
    private JTextField equipeField;  // Champ pour le nom de l'équipe
    private JTextField stadeNomField;  // Champ pour le nom du stade
    private JTextField stadeAdresseField;  // Champ pour l'adresse du stade
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

        // Champ pour le nom de l'équipe
        gbc.gridy = 6;
        signupPanel.add(new JLabel("Nom de l'équipe"), gbc);

        gbc.gridy = 7;
        equipeField = new JTextField(15);
        signupPanel.add(equipeField, gbc);

        // Champ pour le nom du stade
        gbc.gridy = 8;
        signupPanel.add(new JLabel("Nom du stade"), gbc);

        gbc.gridy = 9;
        stadeNomField = new JTextField(15);
        signupPanel.add(stadeNomField, gbc);

        // Champ pour l'adresse du stade
        gbc.gridy = 10;
        signupPanel.add(new JLabel("Adresse du stade"), gbc);

        gbc.gridy = 11;
        stadeAdresseField = new JTextField(15);
        signupPanel.add(stadeAdresseField, gbc);

        // Label et champ pour le mot de passe
        gbc.gridy = 12;
        signupPanel.add(new JLabel("Mot de passe"), gbc);

        gbc.gridy = 13;
        passwordField = new JPasswordField(15);
        signupPanel.add(passwordField, gbc);

        // Bouton pour s'inscrire
        gbc.gridy = 14;
        JButton signupButton = new JButton("S'inscrire");
        signupButton.setBackground(new Color(50, 120, 220));
        signupButton.setForeground(Color.WHITE);
        signupPanel.add(signupButton, gbc);

        // Bouton pour revenir à la page de connexion
        gbc.gridy = 15;
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
                String equipeNom = equipeField.getText();  // Récupération du nom de l'équipe
                String stadeNom = stadeNomField.getText();  // Récupération du nom du stade
                String stadeAdresse = stadeAdresseField.getText();  // Récupération de l'adresse du stade
                String password = new String(passwordField.getPassword());

                // Vérifie que tous les champs sont remplis
                if (email.isEmpty() || nom.isEmpty() || prenom.isEmpty() || equipeNom.isEmpty() || stadeNom.isEmpty() || stadeAdresse.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(Inscription.this, "Tous les champs sont obligatoires.",
                            "Erreur d'inscription", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Appel du DAO pour enregistrer l'utilisateur et l'équipe avec le stade
                UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
                boolean success = utilisateurDAO.inscrireUtilisateurAvecEquipeEtStade(email, nom, prenom, password, equipeNom, stadeNom, stadeAdresse);

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
