package app.condominio.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "numeracao")
public class Numeracao implements Serializable, Comparable<Bloco> {

    @Id
    @Column(name = "numero_da_vaga")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moradias_idMoradia")
    private Moradia moradia;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Moradia getMoradia() {
        return moradia;
    }

    public void setMoradia(Moradia moradia) {
        this.moradia = moradia;
    }
     
    @Override
    public int compareTo(Bloco t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
