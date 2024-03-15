package app.condominio.domain.enums;

public enum TipoPet {
    CACHORRO("Cachorro"),
    GATO("Gato"),
    PASSARO("Passaro"),
    PEIXE("Peixe"),
    COELHO("Coelho"),
    HAMSTER("Hamster"),
    TARTARUGA("Tartaruga"),
    SAPO("Sapo"),
    RATO("Rato");

    private final String tipo;

    TipoPet(String tipo){
        this.tipo = tipo;
    }

    public String getTipo(){
        return this.tipo;
    }


}
