package org.kaoden.ws.homework.service;

import org.junit.jupiter.api.Test;
import org.kaoden.ws.homework.obj.Entry;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ReaderTest {

    private final Reader reader = new Reader();

    @Test
    void invalidFilePath() {
        // Arrange
        String filePath = "test.json";
        ReflectionTestUtils.setField(reader, "filePath", filePath);
        String expectedMessage = "File with this location \""+filePath+"\" doesn't exist";

        // Act
        RuntimeException exception = assertThrows(RuntimeException.class, reader::readEntriesFromJson);

        // Asset
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void emptyDataFile() {
        // Arrange
        ReflectionTestUtils.setField(reader, "filePath", "src\\test\\resources\\emptyData.json");
        Map<UUID, Entry> expectedEntries = new HashMap<>();

        // Act
        Map<UUID, Entry> actualEntries = reader.readEntriesFromJson();

        // Assert
        assertEquals(expectedEntries, actualEntries);
    }

    @Test
    void invalidKeyNameInDataFile() {
        // Arrange
        String filePath = "src\\test\\resources\\dataWithInvalidKeyName.json";
        ReflectionTestUtils.setField(reader, "filePath", filePath);
        String expectedMessage = "An error occurred while reading file: " + filePath;

        // Act
        RuntimeException exception = assertThrows(RuntimeException.class, reader::readEntriesFromJson);

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void someFieldsMissingInDataFile() {
        ReflectionTestUtils.setField(reader, "filePath", "src\\test\\resources\\dataWithMissingFields.json");

        // Act
        Map<UUID, Entry> actualEntries = reader.readEntriesFromJson();

        // Arrange
        assertTrue(actualEntries.isEmpty());
    }
}