package org.kaoden.ws.homework;

import org.kaoden.ws.homework.config.MenuConfig;
import org.kaoden.ws.homework.service.Menu;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main( String[] args ) {
        AnnotationConfigApplicationContext appConfig =
                new AnnotationConfigApplicationContext(MenuConfig.class);

        appConfig.getBean("menu", Menu.class).selectSearchMode();
    }
}
