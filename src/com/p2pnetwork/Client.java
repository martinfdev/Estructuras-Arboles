/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.p2pnetwork;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author pedro
 */
public class Client {
    private String ip_server;   //obtiene la ip donde esta en escucha el servidor
    private int port; //el pueto abierto para la transeferencia de informacion

    //constructor sin  paramentros
    public Client() {
    }
    
    //constructor con parametros

    public Client(String ip_server, int port) {
        this.ip_server = ip_server;
        this.port = port;
    }
    
    //Envia los datos al servidor
    @SuppressWarnings("ConvertToTryWithResources")
    public void send_data(String data){
        
        try {
            Socket connect_server =  new Socket(ip_server, port);
            DataOutputStream  get_out_data = new DataOutputStream(connect_server.getOutputStream());
            get_out_data.writeUTF(data);//lo que necesitamos enviar al servidor
            get_out_data.close();            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null, "Conexion Rechazada");
        }
    }
    
   
    
    
    
    
    
    
}
