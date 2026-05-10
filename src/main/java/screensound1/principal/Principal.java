package screensound1.principal;

import screensound1.model.Artista;
import screensound1.model.Musica;
import screensound1.model.TipoAtista;
import screensound1.repository.ArtistaRepository;
import screensound1.service.ConsultaChatGPT;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private final ArtistaRepository  repositorio;
    
    private Scanner leitura = new Scanner(System.in);

    public Principal(ArtistaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void exibeMenu() {
        var opcao = -1;

        while (opcao!= 9) {
            var menu = """
                    *** Screen Sound Músicas ***                    
                                        
                    1- Cadastrar artistas
                    2- Cadastrar músicas
                    3- Listar músicas
                    4- Buscar músicas por artistas
                    5- Pesquisar dados sobre um artista
                                    
                    9 - Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarArtistas();
                    break;
                case 2:
                    cadastrarMusicas();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    buscarMusicasPorArtista();
                    break;
                case 5:
                    pesquisarDadosDoArtista();
                    break;
                case 9:
                    System.out.println("Encerrando a aplicação!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void pesquisarDadosDoArtista() {
        System.out.println("Pesquisar dados sobre qual artista? ");
        var nome = leitura.nextLine();
        var resposta = ConsultaChatGPT.obterInformacao(nome);
        System.out.println(resposta.trim());


    }

    private void buscarMusicasPorArtista() {
        System.out.println("Buscar musicas de que artista? ");
        var nome = leitura.nextLine();
        List<Musica> musicas = repositorio.buscarMusicasPorArtista(nome);
        musicas.forEach(System.out::println);
    }

    private void listarMusicas() {
        List<Artista> artistas = repositorio.findAll();
        artistas.forEach(a-> a.getMusica().forEach(System.out::println));
    }

    private void cadastrarMusicas() {
        System.out.println("Cadastrar Musicas de que artista: ");
        var nome = leitura.nextLine();
        Optional<Artista> artista = repositorio.findByNomeContainingIgnoreCase(nome);
        if(artista.isPresent()) {
            System.out.println("Infrome o titulo da musica: ");
            var nomeMuscia = leitura.nextLine();
            Musica musica = new Musica(nomeMuscia);
            musica.setAtista(artista.get());
            artista.get().getMusica().add(musica);
            repositorio.save(artista.get());
        }else{
            System.out.println("Artista nao encontrado! ");
        }
    }

    private void cadastrarArtistas() {
        var cadastrarNovo = "S";

        while (cadastrarNovo.equalsIgnoreCase("S")) {
            System.out.println("Informe o nome desse artista: ");
            var nome = leitura.nextLine();
            System.out.println("Informe o tipo desse artista: (solo,dupla ou banda)");
            var tipo = leitura.nextLine();
            TipoAtista tipoArtista;
            try {
                tipoArtista = TipoAtista.fromString(tipo);
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo invalido! Digite solo, dupla ou banda.");
                continue;
            }

            Artista artista = new Artista(nome, tipoArtista);
            repositorio.save(artista);
            System.out.println("Cadastrar novo artista? (S/N) ");
            cadastrarNovo = leitura.nextLine();
        }
    }
}
