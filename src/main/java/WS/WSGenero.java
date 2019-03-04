package WS;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import Modelos.Genero;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
@Path("/Generos")
public class WSGenero {
    @GET
    @Path("/listado")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Genero> getAll(){
        Genero genero=new Genero();
        return genero.mostrarTodas();
    }
    @POST
    @Path("/insert")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String post(Genero genero){
        genero.insert();
        return "{valid:1}";
    }
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String put(Genero genero){
        genero.update();
        return "{valid:1}";
    }
    @DELETE
    @Path("/delete/{idGenero}")
    @Produces(MediaType.TEXT_PLAIN)
    public String delete(@PathParam("idGenero")int idGenero){
        Genero genero=new Genero();
        genero.setIdGenero(idGenero);
        genero.delete();
        return "{valid:1}";
    }
}
