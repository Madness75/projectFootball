package Model;

import java.util.ArrayList;
import java.util.List;

public class Equipe {

    private List<Joueur> effectif;
    private Stade stade;
    private String Nom;

    public Equipe(List<Joueur> effectif, Stade stade, String nom) {
        this.effectif = effectif;
        this.stade = stade;
        Nom = nom;
    }

    public Equipe(Stade stade) {
        this.stade = stade;
        effectif=new ArrayList<>();
    }

    public List<Joueur> getEffectif() {
        return effectif;
    }

    public void setEffectif(List<Joueur> effectif) {
        this.effectif = effectif;
    }

    public Stade getStade() {
        return stade;
    }

    public void setStade(Stade stade) {
        this.stade = stade;
    }

    public void ajouterJoueur(Joueur j){
        effectif.add(j);
    }
    public void supprimerJoueur(Joueur j){
        effectif.remove(j);
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }
}
