/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author jif_c
 */
public class ServerAceppt {
    
     private ServerSocket listener;
     private Socket sock;
     private String body;
     private String subject;
     private String password;
     private String email;
     
     public ServerAceppt() {
         try {
             listener = new ServerSocket(9090);
             //System.out.println(" >> Servidor iniciado..");
             
         } catch (IOException ex) {
             Logger.getLogger(ServerAceppt.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    public void acceptClient() {
        int n = 0;
         try {
             HiloMensajes hm;
             //System.out.println(" >> esperando clientes....");
                hm = new HiloMensajes("        Esperando clientes....","Servidor iniciado");
                hm.start();
             while(n < 5) {
                 
                this.sock = listener.accept();
                String n_cliente = ("    Cliente numero: " + n + " conectado");
                 hm = new HiloMensajes(n_cliente,"Clientes");
                 hm.start();
               
                
                /*recomiendo que por cada cliente que se conecte se abra un form y preguten por
                  las variables como el body, email del enviador, password, y el subject
                  y el clientThread iria dentro de ese form
                */ 
                //adentro del hilo al ser iniciado abre una ventana donde esta esperando en archivo cliente, una vez recibido
                //se abre en formulario email_server
                ClientThread thread = new ClientThread(this.sock,n);
                thread.start();
                n++;
             }
         } catch (IOException ex) {
             Logger.getLogger(ServerAceppt.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
     
}
    
