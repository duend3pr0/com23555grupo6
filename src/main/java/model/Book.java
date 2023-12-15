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
    public int getIdlibros() {
        return idlibros;
    }

    public void setIdlibros(int idlibros) {
        this.idlibros = idlibros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getCantPaginas() {
        return cantPaginas;
    }

    public void setCantPaginas(int cantPaginas) {
        this.cantPaginas = cantPaginas;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public int getCopias() {
        return copias;
    }

    public void setCopias(int copias) {
        this.copias = copias;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getImagenBase64() {
        return imagenBase64;
    }

    public void setImagenBase64(String imagenBase64) {
        this.imagenBase64 = imagenBase64;
    }
}
    
}
