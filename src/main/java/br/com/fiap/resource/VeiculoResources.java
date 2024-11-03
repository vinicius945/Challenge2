package br.com.fiap.resource;

import br.com.fiap.bo.VeiculoBO;
import br.com.fiap.to.VeiculoTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/sos/veiculos")
public class VeiculoResources {
    private VeiculoBO veiculoBO = new VeiculoBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarVeiculos() {
        List<VeiculoTO> veiculos = veiculoBO.listarTodosVeiculos();
        return Response.ok(veiculos).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarVeiculoPorId(@PathParam("id") Long id) {
        VeiculoTO veiculo = veiculoBO.buscarVeiculoPorId(id);
        if (veiculo != null) {
            return Response.ok(veiculo).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Veículo não encontrado.").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response criarVeiculo(VeiculoTO veiculo) {
        VeiculoTO novoVeiculo = veiculoBO.adicionarVeiculo(veiculo);
        if (novoVeiculo != null) {
            return Response.status(Response.Status.CREATED).entity(novoVeiculo).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao criar o veículo.").build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarVeiculo(@PathParam("id") Long id, VeiculoTO veiculo) {
        veiculo.setIdVeiculo(id);
        VeiculoTO veiculoAtualizado = veiculoBO.atualizarVeiculo(veiculo);
        if (veiculoAtualizado != null) {
            return Response.ok(veiculoAtualizado).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Veículo não encontrado para atualização.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarVeiculo(@PathParam("id") Long id) {
        boolean deletado = veiculoBO.deletarVeiculo(id);
        if (deletado) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Veículo não encontrado para exclusão.").build();
        }
    }
}
