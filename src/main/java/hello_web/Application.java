package hello_web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String args[]) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(CampRepository repository) {
        return (args) -> {
            log.info("Start work");
            // save a couple of customers
            repository.save(new Camp("Slavyane", "2018-07-05", "2018-07-14"));
            repository.save(new Camp("Vantit", "2018-07-19", "2018-07-25"));
            repository.save(new Camp("Project S", "2018-07-30", "2018-08-08"));
            repository.save(new Camp("Vantit", "2019-07-19", "2019-07-25"));

            // fetch all customers
            log.info("Camps found with findAll():");
            log.info("-------------------------------");
            for (Camp camp : repository.findAll()) {
                log.info(camp.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            repository.findById(1L)
                    .ifPresent(camp -> {
                        log.info("Customer found with findById(1L):");
                        log.info("--------------------------------");
                        log.info(camp.toString());
                        log.info("");
                    });

            // fetch customers by last name
            log.info("Customer found with findByName('Vantit'):");
            log.info("--------------------------------------------");
            repository.findByName("Vantit").forEach(vantit -> {
                log.info(vantit.toString());
            });
            // for (Customer bauer : repository.findByLastName("Bauer")) {
            // 	log.info(bauer.toString());
            // }
            log.info("");
        };


    }
}