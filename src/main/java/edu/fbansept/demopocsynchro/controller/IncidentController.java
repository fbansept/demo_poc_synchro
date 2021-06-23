package edu.fbansept.demopocsynchro.controller;

import edu.fbansept.demopocsynchro.dao.IncidentDao;
import edu.fbansept.demopocsynchro.model.Incident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class IncidentController {

    IncidentDao incidentDao;

    @Autowired
    IncidentController(IncidentDao incidentDao){
        this.incidentDao = incidentDao;
    }

    @PostMapping("/user/synchronisation")
    public ResponseEntity<List<Incident>> synchronistation (@RequestBody List<Incident> listeIncident) {

        listeIncident.forEach(incident -> {
            if(incident.getId() == 0) {
                incident.setId(null);
            }
        });

        //On recupère tous les éléments qui sont taggué pour la suppression
        //Mais qui on également déjà été ajouté (si l'id est null c'est qu'ils n'ont jamais été synchronisés)
        List<Incident> listeIncidentASupprime = listeIncident.stream()
                .filter(e -> e.isaSupprimer() && e.getId() != null)
                .collect(Collectors.toList());

        incidentDao.deleteAll(listeIncidentASupprime);

        //On enregistre tous les éléments qui ne sont pas taggué pour la suppression
        //que ce soit un update ou un create
        List<Incident> listeIncidentAajouterOuModifier = listeIncident.stream()
                .filter(e -> !e.isaSupprimer())
                .collect(Collectors.toList());

        incidentDao.saveAll(listeIncidentAajouterOuModifier);

        return ResponseEntity.ok(incidentDao.findAll());
    }
}






