package org.example;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MonInterface extends JFrame {
    public MonInterface() {
        // Titre de la fenêtre
        setTitle("Exemple d'Interface Swing");

        // Taille de la fenêtre (largeur, hauteur)
        setSize(1920, 1080);

        // Fermer l'application lors de la fermeture de la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Centrer la fenêtre à l'écran
        setLocationRelativeTo(null);

        // Définir le layout pour organiser les composants
        setLayout(new BorderLayout());

        // Ajouter des composants à la fenêtre
        initComponents();
    }

    private void initComponents() {
        // Exemple : un bouton au centre
        JButton bouton = new JButton("Cliquez-moi");

        // Ajout d'un écouteur d'événement sur le bouton
        bouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MonInterface.this, "Bouton cliqué !");
            }
        });

        // Ajout du bouton dans le centre du BorderLayout
        add(bouton, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        // Pour respecter la sécurité des threads Swing, on utilise invokeLater
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MonInterface fenetre = new MonInterface();
                fenetre.setVisible(true);
            }
        });
    }
}
