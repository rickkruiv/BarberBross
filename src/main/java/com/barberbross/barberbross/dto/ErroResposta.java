package com.barberbross.barberbross.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErroResposta {

    private int status;
    private String mensagem;
    private String caminho;
    
    public ErroResposta(int status, String mensagem, String caminho) {
        this.status = status;
        this.mensagem = mensagem;
        this.caminho = caminho;
    }    
}
