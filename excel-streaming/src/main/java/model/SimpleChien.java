package model;

import api.Chien;
import enums.RaceDeChien;
import enums.Sexe;

import java.util.List;

public class SimpleChien implements Chien {

    private String nom;
    private String nomComplet;
    private Sexe sexe;
    private RaceDeChien race;
    private List<String> couleurs;
    private Double poids;

    public SimpleChien() {
        // rien...
    }

    public SimpleChien(final String nom) {
        this.nom = nom;
    }

    public SimpleChien(final String nom, final RaceDeChien race, final Sexe sexe) {
        this(nom);
        this.race = race;
        this.sexe = sexe;
    }

    @Override
    public String toString() {
        return "SimpleChien [nom=" + nom + "]";
    }

    // Getters et setters

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public RaceDeChien getRace() {
        return race;
    }

    public void setRace(RaceDeChien race) {
        this.race = race;
    }

    public List<String> getCouleurs() {
        return couleurs;
    }

    public void setCouleurs(List<String> couleurs) {
        this.couleurs = couleurs;
    }

    public Double getPoids() {
        return poids;
    }

    public void setPoids(Double poids) {
        this.poids = poids;
    }
}
