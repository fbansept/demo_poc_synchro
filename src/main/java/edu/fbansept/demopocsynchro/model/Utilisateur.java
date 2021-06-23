package edu.fbansept.demopocsynchro.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.fbansept.demopocsynchro.view.VueUtilisateur;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Utilisateur {

    public Utilisateur() {
    }

    public Utilisateur(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(VueUtilisateur.Standard.class)
    private int id;

    @Column(nullable = false, length = 50)
    @JsonView(VueUtilisateur.Standard.class)
    private String pseudo;

    private String motDePasse;

    @ManyToMany
    @JsonView({VueUtilisateur.Standard.class})
    @JoinTable(
            name = "utilisateur_role",
            joinColumns = @JoinColumn(name = "utilisateur_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> listeRole = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Set<Role> getListeRole() {
        return listeRole;
    }

    public void setListeRole(Set<Role> listeRole) {
        this.listeRole = listeRole;
    }
}
