package org.serratec.trabalho.service;

import org.serratec.trabalho.entity.Veiculo;
import org.serratec.trabalho.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public void cadastrarVeiculo(Veiculo veiculo){

        if(veiculo.isVendido() && veiculo.getValorVenda() == null){
//            throw new aqui falando que o valorVenda é obrigatorio
            return;
        }

        this.veiculoRepository.save(veiculo);
    }

    //ha a necessidade do pacote service retornar algo?
    public List<Veiculo> listarVeiculos(){
        return this.veiculoRepository.findAll();
    }

//    public

    public Veiculo atualizarVeiculo(UUID id, Veiculo veiculo){

        Optional<Veiculo> veiculoOptional = this.veiculoRepository.findById(id);

        if(veiculoOptional.isEmpty()){
//            throw new aqui;
            return null;
        }

        Veiculo veiculoBd = veiculoOptional.get();
        veiculoBd.setAno(veiculo.getAno());
        veiculoBd.setMarca(veiculo.getMarca());
        veiculoBd.setModelo(veiculo.getModelo());
        veiculoBd.setPlaca(veiculo.getPlaca());
        veiculoBd.setValor(veiculo.getValor());
        veiculoBd.setMaximoDesconto(veiculo.getMaximoDesconto());
        veiculoBd.setVendido(veiculo.isVendido());
        veiculoBd.setValorVenda(veiculo.getValorVenda());

        this.veiculoRepository.save(veiculoBd);
        return veiculoBd;
    }

}
