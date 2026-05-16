package org.serratec.trabalho.controller;

import jakarta.validation.Valid;
import org.serratec.trabalho.entity.Veiculo;
import org.serratec.trabalho.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/c1/veiculo")
public class VeiculoController {

    @Autowired
    VeiculoService veiculoService;


    @PostMapping
    public ResponseEntity<Void> inserir(@Valid @RequestBody Veiculo veiculo){
        this.veiculoService.cadastrarVeiculo(veiculo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Veiculo>> listar(){
        List<Veiculo> veiculos = this.veiculoService.listarVeiculos();
        return ResponseEntity.ok(veiculos);
    }

//    @GetMapping
//    public

//    @PutMapping()
//    public ResponseEntity<Veiculo> atualizar


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@Valid @PathVariable UUID id){
        this.veiculoService.deletarVeiculo(id);
        return ResponseEntity.ok().build();
    }


}
