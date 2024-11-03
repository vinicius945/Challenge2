package br.com.fiap.bo;

import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.to.ClienteTO;

import java.util.ArrayList;

public class ClienteBO {

    private ClienteDAO clienteDAO;

    public ClienteBO() {

        this.clienteDAO = new ClienteDAO();
    }

    public ArrayList<ClienteTO> findAll() {

        return clienteDAO.findAll();
    }

    public ClienteTO findByIdCliente(Long idCliente) {

        return clienteDAO.findByIdCliente(idCliente);
    }

    public ClienteTO save(ClienteTO cliente) {

        return clienteDAO.save(cliente);
    }

    public boolean delete(Long idCliente) {

        return clienteDAO.delete(idCliente);
    }

    public ClienteTO update(ClienteTO cliente) {

        return clienteDAO.update(cliente);
    }
}

