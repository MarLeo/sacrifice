package api;

import enums.RaceDeChien;
import enums.Sexe;

import java.io.Serializable;
import java.util.List;

public interface Chien extends Serializable {

    String getNom();

    String getNomComplet();

    Sexe getSexe();

    RaceDeChien getRace();

    List<String> getCouleurs();

    Double getPoids();


}
