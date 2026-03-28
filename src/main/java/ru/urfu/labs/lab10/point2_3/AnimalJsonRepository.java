package ru.urfu.labs.lab10.point2_3;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Отвечает за чтение, поиск, добавление и удаление животных в JSON-файле.
 */
public class AnimalJsonRepository {

    private static final Path JSON_PATH = Path.of(
            "src", "main", "resources", "lab10", "point2_3", "animals.json"
    );

    /**
     * Возвращает всех животных из массива animals.
     */
    public List<Animal> findAll() throws Exception {
        JSONArray animalsArray = readAnimalsArray();
        return toAnimalsFromArray(animalsArray);
    }

    /**
     * Ищет животных по имени (частичное совпадение, без учета регистра) через stream + filter.
     */
    public List<Animal> findByName(String nameQuery) throws Exception {
        String normalized = normalize(nameQuery);
        JSONArray animalsArray = readAnimalsArray();

        List<JSONObject> matched = asJsonObjectList(animalsArray).stream()
                .filter(item -> normalize(stringOf(item.get("name"))).contains(normalized))
                .collect(Collectors.toList());

        return toAnimalsFromObjects(matched);
    }

    /**
     * Ищет животных по виду (частичное совпадение, без учета регистра) через stream + filter.
     */
    public List<Animal> findBySpecies(String speciesQuery) throws Exception {
        String normalized = normalize(speciesQuery);
        JSONArray animalsArray = readAnimalsArray();

        List<JSONObject> matched = asJsonObjectList(animalsArray).stream()
                .filter(item -> normalize(stringOf(item.get("species"))).contains(normalized))
                .collect(Collectors.toList());

        return toAnimalsFromObjects(matched);
    }

    /**
     * Добавляет новое животное в JSON-массив через JSONArray.add().
     */
    public void addAnimal(Animal animal) throws Exception {
        JSONObject root = readRoot();
        JSONArray animalsArray = getOrCreateAnimalsArray(root);
        asJsonObjectList(animalsArray).add(toJson(animal));
        saveRoot(root);
    }

    /**
     * Удаляет животных по точному имени (без учета регистра) через Iterator.remove().
     *
     * @return количество удаленных записей
     */
    public int deleteByName(String name) throws Exception {
        JSONObject root = readRoot();
        JSONArray animalsArray = getOrCreateAnimalsArray(root);
        String normalizedName = normalize(name);

        int removed = 0;
        Iterator<JSONObject> iterator = asJsonObjectList(animalsArray).iterator();
        while (iterator.hasNext()) {
            JSONObject animal = iterator.next();
            if (normalize(stringOf(animal.get("name"))).equals(normalizedName)) {
                iterator.remove();
                removed++;
            }
        }

        if (removed > 0) {
            saveRoot(root);
        }
        return removed;
    }

    private JSONObject readRoot() throws Exception {
        ensureJsonExists();
        JSONParser parser = new JSONParser();
        try (InputStreamReader reader = new InputStreamReader(
                new FileInputStream(JSON_PATH.toFile()), StandardCharsets.UTF_8
        )) {
            Object parsed = parser.parse(reader);
            if (parsed instanceof JSONObject root) {
                if (!root.containsKey("animals") || !(root.get("animals") instanceof JSONArray)) {
                    putAnimalsArray(root, new JSONArray());
                }
                return root;
            }
        }

        JSONObject fallback = new JSONObject();
        putAnimalsArray(fallback, new JSONArray());
        return fallback;
    }

    private void saveRoot(JSONObject root) throws Exception {
        Files.createDirectories(JSON_PATH.getParent());
        try (OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(JSON_PATH.toFile()), StandardCharsets.UTF_8
        )) {
            writer.write(root.toJSONString());
        }
    }

    private void ensureJsonExists() throws Exception {
        if (Files.exists(JSON_PATH)) {
            return;
        }
        Files.createDirectories(JSON_PATH.getParent());
        JSONObject root = new JSONObject();
        putAnimalsArray(root, new JSONArray());
        saveRoot(root);
    }

    private JSONArray readAnimalsArray() throws Exception {
        JSONObject root = readRoot();
        return getOrCreateAnimalsArray(root);
    }

    private JSONArray getOrCreateAnimalsArray(JSONObject root) {
        Object value = root.get("animals");
        if (value instanceof JSONArray array) {
            return array;
        }
        JSONArray array = new JSONArray();
        putAnimalsArray(root, array);
        return array;
    }

    private List<Animal> toAnimalsFromArray(JSONArray animalsArray) {
        List<JSONObject> items = asJsonObjectList(animalsArray);
        return toAnimalsFromObjects(items);
    }

    private List<Animal> toAnimalsFromObjects(List<JSONObject> objects) {
        List<Animal> animals = new ArrayList<>();
        for (JSONObject object : objects) {
            animals.add(fromJson(object));
        }
        return animals;
    }

    private JSONObject toJson(Animal animal) {
        JSONObject object = new JSONObject();
        putField(object, "name", animal.getName());
        putField(object, "species", animal.getSpecies());
        putField(object, "breed", animal.getBreed());
        putField(object, "age", animal.getAge());
        putField(object, "owner", animal.getOwner());
        return object;
    }

    private Animal fromJson(JSONObject object) {
        return new Animal(
                stringOf(object.get("name")),
                stringOf(object.get("species")),
                stringOf(object.get("breed")),
                intOf(object.get("age")),
                stringOf(object.get("owner"))
        );
    }

    private String stringOf(Object value) {
        return value == null ? "" : String.valueOf(value);
    }

    private int intOf(Object value) {
        if (value instanceof Number number) {
            return number.intValue();
        }
        try {
            return Integer.parseInt(stringOf(value).trim());
        } catch (Exception ignored) {
            return 0;
        }
    }

    private String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT);
    }

    @SuppressWarnings("unchecked")
    private void putAnimalsArray(JSONObject root, JSONArray array) {
        // JSONObject в json-simple не типизирован, делаем один контролируемый cast.
        ((Map<String, Object>) (Map<?, ?>) root).put("animals", array);
    }

    @SuppressWarnings("unchecked")
    private void putField(JSONObject object, String key, Object value) {
        ((Map<String, Object>) (Map<?, ?>) object).put(key, value);
    }

    @SuppressWarnings("unchecked")
    private List<JSONObject> asJsonObjectList(JSONArray array) {
        // JSONArray в json-simple не типизирован, поэтому делаем один контролируемый cast здесь.
        return (List<JSONObject>) (List<?>) array;
    }
}
