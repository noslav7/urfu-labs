package ru.urfu.labs.lab10.point2_2;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Отвечает за чтение, запись, поиск и удаление данных о животных в XML-файле.
 */
public class AnimalXmlRepository {

    private static final Path XML_PATH = Path.of(
            "src", "main", "resources", "lab10", "point2_2", "animals.xml"
    );

    /**
     * Возвращает список всех животных из XML.
     */
    public List<Animal> findAll() throws Exception {
        Document document = readDocument();
        NodeList nodeList = document.getElementsByTagName("animal");
        return elementsFromNodeList(nodeList).stream()
                .map(this::fromElement)
                .collect(Collectors.toList());
    }

    /**
     * Добавляет новое животное в XML-файл.
     */
    public void addAnimal(Animal animal) throws Exception {
        Document document = readDocument();
        Element root = document.getDocumentElement();
        root.appendChild(toElement(document, animal));
        saveDocument(document);
    }

    /**
     * Ищет животных по имени (частичное совпадение, без учета регистра).
     */
    public List<Animal> findByName(String nameQuery) throws Exception {
        String normalized = normalize(nameQuery);
        Document document = readDocument();
        NodeList nodeList = document.getElementsByTagName("animal");

        return elementsFromNodeList(nodeList).stream()
                .filter(element -> normalize(textOf(element, "name")).contains(normalized))
                .map(this::fromElement)
                .collect(Collectors.toList());
    }

    /**
     * Ищет животных по виду (частичное совпадение, без учета регистра).
     */
    public List<Animal> findBySpecies(String speciesQuery) throws Exception {
        String normalized = normalize(speciesQuery);
        Document document = readDocument();
        NodeList nodeList = document.getElementsByTagName("animal");

        return elementsFromNodeList(nodeList).stream()
                .filter(element -> normalize(textOf(element, "species")).contains(normalized))
                .map(this::fromElement)
                .collect(Collectors.toList());
    }

    /**
     * Удаляет все записи животных с указанным именем (без учета регистра).
     *
     * @return количество удаленных записей
     */
    public int deleteByName(String name) throws Exception {
        Document document = readDocument();
        NodeList nodeList = document.getElementsByTagName("animal");

        String normalizedName = normalize(name);
        List<Element> toRemove = elementsFromNodeList(nodeList).stream()
                .filter(element -> normalize(textOf(element, "name")).equals(normalizedName))
                .collect(Collectors.toList());

        toRemove.forEach(element -> {
            Node parentNode = element.getParentNode();
            parentNode.removeChild(element);
        });

        if (!toRemove.isEmpty()) {
            saveDocument(document);
        }

        return toRemove.size();
    }

    private Document readDocument() throws Exception {
        ensureXmlExists();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(XML_PATH.toFile());
        document.getDocumentElement().normalize();
        return document;
    }

    private void saveDocument(Document document) throws Exception {
        Files.createDirectories(XML_PATH.getParent());
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(XML_PATH.toString()));
        transformer.transform(source, result);
    }

    private void ensureXmlExists() throws Exception {
        if (Files.exists(XML_PATH)) {
            return;
        }

        Files.createDirectories(XML_PATH.getParent());
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        Element root = document.createElement("animals");
        document.appendChild(root);
        saveDocument(document);
    }

    private Element toElement(Document document, Animal animal) {
        Element animalElement = document.createElement("animal");
        animalElement.appendChild(createField(document, "name", animal.getName()));
        animalElement.appendChild(createField(document, "species", animal.getSpecies()));
        animalElement.appendChild(createField(document, "breed", animal.getBreed()));
        animalElement.appendChild(createField(document, "age", String.valueOf(animal.getAge())));
        animalElement.appendChild(createField(document, "owner", animal.getOwner()));
        return animalElement;
    }

    private Animal fromElement(Element element) {
        String name = textOf(element, "name");
        String species = textOf(element, "species");
        String breed = textOf(element, "breed");
        String owner = textOf(element, "owner");
        int age = parseAge(textOf(element, "age"));
        return new Animal(name, species, breed, age, owner);
    }

    private Element createField(Document document, String fieldName, String value) {
        Element field = document.createElement(fieldName);
        field.appendChild(document.createTextNode(value));
        return field;
    }

    private String textOf(Element parent, String tagName) {
        NodeList list = parent.getElementsByTagName(tagName);
        if (list.getLength() == 0 || list.item(0) == null) {
            return "";
        }
        return list.item(0).getTextContent();
    }

    private int parseAge(String rawAge) {
        try {
            return Integer.parseInt(rawAge.trim());
        } catch (Exception ignored) {
            return 0;
        }
    }

    private List<Element> elementsFromNodeList(NodeList nodeList) {
        return IntStream.range(0, nodeList.getLength())
                .mapToObj(nodeList::item)
                .filter(node -> node.getNodeType() == Node.ELEMENT_NODE)
                .map(node -> (Element) node)
                .collect(Collectors.toList());
    }

    private String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT);
    }
}
