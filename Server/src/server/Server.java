
package server;

import InterfazGUI_Server.LoginServer;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class Server {

    static boolean encontrado = false;
    
    
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        LoginServer login = new LoginServer();
        login.setVisible(true);
        while(encontrado == false){
            if(login.isActive()){
                //un ciclo lo mantiene pausado hasta que la verificaion del correo sea correcta la variable encontrado
                //cambia a true y sigue su camino
            }
        }
        
        ServerAceppt server = new ServerAceppt();
        server.acceptClient();
    }
    
    
}
