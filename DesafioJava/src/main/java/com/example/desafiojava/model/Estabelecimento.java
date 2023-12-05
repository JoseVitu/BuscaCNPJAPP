package com.example.desafiojava.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Estabelecimento {

    private Cidade cidade;

    @JsonProperty("situacao_cadastral")
    private String situacaoCadastral;

    @JsonProperty("data_cadastro")
    private LocalDate dataCadastro;

}
