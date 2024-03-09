package app.condominio.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "ocorrencias")
public class Ocorrencia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idocorrencia")
    private Long idOcorrencia;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcondominio")
    private Condominio idCondominio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idbloco")
    private Bloco idBloco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idmoradia")
    private Moradia idMoradia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idautor")
    private Usuario idAutor;

    private LocalDate data_registro;

    private LocalTime hora_registro;

    @Size(min = 1, max = 45)
    private String motivo;

    private LocalDate data_ocorrencia;

    private LocalTime hora_ocorrencia;

    private String descricao;

    @NotNull
    private Boolean ativo;

    public Long getIdOcorrencia() {
        return idOcorrencia;
    }

    public void setIdOcorrencia(Long idOcorrencia) {
        this.idOcorrencia = idOcorrencia;
    }

    public Condominio getIdCondominio() {
        return idCondominio;
    }

    public void setIdCondominio(Condominio idCondominio) {
        this.idCondominio = idCondominio;
    }

    public Bloco getIdBloco() {
        return idBloco;
    }

    public void setIdBloco(Bloco idBloco) {
        this.idBloco = idBloco;
    }

    public Moradia getIdMoradia() {
        return idMoradia;
    }

    public void setIdMoradia(Moradia idMoradia) {
        this.idMoradia = idMoradia;
    }

    public Usuario getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Usuario idAutor) {
        this.idAutor = idAutor;
    }

    public LocalDate getData_registro() {
        return data_registro;
    }

    public void setData_registro(LocalDate data_registro) {
        this.data_registro = data_registro;
    }

    public LocalTime getHora_registro() {
        return hora_registro;
    }

    public void setHora_registro(LocalTime hora_registro) {
        this.hora_registro = hora_registro;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public LocalDate getData_ocorrencia() {
        return data_ocorrencia;
    }

    public void setData_ocorrencia(LocalDate data_ocorrencia) {
        this.data_ocorrencia = data_ocorrencia;
    }

    public LocalTime getHora_ocorrencia() {
        return hora_ocorrencia;
    }

    public void setHora_ocorrencia(LocalTime hora_ocorrencia) {
        this.hora_ocorrencia = hora_ocorrencia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
