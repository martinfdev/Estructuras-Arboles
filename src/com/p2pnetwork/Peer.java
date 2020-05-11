/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.p2pnetwork;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro
 */
public final class Peer {
    private final String ip; //ip del servidor al cual se desea conectar
    private final int port; //puerto local que se habilita para estar a la escucha de peticiones entrantes

    public Peer(String ip, int port) {
       this.ip=ip; 
       this.port = port;
       start_server();
    }
    
    //incia el servidor para la escucha
    public void start_server(){
         Runnable ser = new Server(port);
            Thread t = new Thread(ser);
            t.start();
    }
   
    //para obtener nuestra ip local
    public String getIPLocal(){
        InetAddress ip = null;
        try {
            ip = InetAddress.getLocalHost();
            System.out.println(ip);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Peer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ip.toString();
    }
}
