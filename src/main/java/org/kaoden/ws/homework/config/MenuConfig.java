package org.kaoden.ws.homework.config;

import org.kaoden.ws.homework.service.Menu;
import org.kaoden.ws.homework.obj.Entry;
import org.kaoden.ws.homework.service.Finder;
import org.kaoden.ws.homework.service.Reader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Map;
import java.util.UUID;

@Configuration
@Import(ReaderConfig.class)
public class MenuConfig {

    @Bean
    public Menu menu() {
        return new Menu(finder());
    }

    @Bean
    public Finder finder() {
        return new Finder(entries());
    }

    @Bean
    public Map<UUID, Entry> entries() {
        return reader().readEntriesFromJson();
    }

    @Bean
    public Reader reader() {
        return new Reader();
    }
}
