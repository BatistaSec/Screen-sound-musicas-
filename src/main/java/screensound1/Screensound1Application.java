package screensound1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import screensound1.principal.Principal;
import screensound1.repository.ArtistaRepository;

@SpringBootApplication
public class Screensound1Application implements CommandLineRunner {
    @Autowired
    private ArtistaRepository repositorio;

    public static void main(String[] args) {
        SpringApplication.run(Screensound1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal(repositorio);
        principal.exibeMenu();
    }
}
