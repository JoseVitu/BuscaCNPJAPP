package com.example.desafiojava.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

//anotação lombok @Data evita criação manual de getters, setters, tostring e construtor sem args.
@Data
public class DadosCnpjExterno {


    //precisei usar JsonProperty pois o Json recebido está como razao_social, sendo imcompativel com razaoSocial
    @JsonProperty("razao_social")
    private String razaoSocial;
    private Cidade cidade;
    @JsonProperty("situacao_cadastral")
    private String situacaoCadastral;


    private Estabelecimento estabelecimento;

    /*
    método para buscar somente nome da cidade(não está especificado nos requisitos,
    mas é oque acredito que deva ser armazenado.
    objeto completo retornado:
    "cidade": {
            "id": 4164,
            "nome": "Palotina",
            "ibge_id": 4117909,
            "siafi_id": "7739"
        }
     */
    public String getNomeCidade() {
        return cidade != null ? cidade.getNome() : null;
    }

    public String getSituacaoCadastral() {
        return estabelecimento != null ? estabelecimento.getSituacaoCadastral() : null;
    }



}
