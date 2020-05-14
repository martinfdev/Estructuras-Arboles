/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.structures;

/**
 *
 * @author pedro
 */
public class BooksCategory {
    private String name_category;
    private BTree category;

    public BooksCategory(String name_category) {
        this.name_category = name_category;
        category = new BTree(3);
    }

    public String getName_category() {
        return name_category;
    }
    
    public void setName_category(String name_category) {
        this.name_category = name_category;
    }

    public BTree getCategory() {
        return category;
    }

    public void setCategory(BTree category) {
        this.category = category;
    }
}
