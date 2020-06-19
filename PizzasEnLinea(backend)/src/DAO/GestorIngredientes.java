/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import modelo.Ingredientes;
import org.bson.Document;

/**
 *
 * @author Samanta
 */
public class GestorIngredientes implements Serializable{
    
    private static GestorIngredientes instancia = null;//para singleton
    private static MongoClient mongo = null;
    private String baseDatos;
    MongoDatabase db;
    
    private GestorIngredientes() {//para crear la conexion como un cliente de mongo
        try {
            mongo = MongoClients.create("mongodb://localhost:27017");
            crearConexion();
        } catch (Exception ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
    }

    private void crearConexion() {
        baseDatos = "pizzeria";
        db = mongo.getDatabase(baseDatos);
        System.out.println("Conexión completada..");
    }

    public static GestorIngredientes getInstance() {//para no estar haciendo instancias a cada rato en memoria
        if (instancia == null) {
            instancia = new GestorIngredientes();
        }
        return instancia;
    }
    
    //listar los ingredientes actuales
    public List<Ingredientes> listarIngredientes() {
        List<Document> documents = null;
        List<Ingredientes> ingredientes = null;
        Ingredientes ing = null;
        try {
            MongoCollection<Document> colIngredientes= db.getCollection("ingredientes");
            documents = (List<Document>) colIngredientes.find().into(
                    new ArrayList<>());
            for (Document d: documents){
                System.out.println(d.toString());
                String nombre = d.get("nombre").toString();
                double precio = Double.valueOf(d.get("precio").toString());
                ing = new Ingredientes(precio, nombre);
                ingredientes.add(ing);
            }
        } catch (Exception ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return ingredientes;
    }
    
    //buscar un ingrediente (para sacar el precio)
    
    
    
    
    public static void main(String[] args) {
        GestorIngredientes gI = GestorIngredientes.getInstance();
        System.out.println(gI.listarIngredientes().toString());
        
        

    }
}