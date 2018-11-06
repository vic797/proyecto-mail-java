/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import InterfazGUI_Server.email_server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author jif_c
 */
public class ClientThread extends Thread{
    
    private Socket sock;
    private int cont;
    /*
    private String Body;
    private String subject;
    private String email;
    private String password;
    */
    private BufferedReader input;
    
    
    public ClientThread (Socket sock,int c) {
        this.sock = sock;
        this.cont = c;
        /*
        this.Body = body;
        this.email = email;
        this.subject = subject;
        this.password = passowrd;
        */
        
    }
    
    @Override
    public  void run() {
        try {
            this.input = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));
            HiloMensajes hm = new HiloMensajes("     Esperando archivo de cliente # "+this.cont,"Servidor iniciado");
            hm.start();
            String xml = this.input.readLine();
            XmlDocument doc = new XmlDocument(xml);
            Mail mail = new Mail();
            //pasa los parametros mail y doc al formulario para que sirva de parametros tambien para en metodo EnvioCorreo
            //una ve en el formulario se llena los campos respectivos y se manda como parametros al metodo EnvioCorreo
            email_server gui_email = new email_server(mail, doc,this.cont);
            gui_email.setVisible(true);
            //EnvioCorreo(doc, mail);
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void EnvioCorreo(XmlDocument doc,Mail mail,String Body, String subject, String email, String password){
        try {
                doc.stringXmlConvert();
                for(int i = 0; i < doc.getLength(); i++) {
                    mail.setAddressFrom(email);
                    mail.setAddressTo(doc.getEmail(i));
                    mail.setBody(doc.getFisrtname(i)+ Body);
                    mail.setPassword(password);
                    mail.setSubject(subject);
                    mail.sendMessage();
                }
            } catch (ParserConfigurationException | SAXException | IOException | MessagingException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
