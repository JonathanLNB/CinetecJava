package WS;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import Modelos.Pelicula;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
@Path("/Peliculas")
public class WSPelicula {
    @GET
    @Path("/listado")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pelicula> getAll(){
        Pelicula peliculas=new Pelicula();
        return peliculas.mostrarTodas();
    }
    @POST
    @Path("/insert")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String post(Pelicula pelicula){
        pelicula.insert();
        return "{valid:1}";
    }
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String put(Pelicula pelicula){
        pelicula.update();
        return "{valid:1}";
    }

    @DELETE
    @Path("/delete/{idPelicula}")
    @Produces(MediaType.TEXT_PLAIN)
    public String delete(@PathParam("idPelicula")int idPelicula){
        Pelicula pelicula=new Pelicula();
        pelicula.setIdPelicula(idPelicula);
        pelicula.delete();
        return "{valid:1}";
    }
}
