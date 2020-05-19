/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.structures;

import java.io.File;

/**
 *
 * @author pedro
 * @param <T>
 */
public class ReportList {

    Graphviz graph;

    public ReportList() {
    }

    //metodo para hacer reporte de las fichas
    public String reportelista(Node<BooksCategory> root, int size, String nombre) {
        graph = new Graphviz();
        graph.addln(graph.start_graph());
        graph.addln("rankdir=LR;");
        graph.addln("node [shape=record, color=blue]; ");
        graph.addln();
        int contador = 0;
        String nodos="", enlaces="";
        Node<BooksCategory> temp = root;
        if (temp==null) {
            nodos = "La Lista está vacía ";
        } else {
            while (temp != null) {
                if (contador < size - 1) {
                    nodos = nodos + "node" + contador+ " [label=\"{" + temp.getData().getName_category() + "|<b>}\"];\n";
                    enlaces = enlaces + "node" + contador + ":b:c -> node" + (contador + 1) + ":c [arrowtail=dot, dir=both,tailclip=false];\n";
                } else {
                    nodos = nodos + "node" +contador + " [label=\"{" + temp.getData().getName_category() + "|<b>}\"];\n";
                    nodos = nodos + "node" + (contador + 1) + " [shape=point];\n";
                    enlaces = enlaces + "node" + contador + ":b:c -> node" + (contador + 1) + ":c [arrowtail=dot, dir=both,tailclip=false];\n";
                }
                contador++;
                temp = temp.next;
            }
        }
        graph.addln(nodos);
        graph.addln(enlaces);
        graph.addln(graph.end_graph());
        File out = new File(nombre+".png");
        graph.writeGraphToFile(graph.getGraph(graph.getDotSource(), "png"), out);
        return graph.getPath(); 
    }

//reporte de fichas de usuario en el juego
//    void Reporte::ReporteFichasUsuario(ListaDoble<Ficha  
//        *> *lisfichasUsuario, string nombre)
//{   
//    iterador++;
//        graph = new Graphviz();
//        graph -> addln(graph -> start_graph());
//        graph -> addln("rankdir=LR;");
//        graph -> addln("node [shape=record, color=blue, width=0.5, height=0.5]; ");
//        graph -> addln();
//        graph -> addln(nombre);
//        string nodos, enlaces, enlacesIverso;
//        for (int i = 0; i < lisfichasUsuario -> getSize(); i++) {
//            Ficha * tempF = lisfichasUsuario -> getDataNext();
//            if (i < lisfichasUsuario -> getSize() - 1) {
//                nodos = nodos + "node" + to_string(i) + " [label=\"{<a>|" + tempF -> getLetra() + "|<b>}\"];\n";
//                enlaces = enlaces + "node" + to_string(i) + ":b:c -> node" + to_string(i + 1) + ":a:c [arrowtail=dot, dir=both,tailclip=false];\n";
//                enlacesIverso = enlacesIverso + "node" + to_string(i + 1) + ":a:c -> node" + to_string(i) + ":b:c [arrowtail=dot, dir=both,tailclip=false];\n";
//            } else {
//                nodos = nodos + "node" + to_string(i) + " [label=\"{<a>|" + tempF -> getLetra() + "|<b>}\"];\n";
//            }
//        }
//        graph -> addln(nodos);
//        graph -> addln(enlaces);
//        graph -> addln(enlacesIverso);
//        graph -> addln(graph -> end());
//        graph -> dotGraphGenerator("ListaDobleFichasUsuario" + to_string(iterador), graph -> getDotSource());
//        delete graph;
//    }
}
