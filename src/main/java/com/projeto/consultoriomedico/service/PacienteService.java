package com.projeto.consultoriomedico.service;

import com.projeto.consultoriomedico.models.Paciente;
import com.projeto.consultoriomedico.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente registrarPaciente(Paciente paciente){
        return pacienteRepository.save(paciente);
    }

    public List<Paciente> listarPacientes(){
        return pacienteRepository.findAll();
    }

    public Paciente buscarPacientePorId(UUID id){
        return pacienteRepository.findById(id).orElse(null);
    }

    public void  deletarPaciente(UUID id){
        pacienteRepository.deleteById(id);
    }

    public Paciente editarPaciente(UUID id, Paciente paciente){
        return pacienteRepository.save(paciente);
    }

}