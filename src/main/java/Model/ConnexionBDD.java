package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnexionBDD {
    private static int idEquipeUtilisateur;

    public ConnexionBDD() {}

    public static int getIdEquipeUtilisateur() {
        return idEquipeUtilisateur;
    }



    public static void setUserIdEquipe(int userId) {
        String query = "SELECT equipeId FROM utilisateur WHERE idUtilisateur = ?";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, userId);  // Passe l'ID de l'utilisateur

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    idEquipeUtilisateur = rs.getInt("equipeId");
                } else {
                    System.out.println("Aucun utilisateur trouvé avec cet ID.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            // Charge le driver JDBC pour MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // URL de connexion à la base de données
            String url = "jdbc:mysql://localhost:3306/football?serverTimezone=UTC";
            String user = "root";
            String password = "";

            // Retourne la connexion
            return DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void ajouterJoueur(Joueur j) {
        Connection connection = getConnection();

        if (connection != null) {
            // Récupérer l'idEquipe de l'utilisateur connecté
            int idEquipe = idEquipeUtilisateur;

            String query = "INSERT INTO joueurs (Nom, Prenom, NumMaillot, Poste, idEquipe) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, j.getNom());
                stmt.setString(2, j.getPrenom());
                stmt.setObject(3, j.getNumMaillot(), Types.INTEGER); // Si numMaillot est null, il est géré par Object
                stmt.setString(4, j.getPoste().getPos());
                stmt.setInt(5, idEquipe);  // Ajouter l'idEquipe à la requête

                // Exécution de la requête
                stmt.executeUpdate();
                System.out.println("Le joueur a été ajouté avec succès !");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Joueur> getJoueursByEquipe(int idEquipe) {
        List<Joueur> joueurs = new ArrayList<>();
        String query = "SELECT * FROM joueurs WHERE idEquipe = ?";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, idEquipe);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Récupérer les valeurs de manière sécurisée
                String nom = rs.getString("Nom");
                String prenom = rs.getString("Prenom");
                Integer numMaillot = (rs.getObject("NumMaillot") != null) ? rs.getInt("NumMaillot") : null;
                String posteId = rs.getString("Poste");

                // Si le poste est null, tu peux le traiter de manière différente
                Poste poste;
                switch (posteId){
                    case "GK":
                        poste = new Poste(1);
                        break;
                    case "DEF":
                        poste = new Poste(2);
                        break;
                    case "MIL":
                        poste = new Poste(3);
                        break;
                    case "ATT":
                        poste = new Poste(4);
                        break;
                    default:
                        poste = new Poste(0);
                        break;
                }

                // Créer un joueur avec les informations récupérées
                Joueur joueur = new Joueur(nom, prenom, numMaillot, poste, idEquipe);
                joueurs.add(joueur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return joueurs; // Retourne la liste des joueurs
    }

    public static void supprimerJoueur(String nom, String prenom, int numMaillot) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();

            // La requête pour supprimer un joueur en fonction de nom, prénom et numéro de maillot
            String sql = "DELETE FROM joueurs WHERE Nom = ? AND Prenom = ? AND NumMaillot = ?";
            stmt = conn.prepareStatement(sql);

            // Remplir les paramètres de la requête
            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setInt(3, numMaillot);

            // Exécuter la requête
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void modifierJoueur(Joueur joueur) {
        // Connexion à la base de données
        try (Connection connection = getConnection()) {
            // Vérifie si le joueur existe déjà dans la base de données
            String checkQuery = "SELECT COUNT(*) FROM joueurs WHERE Nom = ? AND Prenom = ? AND NumMaillot = ?";
            try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
                checkStmt.setString(1, joueur.getNom());
                checkStmt.setString(2, joueur.getPrenom());
                checkStmt.setInt(3, joueur.getNumMaillot());

                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        // Le joueur existe, donc on effectue la mise à jour

                        String updateQuery = "UPDATE joueurs SET Nom = ?, Prenom = ?, NumMaillot = ?, Poste = ? WHERE Nom = ? AND Prenom = ? AND NumMaillot = ?";
                        try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                            updateStmt.setString(1, joueur.getNom());
                            updateStmt.setString(2, joueur.getPrenom());
                            updateStmt.setInt(3, joueur.getNumMaillot());
                            updateStmt.setString(4, joueur.getPoste().getPos());
                            updateStmt.setString(5, joueur.getNom());
                            updateStmt.setString(6, joueur.getPrenom());
                            updateStmt.setInt(7, joueur.getNumMaillot());

                            updateStmt.executeUpdate();
                            System.out.println("Le joueur a été mis à jour avec succès !");
                        }
                    } else {
                        // Si le joueur n'existe pas, on peut appeler ajouterJoueur pour l'ajouter
                        ajouterJoueur(joueur);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
