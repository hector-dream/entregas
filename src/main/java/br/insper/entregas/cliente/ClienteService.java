package br.insper.entregas.cliente;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

@Service
public class ClienteService {

    private HashMap<String, Cliente> clientes = new HashMap<>();

    public Collection<Cliente> findAll() {
        return clientes.values().stream()
                .filter(cliente -> cliente.getDeleted().equals(false))
                .toList();
    }

    public Cliente findOne(String cpf) {
        Cliente cliente = clientes.get(cpf);
        if (cliente == null || cliente.getDeleted()) {
            throw new RuntimeException("Cliente não encontrado...");
        }
        return cliente;
    }

    public Cliente cadastrar(Cliente cliente) {
        if (clientes.values().stream().anyMatch(cliente1 -> cliente1.getCpf().equals(cliente.getCpf()))) {
            throw new RuntimeException("Cliente já cadastrado...");
        }
        if (cliente.getEmail() == null
                ||cliente.getNome() == null
                ||cliente.getTelefone() == null
                ||cliente.getCpf() == null) {
            throw new RuntimeException("Dados incompletos para cadastro...");
        }

        cliente.setDeleted(false);
        clientes.put(cliente.getCpf(), cliente);
        return cliente;
    }

    public Cliente alterar(String cpf, Cliente cliente) {
        Cliente clienteAlterado = findOne(cpf);

        clienteAlterado.setNome(cliente.getNome());
        clienteAlterado.setEmail(cliente.getEmail());
        clienteAlterado.setTelefone(cliente.getTelefone());

        return clienteAlterado;
    }

    public void delete(String cpf) {
        Cliente cliente = findOne(cpf);
        cliente.setDeleted(true);
    }
}
