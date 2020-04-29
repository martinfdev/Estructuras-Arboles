package structure;

/**
 *
 * @author pedro
 */
public class AVLTree {

    private NodeAVL root;

    public AVLTree() {
        this.root = null;
    }

    //funcion util que devuelve la altura del arbol
    private int heigt(NodeAVL n) {
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
        y.height = max(heigt(y.left), heigt(y.right)) + 1;
        x.height = max(heigt(x.left), heigt(x.right)) + 1;

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
        x.height = max(heigt(x.left), heigt(x.right)) + 1;
        y.height = max(heigt(y.left), heigt(y.right)) + 1;

        //retornamos la nueva raiz
        return y;
    }

    //obtener el factor de equilibrio del nodo N
    private int getBalance(NodeAVL n) {
        if (n == null) {
            return 0;
        }
        return heigt(n.left) - heigt(n.right);
    }

    //metodo para insertar un nodo en el arbol
    private NodeAVL insertNode(NodeAVL node, String nombre_categoria) {
        //realizamos una insercion normal en el arbol
        if (node == null) {
            return (new NodeAVL(nombre_categoria));
        }

        int val = nombre_categoria.compareTo(node.categoria);
        if (val < 0) {
            node.left = insertNode(node.left, nombre_categoria);
        } else if (val > 0) {
            node.right = insertNode(node.right, nombre_categoria);
        } else //punto donde el nodo es duplicado no hace ninguna operacion
        {
            System.out.println("Elemento duplicado " + nombre_categoria);
            return node;
        }

        /*actualizar la altura de este nodo */
        node.height = 1 + max(heigt(node.left), heigt(node.right));

        //obtener el factor de balance de este nodo y chequear si no esta desbalanceado
        int balance = getBalance(node);

        //si este nodo esta desequilibrado hay 4 casos 
        //caso rotacion simple izquierda
        if (balance > 1 && nombre_categoria.compareTo(node.left.categoria) < 0) {
            return rightRotate(node);
        }

        //caso rotacion simple derecha
        if (balance < -1 && nombre_categoria.compareTo(node.right.categoria) > 0) {
            return leftRotate(node);
        }

        //rotacion dobble izquerda derecha
        if (balance > 1 && nombre_categoria.compareTo(node.left.categoria) > 0) {
            node.left = leftRotate(node);
            return rightRotate(node);
        }

        //rotacion doble derecha inzquierda
        if (balance < -1 && nombre_categoria.compareTo(node.right.categoria) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    //metodo publico para insertar datos tipo string en el arbol 
    public void insert(String nombre_categoria) {
        root = insertNode(root, nombre_categoria);
    }

    //metodo para recorrer el arbol en modo pre-orden
    private void preOrden(NodeAVL root) {
        if (root != null) {
            System.out.print(root.categoria + " ");
            preOrden(root.left);
            preOrden(root.right);
        }
    }

    //metodo para recorrer el arbol en modo in-orden
    private void inOrden(NodeAVL root) {
        if (root != null) {
            inOrden(root.left);
            System.out.print(root.categoria + " ");
            inOrden(root.right);
        }
    }

    //metodo para recorrer el arbol en modo pos-orden
    private void posOrden(NodeAVL root) {
        if (root != null) {
            preOrden(root.left);
            preOrden(root.right);
            System.out.print(root.categoria + " ");
        }
    }

    //metodo publico para recorrer el arbol en  pre-orden
    public void pre_order() {
        preOrden(root);
        System.out.println("");
    }

    //metod publico para recorrer el arbol en post-orden
    public void pos_order() {
        posOrden(root);
        System.out.println("");
    }

    //metodo para recorrer el arbol en in-orden
    public void in_orden() {
        inOrden(root);
        System.out.println("");
    }

    //metodo para obtener el valor minimo 
    /* Given a non-empty binary search tree, return the  
    node with minimum key value found in that tree.  
    Note that the entire tree does not need to be  
    searched. */
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
        int key = nombre_categoria.compareTo(node.categoria);
        if (key < 0) {
            node.left = deleteNode(node.left, nombre_categoria);
        } //si la clave a eliminar es mayor que la clave de la raiz,
        //entonces esta en el subarbol derecho
        else if (key > 0) {
            node.right = deleteNode(node.right, nombre_categoria);
        } //si la clave a eliminar es la misa que la raiz, entonces es el nodo para ser eliminado
        else {
            //el nodo tiene un solo hijo no no tiene hijos
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
                node.categoria = tmp.categoria;
                
                //eliminar el sucesor en orden
                node.right = deleteNode(node.right, tmp.categoria);
            }
        }
        
        //si el arbol solo tenia un nodo entonces lo devolvemos
        if (node==null) {
            return node;
        }
        
        //actualizar la altura del nodo actual
        node.height = max(heigt(node.left), heigt(node.right))+1;
        
        //obtener el factor de balance del nodo (para verificar si se desequilibro)
        int balance =  getBalance(node);
        
        //si el nodo esta desiquilibrado pueden suceder cuatro casos
        //caso rotacion simple izquierda
        if (balance > 1 && getBalance(node.left) >= 0) {
            return rightRotate(node);
        }
        //caso rotacion doble  izquierda derecha
        if (balance > 1 && getBalance(node.left)<0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        
        //caso rotacion simple derecha
        if (balance > -1 && getBalance(node.right)<= 0) {
            return leftRotate(node);
        }
        
        //caso rotacion doble derecha
        if (balance >-1 && getBalance(node.right)>0) {
            node.right =  rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }
    
    //metodo para eliminar nodo del arbol visibilidad publica
    public void delete(String nombre_categoria){
        root = deleteNode(root, nombre_categoria);
    }

}
