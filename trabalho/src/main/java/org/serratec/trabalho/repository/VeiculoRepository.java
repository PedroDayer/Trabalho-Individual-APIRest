package org.serratec.trabalho.repository;

import org.serratec.trabalho.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, UUID> {

    boolean existsByPlaca(String placa);
    boolean existsByMarca(String marca);
    boolean existsByModelo(String modelo);


    List<Veiculo> findByPlacaIgnoreCase(String placa);
    List<Veiculo> findByMarcaIgnoreCase(String marca);
    List<Veiculo> findByModeloIgnoreCase(String modelo);


}
