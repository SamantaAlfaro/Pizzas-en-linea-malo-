package DAO;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;
import org.bson.Document;

public class GestorUsuarios implements Serializable {
    
    private static GestorUsuarios instancia = null;//para singleton
    private static MongoClient mongo = null;
    private String baseDatos;
    MongoDatabase db;
    
    private GestorUsuarios() {//para crear la conexion como un cliente de mongo
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
    
    public static GestorUsuarios getInstance() {//para no estar haciendo instancias a cada rato en memoria
        if (instancia == null) {
            instancia = new GestorUsuarios();
        }
        return instancia;
    }
    
    public void insertUser(Usuario user) {
        MongoCollection<Document> collection = db.getCollection("usuarios");
        
        Document User = new Document();
        User.append("nombre", user.getNombre()).
                append("apellidos", user.getApellidos()).
                append("cedula", user.getCedula()).
                append("direccion", user.getDireccion()).
                append("contraseña", user.getContraseña()).
                append("telefono", user.getTelefono()).
                append("rol", user.isRol());
        
        collection.insertOne(User);
    }
    
    public List<Document> listUser() {
        List<Document> users = new ArrayList<Document>();
        MongoCollection<Document> collection = db.getCollection("usuarios");
        
        users = collection.find().projection(new Document()).into(new ArrayList<Document>());
        
        return users;
    }
    
    public Document getUser(String ced) {
        List<Document> users = new ArrayList<Document>();
        Document user = new Document();
        
        MongoCollection<Document> collection = db.getCollection("usuarios");
        users = collection.find().projection(new Document()).into(new ArrayList<Document>());
        
        for (Document d : users) {
            if (d.get("cedula").equals(ced)) {
                return user = d;
            }
        }
        return user;
    }
    
    public static void main(String[] args) {
        GestorUsuarios prueba = getInstance();
//        prueba.agregar();
//
//        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
//        Document p = prueba.getFacturaMongo(3);
        System.out.println(prueba.getUser("105710421").toString());
    }
}
