package com.example.edressing.ui.models;

/**
 * Created by $(USER) on $(DATE).
 */
public class Vetements {

    private String uid;
    private String type;
    private String style;
    private String couleur;
    private String matiere;
    private int motif;
    private String temperature;
    private String temps;
    private int sorti;
    private String vetement;

    public Vetements(){}

    public Vetements(String uid,
            String type,
            String style,
            String couleur,
            String matiere,
            int motif,
            String temperature,
            String temps,
            int sorti,
            String vetement){
        this.uid = uid;
        this.type = type;
        this.style = style;
        this.couleur = couleur;
        this.matiere = matiere;
        this.motif = motif;
        this.temperature = temperature;
        this.temps = temps;
        this.sorti = sorti;
        this.vetement = vetement;
    }


    //GETTERS----
    public String getUid() { return uid; }

    public String getType() { return type; }

    public String getStyle() { return style; }

    public String getCouleur() { return couleur; }

    public String getMatiere() { return matiere; }

    public int getMotif() { return motif; }

    public String getTemperature() { return temperature; }

    public String getTemps() { return temps; }

    public String getVetement() { return vetement; }

    public int getSorti() { return sorti; }


    //SETTERS----
    public void setUid(String uid) { this.uid = uid; }

    public void setType(String type) { this.type = type; }

    public void setStyle(String style) { this.style = style; }

    public void setCouleur(String couleur) { this.couleur = couleur; }

    public void setMatiere(String matiere) { this.matiere = matiere; }

    public void setMotif(int motif) { this.motif = motif; }

    public void setTemperature(String temperature) { this.temperature = temperature; }

    public void setTemps(String temps) { this.temps = temps; }

    public void setSorti(int sorti) { this.sorti = sorti; }

    public void setVetement(String vetement) { this.vetement = vetement; }
}
