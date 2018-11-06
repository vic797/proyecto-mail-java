/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailclient;

/**
 *
 * @author zyoruk
 */
public class CommunicationService {
    MailClient mailClient;
    public CommunicationService () { 
        this.mailClient = new MailClient("localhost", 9090);
    }
    
    public void init ( ) { 
        this.mailClient.start();
    }
    
    public void sendXML ( String xml ) { 
        this.mailClient.sendXML(xml);
    }
}
