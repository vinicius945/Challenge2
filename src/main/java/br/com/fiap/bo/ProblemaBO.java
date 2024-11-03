package br.com.fiap.bo;


import br.com.fiap.dao.ProblemaDAO;
import br.com.fiap.to.ProblemaTO;

import java.util.ArrayList;


public class ProblemaBO {
    private ProblemaDAO problemaDAO = new ProblemaDAO();

    public ArrayList<ProblemaTO> listarTodosProblemas() {
        return problemaDAO.findAll();
    }

    public ProblemaTO buscarProblemaPorId(Long idProblema) {
        if (idProblema == null || idProblema <= 0) {
            System.out.println("ID de problema inválido");
            return null;
        }
        return problemaDAO.findById(idProblema);
    }

    public ProblemaTO adicionarProblema(ProblemaTO problema) {
        if (problema == null || problema.getDescricao() == null || problema.getDescricao().isEmpty()) {
            System.out.println("Descrição do problema não pode ser nula ou vazia");
            return null;
        }
        return problemaDAO.save(problema);
    }

    public ProblemaTO atualizarProblema(ProblemaTO problema) {
        if (problema == null || problema.getIdProblema() == null || problema.getIdProblema() <= 0) {
            System.out.println("Problema inválido para atualização");
            return null;
        }
        return problemaDAO.update(problema);
    }

    public boolean deletarProblema(Long idProblema) {
        if (idProblema == null || idProblema <= 0) {
            System.out.println("ID de problema inválido para exclusão");
            return false;
        }
        return problemaDAO.delete(idProblema);
    }
}
