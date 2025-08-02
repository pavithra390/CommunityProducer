package com.example.communityproducer;
import com.example.communityproducer.entity.CommunityEntity;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import java.util.HashMap;
import java.util.Map;
@Configuration
public class KafkaProducerConfig {
    /*ProducerFactory is a helper that creates Kafka producers for you.
      Instead of manually creating and configuring a Kafka producer every time,
      you define a ProducerFactory bean once.
      Spring then uses it behind the scenes whenever you send messages.

     */
    @Bean
    public ProducerFactory<Integer, CommunityEntity> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }
//ProducerFactory creates Kafka producers,
// while KafkaTemplate uses those producers to send messages easily.
    @Bean
    public KafkaTemplate<Integer, CommunityEntity> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
