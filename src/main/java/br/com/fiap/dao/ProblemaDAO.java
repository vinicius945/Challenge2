package br.com.fiap.dao;

import br.com.fiap.dao.Repository;
import br.com.fiap.to.ProblemaTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProblemaDAO extends Repository {

    public ArrayList<ProblemaTO> findAll() {
        ArrayList<ProblemaTO> problemas = new ArrayList<>();
        String sql = "SELECT * FROM sos_problema ORDER BY id_problema";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProblemaTO problema = new ProblemaTO();
                problema.setIdProblema(rs.getLong("id_problema"));
                problema.setDescricao(rs.getString("descricao"));
                problemas.add(problema);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }

        return problemas;
    }

    public ProblemaTO findById(Long idProblema) {
        ProblemaTO problema = new ProblemaTO();
        String sql = "SELECT * FROM sos_problema WHERE id_problema = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idProblema);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                problema.setIdProblema(rs.getLong("id_problema"));
                problema.setDescricao(rs.getString("descricao"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }

        return problema;
    }

    public ProblemaTO save(ProblemaTO problema) {
        String sql = "INSERT INTO sos_problema(id_problema, descricao) VALUES(?, ?)";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, problema.getIdProblema());
            ps.setString(2, problema.getDescricao());

            if (ps.executeUpdate() > 0) {
                return problema;
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

    public boolean delete(Long idProblema) {
        String sql = "DELETE FROM sos_problema WHERE id_problema = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idProblema);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            closeConnection();
        }

        return false;
    }

    public ProblemaTO update(ProblemaTO problema) {
        String sql = "UPDATE sos_problema SET descricao = ? WHERE id_problema = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, problema.getDescricao());
            ps.setLong(2, problema.getIdProblema());

            if (ps.executeUpdate() > 0) {
                return problema;
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
