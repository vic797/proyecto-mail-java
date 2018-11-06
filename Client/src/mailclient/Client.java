/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailclient;

import javax.swing.UIManager;

/**
 *
 * @author zyoruk
 */
public class Client {
    CommunicationService communicationService;   
    
    public Client () { 
        this.communicationService = new CommunicationService();
    }
    
    public void init ( ){ 
        this.communicationService.init();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new GUIRunnable(this.communicationService));
    }
    
    public class GUIRunnable implements Runnable {
        CommunicationService communicationService;
        GUIRunnable (CommunicationService communicationService) { 
            this.communicationService = communicationService;
        }
        @Override
        public void run() {
             new MainFrame(this.communicationService).setVisible(true);
        }
        
    }
    
        /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Client client = new Client();     
        client.init();
    }
}
