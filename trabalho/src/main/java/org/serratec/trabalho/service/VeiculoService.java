package org.serratec.trabalho.service;

import org.serratec.trabalho.entity.Cliente;
import org.serratec.trabalho.entity.Veiculo;
import org.serratec.trabalho.exception.CampoInvalidoException;
import org.serratec.trabalho.exception.DadosDuplicadosException;
import org.serratec.trabalho.exception.SolicitacaoNaoEncontradaException;
import org.serratec.trabalho.model.VeiculoAtualizar;
import org.serratec.trabalho.model.VeiculoBuscar;
import org.serratec.trabalho.model.VeiculoCadastrar;
import org.serratec.trabalho.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class VeiculoService {


    private VeiculoRepository veiculoRepository;
    private ClienteService clienteService;

    public VeiculoService(VeiculoRepository veiculoRepository, ClienteService clienteService) {
        this.veiculoRepository = veiculoRepository;
        this.clienteService = clienteService;
    }

    public Veiculo buscarPorId(UUID id){
        return veiculoRepository.findById(id).orElseThrow(() -> new SolicitacaoNaoEncontradaException("Veículo com id: " + id + " não encontrado"));
    }


    public void cadastrarVeiculo(VeiculoCadastrar veiculo){

        if(veiculoRepository.existsByPlaca(veiculo.getPlaca())){
            throw new DadosDuplicadosException("Já existe um veículo com essa placa: " + veiculo.getPlaca() + ". Informe outra!");
        }

        Cliente cliente = this.clienteService.buscarPorId(veiculo.getClienteId());
        Veiculo veiculoDB = new Veiculo(veiculo, cliente);
        this.veiculoRepository.save(veiculoDB);
    }

    public void atualizarVeiculo(UUID id, VeiculoAtualizar veiculo){

        Veiculo veiculoExistente = buscarPorId(id);

        if(veiculo.getVendido() != null && veiculo.getVendido() && veiculo.getValorVenda() == null){
         throw new CampoInvalidoException("O valor da venda precisa ser informado!");
        }
        if(veiculo.getValorVenda() != null && veiculo.getVendido() == null){
            throw new CampoInvalidoException("O campo vendido precisa ser true ou dito novamente para que possa especificar um valor de venda.");
        }
        if (Boolean.FALSE.equals(veiculo.getVendido())){
            if (veiculo.getValorVenda() != null){
                throw new CampoInvalidoException("sendo o campo vendido como false, não é possível que haja um valor de venda!");
            }
        }

        veiculoExistente.atualizarDados(veiculo);
        this.veiculoRepository.save(veiculoExistente);
    }

    public List<VeiculoBuscar> listarOuBuscarPlacaMarcaModelo(String placa, String marca, String modelo){

        List<Veiculo> veiculos = new ArrayList<>();

        if ((placa == null || placa.isBlank()) && (marca == null || marca.isBlank()) && (modelo == null || modelo.isBlank())){
            veiculos = this.veiculoRepository.findAll();
        }

        if(placa != null && !placa.isBlank()){

            if (!this.veiculoRepository.existsByPlaca(placa)){
                throw new SolicitacaoNaoEncontradaException("Placa não encontrada!");
            }

            veiculos = this.veiculoRepository.findByPlaca(placa);
        }

        if(marca != null && !marca.isBlank()){

            String formatoLike = marca + "%";
            veiculos = this.veiculoRepository.findByMarcaLikeIgnoreCase(formatoLike);
        }

        if(modelo != null && !modelo.isBlank()){

            String formatoLike = modelo + "%";
            veiculos = this.veiculoRepository.findByModeloLikeIgnoreCase(formatoLike);
        }

        if(veiculos.isEmpty()){
            throw new SolicitacaoNaoEncontradaException("Veiculos não encontrados.");
        }

        return veiculos.stream().map(veiculo -> new VeiculoBuscar(veiculo)).toList();
    }


    public void deletarVeiculo(UUID id){
        Veiculo veiculoExistente = buscarPorId(id);

        this.veiculoRepository.delete(veiculoExistente);
    }


}
