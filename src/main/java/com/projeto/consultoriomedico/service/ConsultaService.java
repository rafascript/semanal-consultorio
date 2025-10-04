package com.projeto.consultoriomedico.service;

import com.projeto.consultoriomedico.models.Consulta;
import com.projeto.consultoriomedico.repository.ConsultaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;

    public ConsultaService(ConsultaRepository consultaRepository){
        this.consultaRepository = consultaRepository;
    }

    public Consulta criarConsulta(Consulta consulta){
        return consultaRepository.save(consulta);
    }

    public List<Consulta> buscarConsultas(){
        return consultaRepository.findAll();
    }

    public Consulta buscarConsultaPorId(UUID id){
        return consultaRepository.findById(id).orElse(null);
    }

    public void deletarConsulta(UUID id){
        consultaRepository.deleteById(id);
    }

    public Consulta editarConsulta(Consulta consulta, UUID id){
        return consultaRepository.save(consulta);
    }

}
