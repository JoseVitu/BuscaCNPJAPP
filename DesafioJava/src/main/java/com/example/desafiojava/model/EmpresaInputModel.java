package com.example.desafiojava.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmpresaInputModel {
    private Long cnpj;
    private String endereco;
    private String telefone;
    private LocalDate data_cadastro;
    private DadosCnpjExterno dadosCnpjExterno;


}
