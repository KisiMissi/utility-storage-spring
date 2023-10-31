package org.kaoden.ws.homework.config;

import org.kaoden.ws.homework.service.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MenuConfig implements CommandLineRunner {

    private Menu menu;

    @Autowired
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void run(String... args) {
        menu.selectSearchMode();
    }
}
