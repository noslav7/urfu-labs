package ru.urfu.labs.lab10.point2_1.example1;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.urfu.labs.lab10.ConsoleEncodingUtil;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Создаёт XML-файл с примером структуры библиотеки и записывает его в resources.
 */
public class CreateXmlFile {

    private static final Path XML_PATH = Path.of(
            "src", "main", "resources", "lab10", "point2_1", "example1", "example.xml"
    );

    /**
     * Создаёт XML-документ с корневым элементом library и двумя книгами.
     */
    public static void main(String[] args) {
        ConsoleEncodingUtil.ensureUtf8Console();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.newDocument();
            Element root = doc.createElement("library");
            doc.appendChild(root);

            root.appendChild(createBook(doc, "Война и мир", "Лев Толстой", "1869"));
            root.appendChild(createBook(doc, "Мастер и Маргарита", "Михаил Булгаков", "1967"));

            Files.createDirectories(XML_PATH.getParent());

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.transform(new DOMSource(doc), new StreamResult(XML_PATH.toFile()));
            System.out.println("XML-файл успешно создан: " + XML_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Формирует XML-элемент book с вложенными полями title, author и year.
     *
     * @param doc    XML-документ, в котором создаются узлы
     * @param title  название книги
     * @param author автор книги
     * @param year   год издания
     * @return готовый XML-элемент книги
     */
    private static Element createBook(Document doc, String title, String author, String year) {
        Element book = doc.createElement("book");

        Element titleElement = doc.createElement("title");
        titleElement.appendChild(doc.createTextNode(title));
        book.appendChild(titleElement);

        Element authorElement = doc.createElement("author");
        authorElement.appendChild(doc.createTextNode(author));
        book.appendChild(authorElement);

        Element yearElement = doc.createElement("year");
        yearElement.appendChild(doc.createTextNode(year));
        book.appendChild(yearElement);

        return book;
    }
}
