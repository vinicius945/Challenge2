package br.com.fiap.resource;

import br.com.fiap.bo.ClienteBO;
import br.com.fiap.to.ClienteTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/sos")
public class ClienteResource {

    private ClienteBO clienteBO = new ClienteBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        ArrayList<ClienteTO> resultado = clienteBO.findAll();

        if (resultado != null) {
            return Response.ok(resultado).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/{idCliente}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByIdCliente(@PathParam("idCliente") Long idCliente) {
        ClienteTO resultado = clienteBO.findByIdCliente(idCliente);

        if (resultado != null) {
            return Response.ok(resultado).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@Valid ClienteTO cliente) {
        ClienteTO resultado = clienteBO.save(cliente);

        if (resultado != null) {
            return Response.status(Response.Status.CREATED).entity(resultado).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/{idCliente}")
    public Response delete(@PathParam("idCliente") Long idCliente) {
        if (clienteBO.delete(idCliente)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/{idCliente}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Valid ClienteTO cliente, @PathParam("idCliente") Long idCliente) {
        cliente.setIdCliente(idCliente);
        ClienteTO resultado = clienteBO.update(cliente);

        if (resultado != null) {
            return Response.ok(resultado).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
