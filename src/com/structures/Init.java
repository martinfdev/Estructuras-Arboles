/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.structures;


import com.interfaces.Login;
import com.p2pnetwork.*;
/**
 *
 * @author pedro
 */
public class Init {

    HashTable<User> table_user;//tabla has para usuarios
    LinkedList<NodeNet> node_network;//lista simple de nodos para usuarios
    DoubleLinkedList<Block> listblock;//lista doble para los bloques en el block chain
    Peer servidor;
    AVLTree biblioteca_categoria;
    
    public Init() {    
        servidor = new Peer();
        table_user = new HashTable<>(45);//limite para la tabla has es de 45 y libre con una lista siple de usuarios
        node_network = new LinkedList<>();//lista de nodos en la red
        listblock = new DoubleLinkedList<>();//lista de bloques
        biblioteca_categoria = new AVLTree();
        Login login = new Login(table_user, node_network, listblock, servidor, biblioteca_categoria);
        login.setVisible(true);
       // test();
    }

    private void test() {

//           Peer red = new Peer("localhost", 6660);
//           red.start_server();
//          // red.getIPLocal();
//           Client cliente = new Client("localhost", 6660);
//           cliente.send_data("hola");
//           System.out.println("servidor en escucha segundo plano");
    }
}
