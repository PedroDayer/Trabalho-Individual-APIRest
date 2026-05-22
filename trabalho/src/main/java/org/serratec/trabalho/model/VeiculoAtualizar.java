package org.serratec.trabalho.model;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoAtualizar {

    @Size(min = 1, message = "não é permitido vazio")
    private String marca;

    @Size(min = 1, message = "não é permitido vazio")
    private String modelo;

    @Min(1900)
    @Positive
    private Integer ano;

    @Min(1)
    private Float valor;

    @Min(0)
    private Float maximoDesconto;

    private Boolean vendido;

    @Min(0)
    private Float valorVenda;

}
