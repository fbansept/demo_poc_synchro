package edu.fbansept.demopocsynchro.dao;

import edu.fbansept.demopocsynchro.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentDao extends JpaRepository<Incident, Integer> {

}