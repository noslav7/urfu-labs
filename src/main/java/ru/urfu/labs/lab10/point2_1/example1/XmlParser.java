package ru.urfu.labs.lab10.point2_1.example1;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.nio.file.Path;

/**
 * Читает XML-файл библиотеки и выводит в консоль данные о каждой книге.
 */
public class XmlParser {

    private static final Path XML_PATH = Path.of(
            "src", "main", "resources", "lab10", "point2_1", "example1", "example.xml"
    );

    /**
     * Открывает XML-файл и выводит в консоль корневой элемент и данные по всем книгам.
     */
    public static void main(String[] args) {
        try {
            File inputFile = XML_PATH.toFile();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            System.out.println("Корневой элемент: " + doc.getDocumentElement().getNodeName());

            NodeList nodeList = doc.getElementsByTagName("book");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                System.out.println("\nТекущий элемент: " + node.getNodeName());

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("Название книги: "
                            + element.getElementsByTagName("title").item(0).getTextContent());
                    System.out.println("Автор: "
                            + element.getElementsByTagName("author").item(0).getTextContent());
                    System.out.println("Год издания: "
                            + element.getElementsByTagName("year").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
