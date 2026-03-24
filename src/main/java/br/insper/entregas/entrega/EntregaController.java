package br.insper.entregas.entrega;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

    @Autowired
    private EntregaService entregaService;

    @GetMapping
    public Collection<Entrega> getEntregas() {
        return entregaService.findAll();
    }

    @GetMapping("/{id}")
    public Entrega getEntrega(@PathVariable String id) {
        return entregaService.findOne(id);
    }

    @PostMapping
    public Entrega cadastrarEntrega(@RequestBody Entrega entrega) {
        return entregaService.cadastrar(entrega);
    }

    @PutMapping("/{id}")
    public Entrega updateEntrega(@PathVariable String id, @RequestBody Entrega entrega) {
        return entregaService.alterar(id, entrega);
    }

    @DeleteMapping("/{id}")
    public void deleteEntrega(@PathVariable String id) {
        entregaService.delete(id);
    }


}