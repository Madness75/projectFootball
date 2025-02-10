package Model;

public class Joueur {

    private String nom;
    private String prenom;
    private int numMaillot;
    private Poste poste;


    public Joueur(String nom,String prenom , int numMaillot, Poste poste) {
        this.nom = nom;
        this.poste = poste;
        this.numMaillot = numMaillot;
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getNumMaillot() {
        return numMaillot;
    }

    public void setNumMaillot(int numMaillot) {
        this.numMaillot = numMaillot;
    }

    public Poste getPoste() {
        return poste;
    }

    public void setPoste(Poste poste) {
        this.poste = poste;
    }

    public void modifierNum(int n){
        this.numMaillot=n;
    }
}
