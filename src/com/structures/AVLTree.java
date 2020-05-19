package com.structures;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro
 */
public class AVLTree {

    private NodeAVL root;
    private int count = 0;
    private boolean repetido;

    public AVLTree() {
        this.root = null;
    }

    //funcion util que devuelve la altura del arbol
    private int height(NodeAVL n) {
        if (n == null) {
            return 0;
        }
        return n.height;
    }

    //funcion que duvuelve cual es mayor de dos enteros
    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    //funcion util para rotar el sub-arbol a la derecha
    private NodeAVL rightRotate(NodeAVL y) {
        NodeAVL x = y.left;
        NodeAVL t2 = x.right;

        //realizamos la rotacion
        x.right = y;
        y.left = t2;

        //actualizamos la altura
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        //retornamos la nueva raiz
        return x;
    }

    //fucion util para rotar a la izaquierda
    private NodeAVL leftRotate(NodeAVL x) {
        NodeAVL y = x.right;
        NodeAVL t2 = y.left;

        //realizamos la rotacion
        y.left = x;
        x.right = t2;

        //actualizamos la altura
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        //retornamos la nueva raiz
        return y;
    }

    //obtener el factor de equilibrio del nodo N
    private int getBalance(NodeAVL n) {
        if (n == null) {
            return 0;
        }
        return height(n.left) - height(n.right);
    }

    //metodo para insertar un nodo en el arbol
    private NodeAVL insertNode(NodeAVL node, String nombre_categoria) {
        //realizamos una insercion normal en el arbol
        if (node == null) {
            return (new NodeAVL(nombre_categoria));
        }

        int val = nombre_categoria.compareTo(node.getBcategory().getName_category());
        if (val < 0) {
            node.left = insertNode(node.left, nombre_categoria);
        } else if (val > 0) {
            node.right = insertNode(node.right, nombre_categoria);
        } else //punto donde el nodo es duplicado no hace ninguna operacion duplicado
        {
            repetido = true;
            return node;
        }

        /*actualizar la altura de este nodo */
        node.height = 1 + max(height(node.left), height(node.right));

        //obtener el factor de balance de este nodo y chequear si no esta desbalanceado
        int balance = getBalance(node);

        //si este nodo esta desequilibrado hay 4 casos 
        //caso rotacion simple izquierda
        if (balance > 1 && nombre_categoria.compareTo(node.left.getBcategory().getName_category()) < 0) {
            return rightRotate(node);
        }

        //caso rotacion simple derecha
        if (balance < -1 && nombre_categoria.compareTo(node.right.getBcategory().getName_category()) > 0) {
            return leftRotate(node);
        }

        //rotacion dobble izquerda derecha
        if (balance > 1 && nombre_categoria.compareTo(node.left.getBcategory().getName_category()) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        //rotacion doble derecha inzquierda
        if (balance < -1 && nombre_categoria.compareTo(node.right.getBcategory().getName_category()) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    //metodo publico para insertar datos tipo string en el arbol 
    public void insert(String nombre_categoria) {
        repetido = false;
        root = insertNode(root, nombre_categoria);

    }

    //metodo para recorrer el arbol en modo pre-orden
    private void preOrden(NodeAVL root, LinkedList<BooksCategory> lscategory) {
        if (root != null) {
            lscategory.add_queue(root.getBcategory());
            //System.out.print(root.getBcategory().getName_category() + " ");
            preOrden(root.left, lscategory);
            preOrden(root.right, lscategory);
        }
    }

    //metodo para recorrer el arbol en modo in-orden
    private void inOrden(NodeAVL root, LinkedList<BooksCategory> lscategory) {
        if (root != null) {
            inOrden(root.left, lscategory);
            lscategory.add_queue(root.getBcategory());
            //System.out.print(root.getBcategory().getName_category() + " ");
            inOrden(root.right, lscategory);
        }
    }

    //metodo para recorrer el arbol en modo pos-orden
    private void posOrden(NodeAVL root, LinkedList<BooksCategory> lscategory) {
        if (root != null) {
            posOrden(root.left, lscategory);
            posOrden(root.right, lscategory);
            lscategory.add_queue(root.getBcategory());
            //System.out.print(root.getBcategory().getName_category() + " ");
        }
    }

    //metodo publico para recorrer el arbol en  pre-orden
    public LinkedList<BooksCategory> pre_order() {
        LinkedList<BooksCategory> lscategory = new LinkedList<>();
        preOrden(root, lscategory);
        return lscategory;
        //System.out.println("");
    }

    //metod publico para recorrer el arbol en post-orden
    public LinkedList<BooksCategory> pos_order() {
        LinkedList<BooksCategory> lscategory = new LinkedList<>();
        posOrden(root, lscategory);
        return lscategory;
        //System.out.println("");
    }

    //metodo para recorrer el arbol en in-orden
    public LinkedList<BooksCategory> in_orden() {
        LinkedList<BooksCategory> lscategory = new LinkedList<>();
        inOrden(root, lscategory);
        return lscategory;
        //System.out.println("");
    }

    //metodo que devuelve el menor de los mayores 
    //este metodo es usa cuando se elimina un nodo raiz con hijos y hay que reequilibrar el arbol
    private NodeAVL minValueNode(NodeAVL node) {
        NodeAVL current = node;

        /* loop down to find the leftmost leaf */
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    //metodo para eliminar elementos de arbol
    private NodeAVL deleteNode(NodeAVL node, String nombre_categoria) {
        //eliminar como en un arbol binario
        if (node == null) {
            return node;
        }
        //si la clave a eliminar es menor que la clave de la raiz,
        //entonces esta en el subarbol izquierdo
        int key = nombre_categoria.compareTo(node.getBcategory().getName_category());
        if (key < 0) {
            node.left = deleteNode(node.left, nombre_categoria);
        } //si la clave a eliminar es mayor que la clave de la raiz,
        //entonces esta en el subarbol derecho
        else if (key > 0) {
            node.right = deleteNode(node.right, nombre_categoria);
        } //si la clave a eliminar es la misa que la raiz, entonces es el nodo para ser eliminado
        else {
            //el nodo tiene un solo hijo o no tiene hijos
            if ((node.left == null) || (node.right == null)) {
                NodeAVL tmp = null;
                if (tmp == node.left) {
                    tmp = node.right;
                } else {
                    tmp = node.left;
                }

                //caso no tiene hijos
                if (tmp == null) {
                    tmp = node;
                    node = null;
                } //caso que tiene un hijo
                else {
                    node = tmp; //copiar el contenido del hijo no vacio
                }
            } else {
                // nodo con dos hijos: obtener el orden
                // sucesor (el más pequeño en el subárbol derecho)
                NodeAVL tmp = minValueNode(node.right);

                //copiar los datos del sucesor de este nodo
                node.setBcategory(tmp.getBcategory());

                //eliminar el sucesor en orden
                node.right = deleteNode(node.right, tmp.getBcategory().getName_category());
            }
        }

        //si el arbol solo tenia un nodo entonces lo devolvemos
        if (node == null) {
            return node;
        }

        //actualizar la altura del nodo actual
        node.height = max(height(node.left), height(node.right)) + 1;

        //obtener el factor de balance del nodo (para verificar si se desequilibro)
        int balance = getBalance(node);

        //si el nodo esta desiquilibrado pueden suceder cuatro casos
        //caso rotacion simple izquierda
        if (balance > 1 && getBalance(node.left) >= 0) {
            return rightRotate(node);
        }
        //caso rotacion doble  izquierda derecha
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        //caso rotacion simple derecha
        if (balance < -1 && getBalance(node.right) <= 0) {
            return leftRotate(node);
        }

        //caso rotacion doble derecha
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    //metodo para eliminar nodo del arbol visibilidad publica
    public void delete(String nombre_categoria) {
        root = deleteNode(root, nombre_categoria);
    }

    //metodo para generar el dotsuource de graphviz
    private void reportAVL(NodeAVL node, Graphviz dotsource) {

        if (node != null) {
            if (node.left != null) {
                dotsource.addln(node.getBcategory().getName_category() + " -> " + node.left.getBcategory().getName_category());
                reportAVL(node.left, dotsource);
            } else {
                dotsource.addln("null" + count + " [shape=point];");
                dotsource.addln(node.getBcategory().getName_category() + " -> null" + count + ";");
                count++;
            }
            if (node.right != null) {
                dotsource.addln(node.getBcategory().getName_category() + " -> " + node.right.getBcategory().getName_category());
                reportAVL(node.right, dotsource);
            } else {
                dotsource.addln("null" + count + " [shape=point];");
                dotsource.addln(node.getBcategory().getName_category() + " -> null" + count + ";");
                count++;
            }
        }
    }

    //metodo que genera el reporte en graphviz
    public String report() {

        Graphviz graph = new Graphviz();
        graph.addln(graph.start_graph());
        graph.addln("node[fontname=\"Arial\", color=\"blue\"]");
        graph.addln("edge [color=\"green\"]");
        reportAVL(root, graph);
        graph.add(graph.end_graph());
        File out = new File("AVLTree.png");
        graph.writeGraphToFile(graph.getGraph(graph.getDotSource(), "png"), out);
        //Desktop.getDesktop().open(out);
        //System.out.println(graph.getDotSource());
        return graph.getPath(); 
    }

    //regresa el dato dentro de un uno pasando el valor a buscar por parametro
    private NodeAVL search_node(NodeAVL root, String nombre_categoria) {
        //caso base la raiz es nula 
        if (root == null || root.getBcategory().getName_category().equals(nombre_categoria)) {
            return root;
        }
        //el valor del dato de la raiz es mayor que el dato  buscado
        if (root.getBcategory().getName_category().compareTo(nombre_categoria) > 0) {
            return search_node(root.left, nombre_categoria);
        }
        //el valor del dato de la raiz es menor que dato buscado
        return search_node(root.right, nombre_categoria);
    }

    //metodo publico para devolver un objeto de tipo Categoria de libro pide como 
    //parametro el nombre de la categoria
    public BooksCategory search(String nombre_categoria) {
        if (root == null) {
            return null;
        } else {
            return search_node(root, nombre_categoria).getBcategory();
        }
    }

    public boolean isEmpty() {
        if (root == null) {
            return true;
        }
        return false;
    }

    public boolean repetido() {
        return repetido;
    }
}
