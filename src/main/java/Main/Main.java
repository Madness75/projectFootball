package Main;

import Model.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ConnexionBDD connexionBDD= new ConnexionBDD();

        List<Joueur> lesJoueurs = new ArrayList<>();

        /*lesJoueurs.add(new Joueur("Dupont", "Pierre", 10, new Poste(4)));
        lesJoueurs.add(new Joueur("Lefevre", "Paul", 7, new Poste(4)));
        lesJoueurs.add(new Joueur("Martinez", "Carlos", 5, new Poste(2)));
        lesJoueurs.add(new Joueur("Nguyen", "Lan", 12, new Poste(3)));
        lesJoueurs.add(new Joueur("Tanaka", "Hiroshi", 3, new Poste(2)));
        lesJoueurs.add(new Joueur("Zhang", "Wei", 9, new Poste(4)));
        lesJoueurs.add(new Joueur("Durand", "Antoine", 1, new Poste(1)));
        lesJoueurs.add(new Joueur("Petrov", "Ivan", 11, new Poste(4)));
        lesJoueurs.add(new Joueur("Rossi", "Luca", 8, new Poste(3)));
        lesJoueurs.add(new Joueur("Alvarez", "Sofia", 4, new Poste(2)));
        lesJoueurs.add(new Joueur("Moreno", "Luis", 6, new Poste(2)));
        lesJoueurs.add(new Joueur("Gonzalez", "Maria", 2, new Poste(2)));
        lesJoueurs.add(new Joueur("Kumar", "Ravi", 10, new Poste(4)));
        lesJoueurs.add(new Joueur("Bennett", "Samantha", 16, new Poste(1)));
        lesJoueurs.add(new Joueur("Garcia", "Daniel", 7, new Poste(4)));
        lesJoueurs.add(new Joueur("Dufresne", "Luc", 4, new Poste(2)));
        lesJoueurs.add(new Joueur("Lopez", "Lucie", 15, new Poste(3)));
        lesJoueurs.add(new Joueur("Martinez", "Sandra", 11, new Poste(4)));
        lesJoueurs.add(new Joueur("Baron", "François", 3, new Poste(2)));
        lesJoueurs.add(new Joueur("Félix", "Pierre", 14, new Poste(3)));
        lesJoueurs.add(new Joueur("Huguet", "Patrick", 1, new Poste(1)));
        lesJoueurs.add(new Joueur("Roussel", "Christine", 6, new Poste(2)));
        lesJoueurs.add(new Joueur("Richard", "Jean", 12, new Poste(4)));
        lesJoueurs.add(new Joueur("Morin", "David", 9, new Poste(4)));
        lesJoueurs.add(new Joueur("Pires", "Louis", 5, new Poste(2)));
        lesJoueurs.add(new Joueur("O'Connor", "Sean", 3, new Poste(2)));
        lesJoueurs.add(new Joueur("Leblanc", "Catherine", 4, new Poste(2)));
        lesJoueurs.add(new Joueur("Joubert", "Nadine", 8, new Poste(3)));
        lesJoueurs.add(new Joueur("Deschamps", "Marc", 1, new Poste(1)));
        lesJoueurs.add(new Joueur("Piquet", "Sophie", 9, new Poste(4)));
        lesJoueurs.add(new Joueur("Lemoine", "Pierre", 11, new Poste(4)));
        lesJoueurs.add(new Joueur("Montagne", "Laurent", 10, new Poste(3)));
        lesJoueurs.add(new Joueur("Lefevre", "Yves", 6, new Poste(2)));
        lesJoueurs.add(new Joueur("Kovács", "László", 4, new Poste(2)));
        lesJoueurs.add(new Joueur("Barbosa", "Alice", 3, new Poste(2)));
        lesJoueurs.add(new Joueur("Sanchez", "David", 5, new Poste(3)));
        lesJoueurs.add(new Joueur("Yamamoto", "Naoki", 12, new Poste(3)));
        lesJoueurs.add(new Joueur("Noel", "Henri", 9, new Poste(4)));
        lesJoueurs.add(new Joueur("Vasseur", "Élisabeth", 7, new Poste(4)));
        lesJoueurs.add(new Joueur("Lemoine", "Thierry", 6, new Poste(2)));
        lesJoueurs.add(new Joueur("Giraud", "Patrick", 8, new Poste(3)));
        lesJoueurs.add(new Joueur("Lemoine", "Clément", 2, new Poste(2)));
        lesJoueurs.add(new Joueur("Thibault", "Jean", 11, new Poste(4)));
        lesJoueurs.add(new Joueur("Roussel", "Claude", 5, new Poste(2)));
        lesJoueurs.add(new Joueur("Langlois", "Sylvie", 12, new Poste(3)));
        lesJoueurs.add(new Joueur("Meyer", "Frédéric", 7, new Poste(4)));
        lesJoueurs.add(new Joueur("Sato", "Keiko", 6, new Poste(2)));
        lesJoueurs.add(new Joueur("Faure", "Hervé", 9, new Poste(4)));
        lesJoueurs.add(new Joueur("Clerc", "Valérie", 10, new Poste(3)));
        lesJoueurs.add(new Joueur("Germain", "Louis", 8, new Poste(3)));
        lesJoueurs.add(new Joueur("Fontaine", "Gérard", 12, new Poste(2)));
        lesJoueurs.add(new Joueur("Martin", "Sylvain", 3, new Poste(2)));
        lesJoueurs.add(new Joueur("Berger", "Jacques", 7, new Poste(4)));
        lesJoueurs.add(new Joueur("Lemoine", "Marie", 8, new Poste(4)));
        lesJoueurs.add(new Joueur("Charbonnier", "Claude", 5, new Poste(2)));
        lesJoueurs.add(new Joueur("Moyen", "Nicolas", 7, new Poste(4)));
        lesJoueurs.add(new Joueur("Bouchet", "Luc", 9, new Poste(4)));
        lesJoueurs.add(new Joueur("Robert", "Jean", 10, new Poste(3)));
        lesJoueurs.add(new Joueur("Lemoine", "Claude", 11, new Poste(4)));
        lesJoueurs.add(new Joueur("Fournier", "Pierre", 4, new Poste(2)));
        lesJoueurs.add(new Joueur("Boulet", "Jean", 9, new Poste(4)));
        lesJoueurs.add(new Joueur("Bruneau", "David", 12, new Poste(2)));
        lesJoueurs.add(new Joueur("Roulier", "Georges", 7, new Poste(4)));
        lesJoueurs.add(new Joueur("De La Rue", "Charles", 11, new Poste(3)));
        lesJoueurs.add(new Joueur("Klein", "Paul", 8, new Poste(2)));
        lesJoueurs.add(new Joueur("Rivière", "Louis", 3, new Poste(2)));
        lesJoueurs.add(new Joueur("Henri", "Gilles", 7, new Poste(4)));
        lesJoueurs.add(new Joueur("Dumont", "Sophie", 10, new Poste(3)));
        lesJoueurs.add(new Joueur("Faure", "Michel", 2, new Poste(2)));
        lesJoueurs.add(new Joueur("Leclerc", "Marcel", 5, new Poste(2)));
        lesJoueurs.add(new Joueur("Tardieu", "Catherine", 4, new Poste(2)));
        lesJoueurs.add(new Joueur("Dumas", "Pierre", 9, new Poste(4)));
        lesJoueurs.add(new Joueur("Sauvage", "Paul", 7, new Poste(4)));
        lesJoueurs.add(new Joueur("Durand", "Françoise", 10, new Poste(3)));
        lesJoueurs.add(new Joueur("Blanchard", "Michel", 5, new Poste(2)));
        lesJoueurs.add(new Joueur("Lebrun", "Michel", 6, new Poste(2)));
        lesJoueurs.add(new Joueur("Montaigu", "Sophie", 9, new Poste(4)));
        lesJoueurs.add(new Joueur("Dufresne", "Sébastien", 12, new Poste(2)));
        lesJoueurs.add(new Joueur("Jacquet", "Henri", 7, new Poste(4)));
        lesJoueurs.add(new Joueur("Almeida", "Ricardo", 3, new Poste(2)));
        lesJoueurs.add(new Joueur("Ramos", "Sofia", 10, new Poste(3)));
        lesJoueurs.add(new Joueur("Petrova", "Yulia", 4, new Poste(2)));
        lesJoueurs.add(new Joueur("Bianchi", "Francesca", 6, new Poste(2)));
        lesJoueurs.add(new Joueur("Cavalcanti", "Rafael", 7, new Poste(4)));
        lesJoueurs.add(new Joueur("Santos", "Lucia", 9, new Poste(4)));
        lesJoueurs.add(new Joueur("Carvalho", "Nicolas", 10, new Poste(3)));
        lesJoueurs.add(new Joueur("Costa", "Diana", 8, new Poste(2)));
        lesJoueurs.add(new Joueur("Hernandez", "Alejandro", 9, new Poste(4)));
        lesJoueurs.add(new Joueur("Silva", "Roberto", 11, new Poste(4)));
        lesJoueurs.add(new Joueur("Pinto", "Joaquim", 3, new Poste(2)));
        lesJoueurs.add(new Joueur("Sousa", "Carlos", 12, new Poste(2)));
        lesJoueurs.add(new Joueur("Araujo", "Rafael", 10, new Poste(3)));
        lesJoueurs.add(new Joueur("Martins", "Victor", 7, new Poste(4)));
        lesJoueurs.add(new Joueur("Oliveira", "Beatriz", 8, new Poste(3)));
        lesJoueurs.add(new Joueur("Gomes", "Lucas", 11, new Poste(4)));
        lesJoueurs.add(new Joueur("Silva", "Fernanda", 5, new Poste(2)));
        lesJoueurs.add(new Joueur("Machado", "Gabriel", 6, new Poste(2)));
        lesJoueurs.add(new Joueur("Martinez", "Luis", 7, new Poste(4)));
        lesJoueurs.add(new Joueur("Oliveira", "Pedro", 3, new Poste(2)));
        lesJoueurs.add(new Joueur("Guerra", "Ricardo", 4, new Poste(3)));
        lesJoueurs.add(new Joueur("El Belaili", "Ismael", 10, new Poste(4)));*/

        for(Joueur j: lesJoueurs){
            connexionBDD.ajouterJoueur(j);
        }

    }
}
