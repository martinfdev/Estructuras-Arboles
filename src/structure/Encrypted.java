/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

import java.security.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro
 */
public class Encrypted {

    public Encrypted() {
    }

    //fucion para generar hash de pass usuario
    public String getSHA256(String encrypPass) {
        String crypted = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
//            md.update();
            byte[] hash = md.digest(encrypPass.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                sb.append(Integer.toString((hash[i] & 0xff)+0x100, 16).substring(1));
            }
            crypted = sb.toString();
            return crypted;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Encrypted.class.getName()).log(Level.SEVERE, null, ex);
        }
        return crypted;
    }      
}
