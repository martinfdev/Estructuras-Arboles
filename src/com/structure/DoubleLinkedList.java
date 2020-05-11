/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.structure;

/**
 *
 * @author pedro
 * @param <T>
 */
public class DoubleLinkedList<T> {

    private Node<T> head, queue, getaux;
    private int size;

    //constuctor por defecto
    public DoubleLinkedList() {
        this.head = queue = null;
    }

    //vuelve el tamanio de la lista
    public int getSize() {
        return size;
    }

    //funcion util para saver si la lista esta vacia
    public boolean isEmpty() {
        return head != null && queue != null;
    }

    //metodo privado para insertar en la en donde correspoonde ya sea de primero o de ultimo en la lista
    private void insert(Node n, T data, boolean in) {
        Node<T> newnode = new Node<>(data);
        if (!isEmpty()) {
            head = queue = newnode;
            size++;
        } //si n es igual a la cabeza de la lista e in es falso insertar de primero
        else if (n == head && in == false) {
            newnode.next = head;
            head.back = newnode;
            head = newnode;
            size++;
        } //si n es igual a la cola de la lista e in es true se insertaa al fina de la lista
        else if (n == queue && in == true) {
            newnode.back = queue;
            queue.next = newnode;
            queue = newnode;
            size++;
        } //otro caso insertar el nuevo nodo antes de un nodo dado
        else {
            n.back.next = newnode;
            newnode.back = n.back;
            n.back = newnode;
            newnode.next = n;
            size++;
        }
    }

    //metodo para insertar en la cabeza de la lista
    public void add_head(T data) {
        insert(head, data, false);
    }

    //metodo para insertar la cola de la lista
    public void add_end(T data) {
        insert(queue, data, true);
    }

    //fucion publica para buscar un dato dentro de la lista
    public Node<T> search(T data) {
        if (isEmpty()) {
            Node<T> aux = head;
            while (aux != null) {
                if (aux.getData() == data) {
                    return aux;
                }
                aux = aux.next;
            }
        }
        return null;
    }
    
    //metodo retornar los datos en el nodo de la lista 
    public T getData(){
        if (isEmpty()) {
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
  
    //metodo para eliminar 
    private void delete(Node<T> n){
        if (isEmpty()) {
            if (n==head) {
                head= head.next;
                size--;
            }else if(n==queue){
                queue=queue.back;
                size--;
            }else{
               n.back.next = n.next;
               n.next.back = n.back;
               size--;
            }    
        }
    }
    
    //funcion util para borrar en la cabeza de la lista
    public void delete_head(){
        delete(head);
    }
    
    //funcion util para borra en la cola de la lista
    public void delete_end(){
        delete(queue);
    }
    
    //eliminar con parametro
    public void delete_data(T data){
        delete(search(data));
    }
}
