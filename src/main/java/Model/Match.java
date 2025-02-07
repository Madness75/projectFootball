package Model;

import java.sql.Time;
import java.util.Date;
import java.util.Random;

public class Match {

    private Equipe equipe1;
    private Equipe equipe2;
    private int scoreE1;
    private int scoreE2;
    private Date dateDebut;
    private Time heureDebut;
    private Time heureFin;
    private String Vainqueur;

    public Match(Equipe equipe1, Equipe equipe2, Date dateDebut, Time heureDebut, Time heureFin) {
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.dateDebut = dateDebut;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.scoreE1=0;
        this.scoreE2=0;
    }

    public Equipe getEquipe1() {
        return equipe1;
    }

    public void setEquipe1(Equipe equipe1) {
        this.equipe1 = equipe1;
    }

    public Equipe getEquipe2() {
        return equipe2;
    }

    public void setEquipe2(Equipe equipe2) {
        this.equipe2 = equipe2;
    }

    public int getScoreE1() {
        return scoreE1;
    }

    public void setScoreE1(int scoreE1) {
        this.scoreE1 = scoreE1;
    }

    public int getScoreE2() {
        return scoreE2;
    }

    public void setScoreE2(int scoreE2) {
        this.scoreE2 = scoreE2;
    }

    public Time getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(Time heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Time getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Time heureFin) {
        this.heureFin = heureFin;
    }

    public String getVainqueur() {
        return Vainqueur;
    }

    public void setVainqueur(String vainqueur) {
        Vainqueur = vainqueur;
    }

    public void jouerMacth(){
        Random rand= new Random();
        scoreE1= rand.nextInt(7);
        scoreE2= rand.nextInt(7);
        if (scoreE2>scoreE1){
            Vainqueur=equipe2.getNom();
        }
        else if (scoreE2<scoreE1){
            Vainqueur=equipe1.getNom();
        }
        else{
            Vainqueur="Nul";
        }
    }
}
