package com.projeto.consultoriomedico.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.consultoriomedico.models.Endereco;
import com.projeto.consultoriomedico.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.lang.runtime.ObjectMethods;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.UUID;

@Service
public class EnderecoService {

    private final HttpClient client = HttpClient.newHttpClient();

    private final ObjectMapper mapper = new ObjectMapper();

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

    public Endereco searchByCep(String cep){

        try {
            String url = "https://viacep.com.br/ws/" + cep +"/json/";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Endereco endereco = mapper.readValue(response.body(), Endereco.class);
            return endereco;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
