package org.serratec.trabalho.service;

import org.serratec.trabalho.entity.Cliente;
import org.serratec.trabalho.exception.DadosDuplicadosException;
import org.serratec.trabalho.exception.SolicitacaoNaoEncontradaException;
import org.serratec.trabalho.model.ClienteCriar;
import org.serratec.trabalho.model.ClienteBuscar;
import org.serratec.trabalho.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;


    public Cliente buscarPorId(UUID id){
        return clienteRepository.findById(id).orElseThrow(() -> new SolicitacaoNaoEncontradaException("Cliente com id: " + id + " não encontrado"));
    }


    public Cliente inserirCliente (ClienteCriar cliente){

        if (clienteRepository.existsByCpf(cliente.getCpf())){
            throw new DadosDuplicadosException("Já existe um cliente com esse cpf: " + cliente.getCpf() + ". Informe outro!");
        }
        if (clienteRepository.existsByEmail(cliente.getEmail())){
            throw new DadosDuplicadosException("Já existe um cliente com esse email: " + cliente.getEmail() + ". Informe outro!");
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

            if (!this.clienteRepository.existsByCpf(cpf)){
                throw new SolicitacaoNaoEncontradaException("Cpf não encontrado!");
            }
            clientes = this.clienteRepository.findByCpf(cpf);
        }

        if(nome != null && !nome.isBlank()){
            clientes = this.clienteRepository.findByNomeIgnoreCase(nome);
            if (!this.clienteRepository.existsByNome(nome)){
                throw new SolicitacaoNaoEncontradaException("Nome não encontrado!");
            }
        }

        if(clientes.isEmpty()){
            throw new SolicitacaoNaoEncontradaException("Clientes não encontrados.");
        }

        return clientes.stream().map(cliente -> new ClienteBuscar(cliente)).toList();

    }


    public void removerCliente(UUID id){

        Cliente clienteExistente = buscarPorId(id);
        clienteRepository.delete(clienteExistente);
    }

}
