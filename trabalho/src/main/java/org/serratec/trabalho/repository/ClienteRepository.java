package org.serratec.trabalho.repository;

import org.serratec.trabalho.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);

    List<Cliente> findByCpf(String cpf);
    List<Cliente> findByNomeLikeIgnoreCase(String nome);
}