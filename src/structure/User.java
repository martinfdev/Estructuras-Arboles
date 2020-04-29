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
public class User {
    private int numero_carne;
    private StringBuilder nombre, apellido, carrera, password;

    public User(int numero_carne, StringBuilder nombre, StringBuilder apellido, StringBuilder carrera, StringBuilder password) {
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

    public StringBuilder getNombre() {
        return nombre;
    }

    public void setNombre(StringBuilder nombre) {
        this.nombre = nombre;
    }

    public StringBuilder getApellido() {
        return apellido;
    }

    public void setApellido(StringBuilder apellido) {
        this.apellido = apellido;
    }

    public StringBuilder getCarrera() {
        return carrera;
    }

    public void setCarrera(StringBuilder carrera) {
        this.carrera = carrera;
    }

    public StringBuilder getPassword() {
        return password;
    }

    public void setPassword(StringBuilder password) {
        this.password = password;
    }

}