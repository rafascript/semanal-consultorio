package com.projeto.consultoriomedico;

import com.projeto.consultoriomedico.models.Consulta;
import com.projeto.consultoriomedico.models.Endereco;
import com.projeto.consultoriomedico.models.Paciente;
import com.projeto.consultoriomedico.models.StatusConsulta;
import com.projeto.consultoriomedico.service.ConsultaService;
import com.projeto.consultoriomedico.service.EnderecoService;
import com.projeto.consultoriomedico.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@SpringBootApplication
public class ConsultorioMedicoApplication implements CommandLineRunner {
    @Autowired
    private EnderecoService enderecoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private ConsultaService consultaService;

	public static void main(String[] args) {
		SpringApplication.run(ConsultorioMedicoApplication.class, args);
	}

    @Override
    public void run(String... args){
        Scanner sc = new Scanner(System.in);
        String opcao;

        do{

            System.out.println("1 - Pacientes");
            System.out.println("2 - Consultas");
            System.out.println("3 - Enderecos");
            System.out.println("4 - Sair");
            System.out.print("Digite a opcao: ");

            opcao = sc.nextLine();

            switch(opcao){
                case "1":
                    pacientes();
                    break;
                case "2":
                    consultas();
                    break;
                case "3":
                    enderecos();
                    break;
                case "4":
                    System.out.println("Saindo");
                    System.exit(0);
                    break;
            }

        }while(!opcao.equals("4"));
    }

    public void enderecos(){
        Scanner sc = new Scanner(System.in);

        String opcao;
        do{

            System.out.println("1 - Criar");
            System.out.println("2 - Editar");
            System.out.println("3 - Listar");
            System.out.println("4 - Buscar");
            System.out.println("5 - Apagar");
            System.out.println("6 - Voltar");
            System.out.print("Digite a opcao: ");
            opcao = sc.nextLine();

            switch (opcao){
                case "1":
                    Endereco endereco = new Endereco();
                    System.out.println("Digite o Estado:");
                    opcao = sc.nextLine();
                    endereco.setEstado(opcao);
                    System.out.println("Digite o Cidade:");
                    opcao = sc.nextLine();
                    endereco.setCidade(opcao);
                    System.out.println("Digite a Rua:");
                    opcao = sc.nextLine();
                    endereco.setRua(opcao);
                    System.out.println("Digite o Numero:");
                    opcao = sc.nextLine();
                    endereco.setNumero(opcao);
                    System.out.println("Digite o CEP:");
                    opcao = sc.nextLine();
                    endereco.setCep(opcao);
                    this.enderecoService.criarEndereco(endereco);
                    System.out.println("Endereco registrado com sucesso!");
                    break;
                case "2":
                    System.out.println("Editar");
                    List<Endereco> editar = this.enderecoService.listarEnderecos();
                    for (Endereco endereco2 : editar){
                        printEndereco(endereco2);
                    }
                    System.out.println("Digite o CEP do endereco desejado:");
                    opcao = sc.nextLine();
                    Endereco editarEndereco = this.enderecoService.findByCep(opcao);
                    System.out.println("Digite o Estado:");
                    opcao = sc.nextLine();
                    editarEndereco.setEstado(opcao);
                    System.out.println("Digite o Cidade:");
                    opcao = sc.nextLine();
                    editarEndereco.setCidade(opcao);
                    System.out.println("Digite a Rua:");
                    opcao = sc.nextLine();
                    editarEndereco.setRua(opcao);
                    System.out.println("Digite o Numero:");
                    opcao = sc.nextLine();
                    editarEndereco.setNumero(opcao);
                    System.out.println("Digite o CEP:");
                    opcao = sc.nextLine();
                    editarEndereco.setCep(opcao);
                    editarEndereco = this.enderecoService.criarEndereco(editarEndereco);
                    System.out.println("Endereco editado com sucesso!");
                    printEndereco(editarEndereco);
                    break;
                case "3":
                    System.out.println("Listar");
                    List<Endereco> lista = this.enderecoService.listarEnderecos();
                    for (Endereco endereco1 : lista){
                        printEndereco(endereco1);
                    }
                    break;
                case "4":
                    System.out.println("Buscar");
                    System.out.println("Digite o CEP:");
                    opcao = sc.nextLine();
                    Endereco endereco0 = this.enderecoService.findByCep(opcao);
                    if(endereco0 == null){
                        System.out.println("Endenreco não encontrado!");
                        return;
                    }
                    printEndereco(endereco0);
                    break;
                case "5":
                    System.out.println("Apagar");
                    List<Endereco> apagar = this.enderecoService.listarEnderecos();
                    for (Endereco endereco1 : apagar){
                        printEndereco(endereco1);
                    }
                    System.out.println("Digite o CEP: ");
                    opcao = sc.nextLine();
                    this.enderecoService.deletarEnderecoPorId(this.enderecoService.findByCep(opcao).getId());
                    System.out.println("Endereco removido com sucesso!");
                    break;
                case "6":
                    System.out.println("Voltando");
                    break;
            }

        }while(!opcao.equals("6"));
    }

    public void printEndereco(Endereco endereco) {
        if (endereco == null) {
            System.out.println("Endereço não encontrado!");
            return;
        }

        System.out.println("----- Endereço -----");
        System.out.println("ID: " + endereco.getId());
        System.out.println("Estado: " + endereco.getEstado());
        System.out.println("Cidade: " + endereco.getCidade());
        System.out.println("Rua: " + endereco.getRua());
        System.out.println("Número: " + endereco.getNumero());
        System.out.println("CEP: " + endereco.getCep());
        System.out.println("-------------------");
    }

    public void pacientes(){
        Scanner sc = new Scanner(System.in);

        String opcao;
        do{

            System.out.println("1 - Criar");
            System.out.println("2 - Editar");
            System.out.println("3 - Listar");
            System.out.println("4 - Buscar");
            System.out.println("5 - Apagar");
            System.out.println("6 - Voltar");
            System.out.print("Digite a opcao: ");
            opcao = sc.nextLine();

            switch (opcao){
                case "1":
                    Paciente paciente = new Paciente();
                    System.out.println("Digite o Nome:");
                    opcao = sc.nextLine();
                    paciente.setNome(opcao);
                    System.out.println("Digite o CPF:");
                    opcao = sc.nextLine();
                    paciente.setCpf(opcao);
                    System.out.println("Digite o Email:");
                    opcao = sc.nextLine();
                    paciente.setEmail(opcao);
                    System.out.println("Digite o Telefone:");
                    opcao = sc.nextLine();
                    paciente.setTelefone(opcao);

                    System.out.println("Deseja adicionar um endereco? (s/n)");
                    opcao = sc.nextLine();
                    if(opcao.equalsIgnoreCase("s")){
                        System.out.println("1 - Selecionar endereco existente");
                        System.out.println("2 - Criar novo endereco");
                        System.out.print("Escolha uma opcao: ");
                        opcao = sc.nextLine();

                        if(opcao.equals("1")){
                            System.out.println("--- Enderecos Disponiveis ---");
                            List<Endereco> enderecosDisponiveis = this.enderecoService.listarEnderecos();
                            for (Endereco end : enderecosDisponiveis){
                                printEndereco(end);
                            }
                            System.out.println("Digite o CEP do endereco desejado:");
                            opcao = sc.nextLine();
                            Endereco enderecoSelecionado = this.enderecoService.findByCep(opcao);
                            if(enderecoSelecionado != null){
                                paciente.setEndereco(enderecoSelecionado);
                            } else {
                                System.out.println("Endereco nao encontrado! Paciente sera criado sem endereco.");
                            }
                        } else if(opcao.equals("2")){
                            Endereco novoEndereco = new Endereco();
                            System.out.println("Digite o Estado:");
                            opcao = sc.nextLine();
                            novoEndereco.setEstado(opcao);
                            System.out.println("Digite a Cidade:");
                            opcao = sc.nextLine();
                            novoEndereco.setCidade(opcao);
                            System.out.println("Digite a Rua:");
                            opcao = sc.nextLine();
                            novoEndereco.setRua(opcao);
                            System.out.println("Digite o Numero:");
                            opcao = sc.nextLine();
                            novoEndereco.setNumero(opcao);
                            System.out.println("Digite o CEP:");
                            opcao = sc.nextLine();
                            novoEndereco.setCep(opcao);
                            paciente.setEndereco(novoEndereco);
                        }
                    }

                    this.pacienteService.registrarPaciente(paciente);
                    System.out.println("Paciente registrado com sucesso!");
                    break;
                case "2":
                    System.out.println("Editar");
                    List<Paciente> editar = this.pacienteService.listarPacientes();
                    for (Paciente paciente2 : editar){
                        printPaciente(paciente2);
                    }
                    System.out.println("Digite o ID do paciente desejado:");
                    opcao = sc.nextLine();
                    Paciente editarPaciente = this.pacienteService.buscarPacientePorId(UUID.fromString(opcao));
                    System.out.println("Digite o Nome:");
                    opcao = sc.nextLine();
                    editarPaciente.setNome(opcao);
                    System.out.println("Digite o CPF:");
                    opcao = sc.nextLine();
                    editarPaciente.setCpf(opcao);
                    System.out.println("Digite o Email:");
                    opcao = sc.nextLine();
                    editarPaciente.setEmail(opcao);
                    System.out.println("Digite o Telefone:");
                    opcao = sc.nextLine();
                    editarPaciente.setTelefone(opcao);

                    System.out.println("Deseja alterar o endereco? (s/n)");
                    opcao = sc.nextLine();
                    if(opcao.equalsIgnoreCase("s")){
                        System.out.println("1 - Selecionar endereco existente");
                        System.out.println("2 - Criar novo endereco");
                        System.out.println("3 - Remover endereco");
                        System.out.print("Escolha uma opcao: ");
                        opcao = sc.nextLine();

                        if(opcao.equals("1")){
                            System.out.println("--- Enderecos Disponiveis ---");
                            List<Endereco> enderecosDisponiveis = this.enderecoService.listarEnderecos();
                            for (Endereco end : enderecosDisponiveis){
                                printEndereco(end);
                            }
                            System.out.println("Digite o CEP do endereco desejado:");
                            opcao = sc.nextLine();
                            Endereco enderecoSelecionado = this.enderecoService.findByCep(opcao);
                            if(enderecoSelecionado != null){
                                editarPaciente.setEndereco(enderecoSelecionado);
                            } else {
                                System.out.println("Endereco nao encontrado! Endereco do paciente nao foi alterado.");
                            }
                        } else if(opcao.equals("2")){
                            Endereco novoEndereco = new Endereco();
                            System.out.println("Digite o Estado:");
                            opcao = sc.nextLine();
                            novoEndereco.setEstado(opcao);
                            System.out.println("Digite a Cidade:");
                            opcao = sc.nextLine();
                            novoEndereco.setCidade(opcao);
                            System.out.println("Digite a Rua:");
                            opcao = sc.nextLine();
                            novoEndereco.setRua(opcao);
                            System.out.println("Digite o Numero:");
                            opcao = sc.nextLine();
                            novoEndereco.setNumero(opcao);
                            System.out.println("Digite o CEP:");
                            opcao = sc.nextLine();
                            novoEndereco.setCep(opcao);
                            editarPaciente.setEndereco(novoEndereco);
                        } else if(opcao.equals("3")){
                            editarPaciente.setEndereco(null);
                            System.out.println("Endereco removido do paciente.");
                        }
                    }

                    editarPaciente = this.pacienteService.editarPaciente(editarPaciente.getId(), editarPaciente);
                    System.out.println("Paciente editado com sucesso!");
                    printPaciente(editarPaciente);
                    break;
                case "3":
                    System.out.println("Listar");
                    List<Paciente> lista = this.pacienteService.listarPacientes();
                    for (Paciente paciente1 : lista){
                        printPaciente(paciente1);
                    }
                    break;
                case "4":
                    System.out.println("Buscar");
                    System.out.println("Digite o ID:");
                    opcao = sc.nextLine();
                    Paciente paciente0 = this.pacienteService.buscarPacientePorId(UUID.fromString(opcao));
                    if(paciente0 == null){
                        System.out.println("Paciente não encontrado!");
                        return;
                    }
                    printPaciente(paciente0);
                    break;
                case "5":
                    System.out.println("Apagar");
                    List<Paciente> apagar = this.pacienteService.listarPacientes();
                    for (Paciente paciente1 : apagar){
                        printPaciente(paciente1);
                    }
                    System.out.println("Digite o ID: ");
                    opcao = sc.nextLine();
                    this.pacienteService.deletarPaciente(UUID.fromString(opcao));
                    System.out.println("Paciente removido com sucesso!");
                    break;
                case "6":
                    System.out.println("Voltando");
                    break;
            }

        }while(!opcao.equals("6"));
    }

    public void printPaciente(Paciente paciente) {
        if (paciente == null) {
            System.out.println("Paciente não encontrado!");
            return;
        }

        System.out.println("----- Paciente -----");
        System.out.println("ID: " + paciente.getId());
        System.out.println("Nome: " + paciente.getNome());
        System.out.println("CPF: " + paciente.getCpf());
        System.out.println("Email: " + paciente.getEmail());
        System.out.println("Telefone: " + paciente.getTelefone());

        if(paciente.getEndereco() != null){
            System.out.println("--- Endereco do Paciente ---");
            System.out.println("Estado: " + paciente.getEndereco().getEstado());
            System.out.println("Cidade: " + paciente.getEndereco().getCidade());
            System.out.println("Rua: " + paciente.getEndereco().getRua());
            System.out.println("Numero: " + paciente.getEndereco().getNumero());
            System.out.println("CEP: " + paciente.getEndereco().getCep());
        } else {
            System.out.println("Endereco: Nao cadastrado");
        }

        System.out.println("-------------------");
    }

    public void consultas(){
        Scanner sc = new Scanner(System.in);

        String opcao;
        do{

            System.out.println("1 - Criar");
            System.out.println("2 - Editar");
            System.out.println("3 - Listar");
            System.out.println("4 - Buscar");
            System.out.println("5 - Reagendar");
            System.out.println("6 - Confirmar");
            System.out.println("7 - Cancelar");
            System.out.println("8 - Apagar");
            System.out.println("9 - Voltar");
            System.out.print("Digite a opcao: ");
            opcao = sc.nextLine();

            switch (opcao){
                case "1":
                    Consulta consulta = new Consulta();
                    System.out.println("Digite a data e hora (formato: dd/MM/yyyy HH:mm):");
                    opcao = sc.nextLine();
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                        LocalDateTime dataHora = LocalDateTime.parse(opcao, formatter);
                        consulta.setAgendamento(dataHora);
                    } catch (Exception e) {
                        System.out.println("Formato de data invalido! Usando data atual.");
                        consulta.setAgendamento(LocalDateTime.now());
                    }

                    consulta.setStatus(StatusConsulta.AGENDADA);

                    System.out.println("Deseja vincular um paciente? (s/n)");
                    opcao = sc.nextLine();
                    if(opcao.equalsIgnoreCase("s")){
                        System.out.println("--- Pacientes Disponiveis ---");
                        List<Paciente> pacientesDisponiveis = this.pacienteService.listarPacientes();
                        for (Paciente pac : pacientesDisponiveis){
                            printPaciente(pac);
                        }
                        System.out.println("Digite o ID do paciente:");
                        opcao = sc.nextLine();
                        try {
                            Paciente pacienteSelecionado = this.pacienteService.buscarPacientePorId(UUID.fromString(opcao));
                            if(pacienteSelecionado != null){
                                consulta.setPaciente(pacienteSelecionado);
                            } else {
                                System.out.println("Paciente nao encontrado! Consulta sera criada sem paciente.");
                            }
                        } catch (Exception e) {
                            System.out.println("ID invalido! Consulta sera criada sem paciente.");
                        }
                    }

                    this.consultaService.criarConsulta(consulta);
                    System.out.println("Consulta criada com sucesso!");
                    break;
                case "2":
                    System.out.println("Editar");
                    List<Consulta> editar = this.consultaService.buscarConsultas();
                    for (Consulta consulta2 : editar){
                        printConsulta(consulta2);
                    }
                    System.out.println("Digite o ID da consulta desejada:");
                    opcao = sc.nextLine();
                    try {
                        Consulta editarConsulta = this.consultaService.buscarConsultaPorId(UUID.fromString(opcao));

                        System.out.println("Digite a nova data e hora (formato: dd/MM/yyyy HH:mm):");
                        opcao = sc.nextLine();
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                            LocalDateTime dataHora = LocalDateTime.parse(opcao, formatter);
                            editarConsulta.setAgendamento(dataHora);
                        } catch (Exception e) {
                            System.out.println("Formato de data invalido! Mantendo data atual.");
                        }

                        System.out.println("Deseja alterar o paciente? (s/n)");
                        opcao = sc.nextLine();
                        if(opcao.equalsIgnoreCase("s")){
                            System.out.println("--- Pacientes Disponiveis ---");
                            List<Paciente> pacientesDisponiveis = this.pacienteService.listarPacientes();
                            for (Paciente pac : pacientesDisponiveis){
                                printPaciente(pac);
                            }
                            System.out.println("Digite o ID do paciente (ou deixe vazio para remover):");
                            opcao = sc.nextLine();
                            if(opcao.isEmpty()){
                                editarConsulta.setPaciente(null);
                                System.out.println("Paciente removido da consulta.");
                            } else {
                                try {
                                    Paciente pacienteSelecionado = this.pacienteService.buscarPacientePorId(UUID.fromString(opcao));
                                    if(pacienteSelecionado != null){
                                        editarConsulta.setPaciente(pacienteSelecionado);
                                    } else {
                                        System.out.println("Paciente nao encontrado! Paciente da consulta nao foi alterado.");
                                    }
                                } catch (Exception e) {
                                    System.out.println("ID invalido! Paciente da consulta nao foi alterado.");
                                }
                            }
                        }

                        editarConsulta = this.consultaService.editarConsulta(editarConsulta, editarConsulta.getId());
                        System.out.println("Consulta editada com sucesso!");
                        printConsulta(editarConsulta);
                    } catch (Exception e) {
                        System.out.println("ID invalido ou consulta nao encontrada!");
                    }
                    break;
                case "3":
                    System.out.println("Listar");
                    List<Consulta> lista = this.consultaService.buscarConsultas();
                    if(lista.isEmpty()){
                        System.out.println("Nenhuma consulta cadastrada.");
                    } else {
                        for (Consulta consulta1 : lista){
                            printConsulta(consulta1);
                        }
                    }
                    break;
                case "4":
                    System.out.println("Buscar");
                    System.out.println("Digite o ID:");
                    opcao = sc.nextLine();
                    try {
                        Consulta consulta0 = this.consultaService.buscarConsultaPorId(UUID.fromString(opcao));
                        if(consulta0 == null){
                            System.out.println("Consulta nao encontrada!");
                            return;
                        }
                        printConsulta(consulta0);
                    } catch (Exception e) {
                        System.out.println("ID invalido!");
                    }
                    break;
                case "5":
                    System.out.println("Reagendar");
                    List<Consulta> reagendar = this.consultaService.buscarConsultas();
                    for (Consulta consulta1 : reagendar){
                        printConsulta(consulta1);
                    }
                    System.out.println("Digite o ID da consulta:");
                    opcao = sc.nextLine();
                    try {
                        Consulta consultaReagendar = this.consultaService.buscarConsultaPorId(UUID.fromString(opcao));
                        System.out.println("Digite a nova data e hora (formato: dd/MM/yyyy HH:mm):");
                        opcao = sc.nextLine();
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                            LocalDateTime dataHora = LocalDateTime.parse(opcao, formatter);
                            consultaReagendar.setAgendamento(dataHora);
                            consultaReagendar.setStatus(StatusConsulta.AGENDADA);
                            this.consultaService.editarConsulta(consultaReagendar, consultaReagendar.getId());
                            System.out.println("Consulta reagendada com sucesso!");
                            printConsulta(consultaReagendar);
                        } catch (Exception e) {
                            System.out.println("Formato de data invalido!");
                        }
                    } catch (Exception e) {
                        System.out.println("ID invalido ou consulta nao encontrada!");
                    }
                    break;
                case "6":
                    System.out.println("Confirmar");
                    List<Consulta> confirmar = this.consultaService.buscarConsultas();
                    for (Consulta consulta1 : confirmar){
                        printConsulta(consulta1);
                    }
                    System.out.println("Digite o ID da consulta:");
                    opcao = sc.nextLine();
                    try {
                        Consulta consultaConfirmar = this.consultaService.buscarConsultaPorId(UUID.fromString(opcao));
                        consultaConfirmar.setStatus(StatusConsulta.CONFIRMADA);
                        this.consultaService.editarConsulta(consultaConfirmar, consultaConfirmar.getId());
                        System.out.println("Consulta confirmada com sucesso!");
                        printConsulta(consultaConfirmar);
                    } catch (Exception e) {
                        System.out.println("ID invalido ou consulta nao encontrada!");
                    }
                    break;
                case "7":
                    System.out.println("Cancelar");
                    List<Consulta> cancelar = this.consultaService.buscarConsultas();
                    for (Consulta consulta1 : cancelar){
                        printConsulta(consulta1);
                    }
                    System.out.println("Digite o ID da consulta:");
                    opcao = sc.nextLine();
                    try {
                        Consulta consultaCancelar = this.consultaService.buscarConsultaPorId(UUID.fromString(opcao));
                        consultaCancelar.setStatus(StatusConsulta.CANCELADA);
                        this.consultaService.editarConsulta(consultaCancelar, consultaCancelar.getId());
                        System.out.println("Consulta cancelada com sucesso!");
                        printConsulta(consultaCancelar);
                    } catch (Exception e) {
                        System.out.println("ID invalido ou consulta nao encontrada!");
                    }
                    break;
                case "8":
                    System.out.println("Apagar");
                    List<Consulta> apagar = this.consultaService.buscarConsultas();
                    for (Consulta consulta1 : apagar){
                        printConsulta(consulta1);
                    }
                    System.out.println("Digite o ID: ");
                    opcao = sc.nextLine();
                    try {
                        this.consultaService.deletarConsulta(UUID.fromString(opcao));
                        System.out.println("Consulta removida com sucesso!");
                    } catch (Exception e) {
                        System.out.println("ID invalido ou consulta nao encontrada!");
                    }
                    break;
                case "9":
                    System.out.println("Voltando");
                    break;
            }

        }while(!opcao.equals("9"));
    }

    public void printConsulta(Consulta consulta) {
        if (consulta == null) {
            System.out.println("Consulta nao encontrada!");
            return;
        }

        System.out.println("----- Consulta -----");
        System.out.println("ID: " + consulta.getId());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        System.out.println("Agendamento: " + consulta.getAgendamento().format(formatter));

        System.out.println("Status: " + consulta.getStatus());

        if(consulta.getPaciente() != null){
            System.out.println("--- Paciente ---");
            System.out.println("Nome: " + consulta.getPaciente().getNome());
            System.out.println("CPF: " + consulta.getPaciente().getCpf());
            System.out.println("Telefone: " + consulta.getPaciente().getTelefone());
        } else {
            System.out.println("Paciente: Nao vinculado");
        }

        System.out.println("-------------------");
    }
}
