package org.serratec.trabalho.controller;

import jakarta.validation.Valid;
import org.serratec.trabalho.entity.Veiculo;
import org.serratec.trabalho.model.VeiculoAtualizar;
import org.serratec.trabalho.model.VeiculoBuscar;
import org.serratec.trabalho.model.VeiculoCadastrar;
import org.serratec.trabalho.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/veiculo")
public class VeiculoController {

    VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @PostMapping
    public ResponseEntity<Void> inserir(@Valid @RequestBody VeiculoCadastrar veiculo){
        this.veiculoService.cadastrarVeiculo(veiculo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable UUID id, @Valid @RequestBody VeiculoAtualizar veiculo){
        this.veiculoService.atualizarVeiculo(id, veiculo);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<VeiculoBuscar>> buscarPlacaMarcaModelo(@RequestParam(required = false) String placa, @RequestParam(required = false) String marca, @RequestParam(required = false) String modelo){
        List<VeiculoBuscar> veiculos = this.veiculoService.listarOuBuscarPlacaMarcaModelo(placa,marca,modelo);
        return ResponseEntity.status(HttpStatus.OK).body(veiculos);
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@Valid @PathVariable UUID id){
        this.veiculoService.deletarVeiculo(id);
        return ResponseEntity.ok().build();
    }

}
