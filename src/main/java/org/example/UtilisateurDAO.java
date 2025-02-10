package org.example;

import java.sql.*;

public class UtilisateurDAO {

    // Paramètres de connexion pour la base de données football
    final static String URL = "jdbc:mysql://localhost:3306/football?serverTimezone=UTC";
    final static String LOGIN = "root"; // Adaptez votre identifiant MySQL
    final static String PASS = "";      // Adaptez votre mot de passe MySQL

    /**
     * Constructeur de la classe.
     * Il charge le driver JDBC de MySQL.
     */
    public UtilisateurDAO() {
        try {
            // Chargement du driver MySQL JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Impossible de charger le pilote MySQL JDBC. Vérifiez que le .jar est bien ajouté au projet.");
            e.printStackTrace();
        }
    }

    /**
     * Vérifie si un utilisateur existe dans la table Utilisateur en fonction de son email et de son mot de passe.
     *
     * @param email l'adresse e-mail saisie par l'utilisateur
     * @param motdepasse le mot de passe saisi par l'utilisateur
     * @return true si les informations correspondent à un enregistrement de la table, false sinon
     */
    public boolean checkLogin(String email, String motdepasse) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean isValid = false; // Variable qui indiquera si l'utilisateur est trouvé

        try {
            // Connexion à la base de données football
            con = DriverManager.getConnection(URL, LOGIN, PASS);

            // Préparation de la requête SQL pour rechercher l'utilisateur avec l'email et le mot de passe
            String sql = "SELECT * FROM Utilisateur WHERE email = ? AND motdepasse = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, motdepasse);

            // Exécution de la requête
            rs = ps.executeQuery();

            // Si on trouve au moins une ligne, c'est que l'utilisateur existe
            if (rs.next()) {
                isValid = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fermeture des ressources pour éviter les fuites
            try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (ps != null) ps.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (con != null) con.close(); } catch (Exception e) { e.printStackTrace(); }
        }
        return isValid;
    }
}
