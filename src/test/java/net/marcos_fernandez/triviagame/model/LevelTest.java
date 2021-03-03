package net.marcos_fernandez.triviagame.model;

import net.marcos_fernandez.triviagame.configuration.MongoConfiguration;
import net.marcos_fernandez.triviagame.repository.LevelRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

@Testcontainers
@Import(MongoConfiguration.class)
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
class LevelTest {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

    @DynamicPropertySource
    static void setProperties(final DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    LevelRepository repository;

    @AfterEach
    void cleanUp() {
        this.repository.deleteAll();
    }

    @Test
    void shouldControlVersionProperly() {
        final Level level = new Level();
        level.setDescription("My brand new description");

        final Level savedLevel = this.repository.save(level);

        final Level updatedLevel = this.repository.findById(savedLevel.getId()).get();
        updatedLevel.setDescription("Other description");

        this.repository.save(updatedLevel);

        assertThrows(OptimisticLockingFailureException.class, () -> this.repository.save(savedLevel));
    }

    @Test
    void shouldBeAuditedProperly() {

        final Level level = new Level();
        level.setDescription("My description");

        final Level savedLevel = this.repository.save(level);

        assertThat(savedLevel)
                .hasNoNullFieldsOrProperties();
    }
}