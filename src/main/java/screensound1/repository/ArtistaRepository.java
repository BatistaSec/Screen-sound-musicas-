package screensound1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import screensound1.model.Artista;
import screensound1.model.Musica;

import java.util.List;
import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {

    Optional<Artista> findByNomeContainingIgnoreCase(String nome);

    @Query("select m from Artista a join a.musica m where a.nome ILIKE %:nome%")
    List<Musica> buscarMusicasPorArtista(String nome);
}
