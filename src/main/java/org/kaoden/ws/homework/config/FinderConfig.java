package org.kaoden.ws.homework.config;

import org.kaoden.ws.homework.service.Finder;
import org.kaoden.ws.homework.service.Reader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FinderConfig {

    @Bean
    public Finder finder(Reader reader) {
        return new Finder(reader.readEntriesFromJson());
    }
}
