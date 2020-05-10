/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

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

    public BlockChain() {
    lblock = new DoubleLinkedList<>();
    genesisBlock();
    
    }
    
    private Block genesisBlock(){
      Block genesis = new Block(0, getDate(), "");
      return null;  
    }
    
    //funcion util para obtener el timestamp
    private String getDate(){
        String timestamp;
         return  timestamp = new SimpleDateFormat("dd-MM-yy-::hh:mm:ss").format(Calendar.getInstance().getTime());
    }
    
    
    
}
