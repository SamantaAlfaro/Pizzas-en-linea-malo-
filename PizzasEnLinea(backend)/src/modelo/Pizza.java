/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Samanta
 */
public class Pizza implements Serializable{
    private String tamaño;
    private String nombre;
    private double precio; //fijo
    private ArrayList<String> ingredientes;

    public Pizza() {
        this(null,null,0.0, null);
    }

    public Pizza(String tamaño, String nombre, double precio, ArrayList<String> ingredientes) {
        this.tamaño = tamaño;
        this.nombre = nombre;
        this.precio = precio;
        this.ingredientes = ingredientes;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public ArrayList<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(ArrayList<String> ingredientes) {
        this.ingredientes = ingredientes;
    }

    @Override
    public String toString() {
        return "Pizza{" + "tama\u00f1o=" + tamaño + ", nombre=" + nombre + ", precio=" + precio + ", ingredientes=\n" + ingredientes.toString() + '}';
    }
    
    
    
}
