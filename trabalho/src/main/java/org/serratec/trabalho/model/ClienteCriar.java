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
    @Pattern(regexp = "\\d{11}", message = "deve ter exatamente 11 números.")
    private String telefone;

    @NotBlank
    @Pattern(regexp = "\\d{11}", message = "precisa ter exatamente 11 caracteres numéricos!")
    private String cpf;

    @NotBlank
    @Email(message = "mal formato. Utilize o padão 'teste@gmail.com' ")
    private String email;

}
