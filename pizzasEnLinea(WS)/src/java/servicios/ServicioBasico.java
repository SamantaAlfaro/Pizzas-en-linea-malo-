package servicios;

import DAO.GestorBebidas;
import DAO.GestorComplementos;
import DAO.GestorIngredientes;
import DAO.GestorMetodoPago;
import DAO.GestorOrden;
import DAO.GestorPizzas;
import DAO.GestorUsuarios;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import modelo.Ingredientes;
import modelo.Pizza;
import modelo.Usuario;
import modelo.bebidas;
import modelo.complementos;
import modelo.orden;

@WebService(serviceName = "ServicioBasico")
public class ServicioBasico {

    // <editor-fold defaultstate="collapsed" desc="bebidas">
    @WebMethod(operationName = "listDrinks")
    public String listDrinks() {
        try {
            return GestorBebidas.getInstance().listDrinks().toString();
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return "fallo!";
    }

    @WebMethod(operationName = "insertDrink")
    public String insertDrink(
            @WebParam(name = "nombre") String Pnombre,
            @WebParam(name = "precio") String Pprecio) {
        double precio = 0.0;
        try {
            precio = Double.parseDouble(Pprecio);
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        try {
            bebidas b = new bebidas(Pnombre, precio);
            GestorBebidas.getInstance().insertDrink(b);
            return "exito!";
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return "fallo!";
    }

    @WebMethod(operationName = "deleteDrink")
    public String deleteDrink(@WebParam(name = "nombre") String Pnombre) {
        try {
            GestorBebidas.getInstance().deleteDrink(Pnombre);
            return "exito!";
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return "fallo!";
    }

    @WebMethod(operationName = "updateDrink")
    public String updateDrink(
            @WebParam(name = "nombre") String Pnombre,
            @WebParam(name = "campo") String Pcampo,
            @WebParam(name = "value") String Pvalue) {
        try {
            GestorBebidas.getInstance().updateDrink(Pnombre, Pcampo, Pvalue);
            return "exito!";
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return "fallo!";
    }

    @WebMethod(operationName = "getDrink")
    public String getDrink(@WebParam(name = "nombre") String Pnombre) {
        try {
            return GestorBebidas.getInstance().getDrink(Pnombre).toString();
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return "fallo!";
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="ingredientes">
    @WebMethod(operationName = "insertIngredientes")
    public String insertIngredientes(
            @WebParam(name = "nombre") String Pnombre,
            @WebParam(name = "precio") String Pprecio) {
        double precio = 0.0;
        try {
            precio = Double.parseDouble(Pprecio);
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        try {
            Ingredientes i = new Ingredientes(precio, Pnombre);
            GestorIngredientes.getInstance().insertIngredientes(i);
            return "exito!";
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return "fallo!";
    }

    @WebMethod(operationName = "deleteIngrediente")
    public String deleteIngrediente(@WebParam(name = "nombre") String Pnombre) {
        try {
            GestorIngredientes.getInstance().deleteIngrediente(Pnombre);
            return "exito!";
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return "fallo!";
    }

    @WebMethod(operationName = "updateIngrediente")
    public String updateIngrediente(
            @WebParam(name = "nombre") String Pnombre,
            @WebParam(name = "campo") String Pcampo,
            @WebParam(name = "value") String Pvalue) {
        try {
            GestorIngredientes.getInstance().updateIngrediente(Pnombre, Pcampo, Pvalue);
            return "exito!";
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return "fallo!";
    }

    @WebMethod(operationName = "listarIngredientes")
    public String listarIngredientes() {
        try {
            return GestorIngredientes.getInstance().listarIngredientes().toString();
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return "fallo!";
    }

    @WebMethod(operationName = "buscarIngrediente")
    public String buscarIngrediente(@WebParam(name = "nombre") String Pnombre) {
        try {
            return GestorIngredientes.getInstance().buscarIngrediente(Pnombre);
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return "fallo!";
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="complementos">
    @WebMethod(operationName = "listComplements")
    public String listComplements() {
        try {
            return GestorComplementos.getInstance().listComplements().toString();
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
            return GestorComplementos.getInstance().getComplement(Pnombre).toString();
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return "fallo!";
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="usuarios">
    @WebMethod(operationName = "insertUser")
    public String insertUser(
            @WebParam(name = "nombre") String Pnombre,
            @WebParam(name = "apellidos") String Papellidos,
            @WebParam(name = "cedula") String Pcedula,
            @WebParam(name = "direccion") String Pdireccion,
            @WebParam(name = "contraseña") String Pcontraseña,
            @WebParam(name = "telefono") String Ptelefono,
            @WebParam(name = "rol") String Prol) {
        boolean rol = true;
        try {
            rol = Boolean.parseBoolean(Prol);
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        try {
            Usuario u = new Usuario(
                    Pnombre,
                    Papellidos,
                    Pcedula,
                    Pdireccion,
                    Pcontraseña,
                    Ptelefono,
                    rol);
            GestorUsuarios.getInstance().insertUser(u);
            return "exito!";
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return "fallo!";
    }

    @WebMethod(operationName = "updateUser")
    public String updateUser(
            @WebParam(name = "cedula") String Pcedula,
            @WebParam(name = "direccion") String Pdireccion,
            @WebParam(name = "telefono") String Ptelefono) {
        try {
            GestorUsuarios.getInstance().updateUser(Pcedula, Pdireccion, Ptelefono);
            return "exito!";
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return "fallo!";
    }

    @WebMethod(operationName = "updatePassword")
    public String updatePassword(
            @WebParam(name = "cedula") String Pcedula,
            @WebParam(name = "newPass") String PnewPass) {
        try {
            GestorUsuarios.getInstance().updatePassword(Pcedula, PnewPass);
            return "exito!";
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return "fallo!";
    }

    @WebMethod(operationName = "listUser")
    public String listUser() {
        try {
            return GestorUsuarios.getInstance().listUser().toString();
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return "fallo!";
    }

    @WebMethod(operationName = "getUser")
    public String getUser(@WebParam(name = "cedula") String Pcedula) {
        try {
            return GestorUsuarios.getInstance().getUser(Pcedula).toString();
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return "fallo!";
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="pizza">
    @WebMethod(operationName = "insertarPizza")
    public String insertarPizza(
            @WebParam(name = "nombre") String Pnombre,
            @WebParam(name = "precio") String Pprecio,
            @WebParam(name = "ingredientes") String Pingredientes) {
        List<String> ingredientes = new ArrayList<>();
        double precio = 0.0;
        try {
            precio = Double.parseDouble(Pprecio);
            ingredientes = List.class.cast(Pingredientes);
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        try {
            Pizza p = new Pizza(
                    Pnombre,
                    precio,
                    (ArrayList<String>) ingredientes);
            GestorPizzas.getInstance().insertarPizza(p);
            return "exito!";
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return "fallo!";
    }

    @WebMethod(operationName = "eliminarPizza")
    public String eliminarPizza(@WebParam(name = "nombre") String Pnombre) {
        try {
            GestorPizzas.getInstance().eliminarPizza(Pnombre);
            return "exito!";
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return "fallo!";
    }

    @WebMethod(operationName = "actualizarPizza")
    public String actualizarPizza(
            @WebParam(name = "nombre1") String Pnombre1,
            @WebParam(name = "nombre2") String Pnombre2,
            @WebParam(name = "precio") String Pprecio,
            @WebParam(name = "ingredientes") String Pingredientes) {

        List<String> ingredientes = new ArrayList<>();
        double precio = 0.0;
        try {
            precio = Double.parseDouble(Pprecio);
            ingredientes = List.class.cast(Pingredientes);
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        try {
            Pizza p = new Pizza(Pnombre2, precio, (ArrayList<String>) ingredientes);
            GestorPizzas.getInstance().actualizarPizza(Pnombre1, p);
            return "exito!";
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return "fallo!";
    }

    @WebMethod(operationName = "buscarPizza")
    public String buscarPizza(@WebParam(name = "nombre") String Pnombre) {
        try {
            return GestorPizzas.getInstance().buscarPizza(Pnombre).toString();
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return "fallo!";
    }

    @WebMethod(operationName = "listarPizzas")
    public String listarPizzas() {
        try {
            return GestorPizzas.getInstance().listarPizzas().toString();
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return "fallo!";
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="metodos pago">
    @WebMethod(operationName = "listarMetodosPago")
    public String listarMetodosPago() {
        try {
            return GestorMetodoPago.getInstance().listarMetodosPago().toString();
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return "fallo!";
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="orden">
    @WebMethod(operationName = "insertOrder")
    public String insertOrder(
            @WebParam(name = "cedula") String Pcedula,
            @WebParam(name = "nombreCompleto") String PnombreCompleto,
            @WebParam(name = "numOrden") String PnumOrden,
            @WebParam(name = "pizzas") String Ppizzas,
            @WebParam(name = "extras") String Pextras,
            @WebParam(name = "fecha") String Pfecha,
            @WebParam(name = "estado") String Pestado,
            @WebParam(name = "metodo_pago") String Pmetodo_pago,
            @WebParam(name = "precio") String Pprecio) {
        ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
        ArrayList<String> extras = new ArrayList<String>();
        double precio = 0.0;
        int numOrden = 0;
        int estado = 0;
        Date fecha = null;
        try {
            fecha = new Date(Pfecha);
            numOrden = Integer.parseInt(Pprecio);
            estado = Integer.parseInt(Pestado);
            precio = Double.parseDouble(Pprecio);
            pizzas = (ArrayList<Pizza>) List.class.cast(Ppizzas);
            extras = (ArrayList<String>) List.class.cast(Pextras);
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        try {
            orden o = new orden(
                    Pcedula,
                    PnombreCompleto,
                    numOrden,
                    pizzas,
                    extras,
                    fecha,
                    estado,
                    Pmetodo_pago,
                    precio);
            GestorOrden.getInstance().insertOrder(o);
            return "exito!";
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return "fallo!";
    }

    @WebMethod(operationName = "listOrder")
    public String listOrder() {
        try {
            return GestorOrden.getInstance().listOrder().toString();
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return "fallo!";
    }

    @WebMethod(operationName = "getOrder")
    public String getOrder(@WebParam(name = "numero") String Pnumero) {
        int numero = 0;
        try {
            numero = Integer.parseInt(Pnumero);
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        try {
            return GestorOrden.getInstance().getOrder(numero).toString();
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return "fallo!";
    }

    @WebMethod(operationName = "updateEstado")
    public String updateEstado(
            @WebParam(name = "numero") String Pnumero,
            @WebParam(name = "estado") String Pestado)
    {

        int numero = 0;
        int estado = 0;
        try {
            numero = Integer.parseInt(Pnumero);
            estado = Integer.parseInt(Pestado);
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        try {
            GestorOrden.getInstance().updateEstado(numero, estado);
            return "exito!";
        } catch (NullPointerException | NumberFormatException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
        return "fallo!";
    }

    // </editor-fold>
}
