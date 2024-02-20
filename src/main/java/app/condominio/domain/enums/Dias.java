package app.condominio.domain.enums;

public enum Dias {
	
	// Fonte: https://github.com/caelum/caelum-stella/blob/master/stella-nfe/src/main/java/br/com/caelum/stella/nfe/Estados.java
	
	// @formatter:off
	DOMINGO(1,"domingo"),
	SEGUNDA(2,"segunda-feira"),
	TERCA(3,"terça-feira"),
	QUARTA(4,"quarta-feira"),
	QUINTA(5,"quinta-feira"),
	SEXTA(6,"sexta-feira"),
	SABADO(7,"Sábado");
	// @formatter:on

	private final int codigoDia;
	private final String nome;

	private Dias(int codigoDia, String nome) {
		this.codigoDia = codigoDia;
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public int getCodigoDia() {
		return codigoDia;
	}
}
