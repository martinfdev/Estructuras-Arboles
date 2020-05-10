/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;
import interfaz.*;
/**
 *
 * @author pedro
 */
public class Init {

    public Init() {
       // Login login = new Login();
       // login.setVisible(true);
       test();
    }

    private void test() {
       
        //comparacion de cadenas con valor de entero
//        String n1 = "zimena", n2 = "wodo ";
//        if (n1.compareTo(n2) == 0) {
//            System.out.println(n1 + " es igual que " + n2);
//            System.out.println(n1.compareTo(n2));
//        } else if (n1.compareTo(n2) < 0) {
//            System.out.println(n1 + " antes que " + n2);
//            System.out.println(n1.compareTo(n2));
//        } else {
//            System.out.println(n2 + " antes que " + n1);
//            System.out.println(n1.compareTo(n2));
//        }
//        AVLTree tree = new AVLTree();
//        tree.insert("a");
//        tree.insert("b");
//        tree.insert("c");
//        tree.insert("d");
//        tree.insert("e");
//        tree.insert("f");
//        tree.insert("g");
//        tree.insert("h");
//        tree.insert("i");
//        tree.insert("j");
//        tree.insert("k");
//        tree.insert("l");
//        tree.insert("n");
//        tree.insert("m");
//        tree.insert("o");
//        tree.insert("p");
//        tree.insert("q");
//        tree.insert("r");
//        tree.insert("s");
//        tree.insert("t");
//        tree.insert("u");
//        tree.insert("v");
//        tree.insert("w");
//        tree.insert("x");
//        tree.insert("y");
//        tree.insert("z");
        //tree.report();
        // System.out.println(tree.search("r").getName_category());
        //System.out.println(tree.hashCode());
//        LinkedList<User> list = new LinkedList<>();
//        list.add_head(new User(1, "Pedro", "m", "cs", "adf"));
//        list.add_head(new User(2, "Juan", "s", "cs", "12"));
//        list.add_head(new User(3, "mario", "m", "cs", "asfewgfsgfasdw35"));
//        list.add_queue(new User(4, "Time", "t", "ii", "asdfww54sdf4aagd"));
//        list.add_head(new User(5, "Face", "f", "sf", "asdfeag47323434"));
//        list.add_queue(new User(6, "Amanda", "a", "sd", "sagfer66547"));
//        for (int i = 0; i < list.getSize(); i++) {
//                User tmp = list.getData();
//                System.out.println(tmp.getNombre());
//            if (tmp.getNumero_carne()==2) {
//                list.delete_data(tmp);
//            }
//        }
//        System.out.println("");
//        for (int i = 0; i < list.getSize(); i++) {
//            System.out.println(list.getData().getNombre());
//        }
//        BTree tb = new BTree(3);
//        tb.insert(new Book("Jasdufe", "kfshe8is", "djhekjhs", "sdhjfekj", "Espa", 1, 10, 0, 0));
//        tb.insert(new Book("Jasdufe", "kfshe8is", "djhekjhs", "sdhjfekj", "Espa", 1, 20, 0, 0));
//        tb.insert(new Book("Jasdufe", "kfshe8is", "djhekjhs", "sdhjfekj", "Espa", 1, 5, 0, 0));
//        tb.insert(new Book("Jasdufe", "kfshe8is", "djhekjhs", "sdhjfekj", "Espa", 1, 6, 0, 0));
//        tb.insert(new Book("Jasdufe", "kfshe8is", "djhekjhs", "sdhjfekj", "Espa", 1, 12, 0, 0));
//        tb.insert(new Book("Jasdufe", "kfshe8is", "djhekjhs", "sdhjfekj", "Espa", 1, 30, 0, 0));
//        tb.insert(new Book("Jasdufe", "kfshe8is", "djhekjhs", "sdhjfekj", "Espa", 1, 7, 0, 0));
//        tb.insert(new Book("Jasdufe", "kfshe8is", "djhekjhs", "sdhjfekj", "Espa", 1, 17, 0, 0));
//        tb.insert(new Book("Jasdufe", "kfshe8is", "djhekjhs", "sdhjfekj", "Espa", 1, 1, 0, 0));
//        tb.insert(new Book("Jasdufe", "kfshe8is", "djhekjhs", "sdhjfekj", "Espa", 1, 3, 0, 0));
//        tb.insert(new Book("Jasdufe", "kfshe8is", "djhekjhs", "sdhjfekj", "Espa", 1, 7, 0, 0));
//        tb.insert(new Book("Jasdufe", "kfshe8is", "djhekjhs", "sdhjfekj", "Espa", 1, 10, 0, 0));
//        tb.insert(new Book("Jasdufe", "kfshe8is", "djhekjhs", "sdhjfekj", "Espa", 1, 11, 0, 0));
//        tb.insert(new Book("Jasdufe", "kfshe8is", "djhekjhs", "sdhjfekj", "Espa", 1, 13, 0, 0));
//        tb.insert(new Book("Jasdufe", "kfshe8is", "djhekjhs", "sdhjfekj", "Espa", 1, 14, 0, 0));
//        tb.insert(new Book("Jasdufe", "kfshe8is", "djhekjhs", "sdhjfekj", "Espa", 1, 15, 0, 0));
//        tb.insert(new Book("Jasdufe", "kfshe8is", "djhekjhs", "sdhjfekj", "Espa", 1, 18, 0, 0));
//        tb.insert(new Book("Jasdufe", "kfshe8is", "djhekjhs", "sdhjfekj", "Espa", 1, 16, 0, 0));
//        tb.insert(new Book("Jasdufe", "kfshe8is", "djhekjhs", "sdhjfekj", "Espa", 1, 19, 0, 0));
//        tb.insert(new Book("Jasdufe", "kfshe8is", "djhekjhs", "sdhjfekj", "Espa", 1, 24, 0, 0));
//        tb.insert(new Book("Jasdufe", "kfshe8is", "djhekjhs", "sdhjfekj", "Espa", 1, 25, 0, 0));
//        tb.insert(new Book("Jasdufe", "kfshe8is", "djhekjhs", "sdhjfekj", "Espa", 1, 26, 0, 0));
//        tb.insert(new Book("Jasdufe", "kfshe8is", "djhekjhs", "sdhjfekj", "Espa", 1, 21, 0, 0));
//        tb.insert(new Book("Jasdufe", "kfshe8is", "djhekjhs", "sdhjfekj", "Espa", 1, 4, 0, 0));
//        tb.insert(new Book("Jasdufe", "kfshe8is", "djhekjhs", "sdhjfekj", "Espa", 1, 5, 0, 0));
//        tb.insert(new Book("Jasdufe", "kfshe8is", "djhekjhs", "sdhjfekj", "Espa", 1, 20, 0, 0));
//        tb.insert(new Book("Jasdufe", "kfshe8is", "djhekjhs", "sdhjfekj", "Espa", 1, 22, 0, 0));
//        tb.insert(new Book("Jasdufe", "kfshe8is", "djhekjhs", "sdhjfekj", "Espa", 1, 2, 0, 0));
//        tb.insert(new Book("Jasdufe", "kfshe8is", "djhekjhs", "sdhjfekj", "Espa", 1, 17, 0, 0));
//        tb.insert(new Book("Jasdufe", "kfshe8is", "djhekjhs", "sdhjfekj", "Espa", 1, 12, 0, 0));
//        tb.insert(new Book("Jasdufe", "kfshe8is", "djhekjhs", "sdhjfekj", "Espa", 1, 6, 0, 0));
//        System.out.println("El recorrido del arbol es: ");
//        tb.traverse();
//        
//        
//       System.out.println((tb.search(5) != null));
//       System.out.println((tb.search(100) !=null));
        //  tb.remove(16);
        //  tb.remove(15);
        //  tb.remove(14);
        //  tb.remove(13);
        //  tb.remove(12);
        //  tb.remove(11);
        // tb.report();
        //  tb.traverse();
//
//      HashTable test = new HashTable(45);
//      test.insert(new User(20200656, "Pedro", "M F", "ciencias y sistemas", "123"), 20200656);
//      test.insert(new User(20170656, "Maria", "M F", "ciencias y sistemas", "123"), 20170656);
//      test.insert(new User(20190656, "Jose", "M F", "ciencias y sistemas", "123"), 20190656);
//      test.insert(new User(20180656, "Alfaro", "M F", "ciencias y sistemas", "123"), 20180656);
//      test.insert(new User(20150656, "Roser", "M F", "ciencias y sistemas", "123"), 20150656);
//      test.insert(new User(20120656, "Ricardo", "M F", "ciencias y sistemas", "123"), 20120656);
//      test.insert(new User(20100656, "Mateos", "M F", "ciencias y sistemas", "123"), 20100656);
//      test.insert(new User(20170628, "Pablo", "M F", "ciencias y sistemas", "123"), 20170628);
//      test.insert(new User(20100056, "Jackeline", "M F", "ciencias y sistemas", "123"), 20100056);
//      test.insert(new User(20111111, "Jack", "M F", "ciencias y sistemas", "123"), 20111111);
//      test.insert(new User(20141111, "Jack Ojitos", "M F", "ciencias y sistemas", "123"), 20141111);
//      test.insert(new User(20151190, "Jackie Hermosa", "M F", "ciencias y sistemas", "123"), 20151190);
//     // test.report();
//        System.out.println(test.search(20150656)!=null);
//        System.out.println(test.delete(20150656));
//        System.out.println(test.search(20150656)!=null);
//        DoubleLinkedList<String> ld = new DoubleLinkedList<>();
//        ld.add_head("hola");
//        ld.add_head("como");
//        ld.add_head("esta");
//        ld.add_head("la ");
//        ld.add_head("maniana");
//        ld.add_head("del sabado");
//        ld.add_end("ayer");
//        ld.add_end("le escribi");
//        ld.add_end("a Luz");
//        ld.add_end("solo me");
//        ld.add_end("dijo");
//        ld.add_end("hola");
//        ld.add_end("y se desconecto no quiere platicar");
//        ld.delete_head();
//        ld.delete_end();
//        ld.delete_data("solo me");
//        for (int i = 0; i < ld.getSize(); i++) {
//            System.out.println(ld.getData()+" ");
//        }
//        System.out.println(ld.search("solo me")!=null);
//        System.out.println(ld.search("se desconecto no quiere platicar")!= null);
          ReadJson rj = new ReadJson();
          //rj.readJsonBook("/home/pedro/NetBeansProjects/EDD_1S2020_PY2_201700656/Libros.json");
          rj.readJsonUser("/home/pedro/NetBeansProjects/EDD_1S2020_PY2_201700656/usuarios.json");
    } 
}
