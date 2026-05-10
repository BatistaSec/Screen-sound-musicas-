package screensound1.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artista")
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String nome;
    @Enumerated(EnumType.STRING)
    private TipoAtista tipo;

    @OneToMany(mappedBy = "artista",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Musica> musica = new ArrayList<>();

    public Artista() {}

    public Artista(String nome, TipoAtista tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoAtista getTipo() {
        return tipo;
    }

    public void setTipo(TipoAtista tipo) {
        this.tipo = tipo;
    }

    public List<Musica> getMusica() {
        return musica;
    }

    public void setMusica(List<Musica> musica) {
        this.musica = musica;
    }

    @Override
    public String toString() {
        return
                ", Artista='" + nome + '\'' +
                ", tipo=" + tipo +
                ", musica=" + musica ;
    }
}
