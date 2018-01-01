package model;

import api.Chien;
import enums.RaceDeChien;
import enums.Sexe;
import lombok.Data;

import java.util.List;

@Data
public class SimpleChien implements Chien {

    private String nom;
    private String nomComplet;
    private Sexe sexe;
    private RaceDeChien race;
    private List<String> couleurs;
    private Double poids;

    public SimpleChien() {
    }

    public SimpleChien(final String nom) {
        this.nom = nom;
    }

    public SimpleChien(final String nom, final RaceDeChien race, final Sexe sexe) {
        this(nom);
        this.race = race;
        this.sexe = sexe;
    }

}
