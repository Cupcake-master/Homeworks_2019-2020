package ru.bulat;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Bilalov_Bulat
 * @version 1.0
 * @date 22.09.2019
 */
public class ReadXML_XPath {
    private static final String file_path = "src/ru/bulat/file.xml";
    private static Document xml;
    private static ArrayList<Book> books = new ArrayList<>();

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            File file_xml = new File(file_path);
            xml = builder.parse(file_xml);
            XPathFactory factory = XPathFactory.newInstance();
            XPath xPath = factory.newXPath();
            insertingAttributes();
            int count_books = ((Number) xPath.evaluate("count(/bookstore/book)", xml, XPathConstants.NUMBER)).intValue();
            for (int i = 1; i <= count_books; i++) {
                String title = xPath.evaluate(("/bookstore/book[" + i + "]/title"), xml);
                String author = xPath.evaluate(("/bookstore/book[" + i + "]/author"), xml);
                String year = xPath.evaluate(("/bookstore/book[" + i + "]/year"), xml);
                String price = xPath.evaluate(("/bookstore/book[" + i + "]/price"), xml);
                float price1 = Float.parseFloat(price);
                books.get(i - 1).setTitle(title);
                books.get(i - 1).setAuthor(author);
                books.get(i - 1).setYear(Integer.parseInt(year));
                books.get(i - 1).setPrice(price1);
            }
        } catch (XPathExpressionException ex1) {
            System.err.println("Can't parse xPath expression! (XPathExpressionException)");
        } catch (IOException ex2) {
            System.err.println("Can't get XML by URL! (IOException)");
        } catch (SAXException ex3) {
            System.err.println("Can't read downloaded XML! (SAXException)");
        } catch (ParserConfigurationException ex4) {
            System.err.println("Can't create DocumentBuilder! (ParserConfigurationException)");
        } finally {
            printBooks();
        }
    }

    private static void printBooks() {
        books.forEach(books -> {
            System.out.println("Book: ");
            System.out.println("Author: " + books.getAuthor());
            System.out.println("Title: " + books.getTitle());
            System.out.println("Lang: " + books.getLang());
            System.out.println("Year: " + books.getYear());
            System.out.println("Price: " + books.getPrice());
            System.out.println("Category: " + books.getCategory());
            System.out.println("Cover: " + books.getCover());
            System.out.println("---------------------------");
        });
    }

    private static void insertingAttributes() {
        NodeList bookElements1 = xml.getDocumentElement().getElementsByTagName("book");
        NodeList bookElements2 = xml.getDocumentElement().getElementsByTagName("title");
        for (int i = 0; i < bookElements1.getLength(); i++) {
            Node book1 = bookElements1.item(i);
            Node book2 = bookElements2.item(i);
            NamedNodeMap attributes1 = book1.getAttributes();
            NamedNodeMap attributes2 = book2.getAttributes();
            String in1 = attributes1.getNamedItem("category").getNodeValue() == null ? "Not selected" : attributes1.getNamedItem("category").getNodeValue();
            String in2 = attributes1.getNamedItem("cover") == null ? "Not selected" : attributes1.getNamedItem("cover").getNodeValue();
            String in3 = attributes2.getNamedItem("lang").getNodeValue();
            books.add(new Book(in1, in2, in3));
        }
    }
}
