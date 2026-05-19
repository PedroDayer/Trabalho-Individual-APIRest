package org.serratec.trabalho.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;
import org.serratec.trabalho.model.VeiculoAtualizar;
import org.serratec.trabalho.model.VeiculoCadastrar;

import java.math.BigDecimal;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Check(constraints = "ano >= 1900")
@Check(constraints = "maximo_desconto >= 0")
@Check(constraints = "valor >= 1")
@Check(constraints = "valor_venda >= 0")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne()
    @JoinColumn(nullable = false)
    private Cliente cliente;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private Integer ano;

    @Column(nullable = false)
    private Float valor;

    @Column(nullable = false, unique = true)
    private String placa;

    @Column(name = "maximo_desconto", nullable = false)
    private Float maximoDesconto;

    @Column(nullable = false)
    private Boolean vendido;

    @Column(name = "valor_venda")
    private Float valorVenda = null;


    public void atualizarDados(VeiculoAtualizar veiculoAtualizar) {
        if (veiculoAtualizar.getMarca() != null){
            this.marca = veiculoAtualizar.getMarca();
        }
        if (veiculoAtualizar.getModelo() != null){
            this.modelo = veiculoAtualizar.getModelo();
        }
        if (veiculoAtualizar.getAno() != null){
            this.ano = veiculoAtualizar.getAno();
        }
        if (veiculoAtualizar.getValor() != null){
            this.valor = veiculoAtualizar.getValor();
        }
        if(veiculoAtualizar.getPlaca() != null){
            this.placa = veiculoAtualizar.getPlaca();
        }
        if (veiculoAtualizar.getMaximoDesconto() != null){
            this.maximoDesconto = veiculoAtualizar.getMaximoDesconto();
        }

        if (veiculoAtualizar.getVendido() != null) {
            this.vendido = veiculoAtualizar.getVendido();

            // Se vendido = true
            if (this.vendido) {
                if (veiculoAtualizar.getValorVenda() != null) {
                    this.valorVenda = veiculoAtualizar.getValorVenda();
                }
            } else {
                // Se mudou para false, limpa o valor da venda
                this.valorVenda = null;
            }
        }
        //metodo para corrigir o valorVenda apenas
        else if (veiculoAtualizar.getValorVenda() != null) {
            this.valorVenda = veiculoAtualizar.getValorVenda();
        }

    }

    public Veiculo(VeiculoCadastrar  veiculoCadastrar, Cliente cliente) {
        this.marca =  veiculoCadastrar.getMarca();
        this.modelo =  veiculoCadastrar.getModelo();
        this.ano =  veiculoCadastrar.getAno();
        this.valor =  veiculoCadastrar.getValor();
        this.placa =  veiculoCadastrar.getPlaca();
        this.maximoDesconto =  veiculoCadastrar.getMaximoDesconto();
        this.cliente = cliente;

        //começa como falso, pq...
//        @Column(nullable = false)
//        private Boolean vendido;
        this.vendido = false;
    }

}

