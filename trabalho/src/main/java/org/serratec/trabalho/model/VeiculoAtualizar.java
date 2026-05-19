package org.serratec.trabalho.model;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoAtualizar {


    private String marca;

    private String modelo;

    @Min(1900)
    private Integer ano;

    @Min(1)
    private Float valor;

    private String placa;

    @Min(0)
    private Float maximoDesconto;

    private Boolean vendido;

    private Float valorVenda;

}
