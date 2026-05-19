package org.serratec.trabalho.service;

import org.serratec.trabalho.entity.Cliente;
import org.serratec.trabalho.exception.CampoInvalidoException;
import org.serratec.trabalho.exception.DadosDuplicadosException;
import org.serratec.trabalho.exception.SolicitacaoNaoEncontradaException;
import org.serratec.trabalho.model.ClienteCriar;
import org.serratec.trabalho.model.ClienteBuscar;
import org.serratec.trabalho.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {


    ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente buscarPorId(UUID id){
        return clienteRepository.findById(id).orElseThrow(() -> new SolicitacaoNaoEncontradaException("Cliente com id: " + id + " não encontrado"));
    }


    public Cliente inserirCliente (ClienteCriar cliente){

        if (clienteRepository.existsByCpf(cliente.getCpf())){
            throw new DadosDuplicadosException("Já existe um cliente com esse cpf: " + cliente.getCpf() + ". Informe outro!");
        }
        if (clienteRepository.existsByEmail(cliente.getEmail())){
            throw new DadosDuplicadosException("Já existe um cliente com esse email: " + cliente.getEmail() + " . Informe outro!");
        }

        Cliente clienteCriar = new Cliente(cliente);

        return this.clienteRepository.save(clienteCriar);
    }


    public List<ClienteBuscar> listarOubuscarNomeCpf (String nome, String cpf){

        List<Cliente> clientes = new ArrayList<>();

        if((nome == null || nome.isBlank()) && (cpf == null || cpf.isBlank())){

            clientes = clienteRepository.findAll();
        }

        if(cpf != null && !cpf.isBlank()){

            if(!cpf.matches("\\d{11}")){
                throw new CampoInvalidoException("Cpf invalido, digite exatamente 11 caracteres numéricos.");
            }

            if (!this.clienteRepository.existsByCpf(cpf)){
                throw new SolicitacaoNaoEncontradaException("Cpf não encontrado!");
            }
            clientes = this.clienteRepository.findByCpf(cpf);
        }

        if(nome != null && !nome.isBlank()){

            String formatoLike = nome + "%";
            clientes = this.clienteRepository.findByNomeLikeIgnoreCase(formatoLike);
        }

        if(clientes.isEmpty()){
            throw new SolicitacaoNaoEncontradaException("Clientes não encontrados. Não há clientes com esse nome ou Não há clientes disponíveis no momento!");
        }

        return clientes.stream().map(cliente -> new ClienteBuscar(cliente)).toList();

    }


    public void removerCliente(UUID id){

        Cliente clienteExistente = buscarPorId(id);
        clienteRepository.delete(clienteExistente);
    }

}
