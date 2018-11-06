/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author jif_c
 */
public class XmlDocument {
    
    private final String xml;
    private final ArrayList<String> id;
    private final ArrayList<String> lastnames;
    private final ArrayList<String> firstname;
    private final ArrayList<String> phone;
    private final ArrayList<String> email;
    
    public XmlDocument(String xml) {
        this.xml = xml;
        this.id = new ArrayList<String>();
        this.lastnames = new ArrayList<String>();
        this.firstname = new ArrayList<String>();
        this.phone = new ArrayList<String>();
        this.email = new ArrayList<String>();
    }
    
    public void stringXmlConvert() throws ParserConfigurationException, SAXException, IOException {  
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
        DocumentBuilder builder;  
        try {  
            builder = factory.newDocumentBuilder();  
            Document document = builder.parse(new InputSource(new StringReader(this.xml))); 
             document.getDocumentElement().normalize();
             System.out.println("Elemento raiz:" + document.getDocumentElement().getNodeName());
              NodeList listaPersonas = document.getElementsByTagName("personne");
               for (int temp = 0; temp < listaPersonas.getLength(); temp++) {
                    Node nodo = listaPersonas.item(temp);
                    System.out.println("Elemento:" + nodo.getNodeName());
                    Element element = (Element) nodo;
                    System.out.println(element.getAttribute("id"));
                    this.firstname.add(element.getElementsByTagName("firstname").item(0).getTextContent());
                    this.lastnames.add(element.getElementsByTagName("lastname").item(0).getTextContent());
                    this.phone.add(element.getElementsByTagName("phone").item(0).getTextContent());
                    this.email.add(element.getElementsByTagName("email").item(0).getTextContent());
               }
        
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
    }
    
    public String getid(int index) {
        return this.id.get(index);
    }
    
    public String getLastame(int index) {
        return this.lastnames.get(index);
    }
    
    public  String getFisrtname(int index) {
        return this.firstname.get(index);
    }
    
    public String getEmail(int index) {
        return this.email.get(index);
    }
    
    public String getPhone(int index) {
        return this.phone.get(index);
    }
    
    public int getLength(){
        return this.phone.size();
    }
}

