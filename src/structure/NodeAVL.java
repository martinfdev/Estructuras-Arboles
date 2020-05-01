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
public class NodeAVL {
    int height, key;
    BooksCategory bcategory;
    NodeAVL left, right;
    
    public NodeAVL(String name_category){
        bcategory= new BooksCategory(name_category);
        height = 1;
    }
    
    //solo para pruebas de funcionalidad del avl con valores enteros
    public NodeAVL(int key){
        this.key =  key;
        height = 1;
    }

    public BooksCategory getBcategory() {
        return bcategory;
    }

    public void setBcategory(BooksCategory bcategory) {
        this.bcategory = bcategory;
    }
    
    
    
    
}


