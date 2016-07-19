package by.training.ant.validator.parser;

import java.awt.*;
import java.io.IOException;
import java.util.EnumSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Created by Vladislav on 10.06.2016.
 */
public class DOMParser {
    private static final String NAME = "name";
    private static final String MAIN = "main";
    private static final String DEFAULT = "default";
    private static final String DEPENDS = "depends";
    private boolean checkDepends;
    private boolean checkDefault;
    private boolean checkNames;
    private double[] values;
    private DocumentBuilder docBuilder;
    private Pattern p;
    private Matcher m;

    /**
     * Instantiates a new Dom parser.
     *
     * @param checkDepends the check depends
     * @param checkDefault the check default
     * @param checkNames   the check names
     */
    public DOMParser(final boolean checkDepends, final boolean checkDefault, final boolean checkNames) {
        this.checkDepends = checkDepends;
        this.checkDefault = checkDefault;
        this.checkNames = checkNames;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Validation boolean.
     *
     * @param fileName the file name
     * @return the boolean
     */
    public boolean validation(final String fileName) {
        Document doc = null;
        try {
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();

            if (checkDepends) {
                NodeList list = root.getElementsByTagName(TagsEnum.TARGET.getValue());
                for (int i = 0; i < list.getLength(); i++) {
                    Element el = (Element) list.item(i);
                    if (!el.getAttribute(NAME).equals(MAIN) && el.hasAttribute(DEPENDS)) {
                        return false;
                    }
                }
            }
            if (checkDefault) {
                if (!root.hasAttribute(DEFAULT)) {
                    return false;
                }
            }
            if (checkNames) {
                p = Pattern.compile("^[a-zA-Z][a-zA-Z-]*");
                EnumSet<TagsEnum> listTags = EnumSet.range(TagsEnum.PROPERTY, TagsEnum.TASKDEF);
                for (TagsEnum tag : listTags) {
                    NodeList list = root.getElementsByTagName(tag.getValue());
                    for (int i = 0; i < list.getLength(); i++) {
                        Element el = (Element) list.item(i);
                        if (tag == TagsEnum.MACRODEF) {
                            EnumSet<TagsEnum> listAttrEnum = EnumSet.range(TagsEnum.ELEMENT, TagsEnum.ATTRIBUTE);
                            for (TagsEnum attr : listAttrEnum) {
                                NodeList listAttr = el.getElementsByTagName(attr.getValue());
                                for (int j = 0; j < listAttr.getLength(); j++) {
                                    Element elAttr = (Element) listAttr.item(j);
                                    if (!check(elAttr.getAttribute(NAME))) {
                                        return false;
                                    }
                                }
                            }
                        }
                        if (!check(el.getAttribute(NAME))) {
                            return false;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("File error or I/O error: " + e);
        } catch (SAXException e) {
            System.err.println("Parsing failure: " + e);
        }
        return true;
    }

    private boolean check(final String name) {
        if (!p.matcher(name).matches()) {
            return false;
        }
        return true;
    }
}
