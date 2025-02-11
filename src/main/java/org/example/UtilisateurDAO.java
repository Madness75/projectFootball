package org.example;

import java.sql.*;

/**
 * Cette classe gère les interactions avec la base de données pour la table des utilisateurs, en effectuant des opérations telles que l'inscription, la connexion, et l'ajout d'une équipe avec son stade.
 */
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

    /**
     * Inscrit un nouvel utilisateur en lui associant une équipe et un stade dans la base de données.
     * Si l'équipe et/ou le stade n'existent pas, ils sont créés automatiquement.
     *
     * @param email l'adresse e-mail de l'utilisateur
     * @param nom le nom de l'utilisateur
     * @param prenom le prénom de l'utilisateur
     * @param motdepasse le mot de passe de l'utilisateur
     * @param equipeNom le nom de l'équipe associée à l'utilisateur
     * @param stadeNom le nom du stade associé à l'équipe
     * @param stadeAdresse l'adresse du stade
     * @return true si l'inscription a réussi, false sinon
     */
    public boolean inscrireUtilisateurAvecEquipeEtStade(String email, String nom, String prenom, String motdepasse, String equipeNom, String stadeNom, String stadeAdresse) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean isSuccess = false;

        try {
            // Connexion à la base de données
            con = DriverManager.getConnection(URL, LOGIN, PASS);

            // Vérifie si le stade existe déjà
            String checkStadeSQL = "SELECT idStade FROM stade WHERE adresse = ? AND nom = ?";
            ps = con.prepareStatement(checkStadeSQL);
            ps.setString(1, stadeAdresse);
            ps.setString(2, stadeNom);  // Vérifie également le nom du stade
            rs = ps.executeQuery();

            int stadeId;
            if (rs.next()) {
                // Si le stade existe, on récupère son id
                stadeId = rs.getInt("idStade");
            } else {
                // Si le stade n'existe pas, on l'ajoute
                String insertStadeSQL = "INSERT INTO stade (nom, adresse) VALUES (?, ?)";
                ps = con.prepareStatement(insertStadeSQL, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, stadeNom);  // On ajoute le nom du stade
                ps.setString(2, stadeAdresse);
                ps.executeUpdate();

                // Récupère l'ID du stade nouvellement ajouté
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    stadeId = rs.getInt(1);
                } else {
                    // Si l'ajout échoue
                    throw new SQLException("Échec de l'ajout du stade.");
                }
            }

            // Vérifie si l'équipe existe déjà
            String checkEquipeSQL = "SELECT idEquipe FROM equipe WHERE nom = ?";
            ps = con.prepareStatement(checkEquipeSQL);
            ps.setString(1, equipeNom);
            rs = ps.executeQuery();

            int equipeId;
            if (rs.next()) {
                // Si l'équipe existe, on récupère son id
                equipeId = rs.getInt("idEquipe");
            } else {
                // Si l'équipe n'existe pas, on l'ajoute
                String insertEquipeSQL = "INSERT INTO equipe (nom, stadeId) VALUES (?, ?)";
                ps = con.prepareStatement(insertEquipeSQL, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, equipeNom);
                ps.setInt(2, stadeId);
                ps.executeUpdate();

                // Récupère l'ID de l'équipe nouvellement ajoutée
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    equipeId = rs.getInt(1);
                } else {
                    // Si l'ajout échoue
                    throw new SQLException("Échec de l'ajout de l'équipe.");
                }
            }

            // Insertion de l'utilisateur dans la base de données
            String insertUserSQL = "INSERT INTO utilisateur (email, nom, prenom, motdepasse, equipeId) VALUES (?, ?, ?, ?, ?)";
            ps = con.prepareStatement(insertUserSQL);
            ps.setString(1, email);
            ps.setString(2, nom);
            ps.setString(3, prenom);
            ps.setString(4, motdepasse);
            ps.setInt(5, equipeId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                isSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (ps != null) ps.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (con != null) con.close(); } catch (Exception e) { e.printStackTrace(); }
        }

        return isSuccess;
    }
}
