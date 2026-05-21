package org.serratec.trabalho.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.serratec.trabalho.entity.Cliente;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteBuscar {

    private UUID id;
    private String nome;
    private String telefone;
    private String cpf;
    private String email;
    private List<VeiculoBuscar> veiculos;

    private String formatTelefone(String telefone){
        String telefoneFormated = "";
        telefoneFormated += "(" + telefone.substring(0,2) + ") ";
        telefoneFormated += telefone.substring(2,7) + "-";
        telefoneFormated += telefone.substring(7);
        return telefoneFormated;
    }

    private String formatCPF(String cpf) {
        String cpfFormated = "";
        cpfFormated += cpf.substring(0,3) + ".";
        cpfFormated += cpf.substring(3,6) + ".";
        cpfFormated += cpf.substring(6,9) + "-";
        cpfFormated += cpf.substring(9);
        return cpfFormated;
    }

    public ClienteBuscar(Cliente cliente){
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cpf = formatCPF(cliente.getCpf());
        this.email = cliente.getEmail();
        this.telefone = formatTelefone(cliente.getTelefone());

    }
}