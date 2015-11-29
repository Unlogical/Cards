package cards;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by alexandra on 06.09.15.
 */


@SpringBootApplication
public class Main {

    private ConnectionProvider connectionProvider = new ConnectionProvider("localhost", 5432, "postgres", "postgres", "12345");

    @Bean
    public SessionManager sessionManager(){
        return new SessionManager(connectionProvider);
    }

    @Bean
    public Users users(){
        return new Users(connectionProvider);
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
