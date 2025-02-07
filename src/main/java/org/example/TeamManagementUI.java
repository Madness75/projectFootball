package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeamManagementUI extends JFrame {
    public TeamManagementUI() {
        setTitle("Gestion de l'équipe");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Couleur de fond générale
        Color backgroundColor = new Color(220, 225, 230);
        getContentPane().setBackground(backgroundColor);

        // Panel principal avec un layout BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(backgroundColor);
        add(mainPanel, BorderLayout.CENTER);

        // ======= Nom de l'équipe =======
        JPanel teamNamePanel = new JPanel();
        teamNamePanel.setBackground(backgroundColor);
        JLabel teamNameLabel = new JLabel("Nom de l'équipe ");
        teamNameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        JButton editTeamNameButton = new JButton("✎");
        editTeamNameButton.setFocusPainted(false);
        editTeamNameButton.addActionListener(e -> {
            String newName = JOptionPane.showInputDialog("Modifier le nom de l'équipe :");
            if (newName != null && !newName.trim().isEmpty()) {
                teamNameLabel.setText(newName);
            }
        });

        teamNamePanel.add(teamNameLabel);
        teamNamePanel.add(editTeamNameButton);
        mainPanel.add(teamNamePanel, BorderLayout.NORTH);

        // ======= Conteneur pour Liste des joueurs et Postes =======
        JPanel listContainer = new JPanel(new GridLayout(1, 2, 20, 0));
        listContainer.setBackground(backgroundColor);
        mainPanel.add(listContainer, BorderLayout.CENTER);

        // ======= Liste des joueurs =======
        JPanel playersPanel = new JPanel();
        playersPanel.setLayout(new BoxLayout(playersPanel, BoxLayout.Y_AXIS));
        playersPanel.setBorder(BorderFactory.createTitledBorder("Liste des joueurs"));
        playersPanel.setBackground(Color.WHITE);

        for (int i = 0; i < 10; i++) {
            playersPanel.add(createPlayerRow("NOM Prénom"));
        }

        listContainer.add(playersPanel);

        // ======= Liste des postes =======
        JPanel positionsPanel = new JPanel();
        positionsPanel.setLayout(new BoxLayout(positionsPanel, BoxLayout.Y_AXIS));
        positionsPanel.setBorder(BorderFactory.createTitledBorder("Postes"));
        positionsPanel.setBackground(Color.WHITE);

        String[] postes = {
                "Gardien", "Arrière latéral gauche", "Défenseur central", "Arrière latéral droit",
                "Milieu défensif", "Milieu central", "Milieu offensif", "Ailier gauche",
                "Attaquant de pointe", "Ailier droit", "Second attaquant"
        };

        for (String poste : postes) {
            positionsPanel.add(createPositionRow("NOM Prénom", poste));
        }

        listContainer.add(positionsPanel);
    }

    // ======= Création d'une ligne pour la liste des joueurs =======
    private JPanel createPlayerRow(String name) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row.setBackground(Color.LIGHT_GRAY);

        JLabel nameLabel = new JLabel(name);
        JButton editButton = new JButton("✎");
        JButton deleteButton = new JButton("✖");

        editButton.setFocusPainted(false);
        deleteButton.setFocusPainted(false);

        editButton.addActionListener(e -> {
            String newName = JOptionPane.showInputDialog("Modifier le nom du joueur :", nameLabel.getText());
            if (newName != null && !newName.trim().isEmpty()) {
                nameLabel.setText(newName);
            }
        });

        deleteButton.addActionListener(e -> row.setVisible(false));

        row.add(nameLabel);
        row.add(editButton);
        row.add(deleteButton);
        return row;
    }

    // ======= Création d'une ligne pour l'affectation des postes =======
    private JPanel createPositionRow(String name, String position) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row.setBackground(Color.LIGHT_GRAY);

        JLabel nameLabel = new JLabel(name);
        JButton editButton = new JButton("✎");
        JButton deleteButton = new JButton("✖");
        JLabel positionLabel = new JLabel(position);

        editButton.setFocusPainted(false);
        deleteButton.setFocusPainted(false);

        editButton.addActionListener(e -> {
            String newName = JOptionPane.showInputDialog("Modifier le nom du joueur :", nameLabel.getText());
            if (newName != null && !newName.trim().isEmpty()) {
                nameLabel.setText(newName);
            }
        });

        deleteButton.addActionListener(e -> row.setVisible(false));

        row.add(nameLabel);
        row.add(editButton);
        row.add(deleteButton);
        row.add(positionLabel);
        return row;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TeamManagementUI().setVisible(true));
    }
}
