package Model;

public class Joueur {

    private String nom;
    private String prenom;
    private Integer numMaillot;
    private Poste poste;
    private int idEquipe;


    public Joueur(String nom, String prenom, int numMaillot,  Poste poste, int idEquipe) {
        this.nom = nom;
        this.prenom = prenom;
        this.poste = poste;
        this.numMaillot = numMaillot;
        this.idEquipe = idEquipe;
    }


    public int getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
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

    public Integer getNumMaillot() {
        return numMaillot;
    }

    public void setNumMaillot(Integer numMaillot) {
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
