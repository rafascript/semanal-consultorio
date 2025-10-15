package com.projeto.consultoriomedico.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "enderecos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank
    @JsonProperty("estado")
    private String estado;
    @NotBlank
    @JsonProperty("localidade")
    private String cidade;
    @NotBlank
    @JsonProperty("logradouro")
    private String rua;
    @NotBlank
    private String numero;
    @NotBlank
    @JsonProperty("cep")
    private String cep;
}
