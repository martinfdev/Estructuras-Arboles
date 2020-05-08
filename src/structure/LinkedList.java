/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

/**
 *
 * @author pedro
 * @param <T>
 */
public class LinkedList<T> {

    private Node head, queue;
    private int size;
    private Node getaux;

    public LinkedList() {
        head = queue = null;
        size = 0;
    }

    //devuelve el tamanio de la lista
    public int getSize() {
        return size;
    }

    //devuelve un booleano con valor true si la la lista esta vacia
    public boolean isEmpty() {
        if (head == null && queue == null) {
            return true;
        }
        return false;
    }

    //metodo para insertar un nodo dentro de la lista pide como parametro un nodo
    //un boolean si es true se agrega en al inicio como una pila si es false se agrega al final
    //se comporta como una cola
    private void insertNode(Node n, boolean tipo, T data) {
        Node<T> newnode = new Node<>(data);
        if (isEmpty()) {
            head = queue = newnode;
            size++;
            return;
        }
        if (n == head && tipo == true) {
            newnode.next = head;
            head = newnode;
            size++;
            return;
        }
        if (n == queue && tipo == false) {
            queue.next = newnode;
            queue = newnode;
            size++;
        }
    }

    //ingresar un nuevo nodo pide como parametro el dato
    public void add_head(T data) {
        insertNode(head, true, data);
    }

    //agregar un nuevo dato en la cola de la lista
    public void add_queue(T data) {
        insertNode(queue, false, data);
    }

    //borrar elemento de lista pide como parametro el elemento a eliminar
    private void deleteNode(Node n) {
        if (isEmpty()) {
            System.out.println("Lista Vacia!!");
        } else if (n != null && n.next == queue) {
            queue = n;
            n.next = null;
            size--;
        } else if (n != null) {
            Node tmp = n.next;
            n.next = n.next.next;
            size--;
            tmp = null;
        }
    }

    //busca un nodo con el datos especificado y lo devuelve
    private Node searchNode_delete(T data) {
        if (!isEmpty()) {
            Node aux = head;
            do {
                if (aux.getData() == data) {
                    head = head.next;
                    size--;
                } else if (aux.next.getData() == data) {
                    return aux;
                }
                aux = aux.next;
            } while (aux.next != null);
        }
        return null;
    }

    //metodo para borrar en la cabeza de la lista
    public void delete_head() {
        deleteNode(head);
    }

    //metodo para borrar con parametro
    public void delete_data(T data) {
        deleteNode(searchNode_delete(data));
    }

    //buscar dato especifico
    public T search(T data) {
        if (!isEmpty()) {
            Node aux = head;
            do {
                if (aux.getData() == data) {
                    return (T) aux.getData();
                }
                aux = aux.next;
            } while (aux != null);
        }
        return null;
    }

    //imprimir lista
    public T getData() {
        if (!isEmpty()) {
            if (getaux == null) {
                getaux = head;
                T tmp = (T) getaux.getData();
                getaux = getaux.next;
                return tmp;
            }
            if (getaux != null) {
                T tmp = (T) getaux.getData();
                getaux = getaux.next;
                return tmp;
            }
        } else {
            return null;
        }
        return null;
    }
}
