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
public class Init {

    public Init() {
        test();
    }

    private void test() {

        //comparacion de cadenas con valor de entero
      /*  String n1 = "1", n2="110";
        if (n1.compareTo(n2) == 0)
            System.out.println(n1+" es igual que "+n2);
        else if (n1.compareTo(n2) < 0) {
            System.out.println(n1+" antes que "+n2);
        }else
            System.out.println(n2+" antes que "+n1);
        */ 
        AVLTree tree = new AVLTree();

        tree.insert("9");
        tree.insert("5");
        tree.insert("10");
        tree.insert("0");
        tree.insert("6");
        tree.insert("11");
        tree.insert("-1");
        tree.insert("1");
        tree.insert("2");
        
        System.out.println("impresion en pre-orden");
        tree.pre_order();
        System.out.println("Eliminar 10");
        tree.delete("10");
        System.out.println("Impresion pre-orden");
        tree.pre_order();
//        System.out.println("impresion en post-orden");
//        tree.pos_order();
//        System.out.println("impresion en in-orden");
//        tree.in_orden();

    }
}
