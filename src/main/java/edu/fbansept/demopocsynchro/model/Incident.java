package edu.fbansept.demopocsynchro.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.fbansept.demopocsynchro.view.VueUtilisateur;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Incident {

    @Id
    @JsonView(VueUtilisateur.Standard.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonView(VueUtilisateur.Standard.class)
    private String code;

    @JsonView(VueUtilisateur.Standard.class)
    private String texte;

    @Transient
    @JsonView(VueUtilisateur.Standard.class)
    private boolean aSupprimer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public boolean isaSupprimer() {
        return aSupprimer;
    }

    public void setaSupprimer(boolean aSupprimer) {
        this.aSupprimer = aSupprimer;
    }

}
