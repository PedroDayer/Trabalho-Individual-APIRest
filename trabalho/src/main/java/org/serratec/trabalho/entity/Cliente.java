package org.serratec.trabalho.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;


import java.util.UUID;

@Entity
@Table
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false) //nullable false no caso do id seria como um dupla segurança
    private UUID id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @Pattern(regexp = "\\d{11}")
    @Column(nullable = false, length = 11)
    private String telefone;

    //quando lançar a exceçao reforce que o numero precisa ser o celular e 11 digitos necessariamente!

    @NotBlank
    @Column(nullable = false, unique = true, length = 11)
    @Pattern(regexp = "\\d{11}")
//    @Size(min = 11, max = 11)
    private String cpf;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    public Cliente() {
    }

    public Cliente(UUID id, String nome, String telefone, String cpf, String email) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
