package model;

import lombok.*;

    @Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private int idlibros;
    private String nombre;
    private String autor;
    private int cantPaginas;
    private double precio;
    private String sinopsis;
    private int copias;
    private byte[] imagen;
    private String imagenBase64;

    public Book(String nombre, String autor, int cantPaginas, double precio, String sinopsis, int copias, byte[] imagen) {
        this.nombre = nombre;
        this.autor = autor;
        this.cantPaginas = cantPaginas;
        this.precio = precio;
        this.sinopsis = sinopsis;
        this.copias = copias;
        this.imagen = imagen;
    }

    public Book(int idlibros, String nombre, String autor, int cantPaginas, double precio, String sinopsis, int copias, byte[] imagen) {
        this.idlibros = idlibros;
        this.nombre = nombre;
        this.autor = autor;
        this.cantPaginas = cantPaginas;
        this.precio = precio;
        this.sinopsis = sinopsis;
        this.copias = copias;
        this.imagen = imagen;
    }
    
    
}
