package com.example.rental.web;

import com.example.rental.entity.Voiture;
import com.example.rental.service.ServiceLocation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Service web : recoit les requetes http et repond en json
@RestController
public class ServiceWebLocation {

    private final ServiceLocation serviceLocation;

    public ServiceWebLocation(ServiceLocation serviceLocation) {
        this.serviceLocation = serviceLocation;
    }

    @GetMapping("/")
    public String hello() {
        return "hello";
    }

    // http://localhost:8080/cars
    @GetMapping("/cars")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Voiture> listeDesVoitures() {
        return serviceLocation.toutesLesVoitures();
    }

    // http://localhost:8080/cars/free : la liste des voitures non louees
    @GetMapping("/cars/free")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Voiture> listeDesVoituresLibres() {
        return serviceLocation.voituresLibres();
    }

    // http://localhost:8080/cars/AA11BB : les caracteristiques d'une voiture
    @GetMapping("/cars/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Voiture uneVoiture(@PathVariable("plateNumber") String plaque) throws Exception {
        return serviceLocation.trouverVoiture(plaque);
    }

    // http://localhost:8080/cars/AA11BB?rent=true  (avec les dates dans le corps de la requete)
    // http://localhost:8080/cars/AA11BB?rent=false (pour rendre la voiture)
    @PutMapping(value = "/cars/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    public void louerOuRendre(
            @PathVariable("plateNumber") String plaque,
            @RequestParam(value = "rent", required = true) boolean rent,
            @RequestBody(required = false) Dates dates) throws Exception {
        if (rent) {
            serviceLocation.louer(plaque, dates);
        } else {
            serviceLocation.rendre(plaque);
        }
    }

    // si la voiture n'existe pas ou est deja louee : on repond 404 avec le message
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String erreur(Exception e) {
        return e.getMessage();
    }
}
