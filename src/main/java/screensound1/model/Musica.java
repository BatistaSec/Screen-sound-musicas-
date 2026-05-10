package screensound1.model;


import jakarta.persistence.*;

@Entity
@Table(name = "musicas")
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String titulo;

    @ManyToOne
    private Artista artista;

    public Musica(){}

    public Musica(String nomeMuscia) {
        this.titulo = nomeMuscia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Artista getAtista() {
        return artista;
    }

    public void setAtista(Artista atista) {
        this.artista = atista;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return
                "Musica='" + titulo + '\'' +
                ", atista=" + artista.getNome() ;
    }
}
