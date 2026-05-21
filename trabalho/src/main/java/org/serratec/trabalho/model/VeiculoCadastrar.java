package org.serratec.trabalho.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoCadastrar {

    @NotNull
    private UUID clienteId;

    @NotBlank(message = ", nao pode ser nulo ou vazio")
    private String marca;

    @NotBlank(message = ", nao pode ser nulo ou vazio")
    private String modelo;

    @NotNull(message = ", não pode ser nulo!")
    @Min(1900)
    private Integer ano;

    @NotNull(message = "nao pode ser nulo!")
    @Min(1)
    private Float valor;

    @NotBlank(message = ", nao pode ser nulo ou vazio")
    private String placa;

    @NotNull(message = ", não pode ser nulo!")
    @Min(0)
    private Float maximoDesconto;

}
