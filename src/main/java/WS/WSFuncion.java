package WS;

import Modelos.Funcion;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/Funciones")
public class WSFuncion {
    @GET
    @Path("/listado")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Funcion> getAll(){
        Funcion funciones=new Funcion();
        return funciones.mostrarTodas();
    }
    @POST
    @Path("/insert")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String post(Funcion funcion){
        funcion.insert();
        return "{valid:1}";
    }
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String put(Funcion funcion){
        funcion.update();
        return "{valid:1}";
    }

    @DELETE
    @Path("/delete/{idFuncion}")
    @Produces(MediaType.TEXT_PLAIN)
    public String delete(@PathParam("idFuncion")int idFuncion){
        Funcion funcion=new Funcion();
        funcion.setIdFuncion(idFuncion);
        funcion.delete();
        return "{valid:1}";
    }
}
