
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

    Encrypted hash;
    HashTable<User> tableuser;
    User user;
    AVLTree biblioteca_categoria;

    public ReadJson(HashTable<User> tableuser, User user, AVLTree biblioteca_categoria) {
        this.user = user;
        this.tableuser = tableuser;
        this.biblioteca_categoria = biblioteca_categoria;
        this.hash = new Encrypted();
    }

    //funcion para leer json de los libros y crealos automaticamente
    public void readJsonBook(String path) {

        if (path != null) {
            try {
                JSONObject book = (JSONObject) new JSONParser().parse(new FileReader(path));
                JSONArray bo = (JSONArray) book.get("libros");
                bo.forEach((var object) -> {
                    JSONObject nbo = (JSONObject) object;//convertir object a JSONObject
                    biblioteca_categoria.insert(nbo.get("Categoria").toString());
                    BooksCategory tmp = biblioteca_categoria.search(nbo.get("Categoria").toString());
                    if (tmp.getName_category().equals(nbo.get("Categoria").toString())) {
                        biblioteca_categoria.search(nbo.get("Categoria").toString()).getCategory().insert(
                        new Book(nbo.get("Titulo").toString(), nbo.get("Autor").toString(), 
                             nbo.get("Editorial").toString(), nbo.get("Categoria").toString(), 
                             nbo.get("Idioma").toString(), Integer.parseInt(nbo.get("ISBN").toString()),
                             Integer.parseInt(nbo.get("Año").toString()), Integer.parseInt(nbo.get("Edicion").toString()),
                             user.getNumero_carne()));   
                    }
                });
                // ab.traverse();//test delete after                              
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReadJson.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException | IOException ex) {
                Logger.getLogger(ReadJson.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //funcion para leer archivo json de usuarios y crearlos automaticamente
    public void readJsonUser(String path) {
        if (path != null) {
            try {
                JSONObject u = (JSONObject) new JSONParser().parse(new FileReader(path));
                JSONArray us = (JSONArray) u.get("Usuarios");
                us.forEach((var object) -> {
                    JSONObject user = (JSONObject) object;//cast var a JSONObject
                    int cne = Integer.parseInt(user.get("Carnet").toString());
                    tableuser.insert(new User(cne, user.get("Nombre").toString(), user.get("Apellido").toString(),
                            user.get("Carrera").toString(), hash.getMD5(user.get("Password").toString())), cne);
                });
            } catch (FileNotFoundException | ParseException ex) {
                Logger.getLogger(ReadJson.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ReadJson.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //generar json para los libros
    //generar json para operaciones de usuario
}
