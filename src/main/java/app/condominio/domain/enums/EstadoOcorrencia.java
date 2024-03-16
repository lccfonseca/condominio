package app.condominio.domain.enums;

public enum EstadoOcorrencia{

    // @formatter:off
    N("Nova"),
    A("Ativa"),
    F("Finalizada");
    // @formatter:on

    private final String nome;

    private EstadoOcorrencia(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return nome;
    }


}
