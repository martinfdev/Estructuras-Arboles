/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro
 */
public class Block {
    private int index;
    private String Timestamp;
    private BigInteger Nonce;
    private String data, preovioushash, hash;

    public Block(int index, String Timestamp, String data) {
        this.index = index;
        this.Timestamp = Timestamp;
        this.data = data;
    
    }
    
    
    
    
    
    
}
