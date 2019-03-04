package WS;

import Modelos.Idioma;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/Idioma")
public class WSIdioma {
    @GET
    @Path("/listado")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Idioma> getAll() {
        Idioma idiomas = new Idioma();
        return idiomas.mostrarTodas();
    }

    @POST
    @Path("/insert")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String post(Idioma idioma) {
        idioma.insert();
        return "{valid:1}";
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String put(Idioma idioma) {
        idioma.update();
        return "{valid:1}";
    }

    @DELETE
    @Path("/delete/{idIdioma}")
    @Produces(MediaType.TEXT_PLAIN)
    public String delete(@PathParam("idIdioma") int idIdioma) {
        Idioma idioma = new Idioma();
        idioma.setIdIdioma(idIdioma);
        idioma.delete();
        return "{valid:1}";
    }
}
