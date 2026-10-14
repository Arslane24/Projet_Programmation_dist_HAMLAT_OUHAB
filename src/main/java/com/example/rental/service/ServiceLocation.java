package com.example.rental.service;

import com.example.rental.entity.Voiture;
import com.example.rental.web.Dates;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Service : contient la logique metier et la liste des voitures (notre "base de donnees")
@Service
public class ServiceLocation {

    private final List<Voiture> voitures = new ArrayList<>();

    public ServiceLocation() {
        voitures.add(new Voiture("11AA22", "Ferrari", 100));
        voitures.add(new Voiture("AA11BB", "Renault", 40));
        voitures.add(new Voiture("33CC44", "Peugeot", 35));
        voitures.add(new Voiture("55DD66", "Tesla", 120));
    }

    // toutes les voitures
    public List<Voiture> toutesLesVoitures() {
        return voitures;
    }

    // seulement les voitures qui ne sont pas louees
    public List<Voiture> voituresLibres() {
        List<Voiture> libres = new ArrayList<>();
        for (Voiture voiture : voitures) {
            if (!voiture.isLouee()) {
                libres.add(voiture);
            }
        }
        return libres;
    }

    // une seule voiture, trouvee grace a son numero de plaque
    public Voiture trouverVoiture(String plaque) throws Exception {
        for (Voiture voiture : voitures) {
            if (voiture.getPlateNumber().equalsIgnoreCase(plaque)) {
                return voiture;
            }
        }
        throw new Exception("Aucune voiture avec la plaque " + plaque);
    }

    // louer une voiture entre les deux dates donnees
    public void louer(String plaque, Dates dates) throws Exception {
        Voiture voiture = trouverVoiture(plaque);
        if (voiture.isLouee()) {
            throw new Exception("La voiture " + plaque + " est deja louee");
        }
        voiture.setLouee(true);
        voiture.setDatesLocation(dates);
    }

    // rendre une voiture
    public void rendre(String plaque) throws Exception {
        Voiture voiture = trouverVoiture(plaque);
        if (!voiture.isLouee()) {
            throw new Exception("La voiture " + plaque + " n'est pas louee");
        }
        voiture.setLouee(false);
        voiture.setDatesLocation(null);
    }
}
