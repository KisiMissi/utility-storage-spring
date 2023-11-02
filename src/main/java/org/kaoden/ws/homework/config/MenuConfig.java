package org.kaoden.ws.homework.config;

import lombok.RequiredArgsConstructor;
import org.kaoden.ws.homework.service.Menu;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MenuConfig implements CommandLineRunner {

    private final Menu menu;

    @Override
    public void run(String... args) {
        menu.selectSearchMode();
    }
}
