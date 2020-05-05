/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

/**
 *
 * @author pedro
 */
public class BNode {

    int[] key; //array de claves
    int t; //minimo grado (define el rango de numero de claves)
    BNode child[]; //puntero de los hijos
    int n; //actual numero de claves
    boolean leaf; //es verdadero cuando el nodo es hoja, otro caso es falso

    //constructor con parametros
    public BNode(int t, boolean leaf) {
        this.t = t;
        this.leaf = leaf;
        key = new int[2 * t - 1];
        child = new BNode[2 * t];
    }

    //una funcion que recorre todo los nodos enraizdos en el subarbol de este nodo
    void traverse() {
        int i;
        for (i = 0; i < this.n; i++) {
            if (leaf == false) {
                child[i].traverse();
            }
            System.out.print(key[i] + " ");
        }
        if (leaf == false) {
            child[i].traverse();
        }
    }

    //una funcion que busca la clave dentro en un subarbol enraizado con este nodo
    public BNode search(int k) {
        // Find the first key greater than or equal to k 
        int i = 0;
        while (i < n && k > key[i]) {
            i++;
        }

        // If the found key is equal to k, return this node 
        if (key[i] == k) {
            return this;
        }

        // If key is not found here and this is a leaf node 
        if (leaf == true) {
            return null;
        }

        // Go to the appropriate child 
        return child[i].search(k);
    }

    // Una función que devuelve el índice de la primera clave que es mayor
    // o igual a k
    int findKey(int k) {
        int idx = 0;
        while (idx < this.n && key[idx] < k) {
            ++idx;
        }
        return idx;
    }

    // Una función de utilidad para insertar una nueva clave en el subárbol enraizado con
    // este nodo. La suposición es que el nodo no debe estar lleno cuando esta
    // función se llama
    void insertNonFull(int k) {
        // Initialize index as index of rightmost element 
        int i = n - 1;

        // If this is a leaf node 
        if (leaf == true) {
            // The following loop does two things 
            // a) Finds the location of new key to be inserted 
            // b) Moves all greater keys to one place ahead 
            while (i >= 0 && key[i] > k) {
                key[i + 1] = key[i];
                i--;
            }

            // Insert the new key at found location 
            key[i + 1] = k;
            n = n + 1;
        } else // If this node is not leaf 
        {
            // Find the child which is going to have the new key 
            while (i >= 0 && key[i] > k) {
                i--;
            }

            // See if the found child is full 
            if (child[i + 1].n == 2 * t - 1) {
                // If the child is full, then split it 
                splitChild(i + 1, child[i + 1]);

                // After split, the middle key of C[i] goes up and 
                // C[i] is splitted into two.  See which of the two 
                // is going to have the new key 
                if (key[i + 1] < k) {
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

        // The key to be removed is present in this node 
        if (idx < n && key[idx] == k) {

            // If the node is a leaf node - removeFromLeaf is called 
            // Otherwise, removeFromNonLeaf function is called 
            if (leaf) {
                removeFromLeaf(idx);
            } else {
                removeFromNonLeaf(idx);
            }
        } else {

            // If this node is a leaf node, then the key is not present in tree 
            if (leaf) {
                System.out.println("The key " + k + " is does not exist in the tree");
                return;
            }

            // The key to be removed is present in the sub-tree rooted with this node 
            // The flag indicates whether the key is present in the sub-tree rooted 
            // with the last child of this node 
            boolean flag = ((idx == n) ? true : false);

            // If the child where the key is supposed to exist has less that t keys, 
            // we fill that child 
            if (child[idx].n < t) {
                fill(idx);
            }

            // If the last child has been merged, it must have merged with the previous 
            // child and so we recurse on the (idx-1)th child. Else, we recurse on the 
            // (idx)th child which now has atleast t keys 
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
        int k = key[idx];

        // If the child that precedes k (child[idx]) has atleast t keys, 
        // find the predecessor 'pred' of k in the subtree rooted at 
        // child[idx]. Replace k by pred. Recursively delete pred 
        // in child[idx] 
        if (child[idx].n >= t) {
            int pred = getPred(idx);
            key[idx] = pred;
            child[idx].remove(pred);
        } // If the child C[idx] has less that t keys, examine C[idx+1]. 
        // If C[idx+1] has atleast t keys, find the successor 'succ' of k in 
        // the subtree rooted at C[idx+1] 
        // Replace k by succ 
        // Recursively delete succ in C[idx+1] 
        else if (child[idx + 1].n >= t) {
            int succ = getSucc(idx);
            key[idx] = succ;
            child[idx + 1].remove(succ);
        } // If both C[idx] and C[idx+1] has less that t keys,merge k and all of C[idx+1] 
        // into C[idx] 
        // Now C[idx] contains 2t-1 keys 
        // Free C[idx+1] and recursively delete k from C[idx] 
        else {
            merge(idx);
            child[idx].remove(k);
        }
    }

    // Una función para obtener el predecesor de la clave, donde la clave
    // está presente en la posición idx-th en el nodo
    int getPred(int idx) {
        // Keep moving to the right most node until we reach a leaf 
        BNode cur = child[idx];
        while (!cur.leaf) {
            cur = cur.child[cur.n];
        }

        // Return the last key of the leaf 
        return cur.key[cur.n - 1];
    }

    // Una función para obtener el sucesor de la clave, donde la clave
    // está presente en la posición idx-th en el nodo
    int getSucc(int idx) {
        // Keep moving the left most node starting from C[idx+1] until we reach a leaf 
        BNode cur = child[idx + 1];
        while (!cur.leaf) {
            cur = cur.child[0];
        }

        // Return the first key of the leaf 
        return cur.key[0];
    }

    // Una función para llenar el nodo hijo presente en el idx-th
    // posición en la matriz child [] si ese hijo tiene menos de t-1 claves
    void fill(int idx) {
        // If the previous child(C[idx-1]) has more than t-1 keys, borrow a key 
        // from that child 
        if (idx != 0 && child[idx - 1].n >= t) {
            borrowFromPrev(idx);
        } // If the next child(C[idx+1]) has more than t-1 keys, borrow a key 
        // from that child 
        else if (idx != n && child[idx + 1].n >= t) {
            borrowFromNext(idx);
        } // Merge C[idx] with its sibling 
        // If C[idx] is the last child, merge it with with its previous sibling 
        // Otherwise merge it with its next sibling 
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

        // The last key from C[idx-1] goes up to the parent and key[idx-1] 
        // from parent is inserted as the first key in C[idx]. Thus, the  loses 
        // sibling one key and child gains one key 
        // Moving all key in C[idx] one step ahead 
        for (int i = child1.n - 1; i >= 0; --i) {
            child1.key[i + 1] = child1.key[i];
        }

        // If child[idx] is not a leaf, move all its child pointers one step ahead 
        if (!child1.leaf) {
            for (int i = child1.n; i >= 0; --i) {
                child1.child[i + 1] = child1.child[i];
            }
        }
        // Setting child's first key equal to keys[idx-1] from the current node 
        child1.key[0] = key[idx - 1];

        // Moving sibling's last child as C[idx]'s first child 
        if (!child1.leaf) {
            child1.child[0] = sibling.child[sibling.n];
        }

        // Moving the key from the sibling to the parent 
        // This reduces the number of keys in the sibling 
        key[idx - 1] = sibling.key[sibling.n - 1];
        child1.n += 1;
        sibling.n -= 1;
    }

    // Una función para tomar prestada una clave de child [idx + 1] y colocar
    // en child [idx]
    void borrowFromNext(int idx) {
        BNode child1 = child[idx];
        BNode sibling = child[idx + 1];

        // keys[idx] is inserted as the last key in C[idx] 
        child1.key[(child1.n)] = key[idx];

        // Sibling's first child is inserted as the last child 
        // into C[idx] 
        if (!(child1.leaf)) {
            child1.child[(child1.n) + 1] = sibling.child[0];
        }

        //The first key from sibling is inserted into keys[idx] 
        key[idx] = sibling.key[0];

        // Moving all keys in sibling one step behind 
        for (int i = 1; i < sibling.n; ++i) {
            sibling.key[i - 1] = sibling.key[i];
        }

        // Moving the child pointers one step behind 
        if (!sibling.leaf) {
            for (int i = 1; i <= sibling.n; ++i) {
                sibling.child[i - 1] = sibling.child[i];
            }
        }

        // Increasing and decreasing the key count of C[idx] and C[idx+1] 
        // respectively 
        child1.n += 1;
        sibling.n -= 1;
    }

    // Una función para fusionar idx-th hijo del nodo con (idx + 1) th hijo de
    // el nodo
    void merge(int idx) {
        BNode child1 = child[idx];
        BNode sibling = child[idx + 1];

        // Pulling a key from the current node and inserting it into (t-1)th 
        // position of C[idx] 
        child1.key[t - 1] = key[idx];

        // Copying the keys from C[idx+1] to C[idx] at the end 
        for (int i = 0; i < sibling.n; ++i) {
            child1.key[i + t] = sibling.key[i];
        }

        // Copying the child pointers from C[idx+1] to C[idx] 
        if (!child1.leaf) {
            for (int i = 0; i <= sibling.n; ++i) {
                child1.child[i + t] = sibling.child[i];
            }
        }

        // Moving all keys after idx in the current node one step before - 
        // to fill the gap created by moving keys[idx] to C[idx] 
        for (int i = idx + 1; i < n; ++i) {
            key[i - 1] = key[i];
        }

        // Moving the child pointers after (idx+1) in the current node one 
        // step before 
        for (int i = idx + 2; i <= n; ++i) {
            child[i - 1] = child[i];
        }
        // Updating the key count of child and the current node 
        child1.n += sibling.n + 1;
        n--;
    }
}
