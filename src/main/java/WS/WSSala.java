package WS;

import Modelos.Sala;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/Salas")
public class WSSala {
    @GET
    @Path("/listado")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sala> getAll(){
        Sala peliculas=new Sala();
        return peliculas.mostrarTodas();
    }
    @POST
    @Path("/insert")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String post(Sala sala){
        sala.insert();
        return "{valid:1}";
    }
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String put(Sala sala){
        sala.update();
        return "{valid:1}";
    }

    @DELETE
    @Path("/delete/{idSala}")
    @Produces(MediaType.TEXT_PLAIN)
    public String delete(@PathParam("idSala")int idSala){
        Sala sala=new Sala();
        sala.setIdSala(idSala);
        sala.delete();
        return "{valid:1}";
    }
}
