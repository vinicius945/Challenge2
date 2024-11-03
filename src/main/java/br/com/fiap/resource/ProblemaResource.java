package br.com.fiap.resource;

import br.com.fiap.bo.ProblemaBO;
import br.com.fiap.to.ProblemaTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/sos/problema")
public class ProblemaResource {
    private ProblemaBO problemaBO = new ProblemaBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarProblemas() {
        List<ProblemaTO> problemas = problemaBO.listarTodosProblemas();
        return Response.ok(problemas).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarProblemaPorId(@PathParam("id") Long id) {
        ProblemaTO problema = problemaBO.buscarProblemaPorId(id);
        if (problema != null) {
            return Response.ok(problema).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Problema não encontrado.").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response criarProblema(ProblemaTO problema) {
        ProblemaTO novoProblema = problemaBO.adicionarProblema(problema);
        if (novoProblema != null) {
            return Response.status(Response.Status.CREATED).entity(novoProblema).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao criar o problema.").build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarProblema(@PathParam("id") Long id, ProblemaTO problema) {
        problema.setIdProblema(id);
        ProblemaTO problemaAtualizado = problemaBO.atualizarProblema(problema);
        if (problemaAtualizado != null) {
            return Response.ok(problemaAtualizado).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Problema não encontrado para atualização.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarProblema(@PathParam("id") Long id) {
        boolean deletado = problemaBO.deletarProblema(id);
        if (deletado) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Problema não encontrado para exclusão.").build();
        }
    }
}

