package DAO;

import static DAO.GestorUsuarios.getInstance;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

public class GestorBebidas {

    private static GestorBebidas instancia = null;
    private static MongoClient mongo = null;
    private String baseDatos;
    MongoDatabase db;

    private GestorBebidas() {
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

    public static GestorBebidas getInstance() {
        if (instancia == null) {
            instancia = new GestorBebidas();
        }
        return instancia;
    }

    public List<Document> listDrinks() {
        List<Document> users = new ArrayList<Document>();
        MongoCollection<Document> collection = db.getCollection("bebidas");

        users = collection.find().projection(new Document()).into(new ArrayList<Document>());

        return users;
    }

    public Document getDrink(String nom) {
        List<Document> users = new ArrayList<Document>();
        Document drinks = new Document();

        MongoCollection<Document> collection = db.getCollection("bebidas");
        users = collection.find().projection(new Document()).into(new ArrayList<Document>());

        for (Document d : users) {
            if (d.get("nombre").equals(nom)) {
                return drinks = d;
            }
        }
        return drinks;
    }

    public static void main(String[] args) {
        GestorBebidas prueba = getInstance();

//        System.out.println(prueba.listDrinks().toString());
//        System.out.println(prueba.getDrink("Coca-cola").toString());
    }

}
