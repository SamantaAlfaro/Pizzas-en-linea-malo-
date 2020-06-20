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
import static com.mongodb.client.model.Filters.eq;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import modelo.Pizza;
import org.bson.Document;

/**
 *
 * @author Samanta
 */
public class GestorPizzas implements Serializable {

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

    //insertar una pizza
    public boolean insertarPizza(Pizza p) {
        try {
            MongoCollection<Document> colPizzas = db.getCollection("pizzas");
            Document doc = new Document();
            doc.append("nombre", p.getNombre()).append("precioBase", p.getPrecio()).append("ingredientes", p.getIngredientes());
            colPizzas.insertOne(doc);
            return true;
        } catch (Exception ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return false;
    }

    //listar todas las pizzas disponibles
    public List<Pizza> listarPizzas() {
        MongoCollection<Document> colProductos = db.getCollection("pizzas");
        FindIterable<Document> cursor = colProductos.find();
        List<Pizza> listaPizzas = new ArrayList<>();
        String nomPizza = null;
        double precio = 0.0;
        try {
            for (Document doc : cursor) {
                //System.out.println(doc.toString());
                nomPizza = doc.get("nombre").toString();
                precio = Double.valueOf(doc.get("precioBase").toString());
                ArrayList<String> ingredAux = (ArrayList<String>) doc.get("ingredientes");
                Pizza pizza = new Pizza(nomPizza, precio, ingredAux);
                listaPizzas.add(pizza);
            }
        } catch (Exception ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return listaPizzas;
    }

    //eliminar una pizza
    public boolean eliminarPizza(String nombre) {
        try {
            MongoCollection<Document> colPizzas = db.getCollection("pizzas");
            colPizzas.deleteOne(eq("nombre", nombre));
            return true;
        } catch (Exception ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return false;
    }
    
    
    

    //actualizar una pizza
    public static void main(String[] args) {
        GestorPizzas gP = GestorPizzas.getInstance();
        //System.out.println(gP.listarPizzas());
//        ArrayList<String> ingredientes = new ArrayList<>();
//        ingredientes.add("queso");
//        ingredientes.add("ajo");
//        gP.insertarPizza(new Pizza("Cheese", 1500.0, ingredientes));
//        System.out.println(gP.eliminarPizza("Cheese"));
    }

}
