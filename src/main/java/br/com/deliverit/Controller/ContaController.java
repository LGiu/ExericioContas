package br.com.deliverit.Controller;

import br.com.deliverit.DTO.ContaDTO;
import br.com.deliverit.Model.Conta;
import br.com.deliverit.Service.ContaService;
import br.com.deliverit.Util.Patch;
import br.com.deliverit.Util.Retorno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContaController {

    private final Patch<ContaDTO, Conta> patch;

    @Autowired
    public ContaController(ContaService contaService) {
        patch = new Patch<>(contaService, ContaDTO.class, Conta.class);
    }

    @GetMapping(value = "/conta/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContaDTO> busca(@PathVariable Long id) {
        return patch.consultar(id);
    }

    @GetMapping(value = "/contas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ContaDTO>> buscaLista() {
        return patch.consultar();
    }

    @PostMapping(value = "/conta", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Retorno> salva(@RequestBody Conta conta) {
        return patch.salva(conta);
    }

    @PutMapping(value = "/conta", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Retorno> altera(@RequestBody Conta conta) {
        return patch.altera(conta);
    }

    @DeleteMapping(value = "/conta/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Retorno> exclui(@PathVariable Long id) {
        return patch.exclui(id);
    }
}