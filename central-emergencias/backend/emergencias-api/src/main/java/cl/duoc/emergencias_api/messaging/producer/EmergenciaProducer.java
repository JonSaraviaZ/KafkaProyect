
package cl.duoc.emergencias_api.messaging.producer;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import cl.duoc.emergencias_api.model.EmergenciaEvento;

@Component
public class EmergenciaProducer {

    private final KafkaTemplate<String, EmergenciaEvento> kafkaTemplate;

    private final String topic;

    public EmergenciaProducer(
            KafkaTemplate<String, EmergenciaEvento> kafkaTemplate,
            @Value("${app.kafka.topic.emergencias:emergencias}") String topic) {

        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }
    //comentario de prueba
    public CompletableFuture<Void> publicar(EmergenciaEvento evento) {

        String key = evento.comuna().trim().toLowerCase();

        return kafkaTemplate.send(topic, key, evento)
                .thenAccept(resultado -> {

                    var metadata = resultado.getRecordMetadata();

                    System.out.println(
                            "Evento publicado: " + evento.id()
                            + " | Partición: " + metadata.partition()
                            + " | Offset: " + metadata.offset()
                    );
                });
    }
}
