package org.serratec.trabalho.model;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoAtualizar {


    @NotBlank
    private String marca;

    @NotBlank
    private String modelo;

    @NotNull
    @Min(1900)
    private int ano;

    @NotNull
    @Min(1)
    private float valor;

    @NotBlank
    private String placa;

    @NotNull
    @Min(0)
    private float maximoDesconto;

    @NotNull
    private boolean vendido;

    private Float valorVenda;

}
