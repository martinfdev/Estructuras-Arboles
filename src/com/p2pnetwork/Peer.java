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
public class Peer {

    private String ip; //ip del servidor al cual se desea conectar
    private int port; //puerto local que se habilita para estar a la escucha de peticiones entrantes

    //constructor por defecto
    public Peer() {
    }

    //constructor con parametros
    public Peer(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    //incia el servidor para la escucha
    public boolean start_server() {
        if (port > 0) {
            Runnable ser = new Server(port);
            Thread t = new Thread(ser);
            t.start();
            return true;
        }else
            return false;     
    }

    //para obtener nuestra ip local
    public String getIPLocal() {
        InetAddress ip = null;
        try {
            ip = InetAddress.getLocalHost();
            System.out.println(ip);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Peer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ip.toString();
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
