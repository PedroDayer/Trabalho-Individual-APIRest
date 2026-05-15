package org.serratec.trabalho.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false,updatable = false)
    private UUID id;

//    private Cliente cliente;

    @NotBlank
    @Column(nullable = false)
    private String marca;

    @NotBlank
    @Column(nullable = false)
    private String modelo;

    @NotNull
    @Min(1900)
    @Column(nullable = false, precision = 4) //precision = total de digitos
    private int ano;

    @Min(1)
    @NotNull
    @Column(nullable = false)
    private BigDecimal valor;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String placa;

    @Min(0)
    @NotNull
    @Column(name = "maximo_desconto", nullable = false)
    private BigDecimal maximoDesconto;

    @NotNull
    @Column(nullable = false)
    private boolean vendido;


    @Column(name = "valor_venda")
    private BigDecimal valorVenda;

    public Veiculo() {
    }

    public Veiculo(UUID id, String marca, String modelo, int ano,  BigDecimal valor, String placa,  BigDecimal maximoDesconto, boolean vendido,  BigDecimal valorVenda) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.valor = valor;
        this.placa = placa;
        this.maximoDesconto = maximoDesconto;
        this.vendido = vendido;
        this.valorVenda = valorVenda;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public  BigDecimal getMaximoDesconto() {
        return maximoDesconto;
    }

    public void setMaximoDesconto( BigDecimal maximoDesconto) {
        this.maximoDesconto = maximoDesconto;
    }

    public boolean isVendido() {
        return vendido;
    }

    public void setVendido(boolean vendido) {
        this.vendido = vendido;
    }

    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(BigDecimal valorVenda) {
        this.valorVenda = valorVenda;
    }
}

