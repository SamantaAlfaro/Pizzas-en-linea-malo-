
package PizzaEnLinea.modelo.complemento;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ServicioComplemento", targetNamespace = "http://servicios/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ServicioComplemento {


    /**
     * 
     * @param nombre
     * @param value
     * @param campo
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "updateComplements", targetNamespace = "http://servicios/", className = "PizzaEnLinea.modelo.complemento.UpdateComplements")
    @ResponseWrapper(localName = "updateComplementsResponse", targetNamespace = "http://servicios/", className = "PizzaEnLinea.modelo.complemento.UpdateComplementsResponse")
    @Action(input = "http://servicios/ServicioComplemento/updateComplementsRequest", output = "http://servicios/ServicioComplemento/updateComplementsResponse")
    public String updateComplements(
        @WebParam(name = "nombre", targetNamespace = "")
        String nombre,
        @WebParam(name = "campo", targetNamespace = "")
        String campo,
        @WebParam(name = "value", targetNamespace = "")
        String value);

    /**
     * 
     * @param nombre
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "deleteComplement", targetNamespace = "http://servicios/", className = "PizzaEnLinea.modelo.complemento.DeleteComplement")
    @ResponseWrapper(localName = "deleteComplementResponse", targetNamespace = "http://servicios/", className = "PizzaEnLinea.modelo.complemento.DeleteComplementResponse")
    @Action(input = "http://servicios/ServicioComplemento/deleteComplementRequest", output = "http://servicios/ServicioComplemento/deleteComplementResponse")
    public String deleteComplement(
        @WebParam(name = "nombre", targetNamespace = "")
        String nombre);

    /**
     * 
     * @param precio
     * @param nombre
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "insertComplemet", targetNamespace = "http://servicios/", className = "PizzaEnLinea.modelo.complemento.InsertComplemet")
    @ResponseWrapper(localName = "insertComplemetResponse", targetNamespace = "http://servicios/", className = "PizzaEnLinea.modelo.complemento.InsertComplemetResponse")
    @Action(input = "http://servicios/ServicioComplemento/insertComplemetRequest", output = "http://servicios/ServicioComplemento/insertComplemetResponse")
    public String insertComplemet(
        @WebParam(name = "nombre", targetNamespace = "")
        String nombre,
        @WebParam(name = "precio", targetNamespace = "")
        String precio);

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "listComplements", targetNamespace = "http://servicios/", className = "PizzaEnLinea.modelo.complemento.ListComplements")
    @ResponseWrapper(localName = "listComplementsResponse", targetNamespace = "http://servicios/", className = "PizzaEnLinea.modelo.complemento.ListComplementsResponse")
    @Action(input = "http://servicios/ServicioComplemento/listComplementsRequest", output = "http://servicios/ServicioComplemento/listComplementsResponse")
    public String listComplements();

    /**
     * 
     * @param nombre
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getComplement", targetNamespace = "http://servicios/", className = "PizzaEnLinea.modelo.complemento.GetComplement")
    @ResponseWrapper(localName = "getComplementResponse", targetNamespace = "http://servicios/", className = "PizzaEnLinea.modelo.complemento.GetComplementResponse")
    @Action(input = "http://servicios/ServicioComplemento/getComplementRequest", output = "http://servicios/ServicioComplemento/getComplementResponse")
    public String getComplement(
        @WebParam(name = "nombre", targetNamespace = "")
        String nombre);

}
