package Model;

public class Poste {

    private int idPoste;
    private String pos;

    public Poste(int idPoste) {
        this.idPoste = idPoste;
        switch (idPoste){
            case 1:
                pos= "GK";
            case 2:
                pos= "DEF";
            case 3:
                pos= "MIL";
            case 4:
                pos= "ATT";
            default:
                pos="null";
        }
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public int getIdPoste() {
        return idPoste;
    }

    public void setIdPoste(int idPoste) {
        this.idPoste = idPoste;
    }
}
