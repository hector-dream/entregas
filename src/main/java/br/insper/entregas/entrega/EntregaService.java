package br.insper.entregas.entrega;

import br.insper.entregas.cliente.Cliente;
import br.insper.entregas.cliente.ClienteService;
import br.insper.entregas.entregador.Entregador;
import br.insper.entregas.entregador.EntregadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

@Service
public class EntregaService {

    private HashMap<String, Entrega> entregas = new HashMap<>();

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EntregadorService entregadorService;

    public Collection<Entrega> findAll() {
        return entregas.values().stream()
                .filter(entrega -> entrega.getDeleted().equals(false))
                .toList();
    }

    public Entrega findOne(String id) {
        Entrega entrega = entregas.get(id);
        if (entrega == null || entrega.getDeleted()) {
            throw new RuntimeException("Entrega não encontrada...");
        }
        return entrega;
    }

    public Entrega cadastrar(Entrega entrega) {
        if (entregas.values().stream().anyMatch(entrega1 -> entrega1.getId().equals(entrega.getId()))) {
            throw new RuntimeException("Entrega já cadastrada...");
        }
        if (entrega.getCliente() == null) {
            throw new RuntimeException("Dados incompletos para cadastro...");
        }

        Entregador entregador = entregadorService.selecaoAutomatica(); //como o exercício pede que a seleção do entregador seja automática, fiz essa função e tirei a obrigação de criar com entregador
        Cliente cliente = clienteService.findOne(entrega.getCliente().getCpf());
        entrega.setEntregador(entregador);
        entrega.setCliente(cliente);
        entrega.setDataSolicitacao(LocalDate.now());
        entrega.setId(UUID.randomUUID().toString());
        entrega.setDeleted(false);
        entregas.put(entrega.getId(), entrega);
        return entrega;
    }

    public Entrega alterar(String id, Entrega entrega) {
        Entrega entregaAlterada = findOne(id);

        entregaAlterada.setEntregador(entrega.getEntregador());
        entregaAlterada.setCliente(entrega.getCliente());

        return entregaAlterada;
    }

    public void delete(String id) {
        Entrega entrega = findOne(id);
        entrega.setDeleted(true);
    }
}
