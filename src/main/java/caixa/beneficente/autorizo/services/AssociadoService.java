package caixa.beneficente.autorizo.services;


import caixa.beneficente.autorizo.models.Associado;
import caixa.beneficente.autorizo.repositories.AssociadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssociadoService {

    AssociadoRepository associadoRepository;

    public AssociadoService(AssociadoRepository associadoRepository) {
        this.associadoRepository = associadoRepository;
    }

    public List<Associado> findAll(){
        return associadoRepository.findAll();
    }

    public List<Associado> findByRg(String rg) {
        return associadoRepository.findByRg(rg);
    }

    public List<Associado> findByNome(String nome){
        return associadoRepository.findByNome(nome);
    }

    public List<Associado> findByCpf(String cpf){
        return associadoRepository.findByCpf(cpf);
    }

    public Associado findById(Long id){
        return associadoRepository.findById(id).get();
    }
}
