package screensound1.model;




public enum TipoAtista {
    SOLO,
    DUPLA,
    BANDA;

    public static TipoAtista fromString(String texto) {
        for (TipoAtista tipo : TipoAtista.values()) {
            if (tipo.name().equalsIgnoreCase(texto.trim())) {
                return tipo;
            }
        }

        throw new IllegalArgumentException("Tipo de artista invalido: " + texto);
    }
}
