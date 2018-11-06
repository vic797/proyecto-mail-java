package mailclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zyoruk
 */
public class MailClient extends Thread{
    
    private PrintWriter writer;
    private BufferedReader reader;
    private Socket socket;
    private final String ip;
    private final int port;
    private boolean keepRunning;
    
    public MailClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
        keepRunning = true;
    }
    
    
    /**
     * Method that connects to the server
     */
    @Override
    public void run() {
        try {
            InetAddress server = InetAddress.getByName(ip);

            this.socket = new Socket(server, this.port);
            this.writer = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    this.socket.getOutputStream())
                    ), true);
            this.reader = new BufferedReader(new InputStreamReader(
                    this.socket.getInputStream()));
            
            while ( this.keepRunning ){
                //Keep alive
            }

        } catch (UnknownHostException ex) {
            Logger.getLogger(MailClient.class.getName()).log(
                    Level.INFO, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MailClient.class.getName()).log(
                    Level.INFO, null, ex);
        }
    }
    
    @Override 
    public void interrupt(){
        try {
            this.socket.close();
            this.keepRunning = false;
        } catch (IOException ex) {
            Logger.getLogger(MailClient.class.getName()).log(
                    Level.INFO, null, ex);
        }
    }
    
    public void sendXML(String xml) {
        if (writer == null) return;
        writer.println(xml);
        writer.flush();
    }
}
