package org.serratec.trabalho.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.serratec.trabalho.model.VeiculoAtualizar;
import org.serratec.trabalho.model.VeiculoCadastrar;

import java.math.BigDecimal;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID id;

//    private Cliente cliente;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false, precision = 4) //precision = total de digitos
    private int ano;

    @Column(nullable = false)
    private float valor;

    @Column(nullable = false, unique = true)
    private String placa;

    @Column(name = "maximo_desconto", nullable = false)
    private float maximoDesconto;

    @Column(nullable = false)
    private boolean vendido;

    @Column(name = "valor_venda")
    private Float valorVenda = null;

    public Veiculo(VeiculoCadastrar veiculoCadastrar){
        this.marca = veiculoCadastrar.getMarca();
        this.modelo = veiculoCadastrar.getModelo();
        this.ano = veiculoCadastrar.getAno();
        this.valor = veiculoCadastrar.getValor();
        this.placa = veiculoCadastrar.getPlaca();
        this.maximoDesconto = veiculoCadastrar.getMaximoDesconto();
    }

    public void atualizarDados(VeiculoAtualizar veiculoAtualizar) {
        this.marca = veiculoAtualizar.getMarca();
        this.modelo = veiculoAtualizar.getModelo();
        this.ano = veiculoAtualizar.getAno();
        this.valor = veiculoAtualizar.getValor();
        this.placa = veiculoAtualizar.getPlaca();
        this.maximoDesconto = veiculoAtualizar.getMaximoDesconto();
        this.vendido = veiculoAtualizar.isVendido();

        //ou seja, se atualizar para nao vendido, o valor venda vai ser null
        this.valorVenda = veiculoAtualizar.isVendido() ? veiculoAtualizar.getValorVenda() : null;
    }


}

