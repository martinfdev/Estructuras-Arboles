/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

import java.lang.reflect.Array;

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
                array[hash].getList_user().add_queue((User)data);
                insert = true;
                busy++;
            }else if (array[hash]!=null) {
               array[hash].getList_user().add_queue((User)data);
            }
        }
    }
    
    public void print(){
        for (int i = 0; i < size; i++) {
            if (array[i]!=null) {
                LinkedList<User> tmp = array[i].getList_user();
                for (int q = 0; q < tmp.getSize(); q++) {
                    System.out.print(tmp.getData()+" ");
                }
                System.out.println("");
            }
        }
    }
}
