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
public class Book {
    private String titulo, autor, editorial, categoria, idioma;
    private int carne_usuario_que_agrega, ISBN, year, edicion;

    public Book(String titulo, String autor, String editorial, String categoria, String idioma, int ISBN, int year, int edicion) {
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.categoria = categoria;
        this.idioma = idioma;
        this.ISBN = ISBN;
        this.year = year;
        this.edicion = edicion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getCarne_usuario_que_agrega() {
        return carne_usuario_que_agrega;
    }

    public void setCarne_usuario_que_agrega(int carne_usuario_que_agrega) {
        this.carne_usuario_que_agrega = carne_usuario_que_agrega;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getEdicion() {
        return edicion;
    }

    public void setEdicion(int edicion) {
        this.edicion = edicion;
    }

    
}