package org.kaoden.ws.homework.config;

import org.kaoden.ws.homework.obj.Entry;
import org.kaoden.ws.homework.service.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.UUID;

@Configuration
public class FinderConfig {

    private Reader reader;

    @Autowired
    public void setReader(Reader reader) {
        this.reader = reader;
    }

    @Bean
    public Map<UUID, Entry> entries() {
        return reader.readEntriesFromJson();
    }
}
