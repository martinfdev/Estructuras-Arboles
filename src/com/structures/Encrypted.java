/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.structures;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
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

    //fucion util para generar hash MD5
    public String getMD5(String encrypPass) {
        String crypted = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
//            md.update();
            byte[] hash = md.digest(encrypPass.getBytes(StandardCharsets.UTF_8));
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

    //funcion util para generar hash SHA-256
    public static String getSHA256(String stringToHash){
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			//Applies sha256 to our input, 
			byte[] hash = digest.digest(stringToHash.getBytes("UTF-8"));
			StringBuilder hexString = new StringBuilder(); // This will contain hash as hexidecimal
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		}
		catch(UnsupportedEncodingException | NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
    }
    
    //devuelce la dificulaltad de cuantos ceros se generan para encontrar el Nonce para hacer la comparacion  
	public static String getDificultyString(int difficulty) {
		return new String(new char[difficulty]).replace('\0', '0');
	}
}
