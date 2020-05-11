/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.structure;

/**
 *
 * @author pedro
 * @param <T>
 */
public class Node<T>  {
    private T data;
    Node next;
    Node back;

    public Node(T data) {
        this.data = data;
        this.back = this.next = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
