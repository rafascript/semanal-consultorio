package com.projeto.consultoriomedico.service;

import com.projeto.consultoriomedico.models.Endereco;
import com.projeto.consultoriomedico.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public Endereco criarEndereco(Endereco endereco){
        return enderecoRepository.save(endereco);
    }

    public List<Endereco> listarEnderecos(){
        return enderecoRepository.findAll();
    }

    public Endereco enderecoPorId(UUID id){
        return enderecoRepository.findById(id).orElse(null);
    }

    public void deletarEnderecoPorId(UUID id){
        this.enderecoRepository.deleteById(id);
    }

    public Endereco editarEndereco(Endereco endereco, UUID id){
        return enderecoRepository.save(endereco);
    }

    public Endereco findByCep(String cep){
        return enderecoRepository.findByCep(cep);
    }

}
