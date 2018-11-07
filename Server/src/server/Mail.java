
package server;

import InterfazGUI_Server.LoginServer;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.AuthenticationFailedException;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class Mail {
    private Properties props;
    private Session session; 
    private String addressTo;
    private String addressFrom;
    private String body;
    private String subject;
    private String password;
    
    public Mail(){
        this.props= new Properties();
        this.session= Session.getDefaultInstance(props);
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.user", "USER GMAIL");
        props.setProperty("mail.smtp.auth", "true");
    }
    
    public void verificar(String correo,String pass) throws MessagingException{
        try{
         //verifica si en from email existe o si hay alguna excepcion se vuelve a pedir  
        Transport t = session.getTransport("smtp");
        t.connect(correo, pass);
        Server.encontrado =true;
        JOptionPane.showMessageDialog(null, "Bienvenido"); 
        }catch(AuthenticationFailedException e){
             JOptionPane.showMessageDialog(null, "Correo o Contraseña incorrecta");  
        }
    }
    
    public void sendMessage() throws MessagingException {
        MimeMessage message = new MimeMessage(this.session);
        try {     
            message.setFrom(new InternetAddress(this.addressFrom));
             message.addRecipient(
            Message.RecipientType.TO,
                new InternetAddress(this.addressTo));
            message.setSubject(this.subject);
            message.setText(this.body);
            
            Transport t = session.getTransport("smtp");            
            t.connect(this.addressFrom, this.password);
            t.sendMessage(message, message.getAllRecipients());              
        } catch (AddressException ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setAddressTo(String address) {
        this.addressTo = address;
    }
    
    public void setAddressFrom(String address) {
        this.addressFrom = address;
    }
    
    public void setBody(String body) {
        this.body = body;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
