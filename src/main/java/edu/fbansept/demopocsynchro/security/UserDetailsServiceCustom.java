package edu.fbansept.demopocsynchro.security;

import edu.fbansept.demopocsynchro.dao.UtilisateurDao;
import edu.fbansept.demopocsynchro.model.Utilisateur;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceCustom implements UserDetailsService {

    private final UtilisateurDao utilisateurDao;

    public UserDetailsServiceCustom(UtilisateurDao utilisateurDao) {
        this.utilisateurDao = utilisateurDao;
    }

    @Override
    public UserDetailsCustom loadUserByUsername(String pseudoSaisi) throws UsernameNotFoundException {

        Utilisateur utilisateur = utilisateurDao
                .trouverParPseusoAvecRoles(pseudoSaisi)
                .orElseThrow(() -> new UsernameNotFoundException(pseudoSaisi + " inconnu"));

        return new UserDetailsCustom(utilisateur);
    }
}
