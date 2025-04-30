package com.barberbross.barberbross.dto;

import lombok.Data;

@Data
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
