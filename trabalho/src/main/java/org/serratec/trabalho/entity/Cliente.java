package org.serratec.trabalho.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.serratec.trabalho.model.ClienteCriar;


import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    //mappedBy = "cliente": Avisa ao Hibernate que o dono real desse relacionamento é o campo private Cliente cliente; que está lá na classe Veiculo.
    //cascade = CascadeType.ALL: Diz que qualquer operação feita no cliente (salvar, atualizar, deletar) deve ser replicada para os seus veículos.
    //orphanRemoval = true: Garante que se um veículo for removido da lista desse cliente, ele também será apagado fisicamente do banco de dados.
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude // evitar loop infinito
    @JsonManagedReference
    @EqualsAndHashCode.Exclude
    private List<Veiculo> veiculos;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, length = 11)
    private String telefone;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(nullable = false, unique = true)
    private String email;


    public Cliente(ClienteCriar cliente){
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.email = cliente.getEmail();
        this.telefone = cliente.getTelefone();
    }


}
