/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author jonap
 */
public class Producto {
    private int idProducto;
    private String nombre;
    private double precio;
    private int cantidad;
    private Categoria categoria;

    public Producto() {
    }

    public Producto(int idProducto, String nombre, double precio, int cantidad, Categoria categoria) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.categoria = categoria;
    }

    /*
    getters
    */
    public int getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    /*
    setters
    */
    
    public void setPrecio(double precio) {
        if (precio > 0){
            this.precio = precio;    
        }else{
            System.out.println("El precio debe ser mayor a cero y no puede ser negativo :b");
        }
        
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setNombre(String nombre) {
        if (nombre.isEmpty()){
            System.out.println("El nombre no puede estar vacio :c");
        }else{
            this.nombre = nombre;
        }
        
    }

    public void setCantidad(int cantidad) {
        if (this.cantidad >= 0){
            this.cantidad = cantidad;
        }else{
            System.out.println("La cantidad no puede ser negativa");
        }
        
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    

    
    
    
}
