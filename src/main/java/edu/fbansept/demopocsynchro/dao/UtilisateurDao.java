package edu.fbansept.demopocsynchro.dao;

import edu.fbansept.demopocsynchro.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurDao extends JpaRepository<Utilisateur, Integer> {
    @Query("FROM Utilisateur u JOIN FETCH u.listeRole WHERE pseudo = :pseudo")
    Optional<Utilisateur> trouverParPseusoAvecRoles(@Param("pseudo") String pseudo);

    Optional<Utilisateur> findByPseudo(@Param("pseudo") String pseudo);


}
