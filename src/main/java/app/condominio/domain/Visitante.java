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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import app.condominio.domain.validators.CPF;

@SuppressWarnings("serial")
@Entity
@Table(name = "visitantes")
public class Visitante implements Serializable, Comparable<Visitante> {

   @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idvisitante")
	private Long idVisitante;

   @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idcondominio")
	private Condominio condominio;  
   

   
   @CPF
	private String cpf;
   
   @NotBlank
	@Size(min = 1, max = 50)
	private String nome;
   
   @Size(max = 15)
	private String telefone;
   
   @Email
	@Size(max = 100)
	private String email;
   
   private short recorrente;
   
   
   private short dia_recorrente;
   
   
   @Size(max=45)
   private String vinculo;
   
   
   
   
   public Long getIdvisitante() {
      return idVisitante;
   }
   
   public void setIdvisitante(Long idVisitante) {
      this.idVisitante = idVisitante;
   }

   public Condominio getCondominio() {
      return condominio;
   }

   public void setCondominio(Condominio condominio) {
      this.condominio = condominio;
   }
   
   public String getCpf() {
      return cpf;
   }
   
   public void setCpf(String cpf) {
      this.cpf = cpf;
   }
   
   public String getNome() {
      return nome;
   }
   
   public void setNome(String nome) {
      this.nome = nome;
   }
   
   public String getTelefone() {
      return telefone;
   }
   
   public void setTelefone(String telefone) {
      this.telefone = telefone;
   }
   
   public String getEmail() {
      return email;
   }
   
   public void setEmail(String email) {
      this.email = email;
   }
   
   public short getRecorrente() {
      return recorrente;
   }
   
   public void setRecorrente(short recorrente) {
      this.recorrente = recorrente;
   }

   public short getDia_recorrente() {
      return dia_recorrente;
   }
   
   public void setDia_recorrente(short dia_recorrente) {
      this.dia_recorrente = dia_recorrente;
   }

   public String getVinculo() {
      return vinculo;
   }
   
   public void setVinculo(String vinculo) {
      this.vinculo = vinculo;
   }

   @Override
	public String toString() {
		return nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idVisitante == null) ? 0 : idVisitante.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Visitante other = (Visitante) obj;
		if (idVisitante == null) {
			if (other.idVisitante != null) {
				return false;
			}
		} else if (!idVisitante.equals(other.idVisitante)) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(Visitante arg0) {
		return this.toString().compareTo(arg0.toString());
	}
}
