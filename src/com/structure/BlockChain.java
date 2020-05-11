/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.structure;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;



/**
 *
 * @author pedro
 */
public class BlockChain {
    private DoubleLinkedList<Block> lblock;
    private int index;
    private final int dificultNonce = 4;
    public BlockChain() {
    lblock = new DoubleLinkedList<>();
    this.index = lblock.getSize();
    }
    
    //crear el bloque genesis 
    private void generateGensisBlock(){
        Block genesis = new Block(0, "0", "");
        genesis.mineBlock(index);
    
    }
    
    
    
   
    
   
    
    
    
    
    
    
}
