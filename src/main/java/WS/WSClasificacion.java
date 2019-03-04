package WS;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import Modelos.Clasificacion;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
@Path("/Clasificaciones")
public class WSClasificacion {
    @POST
    @Path("/insert")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String post(Clasificacion clasificacion){
        clasificacion.insert();
        return "{valid:1}";
    }
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String put(Clasificacion clasificacion){
        clasificacion.update();
        return "{valid:1}";
    }
    @DELETE
    @Path("/delete/{id_clasificacion}")
    @Produces(MediaType.APPLICATION_JSON)
    public String delete(@PathParam("id_clasificacion")int idClasificacion){
        Clasificacion clasificacion=new Clasificacion();
        clasificacion.setIdClasificacion(idClasificacion);
        clasificacion.delete();
        return "{valid:1}";
    }

    @GET
    @Path("/listado")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Clasificacion> getAll(){
        Clasificacion clasificaciones=new Clasificacion();
        return clasificaciones.mostrarTodas();
    }
}
