/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.structure;

/**
 *
 * @author pedro
 */
public class BNode {

    Book[] key; //array de claves
    int t; //minimo grado (define el rango de numero de claves)
    BNode child[]; //puntero de los hijos
    int n; //actual numero de claves
    boolean leaf; //es verdadero cuando el nodo es hoja, otro caso es falso

    //constructor con parametros
    public BNode(int t, boolean leaf) {
        this.t = t;
        this.leaf = leaf;
        key = new Book[2 * t - 1];
        child = new BNode[2 * t];
    }

    //una funcion que recorre todo los nodos enraizados en el subarbol de este nodo
    void traverse() {
        int i;
        // Hay n claves y n + 1 hijos, atraviesa n claves
        // y primero n hijos
        for (i = 0; i < this.n; i++) {
            // Si esto no es hoja, entonces antes de imprimir la clave [i],
            // atraviesa el subárbol enraizado con el hijo child[i].
            if (leaf == false) {
                child[i].traverse();
            }
            System.out.print(key[i].getISBN() + " ");
        }
        //Imprime el subárbol enraizado derecho con el último hijo
        if (leaf == false) {
            child[i].traverse();
        }
    }

    //una funcion que busca la clave dentro en un subarbol enraizado con este nodo
    public Book search(int k) {
        // Encuentra la primera clave mayor o igual a k
        int i = 0;
        while (i < n && k > key[i].getISBN()) {
            i++;
        }

        try {
            // Si la clave encontrada es igual a k, devuelve este nodo
            if (key[i].getISBN() == k) {
                return this.key[i];
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "No existe el libro");
        }

        // Si la clave no se encuentra aquí y este es un nodo hoja
        if (leaf == true) {
            return null;
        }

        // Ir al nodo hoja apropiado
        return child[i].search(k);
    }

    // Una función que devuelve el índice de la primera clave que es mayor
    // o igual a k
    int findKey(int k) {
        int idx = 0;
        while (idx < this.n && key[idx].getISBN() < k) {
            ++idx;
        }
        return idx;
    }

    // Una función de utilidad para insertar una nueva clave en el subárbol enraizado con
    // este nodo. La suposición es que el nodo no debe estar lleno cuando esta
    // función se llama
    void insertNonFull(Book k) {
        // Inicializa el índice como índice del elemento más a la derecha 
        int i = n - 1;

        // Si este es un nodo hoja
        if (leaf == true) {
            // El siguiente ciclo hace dos cosas
            // a) Encuentra la ubicación de la nueva clave para insertar
            // b) Mueve todas las claves mayores a un lugar adelante
            while (i >= 0 && key[i].getISBN() > k.getISBN()) {
                key[i + 1] = key[i];
                i--;
            }

            // Inserte la nueva clave en la ubicación encontrada
            key[i + 1] = k;
            n = n + 1;
        } else // Si este nodo no es hoja
        {
            // Encuentra el hijo que tendrá la nueva clave 
            while (i >= 0 && key[i].getISBN() > k.getISBN()) {
                i--;
            }

            // Ver si el hijo encontrado está lleno
            if (child[i + 1].n == 2 * t - 1) {
                // Si el hijo está lleno, separarlo
                splitChild(i + 1, child[i + 1]);

                // Después de dividir, la clave central de child[i] sube y
                // child [i] se divide en dos. Ver cuál de los dos
                // va a tener la nueva clave
                if (key[i + 1].getISBN() < k.getISBN()) {
                    i++;
                }
            }
            child[i + 1].insertNonFull(k);
        }
    }

    // Una función de utilidad para dividir el hijo y de este nodo. yo soy indice
    // de y en la matriz secundaria C []. El niño debe estar lleno cuando esto
    // la función se llama
    void splitChild(int i, BNode y) {
        // Create a new node which is going to store (t-1) keys 
        // of y 
        BNode z = new BNode(y.t, y.leaf);
        z.n = t - 1;

        // Copy the last (t-1) keys of y to z 
        for (int j = 0; j < t - 1; j++) {
            z.key[j] = y.key[j + t];
        }

        // Copy the last t children of y to z 
        if (y.leaf == false) {
            for (int j = 0; j < t; j++) {
                z.child[j] = y.child[j + t];
            }
        }
        // Reduce the number of keys in y 
        y.n = t - 1;

        // Since this node is going to have a new child, 
        // create space of new child 
        for (int j = n; j >= i + 1; j--) {
            child[j + 1] = child[j];
        }

        // Link the new child to this node 
        child[i + 1] = z;

        // A key of y will move to this node. Find location of 
        // new key and move all greater keys one space ahead 
        for (int j = n - 1; j >= i; j--) {
            key[j + 1] = key[j];
        }

        // Copy the middle key of y to this node 
        key[i] = y.key[t - 1];

        // Increment count of keys in this node 
        n = n + 1;
    }

    // Una función de contenedor para eliminar la clave k en el subárbol enraizado con
    // este nodo.
    void remove(int k) {
        int idx = findKey(k);

        // La clave a eliminar está presente en este nodo
        if (idx < n && key[idx].getISBN() == k) {

            // Si el nodo es un nodo hoja, se llama a removeFromLeaf
            // De lo contrario, la función removeFromNonLeaf 
            if (leaf) {
                removeFromLeaf(idx);
            } else {
                removeFromNonLeaf(idx);
            }
        } else {

            // Si este nodo es un nodo hoja, entonces la clave no está presente en el árbol
            if (leaf) {
                System.out.println("El libro con ISB " + k + " no existe en el arbol");
                return;
            }

            // La clave a eliminar está presente en el subárbol enraizado con este nodo
            // La bandera indica si la clave está presente en el subárbol enraizado
            // con el último hijo de este nodo
            boolean flag = ((idx == n) ? true : false);

            // Si el hijo donde se supone que existe la clave tiene menos de t claves,
            // llenamos a ese hijo
            if (child[idx].n < t) {
                fill(idx);
            }

            // Si el último hijo se ha fusionado, debe haberse fusionado con el anterior
            // child y, por lo tanto, recurrimos en el (idx-1) th child. De lo contrario, recurrimos en el
            // (idx) th child que ahora tiene al menos t claves
            if (flag && idx > n) {
                child[idx - 1].remove(k);
            } else {
                child[idx].remove(k);
            }
        }
    }

    // Una función para eliminar la clave presente en la posición idx-th en
    // este nodo que es una hoja
    void removeFromLeaf(int idx) {

        // Move all the keys after the idx-th pos one place backward 
        for (int i = idx + 1; i < n; ++i) {
            key[i - 1] = key[i];
        }

        // Reduce the count of keys 
        n--;
    }

    // Una función para eliminar la clave presente en la posición idx-th en
    // este nodo que es un nodo no hoja
    void removeFromNonLeaf(int idx) {
        int k = key[idx].getISBN();

        // Si el hijo que precede a k (hijo [idx]) tiene al menos t claves,
        // encuentra el predecesor 'pred' de k en el subárbol enraizado en
        // hijo [idx]. Reemplace k por pred. Eliminar recursivamente pred
        // en hijo [idx]
        if (child[idx].n >= t) {
            Book pred = getPred(idx);
            key[idx] = pred;
            child[idx].remove(pred.getISBN());
        } // Si el hijo child [idx] tiene menos de t claves, examinar child[idx + 1].
        // Si child [idx + 1] tiene al menos t claves, busque el sucesor 'succ' de k en
        // el subárbol enraizado en child [idx + 1]
        // Reemplazar k por succ
        // Elimina recursivamente succ en child[idx + 1]
        else if (child[idx + 1].n >= t) {
            Book succ = getSucc(idx);
            key[idx] = succ;
            child[idx + 1].remove(succ.getISBN());
        } // Si tanto child[idx] como child[idx + 1] tienen menos de t claves, combinar k y todo child[idx + 1] 
        // en child[idx]
        // Ahora child[idx] contiene claves 2t-1
        // Liberar child[idx + 1] y eliminar recursivamente k de child[idx]
        else {
            merge(idx);
            child[idx].remove(k);
        }
    }

    // Una función para obtener el predecesor de la clave, donde la clave
    // está presente en la posición idx-th en el nodo
    Book getPred(int idx) {
        // Seguir moviendose hacia el nodo más a la derecha hasta llegar a una hoja
        BNode cur = child[idx];
        while (!cur.leaf) {
            cur = cur.child[cur.n];
        }

        // Devuelve la última clave de la hoja
        return cur.key[cur.n - 1];
    }

    // Una función para obtener el sucesor de la clave, donde la clave
    // está presente en la posición idx-th en el nodo
    Book getSucc(int idx) {
        // Sigue moviendo el nodo más a la izquierda comenzando desde child[idx + 1] hasta que alcancemos una hoja
        BNode cur = child[idx + 1];
        while (!cur.leaf) {
            cur = cur.child[0];
        }

        // Devuelve la primera clave de la hoja
        return cur.key[0];
    }

    // Una función para llenar el nodo hijo presente en el idx-th
    // posición en la matriz child [] si ese hijo tiene menos de t-1 claves
    void fill(int idx) {
        // Si el hijo anterior (child [idx-1]) tiene más de t-1 claves, pida prestada una clave
        // de ese hijo
        if (idx != 0 && child[idx - 1].n >= t) {
            borrowFromPrev(idx);
        } // Si el siguiente elemento secundario (child [idx + 1]) tiene más de t-1 claves, pida prestada una clave
        // de ese hijo
        else if (idx != n && child[idx + 1].n >= t) {
            borrowFromNext(idx);
        } // Combinar child [idx] con su hermano
        // Si child [idx] es el último hijo, fusionarlo con su hermano anterior
        // De lo contrario, combinar con su próximo hermano
        else {
            if (idx != n) {
                merge(idx);
            } else {
                merge(idx - 1);
            }
        }
    }

    // Una función para tomar prestada una clave del nodo child [idx-1] -th y colocar
    // en el nodo child[idx]
    void borrowFromPrev(int idx) {
        BNode child1 = child[idx];
        BNode sibling = child[idx - 1];

        // La última clave de child [idx-1] sube al padre y la clave [idx-1]
        // desde padre se inserta como la primera clave en child [idx]. Así, el pierde
        // hermano una clave y el hijo gana una clave
        // Mover todas las claves en child [idx] un paso adelante
        for (int i = child1.n - 1; i >= 0; --i) {
            child1.key[i + 1] = child1.key[i];
        }

        // Si child [idx] no es una hoja, mueve todos sus punteros child un paso adelante    
        if (!child1.leaf) {
            for (int i = child1.n; i >= 0; --i) {
                child1.child[i + 1] = child1.child[i];
            }
        }
        // Establecer la primera clave del hijo igual a las claves [idx-1] del nodo actual
        child1.key[0] = key[idx - 1];

        // Moving sibling's last child as C[idx]'s first child 
        if (!child1.leaf) {
            child1.child[0] = sibling.child[sibling.n];
        }

        // Mover la clave del hermano al padre
        // Esto reduce el número de claves en el hermano
        key[idx - 1] = sibling.key[sibling.n - 1];
        child1.n += 1;
        sibling.n -= 1;
    }

    // Una función para tomar prestada una clave de child [idx + 1] y colocar
    // en child [idx]
    void borrowFromNext(int idx) {
        BNode child1 = child[idx];
        BNode sibling = child[idx + 1];

        // keys [idx] se inserta como la última clave en child [idx]
        child1.key[(child1.n)] = key[idx];

        // El primer hijo del hermano se inserta como el último hijo
        // en child [idx]
        if (!(child1.leaf)) {
            child1.child[(child1.n) + 1] = sibling.child[0];
        }

        // La primera clave del hermano se inserta en las claves [idx]
        key[idx] = sibling.key[0];

        // Mover todas las llaves en el hermano un paso atrás
        for (int i = 1; i < sibling.n; ++i) {
            sibling.key[i - 1] = sibling.key[i];
        }

        // Mover los punteros del hijo un paso atrás
        if (!sibling.leaf) {
            for (int i = 1; i <= sibling.n; ++i) {
                sibling.child[i - 1] = sibling.child[i];
            }
        }
        // Aumentando y disminuyendo la cantidad de claves de child[idx] y child[idx + 1]
        // respectivamente
        child1.n += 1;
        sibling.n -= 1;
    }

    // Una función para fusionar idx-th hijo del nodo con (idx + 1) th hijo de
    // el nodo
    void merge(int idx) {
        BNode child1 = child[idx];
        BNode sibling = child[idx + 1];

        // Extraer una clave del nodo actual e insertarla en (t-1) th
        // posición de child[idx]
        child1.key[t - 1] = key[idx];

        // Copiando las claves de child[idx + 1] a child [idx] al final
        for (int i = 0; i < sibling.n; ++i) {
            child1.key[i + t] = sibling.key[i];
        }

        // Copiar los punteros secundarios de child[idx + 1] a child[idx] 
        if (!child1.leaf) {
            for (int i = 0; i <= sibling.n; ++i) {
                child1.child[i + t] = sibling.child[i];
            }
        }

        // Mover todas las claves después de idx en el nodo actual un paso antes -
        // para llenar el vacío creado moviendo las claves [idx] a child [idx]
        for (int i = idx + 1; i < n; ++i) {
            key[i - 1] = key[i];
        }

        // Mover los punteros secundarios después (idx + 1) en el nodo actual uno
        // paso antes
        for (int i = idx + 2; i <= n; ++i) {
            child[i - 1] = child[i];
        }
        // Actualización del recuento de claves del elemento secundario y el nodo actual
        child1.n += sibling.n + 1;
        n--;
    }

}
