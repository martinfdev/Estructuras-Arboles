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
public class NodoHash {
    private LinkedList<User> list_user;

    public NodoHash() {
        list_user = new LinkedList<>();
    }

    public LinkedList<User> getList_user() {
        return list_user;
    }

    public void setList_user(LinkedList<User> list_user) {
        this.list_user = list_user;
    }
}
