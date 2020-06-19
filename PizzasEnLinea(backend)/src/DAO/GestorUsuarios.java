/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import java.io.Serializable;

/**
 *
 * @author Samanta
 */
public class GestorUsuarios implements Serializable{
    
    private static GestorUsuarios instancia = null;//para singleton
    private static MongoClient mongo = null;
    private String baseDatos;
    MongoDatabase db;
    
    private GestorUsuarios() {//para crear la conexion como un cliente de mongo
        try {
            mongo = (MongoClient) MongoClients.create("mongodb://localhost:27017");
            crearConexion();
        } catch (Exception ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
    }

    private void crearConexion() {
        baseDatos = "facturas";
        db = mongo.getDatabase(baseDatos);
        System.out.println("Conexión completada..");
    }

    public static GestorUsuarios getInstance() {//para no estar haciendo instancias a cada rato en memoria
        if (instancia == null) {
            instancia = new GestorUsuarios();
        }
        return instancia;
    }
    
    
    
    
    
}
