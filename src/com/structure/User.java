/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.structure;

/**
 *
 * @author pedro
 */
public class User {
    private int numero_carne;
    private String nombre, apellido, carrera, password;

    public User() {
        
    }

    public User(int numero_carne, String nombre, String apellido, String carrera, String password) {
        this.numero_carne = numero_carne;
        this.nombre = nombre;
        this.apellido = apellido;
        this.carrera = carrera;
        this.password = password;
    }

    public int getNumero_carne() {
        return numero_carne;
    }

    public void setNumero_carne(int numero_carne) {
        this.numero_carne = numero_carne;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }    
}