package DAO;

import static DAO.GestorUsuarios.getInstance;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

public class GestorComplementos {

    private static GestorComplementos instancia = null;
    private static MongoClient mongo = null;
    private String baseDatos;
    MongoDatabase db;

    private GestorComplementos() {
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

    public static GestorComplementos getInstance() {
        if (instancia == null) {
            instancia = new GestorComplementos();
        }
        return instancia;
    }

    public List<Document> listComplements() {
        List<Document> users = new ArrayList<Document>();
        MongoCollection<Document> collection = db.getCollection("complementos");

        users = collection.find().projection(new Document()).into(new ArrayList<Document>());

        return users;
    }

    public Document getComplement(String nom) {
        List<Document> users = new ArrayList<Document>();
        Document complements = new Document();

        MongoCollection<Document> collection = db.getCollection("complementos");
        users = collection.find().projection(new Document()).into(new ArrayList<Document>());

        for (Document d : users) {
            if (d.get("nombre").equals(nom)) {
                return complements = d;
            }
        }
        return complements;
    }

    public static void main(String[] args) {
        GestorComplementos prueba = getInstance();

//        System.out.println(prueba.listComplements().toString());
//        System.out.println(prueba.getComplement("Pan de ajo").toString());
    }
}
