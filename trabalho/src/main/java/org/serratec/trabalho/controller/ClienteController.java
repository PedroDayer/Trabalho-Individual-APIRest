package org.serratec.trabalho.controller;

import org.serratec.trabalho.entity.Cliente;
import org.serratec.trabalho.repository.ClienteRepository;
import org.serratec.trabalho.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/c1/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Void> inserir(@RequestBody Cliente cliente){
        this.clienteService.inserirCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listar(){
        List<Cliente> clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }

//    @GetMapping("/{cpf}")
//    public ResponseEntity<Cliente> buscarCpf(@PathVariable String cpf){
//        Cliente cliente = clienteService.buscarClienteCpf(cpf);
//        return ResponseEntity.status(HttpStatus.OK).body(cliente);
//    }
//
//    @GetMapping("/buscar")
//    public ResponseEntity<List<Cliente>> buscarNome(@RequestParam String nome){
//        List<Cliente> clientes = clienteService.buscarClientesNome(nome);
//        return ResponseEntity.status(HttpStatus.OK).body(clientes);
//    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable UUID id){
        clienteService.removerClientes(id);
        return ResponseEntity.ok().build();
    }

}