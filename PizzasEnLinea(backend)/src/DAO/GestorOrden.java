package DAO;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.Ingredientes;
import modelo.Pizza;
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

        ArrayList<Document> extras = new ArrayList<>();
        for (String s : or.getExtras()) {
            extras.add(new Document("nombre", s));
        }

        ArrayList<Document> pizzas = new ArrayList<>();
        for (Pizza s : or.getPizzas()) {
            pizzas.add(new Document("nombre", s.getNombre()).append("precio", s.getPrecio()));
        }

        Document Order = new Document();
        Order.append("ced cliente", or.getCedula()).
                append("nomb cliente", or.getNombreCompleto()).
                append("num orden", getConsecutivo() + 1).
                append("fecha", or.getFecha()).
                append("pizzas", pizzas).
                append("extras", extras).
                append("estado", or.getEstado()).
                append("metodo pago", or.getMetodo_pago()).
                append("precio", or.getPrecio());

        collection.insertOne(Order);
    }

    public ArrayList<orden> listOrder() {
        List<Document> Orders = new ArrayList<Document>();
        ArrayList<orden> ordenes = new ArrayList<>();
        ArrayList<Pizza> pizza = new ArrayList<>();
        ArrayList<String> ingredientes = new ArrayList<String>();
        ArrayList<String> extras = new ArrayList<String>();
        MongoCollection<Document> collection = db.getCollection("ordenes");

        Orders = collection.find().projection(new Document()).into(new ArrayList<Document>());
        for (Document o : Orders) {
            ArrayList<Document> pizzas = (ArrayList<Document>) o.get("pizzas");
            for (Document p : pizzas) {
                pizza.add(new Pizza(p.getString("nombre"), p.getDouble("precio"), ingredientes));
            }
            ArrayList<Document> extra = (ArrayList<Document>) o.get("extras");
            for (Document e : extra) {
                extras.add(e.getString("nombre"));
            }
            ordenes.add(new orden(o.getString("ced cliente"),
                    o.getString("nomb cliente"),
                    o.getInteger("num orden"),
                    pizza,
                    extras,
                    o.getDate("fecha"),
                    o.getInteger("estado"),
                    o.getString("metodo pago"),
                    o.getDouble("precio")));
        }
        return ordenes;
    }

    public orden getOrder(int num) {
        List<Document> orders = new ArrayList<Document>();
        orden order = new orden();

        ArrayList<Pizza> pizza = new ArrayList<>();
        ArrayList<String> ingredientes = new ArrayList<String>();
        ArrayList<String> extras = new ArrayList<String>();

        MongoCollection<Document> collection = db.getCollection("ordenes");
        orders = collection.find().projection(new Document()).into(new ArrayList<Document>());

        for (Document d : orders) {
            if (d.get("num orden").equals(num)) {
                order.setCedula(d.getString("ced cliente"));
                order.setNombreCompleto(d.getString("nomb cliente"));
                order.setNumOrden(d.getInteger("num orden"));
                order.setFecha(d.getDate("fecha"));

                ArrayList<Document> pizzas = (ArrayList<Document>) d.get("pizzas");
                for (Document p : pizzas) {
                    pizza.add(new Pizza(p.getString("nombre"), p.getDouble("precio"), ingredientes));
                }
                order.setPizzas(pizza);

                ArrayList<Document> extra = (ArrayList<Document>) d.get("extras");
                for (Document e : extra) {
                    extras.add(e.getString("nombre"));
                }
                order.setExtras(extras);

                order.setEstado(d.getInteger("estado"));
                order.setMetodo_pago(d.getString("metodo pago"));
                order.setPrecio(d.getDouble("precio"));

                return order;
            }
        }
        return order;
    }

    public void updateEstado(int num, int estado) {
        MongoCollection<Document> collection = db.getCollection("ordenes");
        BasicDBObject query = new BasicDBObject();
        query.put("num orden", getOrder(num).getNumOrden());

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("estado", estado);
        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newDocument);

        collection.updateOne(query, updateObject);
    }

    public int getConsecutivo() {
        int r = 0;
        try {
            MongoCollection<Document> colFacturas = db.getCollection("ordenes");
            Document d = colFacturas.find().sort(new Document("num orden", -1)).first();
            if (d != null) {
                System.out.println(d.toString());
                r = d.getInteger("num orden");
            }

        } catch (Exception ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return r;
    }

//    public static void main(String[] args) {
//        GestorOrden prueba = getInstance();
//        GestorPizzas pizza = GestorPizzas.getInstance();
//        GestorBebidas extra = GestorBebidas.getInstance();
//        ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
//        ArrayList<String> extras = new ArrayList<String>();
//
//        pizzas.add(pizza.buscarPizza("Suprema"));
////        pizzas.add(pizza.buscarPizza("Napolitana"));
//
//        System.out.println(pizzas.toString());
//
//        extras.add(extra.getDrink("Coca-cola").getNombre());
////
//        System.out.println(extras.toString());
//        orden u = new orden("304950273", "Leonardo Baldares Gómez", 0, pizzas, extras, new Date(), 0, "contado", 20000);
//        prueba.insertOrder(u);
//        orden u2 = new orden("111111111", "Leonardo", 0, pizzas, extras, new Date(), 0, "contado", 20000);
//        prueba.insertOrder(u2);
//        System.out.println(prueba.listOrder().toString());
////        System.out.println(prueba.getOrder(1).toString());
////        prueba.updateEstado(1, 3);
////        System.out.println(prueba.getOrder(1).toString());
//    }
}
