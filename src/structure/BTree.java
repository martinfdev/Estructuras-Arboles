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
public class BTree {

    BNode root; //puntero al nodo raiz
    int t; //grado minimo

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
    public BNode search(int k) {
        if (this.root == null) {
            return null;
        } else {
            return this.root.search(k);
        }
    }

    public void insert(int k) {
        // If tree is empty 
        if (root == null) {
            // Allocate memory for root 
            root = new BNode(t, true);
            root.key[0] = k;  // Insert key 
            root.n = 1;  // Update number of keys in root 
        } else // If tree is not empty 
        {
            // If root is full, then tree grows in height 
            if (root.n == 2 * t - 1) {
                // Allocate memory for new root 
                BNode s = new BNode(t, false);

                // Make old root as child of new root 
                s.child[0] = root;

                // Split the old root and move 1 key to the new root 
                s.splitChild(0, root);

                // New root has two children now.  Decide which of the 
                // two children is going to have new key 
                int i = 0;
                if (s.key[0] < k) {
                    i++;
                }
                s.child[i].insertNonFull(k);

                // Change root 
                root = s;
            } else // If root is not full, call insertNonFull for root 
            {
                root.insertNonFull(k);
            }
        }
    }

    public void remove(int k) {
        if (root == null) {
            System.out.println("The tree is empty");
            return;
        }

        // Call the remove function for root 
        root.remove(k);

        // If the root node has 0 keys, make its first child as the new root 
        //  if it has a child, otherwise set root as NULL 
        if (root.n == 0) {
            BNode tmp = root;
            if (root.leaf) {
                root = null;
            } else {
                root = root.child[0];
            }
        }
    }

    public void report_graph(BNode root) {
        int i;
        for (i = 0; i < root.n; i++) {
            if (root.leaf == false) {
                System.out.print(root.key[i] + " ");
                System.out.println("No hojas");
                report_graph(root.child[i]);
                System.out.println("Hojas");
            } else if (root.leaf == true) {
                System.out.print(root.key[i] + " ");
            }
        }
        if (root.leaf == false) {
            //System.out.println("No");
            report_graph(root.child[i]);
        }
    }

    public void report() {
        report_graph(root);
        System.out.println("");
    }

}
