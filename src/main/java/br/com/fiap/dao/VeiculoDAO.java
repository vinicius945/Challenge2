package br.com.fiap.dao;

import br.com.fiap.to.VeiculoTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VeiculoDAO extends Repository{
    public ArrayList<VeiculoTO> findAll() {
        ArrayList<VeiculoTO> veiculos = new ArrayList<>();
        String sql = "SELECT * FROM sos_veiculo ORDER BY id_veiculo";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                VeiculoTO veiculo = new VeiculoTO();
                veiculo.setIdVeiculo(rs.getLong("id_veiculo"));
                veiculo.setPlacaCarro(rs.getString("placa_carro"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setIdProblema(rs.getLong("id_problema"));
                veiculos.add(veiculo);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }

        return veiculos;
    }

    public VeiculoTO findById(Long idVeiculo) {
        VeiculoTO veiculo = new VeiculoTO();
        String sql = "SELECT * FROM sos_veiculo WHERE id_veiculo = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idVeiculo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                veiculo.setIdVeiculo(rs.getLong("id_veiculo"));
                veiculo.setPlacaCarro(rs.getString("placa_carro"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setIdProblema(rs.getLong("id_problema"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }

        return veiculo;
    }

    public VeiculoTO save(VeiculoTO veiculo) {
        String sql = "INSERT INTO sos_veiculo(id_veiculo, placa_carro, modelo, id_problema) VALUES(?, ?, ?, ?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, veiculo.getIdVeiculo());
            ps.setString(2, veiculo.getPlacaCarro());
            ps.setString(3, veiculo.getModelo());
            ps.setLong(4, veiculo.getIdProblema());

            if (ps.executeUpdate() > 0) {
                return veiculo;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        } finally {
            closeConnection();
        }

        return null;
    }

    public boolean delete(Long idVeiculo) {
        String sql = "DELETE FROM sos_veiculo WHERE id_veiculo = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idVeiculo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            closeConnection();
        }

        return false;
    }

    public VeiculoTO update(VeiculoTO veiculo) {
        String sql = "UPDATE sos_veiculo SET placa_carro = ?, modelo = ?, id_problema = ? WHERE id_veiculo = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, veiculo.getPlacaCarro());
            ps.setString(2, veiculo.getModelo());
            ps.setLong(3, veiculo.getIdProblema());
            ps.setLong(4, veiculo.getIdVeiculo());

            if (ps.executeUpdate() > 0) {
                return veiculo;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        } finally {
            closeConnection();
        }

        return null;
    }
}
