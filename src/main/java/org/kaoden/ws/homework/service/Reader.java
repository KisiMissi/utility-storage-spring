package org.kaoden.ws.homework.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.kaoden.ws.homework.obj.Entry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class Reader {

    @Value("${data.path}")
    private String filePath;
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public Map<UUID, Entry> readEntriesFromJson() {
        System.out.println(filePath);
        List<Entry> entryList = readEntriesAsList(getFile(filePath));
        return convertListToMap(entryList);
    }

    private File getFile(String filePath) {
        File file = new File(filePath);
        if (file.exists())
            return file;
        else
            throw new RuntimeException("File with this location \"" +filePath+ "\" doesn't exist");
    }

    private List<Entry> readEntriesAsList(File file) {
        try {
            return MAPPER.readValue(file, new TypeReference<>() {});
        } catch (IOException ioe) {
            throw new RuntimeException("An error occurred while reading file: " + file.toString());
        }
    }

    private Map<UUID, Entry> convertListToMap(List<Entry> entryList) {
        Map<UUID, Entry> entries = new HashMap<>();
        entryList.stream()
                .filter(entry -> entry.getUuid() != null & entry.getName() != null)
                .forEach(entry -> entries.put(entry.getUuid(), entry));
        return entries;
    }
}
