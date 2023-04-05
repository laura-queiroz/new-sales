package NewSales;

import NewSales.entities.Client;
import NewSales.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication

    public class Application {


    public static void main(String[] args) {

        SpringApplication.run(NewSales.Application.class, args);}
}




