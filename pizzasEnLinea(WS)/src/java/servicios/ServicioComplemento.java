/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import DAO.GestorComplementos;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import modelo.complementos;

/**
 *
 * @author leoba
 */
@WebService(serviceName = "ServicioComplemento")
public class ServicioComplemento {

// <editor-fold defaultstate="collapsed" desc="complementos">
    @WebMethod(operationName = "listComplements")
    public String listComplements() {
        try {
            return GestorComplementos.getInstance().listComplements();
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return "fallo!";
    }

    @WebMethod(operationName = "insertComplemet")
    public String insertComplemet(
            @WebParam(name = "nombre") String Pnombre,
            @WebParam(name = "precio") String Pprecio) {
        double precio = 0.0;
        try {
            precio = Double.parseDouble(Pprecio);
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        try {
            complementos c = new complementos(Pnombre, precio);
            GestorComplementos.getInstance().insertComplemet(c);
            return "exito!";
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return "fallo!";
    }

    @WebMethod(operationName = "deleteComplement")
    public String deleteComplement(@WebParam(name = "nombre") String Pnombre) {
        try {
            GestorComplementos.getInstance().deleteComplement(Pnombre);
            return "exito!";
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return "fallo!";
    }

    @WebMethod(operationName = "updateComplements")
    public String updateComplements(
            @WebParam(name = "nombre") String Pnombre,
            @WebParam(name = "campo") String Pcampo,
            @WebParam(name = "value") String Pvalue) {
        try {
            GestorComplementos.getInstance().updateComplements(Pnombre, Pcampo, Pvalue);
            return "exito!";
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return "fallo!";
    }

    @WebMethod(operationName = "getComplement")
    public String getComplement(@WebParam(name = "nombre") String Pnombre) {
        try {
            return GestorComplementos.getInstance().getComplement(Pnombre);
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return "fallo!";
    }

    // </editor-fold>
}
