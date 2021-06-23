package edu.fbansept.demopocsynchro.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.fbansept.demopocsynchro.dao.UtilisateurDao;
import edu.fbansept.demopocsynchro.model.Role;
import edu.fbansept.demopocsynchro.model.Utilisateur;
import edu.fbansept.demopocsynchro.security.JwtUtil;
import edu.fbansept.demopocsynchro.security.UserDetailsCustom;
import edu.fbansept.demopocsynchro.security.UserDetailsServiceCustom;
import edu.fbansept.demopocsynchro.view.VueUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class UtilisateurController {

    private UtilisateurDao utilisateurDao;
    private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;
    private UserDetailsServiceCustom userDetailsServiceCustom;
    private PasswordEncoder passwordEncoder;

    @Autowired
    UtilisateurController(
            UtilisateurDao utilisateurDao,
            JwtUtil jwtUtil,
            AuthenticationManager authenticationManager,
            UserDetailsServiceCustom userDetailsServiceCustom,
            PasswordEncoder passwordEncoder
    ) {
        this.utilisateurDao = utilisateurDao;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userDetailsServiceCustom = userDetailsServiceCustom;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/authentification")
    public ResponseEntity<String> authentification(@RequestBody Utilisateur utilisateur) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            utilisateur.getPseudo(), utilisateur.getMotDePasse()
                    )
            );
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body("Mauvais pseudo / mot de passe");
        }

        UserDetailsCustom userDetails = this.userDetailsServiceCustom.loadUserByUsername(utilisateur.getPseudo());

        return ResponseEntity.ok(jwtUtil.generateToken(userDetails));
    }

    @PostMapping("/inscription")
    public ResponseEntity<String> inscription(@RequestBody Utilisateur utilisateur) {

        Optional<Utilisateur> utilisateurDoublon = utilisateurDao.findByPseudo(utilisateur.getPseudo());

        if (utilisateurDoublon.isPresent()) {
            return ResponseEntity.badRequest().body("Ce pseudo est déja utilisé");
        } else {

            utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));

            Role roleUtilisateur = new Role();
            roleUtilisateur.setId(1);

            utilisateur.getListeRole().add(roleUtilisateur);

            utilisateurDao.saveAndFlush(utilisateur);

            return ResponseEntity.ok(Integer.toString(utilisateur.getId()));
        }
    }
}






