package org.serratec.trabalho.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteCriar {

    @NotBlank
    private String nome;

    @NotBlank
    @Pattern(regexp = "\\d{11}")
    private String telefone;

    @NotBlank
    @Pattern(regexp = "\\d{11}", message = "O Cpf precisa ter 11 caracteres!")
    private String cpf;

    @NotBlank
    @Email //ver sobre isso
    private String email;

}
