package br.insper.entregas.entregador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/entregadores")
public class EntregadorController {

    @Autowired
    private EntregadorService entregadorService;

    @GetMapping
    public Collection<Entregador> getEntregadores() {
        return entregadorService.findAll();
    }

    @GetMapping("/{email}")
    public Entregador getEntregador(@PathVariable String email) {
        return entregadorService.findOne(email);
    }

    @PostMapping
    public Entregador cadastrarEntregador(@RequestBody Entregador entregador) {
        return entregadorService.cadastrar(entregador);
    }

    @PutMapping("/{email}")
    public Entregador updateEntregador(@PathVariable String email, @RequestBody Entregador entregador) {
        return entregadorService.alterar(email, entregador);
    }

    @DeleteMapping("/{email}")
    public void deleteEntregador(@PathVariable String email) {
        entregadorService.delete(email);
    }


}