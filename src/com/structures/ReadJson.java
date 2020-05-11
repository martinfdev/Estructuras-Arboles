/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.structures;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

/**
 *
 * @author pedro
 */
public class ReadJson {
    private Encrypted hash;
    public ReadJson() {
        this.hash = new Encrypted();
    }

    //funcion para leer json de los libros
    public void readJsonBook(String path)  {
        BTree ab = new BTree(3);//test modify after
        if (path != null) {
            try {
                JSONObject book = (JSONObject) new JSONParser().parse(new FileReader(path));
                JSONArray bo = (JSONArray) book.get("libros");
                bo.forEach((var object) -> {
                   JSONObject nbo =  (JSONObject)object;//convertir object a JSONObject
                   ab.insert(new Book(nbo.get("Titulo").toString(), nbo.get("Autor").toString(), 
                             nbo.get("Editorial").toString(), nbo.get("Categoria").toString(), 
                             nbo.get("Idioma").toString(), Integer.parseInt(nbo.get("ISBN").toString()),
                             Integer.parseInt(nbo.get("Año").toString()), Integer.parseInt(nbo.get("Edicion").toString())));
                });
                ab.traverse();//test delete after                              
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReadJson.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException | IOException ex) {
                Logger.getLogger(ReadJson.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //funcion para leer archivo json de usuarios
    public void readJsonUser(String path) {
        HashTable<User> tst = new HashTable<>(45);
        if (path!=null) {
            try {
                JSONObject u = (JSONObject) new JSONParser().parse(new FileReader(path));
                JSONArray us =  (JSONArray) u.get("Usuarios");
                us.forEach((var object) -> {
                    JSONObject user = (JSONObject)object;//cast var a JSONObject
                    int cne = Integer.parseInt(user.get("Carnet").toString());
                    tst.insert(new User(cne, user.get("Nombre").toString(), user.get("Apellido").toString(), 
                            user.get("Carrera").toString(), hash.getMD5(user.get("Password").toString())), cne);                
                });
                
            } catch (FileNotFoundException | ParseException ex) {
                Logger.getLogger(ReadJson.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ReadJson.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        tst.report();
    }
    
    //generar json para los libros
    
    
}
