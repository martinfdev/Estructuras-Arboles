/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.structure;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author pedro
 */
public class Block {

    private final int index;
    private final String Timestamp;
    private int Nonce;
    private String data;
    private final String preovioushash;
    private String hash;

    public Block(int index, String previoshash, String data) {
        this.index = index;
        this.preovioushash = previoshash;
        this.Timestamp = getDate();
        this.data = data;
        this.hash = calculateHash();
    }

    //funcion util para obtener el timestamp
    private String getDate() {
        String timestamp;
        return timestamp = new SimpleDateFormat("dd-MM-yy-::hh:mm:ss").format(Calendar.getInstance().getTime());
    }

    //funcion que devuelve el hash minado
    private String calculateHash() {
        String tohashblock = index + Timestamp + preovioushash + data + Nonce;
        String calhash = Encrypted.getSHA256(tohashblock);
        return calhash;
    }

    //funcion para minar el bloque
    public void mineBlock(int difficulty) {
        String target = Encrypted.getDificultyString(difficulty); //Create a string with difficulty * "0" 
        while (!hash.substring(0, difficulty).equals(target)) {
            Nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Minado!!! : " + hash);
    }

    public int getIndex() {
        return index;
    }

    public String getTimestamp() {
        return Timestamp;
    }

    public int getNonce() {
        return Nonce;
    }

    public String getData() {
        return data;
    }

    public String getPreovioushash() {
        return preovioushash;
    }

    public String getHash() {
        return hash;
    }

    public void setData(String data) {
        this.data = data;
    }     
}
