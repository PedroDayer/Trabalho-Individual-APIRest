package org.serratec.trabalho.service;

import org.serratec.trabalho.entity.Cliente;
import org.serratec.trabalho.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public void inserirCliente (Cliente cliente){
        this.clienteRepository.save(cliente);
    }

    public List<Cliente> listarClientes(){
        return this.clienteRepository.findAll();
    }

    public Cliente buscarClienteCpf(String cpf){

        Optional<Cliente> clienteOptional = this.clienteRepository.findByCpf(cpf);

        if (clienteOptional.isEmpty()){
            return null;
            //throw new exception aqui!
        }

        return clienteOptional.get();
    }

    public List<Cliente> buscarClientesNome(String nome){

        List<Cliente> clientes = this.clienteRepository.findAllByNome(nome);

        if(clientes.isEmpty()){
            //throw new exception aqui!
        }

        return clientes;
    }

    public void removerClientes(UUID id){

        Optional<Cliente> clienteOptional = this.clienteRepository.findById(id);

        if(clienteOptional.isEmpty()){
            //throw new exception aqui!
        }

        clienteRepository.deleteById(id);
    }

}
