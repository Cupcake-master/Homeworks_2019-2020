package ru.bulat;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.PrettyXmlSerializer;
import org.htmlcleaner.TagNode;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Bilalov_Bulat
 * @version 1.0
 * @date 24.09.2019
 */

public class ReadHTML {
    private static final String site_address = "http://noutbukov.net/shop/";
    private static File file_xml = new File("src/main/java/ru/bulat/good.xml");
    private static ArrayList<Laptop> laptops = new ArrayList<>();
    private static XPathFactory factory = XPathFactory.newInstance();
    private static XPath xPath = factory.newXPath();

    private static final String descriptionIOException = "Can't get XML by URL! (IOException)";
    private static final String descriptionSAXException = "Can't read downloaded XML! (SAXException)";
    private static final String descriptionXPathExpressionException = "Can't parse xPath expression! (XPathExpressionException)";

    public static void main(String[] args) {
        try {
            cleaning();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xml = builder.parse(file_xml);
            creationOfLaptops(xml);
            insertingAttributes(xml);
            addingOtherAttributes(xml);
        } catch (IOException ex) {
            DescriptionOfTheException(descriptionIOException, ex);
        } catch (SAXException ex) {
            DescriptionOfTheException(descriptionSAXException, ex);
        } catch (ParserConfigurationException ex) {
            DescriptionOfTheException("Can't create DocumentBuilder! (ParserConfigurationException)", ex);
        } finally {
            conclusionLaptops();
        }
    }


    private static void creationOfLaptops(Document xml) {
        try {
            int count_laptops = ((Number) xPath.evaluate("count(//font)", xml, XPathConstants.NUMBER)).intValue();
            for (int i = 0; i < count_laptops; i++) {
                laptops.add(new Laptop());
            }
        } catch (XPathExpressionException ex) {
            DescriptionOfTheException("Can't parse xPath expression! (XPathExpressionException)", ex);
        }
    }


    private static void cleaning() {
        try {
            CleanerProperties cleaner = new CleanerProperties();
            cleaner.setTranslateSpecialEntities(true);
            cleaner.setTransResCharsToNCR(true);
            cleaner.setOmitComments(true);
            TagNode tagNode = new HtmlCleaner(cleaner).clean(new URL(site_address));
            new PrettyXmlSerializer(cleaner).writeToFile(tagNode, "src/main/java/ru/bulat/good.xml", "utf-8");
        } catch (IOException ex) {
            DescriptionOfTheException(descriptionIOException, ex);
        }
    }

    private static void insertingAttributes(Document xml) {
        NodeList all_colors = xml.getDocumentElement().getElementsByTagName("font");
        for (int i = 0; i < all_colors.getLength(); i++) {
            Node a = all_colors.item(i);
            NamedNodeMap find_color = a.getAttributes();
            String color = find_color.getNamedItem("color").getNodeValue() == null ? "Not selected" : find_color.getNamedItem("color").getNodeValue();
            laptops.get(i).setColor(color);
        }
        NodeList all_links = xml.getDocumentElement().getElementsByTagName("a");
        Pattern patternForLink = Pattern.compile("/shop/mid/[0-9].+/");
        Set<String> set_all_links = new HashSet<>();
        int useful = 0;
        for (int i = 0; i < all_links.getLength(); i++) {
            Node b = all_links.item(i);
            NamedNodeMap find_link = b.getAttributes();
            String link = find_link.getNamedItem("href").getNodeValue();
            Matcher matcherForLink = patternForLink.matcher(link);
            if (matcherForLink.matches()) {
                boolean check = set_all_links.add(link);
                if (!check) {
                    laptops.get(useful).setLink(link);
                    useful++;
                }
            }
        }
    }

    private static void addingOtherAttributes(Document xml) {
        try {
            int count_laptops = ((Number) xPath.evaluate("count(//font)", xml, XPathConstants.NUMBER)).intValue();
            for (int i = 1; i <= count_laptops; i++) {
                String price = xPath.evaluate(("//font[" + i + "]/b"), xml);
                laptops.get(i - 1).setPrice(price);
            }
            //int count_differentNames = ((Number) xPath.evaluate("count(//td/div/a)", xml, XPathConstants.NUMBER)).intValue();
//            for (int j = 1; j <= count_differentNames; j++) {
//                String name = xPath.evaluate("count(//td/div/a)", xml);
//                Pattern patternForName = Pattern.compile("");
//                Matcher matcherForName = patternForName.matcher("");
//                matcherForName.matches();
//            }
        } catch (XPathExpressionException ex) {
            DescriptionOfTheException(descriptionXPathExpressionException, ex);
        }
    }

    private static void conclusionLaptops() {
        for (Laptop x : laptops) {
            System.out.println("Laptop: ");
            System.out.println("Link: " + x.getLink());
            System.out.println("Name: " + x.getName());
            System.out.println(x.getCharacteristics());
            System.out.println("Color: " + x.getColor());
            System.out.println(x.getPrice());
            System.out.println("----------------------");
        }
    }

    private static void DescriptionOfTheException(String description, Exception ex) {
        System.out.println(description);
        Logger.getLogger(ReadHTML.class.getName()).log(Level.SEVERE, null, ex);
    }

}
