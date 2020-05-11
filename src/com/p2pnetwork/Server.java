/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.p2pnetwork;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro
 */
public class Server implements Runnable{
   private ServerSocket server;
   private DataInputStream in;
   private DataOutputStream out;
   private String ip;
   private final int port; //puerto en el que se habilita

    public Server(int port) {
        this.port = port;
    }
   
   @Override
   public void run(){
       try {
           ServerSocket serv = new ServerSocket(port);
           while (true) {           //aceptamos conexiones infinitamente    
           Socket conect_in = serv.accept();
           in = new DataInputStream(conect_in.getInputStream());
           String dato_entrada = in.readUTF(); //temporal
           System.out.println("El cliente manda: "+dato_entrada);
           conect_in.close();
           }
       } catch (IOException ex) {
           Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
      
}
