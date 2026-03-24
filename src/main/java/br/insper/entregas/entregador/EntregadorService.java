package br.insper.entregas.entregador;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

@Service
public class EntregadorService {

    private HashMap<String, Entregador> entregadores = new HashMap<>();

    public Collection<Entregador> findAll() {
        return entregadores.values().stream()
                .filter(entregador -> entregador.getDeleted().equals(false))
                .toList();
    }

    public Entregador findOne(String email) {
        Entregador entregador = entregadores.get(email);
        if (entregador == null || entregador.getDeleted()) {
            throw new RuntimeException("Entregador não encontrado...");
        }
        return entregador;
    }

    public Entregador cadastrar(Entregador entregador) {
        if (entregadores.values().stream().anyMatch(entregador1 -> entregador1.getEmail().equals(entregador.getEmail()))) {
            throw new RuntimeException("Entregador já cadastrado...");
        }
        if (entregador.getEmail() == null
                ||entregador.getNome() == null
                ||entregador.getDocumento() == null
                ||entregador.getVeiculo() == null) {
            throw new RuntimeException("Dados incompletos para cadastro...");
        }

        entregador.setDeleted(false);
        entregadores.put(entregador.getEmail(), entregador);
        return entregador;
    }

    public Entregador alterar(String email, Entregador entregador) {
        Entregador entregadorAlterado = findOne(email);

        entregadorAlterado.setNome(entregador.getNome());
        entregadorAlterado.setDocumento(entregador.getDocumento());
        entregadorAlterado.setVeiculo(entregador.getVeiculo());

        return entregadorAlterado;
    }

    public void delete(String email) {
        Entregador entregador = findOne(email);
        entregador.setDeleted(true);
    }

    public Entregador selecaoAutomatica() {
        for (String email : entregadores.keySet()) {
             return findOne(email);
        }
        throw new RuntimeException("Nenhum entregador ativo!");
    }
}
