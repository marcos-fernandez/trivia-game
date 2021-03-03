package net.marcos_fernandez.triviagame.configuration;

import com.mongodb.WriteConcern;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.WriteConcernResolver;

@EnableMongoAuditing
@Configuration
public class MongoConfiguration {

    @Bean
    public WriteConcernResolver writeConcernResolver() {
        return action -> WriteConcern.ACKNOWLEDGED;
    }
}
