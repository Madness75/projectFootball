package org.example;

import javax.swing.*;
import java.awt.*;

public class MainUI extends JFrame {
    private JTabbedPane tabbedPane;
    private TeamManagementUI teamManagementPanel;
    private MatchSchedulerUI matchSchedulerPanel;

    public MainUI(String userEmail) {
        setTitle("Gestion d'équipe et Planification des matchs");
        setSize(850, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Créer le JTabbedPane pour les onglets
        tabbedPane = new JTabbedPane();

        // Créer les panels pour chaque section
        teamManagementPanel = new TeamManagementUI();
        matchSchedulerPanel = new MatchSchedulerUI(userEmail);

        // Ajouter les panels aux onglets
        tabbedPane.addTab("Gestion de l'équipe", teamManagementPanel);
        tabbedPane.addTab("Planification des matchs", matchSchedulerPanel);

        // Ajouter le JTabbedPane à la fenêtre principale
        getContentPane().add(tabbedPane);

        setVisible(true);
    }

    public static void main(String[] args) {
        String userEmail = "user@example.com"; // Adresse e-mail simulée de l'utilisateur
        SwingUtilities.invokeLater(() -> new MainUI(userEmail).setVisible(true));
    }
}
