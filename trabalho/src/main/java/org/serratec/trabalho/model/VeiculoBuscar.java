package org.serratec.trabalho.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.serratec.trabalho.entity.Veiculo;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoBuscar {

    private UUID id;
    private String marca;
    private String modelo;
    private Integer ano;
    private Float valor;
    private String placa;
    private Float maximoDesconto;
    private boolean vendido;
    private Float valorVenda;

    public VeiculoBuscar(Veiculo veiculo){
        this.id = veiculo.getId();
        this.marca = veiculo.getMarca();
        this.modelo = veiculo.getModelo();
        this.ano = veiculo.getAno();
        this.valor = veiculo.getValor();
        this.placa = veiculo.getPlaca();
        this.maximoDesconto = veiculo.getMaximoDesconto();
        this.vendido = veiculo.isVendido();
        this.valorVenda = veiculo.getValorVenda();
    }


}
