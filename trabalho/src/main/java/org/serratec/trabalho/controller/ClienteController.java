package org.serratec.trabalho.controller;

import org.serratec.trabalho.entity.Cliente;
import org.serratec.trabalho.repository.ClienteRepository;
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
    ClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity<Void> inserirCliente(@RequestBody Cliente cliente){
        this.clienteRepository.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes(){
        List<Cliente> clientes = this.clienteRepository.findAll();
        return ResponseEntity.ok(clientes);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Cliente> buscarClienteNomeCpf(@RequestParam String nome, @PathVariable String cpf){
//
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable UUID id){
        if(!clienteRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        this.clienteRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}