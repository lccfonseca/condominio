package app.condominio.domain;

import app.condominio.domain.enums.TipoPet;
import org.hibernate.collection.internal.PersistentSortedMap;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("serial")
@Entity
@Table(name = "pets")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpet")
    private Long idPet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idpessoa")
    private Pessoa dono;

    @NotBlank
    @Size(min = 1, max = 45)
    private String nome;

    @NotBlank
    @Size(min = 1, max = 1)
    private char sexo;

    @NotBlank
    @Size(min = 1, max = 1)
    private char porte;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate nascimento;

    @Size(max = 200)
    private String personalidade;

    @NotBlank
    @Size(min = 1, max = 1)
    private char animalSuporte;

    @NotBlank
    @Size(max = 80)
    private String suporteDescricao;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoPet tipo_Pet;

    @NotBlank
    @Size(min = 1, max = 30)
    private String raca;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcondominio")
    private Condominio condominio;

    public Long getIdPet() {
        return idPet;
    }

    public void setIdPet(Long idPet) {
        this.idPet = idPet;
    }

    public Pessoa getDono() {
        return dono;
    }

    public void setDono(Pessoa dono) {
        this.dono = dono;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public Condominio getCondominio() {
        return condominio;
    }

    public void setCondominio(Condominio condominio) {
        this.condominio = condominio;
    }

    public char getPorte() {
        return porte;
    }

    public void setPorte(char porte) {
        this.porte = porte;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getPersonalidade() {
        return personalidade;
    }

    public void setPersonalidade(String personalidade) {
        this.personalidade = personalidade;
    }

    public char getAnimalSuporte() {
        return animalSuporte;
    }

    public void setAnimalSuporte(char animalSuporte) {
        this.animalSuporte = animalSuporte;
    }

    public String getSuporteDescricao() {
        return suporteDescricao;
    }

    public void setSuporteDescricao(String suporteDescricao) {
        this.suporteDescricao = suporteDescricao;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public TipoPet getTipo_Pet() {
        return tipo_Pet;
    }

    public void setTipo_Pet(TipoPet tipo_Pet) {
        this.tipo_Pet = tipo_Pet;
    }
}
