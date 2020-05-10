/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

import java.io.File;

/**
 *
 * @author pedro
 * @param <T>
 */
public class HashTable<T> {

    private int busy; //tamanio del vector y porcetaje ocupado actualmente
    private final int size; //tamanio del vector que se utilizara
    private NodoHash[] array; //lista de donde se almacenara los datos

    public HashTable(int size) {
        this.size = size;
        array = new NodoHash[this.size];
    }

    //una funcion util para generar el hash
    private int function_hash(int key) {
        return ((key % size));
    }

    //funcion util que devuelve el porcentaje ocupado
    private float busy_porcent() {
        return (busy / size) * 100;
    }

    //funcion util para insertar un elemento dentro del array
    public void insert(T data, int key) {

        boolean insert = false;
        if (busy_porcent() < 90.00) {
            int hash = function_hash(key);
            if (this.array[hash] == null) {
                array[hash] = new NodoHash();
                array[hash].getList_user().add_queue((User) data);
                insert = true;
                busy++;
            } else if (array[hash] != null) {
                array[hash].getList_user().add_queue((User) data);
            }
        }
    }

    //metodo privado  para graficar el tabla hash en graphviz
    private void generate_source_dot(StringBuilder dotsource) {
        dotsource.append("nodel[label=\"Vector");
        StringBuilder nodes = new StringBuilder(); //string para nodos
        StringBuilder dir = new StringBuilder(); //string para direccion a que nodos debe apuntar
        for (int i = 0; i < size; i++) {
            if (array[i] != null) {
               // System.out.print("[" + i + "] -> ");//encabezado
                dotsource.append("|<f").append(i).append(">").append(i);//vector posiciones llenas
                dir.append("nodel:f").append(i).append(" -> ");
                LinkedList<User> tmp = array[i].getList_user();
                nodes.append("node").append(i).append("[label=\"{<g> |");
                for (int j = 0; j < tmp.getSize(); j++) {
                    User temp = tmp.getData();
                    nodes.append(temp.getNombre()).append("\\n").append(temp.getNumero_carne()).append("\\n").append(temp.getPassword()).append("|");
                }
                nodes.append("<s>}\"]\n");
                dir.append("node").append(i).append(":g[arrowtail=dot, dir=both,tailclip=false];\n");
            }
        }
        dotsource.append("\", height=.5];\n");
        dotsource.append(nodes);
        dotsource.append(dir);
    }

    //metod publico para generar imagen
    public String report() {
        StringBuilder dotsource = new StringBuilder();
        Graphviz graph = new Graphviz();
        graph.addln(graph.start_graph());
        graph.addln("rankdir=LR;");
        graph.addln("node [shape=record, color=blue, height=.1, widht=.1];");
        graph.addln("edge[color=red];");
        graph.addln("graph [nodesep=0.5];");
        generate_source_dot(dotsource);
        graph.add(dotsource.toString());
        graph.add(graph.end_graph());
        File f = new File("Hashtable.png");
        graph.writeGraphToFile(graph.getGraph(graph.getDotSource(), "png"), f);
        return graph.getPath();
    }
    
    //metodo para buscar en tabla hash
    public User search(int key){
        int i = function_hash(key);
        if (array[i]!=null) {
            LinkedList<User> tmp = array[i].getList_user();
            for (int j = 0; j < tmp.getSize(); j++) {
                if (tmp.getData().getNumero_carne()==key) {
                    return tmp.getData();
                }
            }
        }
        return null;
    }
    
    //metodo para elimar de la tabla hash
    public boolean delete(int key){
        int i = function_hash(key);
        if (array[i]!=null) {
            LinkedList<User> tmp = array[i].getList_user();
            for (int j = 0; j < tmp.getSize(); j++) {
                User temp = tmp.getData();
                if (temp.getNumero_carne()==key) {
                    tmp.delete_data(temp);
                    return true;
                }
            }
        }
        return false;
    }
    
}
