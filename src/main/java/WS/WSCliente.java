package WS;

import Modelos.Cliente;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/Clientes")
public class WSCliente {
    @GET
    @Path("/listado")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> getAll() {
        Cliente clientes = new Cliente();
        return clientes.mostrarTodas();
    }

    @POST
    @Path("/insert")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String post(Cliente cliente) {
        cliente.insert();
        return "{valid:1}";
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String put(Cliente cliente) {
        cliente.update();
        return "{valid:1}";
    }

    @DELETE
    @Path("/delete/{idCliente}")
    @Produces(MediaType.TEXT_PLAIN)
    public String delete(@PathParam("idCliente") int idCliente) {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(idCliente);
        cliente.delete();
        return "{valid:1}";
    }
}
