package org.example;

import Model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeamManagementUI extends JFrame {

    private JPanel editPanel, listPanel;
    private JTextField nameField, firstNameField, numField;
    private JComboBox<String> positionComboBox;
    private JCheckBox attCheckBox, defCheckBox, milCheckBox, gkCheckBox;
    private JButton submitButton, modifyButton, deleteButton;
    private JList<String> list;
    private DefaultListModel<String> listModel;
    private Joueur selectedJoueur;  // Pour garder une référence au joueur sélectionné

    public TeamManagementUI() {
        setTitle("Team Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Créer un JSplitPane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, createFormPanel(), createListPanel());
        splitPane.setDividerLocation(300);
        getContentPane().add(splitPane);

        setVisible(true);
    }

    private JPanel createFormPanel() {
        editPanel = new JPanel();
        editPanel.setLayout(new GridLayout(7, 2));  // 7 lignes pour ajouter les boutons

        nameField = new JTextField();
        firstNameField = new JTextField();
        numField = new JTextField();

        // ComboBox pour sélectionner le poste
        positionComboBox = new JComboBox<>(new String[]{"ATT", "DEF", "MIL", "GK"});
        positionComboBox.setSelectedIndex(-1); // Aucun poste sélectionné par défaut

        // Cases à cocher pour les postes
        attCheckBox = new JCheckBox("ATT");
        defCheckBox = new JCheckBox("DEF");
        milCheckBox = new JCheckBox("MIL");
        gkCheckBox = new JCheckBox("GK");

        // Réduire la taille des éléments
        nameField.setPreferredSize(new Dimension(150, 20));
        firstNameField.setPreferredSize(new Dimension(150, 20));
        numField.setPreferredSize(new Dimension(60, 20));

        attCheckBox.setPreferredSize(new Dimension(60, 20));
        defCheckBox.setPreferredSize(new Dimension(60, 20));
        milCheckBox.setPreferredSize(new Dimension(60, 20));
        gkCheckBox.setPreferredSize(new Dimension(60, 20));

        submitButton = new JButton("Sauvegarder");
        submitButton.setPreferredSize(new Dimension(120, 30));

        modifyButton = new JButton("Modifier");
        modifyButton.setPreferredSize(new Dimension(120, 30));

        deleteButton = new JButton("Supprimer");
        deleteButton.setPreferredSize(new Dimension(120, 30));

        positionComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Vérifier si un poste est sélectionné
                if (positionComboBox.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(editPanel, "Veuillez sélectionner un poste.");
                }
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String posteSelectionne = (String) positionComboBox.getSelectedItem();

                if (posteSelectionne != null) {
                    Poste poste = null;
                    switch (posteSelectionne) {
                        case "ATT":
                            poste = new Poste(4);
                            break;
                        case "DEF":
                            poste = new Poste(2);
                            break;
                        case "MIL":
                            poste = new Poste(3);
                            break;
                        case "GK":
                            poste = new Poste(1);
                            break;
                    }

                    //int userId = ConnexionBDD.getUserId() ; // Récupère l'ID utilisateur connecté
                    //ConnexionBDD.setUserIdEquipe(userId);

                    int userIdEquipe = ConnexionBDD.getIdEquipeUtilisateur();
                    Joueur joueur = new Joueur(
                            nameField.getText(),
                            firstNameField.getText(),
                            Integer.parseInt(numField.getText()),
                            poste,
                            userIdEquipe);

                    ConnexionBDD.ajouterJoueur(joueur);
                    updateListModel();  // Met à jour la liste après ajout
                    JOptionPane.showMessageDialog(editPanel, "Joueur sauvegardé !");
                } else {
                    JOptionPane.showMessageDialog(editPanel, "Veuillez sélectionner un poste.");
                }
            }
        });


        // Bouton modifier
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedJoueur != null) {
                    // Mettre à jour les champs avec les données du joueur sélectionné
                    nameField.setText(selectedJoueur.getNom());
                    firstNameField.setText(selectedJoueur.getPrenom());
                    numField.setText(selectedJoueur.getNumMaillot() != null ? String.valueOf(selectedJoueur.getNumMaillot()) : "");
                    positionComboBox.setSelectedItem(selectedJoueur.getPoste().getPos());
                }
            }
        });

        // Bouton supprimer
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedJoueur = list.getSelectedValue();

                if (selectedJoueur != null) {
                    // Extraire les informations du joueur sélectionné
                    String[] playerInfo = selectedJoueur.split(" ");

                    if (playerInfo.length == 3) {
                        String nom = playerInfo[0];
                        String prenom = playerInfo[1];
                        Integer numMaillot = Integer.parseInt(playerInfo[2]);

                        // Supprimer le joueur avec ces informations
                        ConnexionBDD.supprimerJoueur(nom, prenom, numMaillot);

                        // Mettre à jour la liste
                        updateListModel();
                        JOptionPane.showMessageDialog(editPanel, "Joueur supprimé !");
                    } else {
                        JOptionPane.showMessageDialog(editPanel, "Données du joueur mal formatées !");
                    }
                } else {
                    JOptionPane.showMessageDialog(editPanel, "Aucun joueur sélectionné !");
                }
            }
        });




        editPanel.add(new JLabel("Nom :"));
        editPanel.add(nameField);
        editPanel.add(new JLabel("Prénom :"));
        editPanel.add(firstNameField);
        editPanel.add(new JLabel("Numéro de maillot :"));
        editPanel.add(numField);
        editPanel.add(new JLabel("Poste :"));
        editPanel.add(positionComboBox);
        editPanel.add(submitButton);
        editPanel.add(modifyButton);
        editPanel.add(deleteButton);

        return editPanel;
    }

    private JPanel createListPanel() {
        listPanel = new JPanel();
        listPanel.setLayout(new BorderLayout());

        // Liste des joueurs filtrée par idEquipe
        listModel = new DefaultListModel<>();
        updateListModel();

        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedPlayer = list.getSelectedValue();
                if (selectedPlayer != null) {
                    // Extraire l'ID du joueur de la chaîne sélectionnée
                    String[] parts = selectedPlayer.split(" ");
                    String nom = parts[0];
                    String prenom = parts[1];
                    selectedJoueur = getJoueurByName(nom, prenom);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(list);
        listPanel.add(scrollPane, BorderLayout.CENTER);

        return listPanel;
    }

    private void updateListModel() {
        listModel.clear();
        for (Joueur joueur : ConnexionBDD.getJoueursByEquipe(ConnexionBDD.getIdEquipeUtilisateur())) {
            listModel.addElement(joueur.getNom() + " " + joueur.getPrenom());
        }
    }

    private Joueur getJoueurByName(String nom, String prenom) {
        for (Joueur joueur : ConnexionBDD.getJoueursByEquipe(ConnexionBDD.getIdEquipeUtilisateur())) {
            if (joueur.getNom().equals(nom) && joueur.getPrenom().equals(prenom)) {
                return joueur;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // Créer l'instance de l'interface utilisateur
        TeamManagementUI frame = new TeamManagementUI();
        frame.setTitle("Gestion de l'équipe");
    }
}
