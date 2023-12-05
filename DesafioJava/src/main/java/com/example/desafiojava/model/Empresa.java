package com.example.desafiojava.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;


//anotação lombok @Data evita criação manual de getters, setters, tostring e construtor sem args.
@Data
@Entity
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cnpj;
    private String razaoSocial;
    private String cidade;
    private String situacaoCadastral;
    private LocalDate dataCadastro;
    private String endereco;
    private String telefone;

}
