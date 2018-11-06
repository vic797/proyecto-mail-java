/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import javax.swing.JOptionPane;


public class HiloMensajes extends Thread{
    
    String mensaje;
    String titulo;

    public HiloMensajes(String mensaje, String titulo) {
        this.mensaje = mensaje;
        this.titulo = titulo;
    }
    
    public void run(){
        JOptionPane.showMessageDialog(null,mensaje,titulo,JOptionPane.DEFAULT_OPTION);
    }
    
}
