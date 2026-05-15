package org.serratec.trabalho.repository;

import org.serratec.trabalho.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    Optional<Cliente> findByCpf(String cpf);

    List<Cliente> findAllByNome(String nome);
}