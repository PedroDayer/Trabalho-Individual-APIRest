package org.serratec.trabalho.controller;

import jakarta.validation.Valid;
import org.serratec.trabalho.entity.Cliente;
import org.serratec.trabalho.model.ClienteCriar;
import org.serratec.trabalho.model.ClienteBuscar;
import org.serratec.trabalho.model.MensagemSucesso;
import org.serratec.trabalho.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteController {


    ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<MensagemSucesso> inserir(@Valid @RequestBody ClienteCriar cliente){
        this.clienteService.inserirCliente(cliente);

        MensagemSucesso mensagemSucesso = new MensagemSucesso("Cliente criado com sucesso.");

        return ResponseEntity.status(HttpStatus.CREATED).body(mensagemSucesso);
    }

    @GetMapping
    public ResponseEntity<List<ClienteBuscar>> buscarNomeCpf(@RequestParam(required = false) String nome, @RequestParam(required = false) String cpf){
        List<ClienteBuscar> clientes = this.clienteService.listarOubuscarNomeCpf(nome,cpf);
        return ResponseEntity.status(HttpStatus.OK).body(clientes);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable UUID id){
        clienteService.removerCliente(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}