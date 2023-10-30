package org.kaoden.ws.homework.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    @Mock
    private Finder finder;

    @BeforeEach
    void setMockitoAnnotations() {
        MockitoAnnotations.openMocks(this);
    }

    void provideInput(String[] strings) {
        StringBuilder data = new StringBuilder();
        for (String line : strings) {
            data.append(line);
            data.append(System.lineSeparator());
        }
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data.toString().getBytes());
        System.setIn(inputStream);
    }

    @Test
    void findByUUID() {
        // Arrange
        String[] modeAndUUID = {"1", "1a92d32f-8273-4c48-b5e1-3e0b762a7c11", "3"};
        provideInput(modeAndUUID);

        // Act
        new Menu(finder).selectSearchMode();

        // Assert
        verify(finder).findByUUID(UUID.fromString("1a92d32f-8273-4c48-b5e1-3e0b762a7c11"));
    }

    @Test
    void findByName() {
        // Arrange
        String[] modeAndUUID = {"2", "SOME-NAME", "3"};
        provideInput(modeAndUUID);

        // Act
        new Menu(finder).selectSearchMode();

        // Assert
        verify(finder).findByName("SOME-NAME");
    }

    @Test
    void invalidSearchModeNumber() {
        // Arrange
        String[] mode = {"100", "3"};
        provideInput(mode);
        ByteArrayOutputStream outStreamCap = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStreamCap));

        // Act
        new Menu(finder).selectSearchMode();

        // Arrange
        assertTrue(outStreamCap.toString().contains("Неверный режим поиска."));
    }

    @Test
    void invalidSearchModeType() {
        // Arrange
        String[] invalidMode = {"MODE", "3"};
        provideInput(invalidMode);

        // Act
        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> new Menu(finder).selectSearchMode());

        // Assert
        assertEquals("For input string: \"MODE\"", exception.getMessage());
    }

}