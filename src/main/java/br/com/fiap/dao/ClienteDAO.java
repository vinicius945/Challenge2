package br.com.fiap.dao;

import br.com.fiap.to.ClienteTO;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO extends Repository {

    public ArrayList<ClienteTO> findAll() {
        ArrayList<ClienteTO> clientes = new ArrayList<>();
        String sql = "select * from sos_cliente order by id_cliente";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ClienteTO cliente = new ClienteTO();
                cliente.setIdCliente(rs.getLong("id_cliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setGenero(rs.getString("genero"));
                cliente.setMotivoDeContato(rs.getString("motivo_de_contato"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }

        return clientes;
    }

    public ClienteTO findByIdCliente(Long idCliente) {
        ClienteTO cliente = new ClienteTO();
        String sql = "select * from sos_cliente where id_cliente = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idCliente);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cliente.setIdCliente(rs.getLong("id_cliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setGenero(rs.getString("genero"));
                cliente.setMotivoDeContato(rs.getString("motivo_de_contato"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }

        return cliente;
    }

    public ClienteTO save(ClienteTO cliente) {
        String sql = "insert into sos_cliente(id_cliente, nome, cpf, genero, motivo_de_contato) values(?, ?, ?, ?, ?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, cliente.getIdCliente());
            ps.setString(2, cliente.getNome());
            ps.setString(3, cliente.getCpf());
            ps.setString(4, cliente.getGenero());
            ps.setString(5, cliente.getMotivoDeContato());

            if (ps.executeUpdate() > 0) {
                return cliente;
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

    public boolean delete(Long idCliente) {
        String sql = "delete from sos_cliente where id_cliente = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idCliente);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            closeConnection();
        }

        return false;
    }

    public ClienteTO update(ClienteTO cliente) {
        String sql = "update sos_cliente set nome=?, cpf=?, genero=?, motivo_de_contato=? where id_cliente=?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getGenero());
            ps.setString(4, cliente.getMotivoDeContato());
            ps.setLong(5, cliente.getIdCliente());

            if (ps.executeUpdate() > 0) {
                return cliente;
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
