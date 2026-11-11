
package cl.duoc.emergencias_api;

import java.time.Instant;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import cl.duoc.emergencias_api.messaging.producer.EmergenciaProducer;
import cl.duoc.emergencias_api.model.EmergenciaEvento;

@SpringBootApplication
public class EmergenciasApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmergenciasApiApplication.class, args);
    }

    @Bean
    CommandLineRunner probarKafka(EmergenciaProducer producer) {

        return args -> {

            EmergenciaEvento evento = new EmergenciaEvento(
                    UUID.randomUUID().toString(),
                    "Quilicura",
                    "INCENDIO",
                    "Incendio en una bodega",
                    Instant.now()
            );

            producer.publicar(evento).join();
        };
    }
}
