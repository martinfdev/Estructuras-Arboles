/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.structures;

/**
 *
 * @author pedro
 */
public class NodeNet {
    private String ip_network;
    private int port;

    public NodeNet(String ip_network, int port) {
        this.ip_network = ip_network;
        this.port = port;
    }

    public String getIp_network() {
        return ip_network;
    }

    public void setIp_network(String ip_network) {
        this.ip_network = ip_network;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
