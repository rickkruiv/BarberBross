package com.barberbross.barberbross.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizarClienteDTO {

    private String nome;

    @NotBlank( message = "Telefone e obrigatorio" )
    private String telefone;
    private String email;
}
