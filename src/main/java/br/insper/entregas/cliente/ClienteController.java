package br.insper.entregas.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public Collection<Cliente> getClientes() {
        return clienteService.findAll();
    }

    @GetMapping("/{cpf}")
    public Cliente getCliente(@PathVariable String cpf) {
        return clienteService.findOne(cpf);
    }

    @PostMapping
    public Cliente cadastrarCliente(@RequestBody Cliente entregador) {
        return clienteService.cadastrar(entregador);
    }

    @PutMapping("/{cpf}")
    public Cliente updateCliente(@PathVariable String cpf, @RequestBody Cliente entregador) {
        return clienteService.alterar(cpf, entregador);
    }

    @DeleteMapping("/{cpf}")
    public void deleteCliente(@PathVariable String cpf) {
        clienteService.delete(cpf);
    }


}