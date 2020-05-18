/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.structures;

import java.io.File;
import java.util.Stack;

/**
 *
 * @author pedro
 */
public class BTree {

    private BNode root; //puntero al nodo raiz
    private final int t; //grado minimo
    private int count;
    String tempnivel = "";

    public BTree(int t) {
        root = null;
        this.t = t;
    }

    public void traverse() {
        if (this.root != null) {
            this.root.traverse();
            System.out.println("");
        }
    }

    //funcion para buscar una clave en el arbol
    public Book search(int k) {
        if (this.root == null) {
            return null;
        } else {
            return this.root.search(k);
        }
    }

    public void insert(Book k) {
        // Si el árbol está vacío
        if (root == null) {
            // Asigna memoria para la raiz 
            root = new BNode(t, true);
            root.key[0] = k;  // Insetar la clave 
            root.n = 1;  // Actualiza el numero de claves en la raiz 
        } else // Si el arbol no esta vacio 
        {
            // Si la raíz está llena, entonces el árbol crece en altura
            if (root.n == 2 * t - 1) {
                // Asigna memoria para la nueva raiz
                BNode s = new BNode(t, false);

                // Hacer antigua la raiz como hija de la nueva raiz 
                s.child[0] = root;

                // Divide la raíz anterior y mueve 1 clave a la nueva raíz
                s.splitChild(0, root);

                // La nueva raíz tiene dos hijos ahora. Decidir cuál de los
                // dos hijos van a tener una nueva clave
                int i = 0;
                if (s.key[0].getISBN() < k.getISBN()) {
                    i++;
                }
                s.child[i].insertNonFull(k);

                // cambiar la raiz 
                root = s;
            } else // Si la raíz no está llena, llamar a insertNonFull para la raíz
            {
                root.insertNonFull(k);
            }
        }
    }

    public void remove(int k) {
        if (root == null) {
            System.out.println("El arbol esta vacio");
            return;
        }
        // // Llamar a la función remove para la raiz
        root.remove(k);

        // Si el nodo raíz tiene 0 claves, hacer su primer hijo como la nueva raíz
        // si tiene un hijo, de lo contrario establecer la raiz como null
        if (root.n == 0) {
            BNode tmp = root;
            if (root.leaf) {
                root = null;
            } else {
                root = root.child[0];
            }
        }
    }

    private void report_graph(BNode root, StringBuilder dotSource, Stack pila) {
        int i;
        if (root != null) {
            for (i = 0; i < root.n; i++) {
                if (root.leaf == false) {
                    // System.out.println("nodo"+count);
                    System.out.println("izquierda " + root.key[i].getISBN());
                    pila.push(root.key[i]);//pila
                    report_graph(root.child[i], dotSource, pila);
                    if (pila.peek() == root.key[i]) {
                        pila.pop();

                        dotSource.append("nodo" + count + "[ label = ;");
                        count++;
                        System.out.println("");//moviendo para la derecha
                        System.out.println("Derecha " + root.key[i].getISBN());
                    }
                } else {
                    System.out.print(root.key[i].getISBN() + "\t");
                }
            }
            if (root.leaf == false) {
                System.out.println("---------------------------------------");
                report_graph(root.child[i], dotSource, pila);
            }
        }
    }

    public void report() {
        Stack pila = new Stack();
        StringBuilder dotSource = new StringBuilder();
        Graphviz grap = new Graphviz();
        grap.addln(grap.start_graph());
        grap.addln("rankdir=TB;\nnode [shape = record, color=blue];");
        report_graph(root, dotSource, pila);//metodo recursivo para recorrer el arbol
        System.out.println("");//salto de linea temporal
        grap.add(dotSource.toString());
        grap.add(grap.end_graph());
        File f = new File("Btree.png");
        grap.writeGraphToFile(grap.getGraph(grap.getDotSource(), "png"), f);

        System.out.println(grap.getDotSource());
        // System.out.println( grap.getPath());
    }

    private void listBook(BNode root, LinkedList<Book> listB) {
        int i;
        if (root != null) {
            for (i = 0; i < root.n; i++) {
                if (root.leaf == false) {
                    listBook(root.child[i], listB);
                    //System.out.print(root.key[i].getISBN() + "\t");
                } else {
                    listB.add_queue(root.key[i]);
                    //System.out.print(root.key[i].getISBN() + "\t");
                }
            }
            if (root.leaf == false) {
                listBook(root.child[i], listB);
            }
        }
    }

    public LinkedList<Book> listaLibros() {
        LinkedList<Book> listB = new LinkedList<>();
        listBook(root, listB);
        return listB;
    }
}
