package com.mediscreen.services.note.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mediscreen.services.note.repository.NoteRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

@Configuration
public class PopulatorConfig {

    private final NoteRepository noteRepository;

    public PopulatorConfig(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Bean
    public Jackson2RepositoryPopulatorFactoryBean getRepositoryPopulator() {
        noteRepository.deleteAll();
        Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        factory.setMapper(objectMapper);
        factory.setResources(new Resource[]{
                new ClassPathResource("notes.json")
        });
        return factory;
    }
}
