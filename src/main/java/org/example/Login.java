package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame {

    // Champs de saisie pour la connexion
    private JTextField emailField;
    private JPasswordField passwordField;

    // Panel principal qui contiendra soit le formulaire de connexion, soit celui d'inscription
    private JPanel contentPanel;

    public Login() {
        setTitle("Page de Connexion");
        setSize(800, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Création du panel principal avec un BorderLayout
        contentPanel = new JPanel(new BorderLayout());
        setContentPane(contentPanel);

        // Affiche le formulaire de connexion dès le démarrage
        showLoginPanel();
    }

    /**
     * Affiche le formulaire de connexion.
     */
    public void showLoginPanel() {
        // Supprime le contenu actuel du panel principal
        contentPanel.removeAll();

        // Création du panel de connexion
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBorder(BorderFactory.createTitledBorder("Connexion"));
        loginPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;  // On reste sur la première colonne

        // Label et champ pour l'adresse e-mail
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

        // Bouton de validation de connexion
        gbc.gridy = 4;
        JButton loginButton = new JButton("Valider");
        loginButton.setBackground(new Color(50, 120, 220));
        loginButton.setForeground(Color.WHITE);
        loginPanel.add(loginButton, gbc);

        // Lien "Vous enregistrer ?" pour accéder au formulaire d'inscription
        gbc.gridy = 5;
        JLabel registerLabel = new JLabel("<html><a href='#'>Vous enregistrer ?</a></html>");
        registerLabel.setForeground(Color.BLUE);
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginPanel.add(registerLabel, gbc);

        // Action lors du clic sur le bouton "Valider" (exemple de vérification)
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupération des données saisies
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                // Vérification de la connexion à l'aide du DAO
                UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
                boolean isConnected = utilisateurDAO.checkLogin(email, password);

                if (isConnected) {
                    JOptionPane.showMessageDialog(Login.this, "Connexion réussie !");
                    // Vous pouvez ensuite ouvrir la fenêtre principale de l'application
                } else {
                    JOptionPane.showMessageDialog(Login.this, "Email ou mot de passe incorrect.",
                            "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action lors du clic sur le lien "Vous enregistrer ?"
        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Appelle la méthode pour afficher le panel d'inscription
                showInscriptionPanel();
            }
        });

        // Ajoute le panel de connexion dans le panel principal
        contentPanel.add(loginPanel, BorderLayout.CENTER);

        // Met à jour la fenêtre
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    /**
     * Affiche le formulaire d'inscription provenant de la classe Inscription.
     */
    public void showInscriptionPanel() {
        // Supprime le contenu actuel du panel principal
        contentPanel.removeAll();

        // Crée une instance du panel d'inscription en lui passant la référence de cette fenêtre
        Inscription inscriptionPanel = new Inscription(this);
        contentPanel.add(inscriptionPanel, BorderLayout.CENTER);

        // Met à jour la fenêtre
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public static void main(String[] args) {
        // Pour respecter la sécurité des threads Swing, on utilise invokeLater
        SwingUtilities.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }
}
