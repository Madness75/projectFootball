package Model;
import java.sql.*;

public class ConnexionBDD {

    public ConnexionBDD() {}

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
            String query = "INSERT INTO joueurs (Nom, Prenom, NumMaillot, Poste) VALUES (?, ?, ?, ?)";

            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, j.getNom());
                stmt.setString(2,j.getPrenom());
                stmt.setObject(3, j.getNumMaillot(), Types.INTEGER); // Si numMaillot est null, il est géré par Object
                stmt.setString(4, j.getPoste().getPos());

                // Exécution de la requête
                stmt.executeUpdate();
                System.out.println("Le joueur a été ajouté avec succès !");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}