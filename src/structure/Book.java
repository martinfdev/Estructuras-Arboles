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
public class Book {
    private StringBuilder titulo, autor, editorial, categoria, idioma;
    private int carne_usuario_que_agrega, ISBN, year, edicion;

    public Book(StringBuilder titulo, StringBuilder autor, StringBuilder editorial, StringBuilder categoria, StringBuilder idioma, int carne_usuario_que_agrega, int ISBN, int year, int edicion) {
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.categoria = categoria;
        this.idioma = idioma;
        this.carne_usuario_que_agrega = carne_usuario_que_agrega;
        this.ISBN = ISBN;
        this.year = year;
        this.edicion = edicion;
    }

    public StringBuilder getTitulo() {
        return titulo;
    }

    public void setTitulo(StringBuilder titulo) {
        this.titulo = titulo;
    }

    public StringBuilder getAutor() {
        return autor;
    }

    public void setAutor(StringBuilder autor) {
        this.autor = autor;
    }

    public StringBuilder getEditorial() {
        return editorial;
    }

    public void setEditorial(StringBuilder editorial) {
        this.editorial = editorial;
    }

    public StringBuilder getCategoria() {
        return categoria;
    }

    public void setCategoria(StringBuilder categoria) {
        this.categoria = categoria;
    }

    public StringBuilder getIdioma() {
        return idioma;
    }

    public void setIdioma(StringBuilder idioma) {
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