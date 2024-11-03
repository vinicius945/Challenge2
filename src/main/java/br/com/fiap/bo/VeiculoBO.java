package br.com.fiap.bo;

import br.com.fiap.dao.VeiculoDAO;
import br.com.fiap.to.VeiculoTO;

import java.util.ArrayList;

public class VeiculoBO {
    private VeiculoDAO veiculoDAO = new VeiculoDAO();

    public ArrayList<VeiculoTO> listarTodosVeiculos() {
        return veiculoDAO.findAll();
    }

    public VeiculoTO buscarVeiculoPorId(Long idVeiculo) {
        if (idVeiculo == null || idVeiculo <= 0) {
            System.out.println("ID de veículo inválido");
            return null;
        }
        return veiculoDAO.findById(idVeiculo);
    }

    public VeiculoTO adicionarVeiculo(VeiculoTO veiculo) {
        if (veiculo == null || veiculo.getPlacaCarro() == null || veiculo.getPlacaCarro().isEmpty()) {
            System.out.println("Placa do veículo não pode ser nula ou vazia");
            return null;
        }
        return veiculoDAO.save(veiculo);
    }

    public VeiculoTO atualizarVeiculo(VeiculoTO veiculo) {
        if (veiculo == null || veiculo.getIdVeiculo() == null || veiculo.getIdVeiculo() <= 0) {
            System.out.println("Veículo inválido para atualização");
            return null;
        }
        return veiculoDAO.update(veiculo);
    }

    public boolean deletarVeiculo(Long idVeiculo) {
        if (idVeiculo == null || idVeiculo <= 0) {
            System.out.println("ID de veículo inválido para exclusão");
            return false;
        }
        return veiculoDAO.delete(idVeiculo);
    }
}
