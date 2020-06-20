/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.io.Serializable;
import java.util.List;
import modelo.Pizza;
import org.bson.Document;

/**
 *
 * @author Samanta
 */
public class GestorPizzas implements Serializable{
    
    private static GestorPizzas instancia = null;//para singleton
    private static MongoClient mongo = null;
    private String baseDatos;
    MongoDatabase db;
    
    private GestorPizzas() {//para crear la conexion como un cliente de mongo
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

    public static GestorPizzas getInstance() {
        if (instancia == null) {
            instancia = new GestorPizzas();
        }
        return instancia;
    }
    
    
    //listar todas las pizzas disponibles
    public List<Document> listarPizzas() {
        MongoCollection<Document> colProductos = db.getCollection("pizzas");
        FindIterable<Document> cursor = colProductos.find();
        Pizza pizza = null;
        for (Document doc : cursor) {
            try {
                System.out.println(doc.toString());
                pizza = new Pizza();
            } catch (Exception ex) {
                System.err.printf("Excepción: '%s'%n", ex.getMessage());
            }
        }
        return null;
    }
    
    
    public static void main(String[] args) {
        GestorPizzas gP = GestorPizzas.getInstance();
        gP.listarPizzas();
        

    }
    
    
}
