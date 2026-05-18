package org.serratec.trabalho.controller;

import jakarta.validation.Valid;
import org.serratec.trabalho.entity.Veiculo;
import org.serratec.trabalho.model.*;
import org.serratec.trabalho.service.VeiculoService;
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
    public ResponseEntity<MensagemSucesso> inserir(@Valid @RequestBody VeiculoCadastrar veiculo){
        this.veiculoService.cadastrarVeiculo(veiculo);

        MensagemSucesso mensagemSucesso = new MensagemSucesso("Veiculo cadastrado com sucesso.");
        return ResponseEntity.status(HttpStatus.CREATED).body(mensagemSucesso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensagemSucesso> atualizar(@PathVariable UUID id, @Valid @RequestBody VeiculoAtualizar veiculo){
        this.veiculoService.atualizarVeiculo(id, veiculo);

        MensagemSucesso mensagemSucesso = new MensagemSucesso("Veiculo atualizado com sucesso.");
        return ResponseEntity.ok(mensagemSucesso);
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
