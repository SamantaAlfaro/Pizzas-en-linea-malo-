package DAO;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import modelo.orden;
import org.bson.Document;

public class GestorOrden {

    private static GestorOrden instancia = null;
    private static MongoClient mongo = null;
    private String baseDatos;
    MongoDatabase db;

    public GestorOrden() {
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

    public static GestorOrden getInstance() {
        if (instancia == null) {
            instancia = new GestorOrden();
        }
        return instancia;
    }

    public void insertOrder(orden or) {
        MongoCollection<Document> collection = db.getCollection("ordenes");

        Document Order = new Document();
        Order.append("ced cliente", or.getCedula()).
                append("nomb cliente", or.getNombreCompleto()).
                append("num orden", or.getNumOrden()).
                append("fecha", or.getFecha()).
                append("pizzas", or.getPizzas()).
                append("extras", or.getExtras()).
                append("estado", or.getEstado()).
                append("metodo pago", or.getMetodo_pago()).
                append("precio", or.getPrecio());

        collection.insertOne(Order);
    }

    public List<Document> listOrder() {
        List<Document> Orders = new ArrayList<Document>();
        MongoCollection<Document> collection = db.getCollection("ordenes");

        Orders = collection.find().projection(new Document()).into(new ArrayList<Document>());

        return Orders;
    }

    public Document getOrder(int num) {
        List<Document> orders = new ArrayList<Document>();
        Document order = new Document();

        MongoCollection<Document> collection = db.getCollection("ordenes");
        orders = collection.find().projection(new Document()).into(new ArrayList<Document>());

        for (Document d : orders) {
            if (d.get("num orden").equals(num)) {
                return order = d;
            }
        }
        return order;
    }

    public void updateEstado(int num, int estado) {
        MongoCollection<Document> collection = db.getCollection("ordenes");
        BasicDBObject query = new BasicDBObject();
        query.put("num orden", getOrder(num).get("num orden"));

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("estado", estado);
        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newDocument);

        collection.updateOne(query, updateObject);
    }

//    public static void main(String[] args) {
//        GestorOrden prueba = getInstance();
////        ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
////        ArrayList<String> extras = new ArrayList<String>();
////
////        orden u = new orden("304950273", "Leonardo Baldares Gómez", 1, pizzas, extras, new Date(), 0,"contado",20000);
////        prueba.insertOrder(u);
////        orden u2 = new orden("304980727", "Melissa Fernandez Ramirez", 2, pizzas, extras, new Date(), 0,"tarjeta",100000);
////        prueba.insertOrder(u2);
////        
////        System.out.println(prueba.listOrder().toString());
////        System.out.println(prueba.getOrder(1).toString());
////        prueba.updateUser("304950273", "Heredia", "11111111");
////        prueba.updatePassword("304950273", "leo123");
//        System.out.println(prueba.getOrder(1).toString());
//        prueba.updateEstado(1, 3);
//        System.out.println(prueba.getOrder(1).toString());
//    }

}
