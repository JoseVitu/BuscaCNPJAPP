package com.example.desafiojava.model;


import lombok.Data;

//precisei declarar cidade separademente, por ser um objeto
@Data
public class Cidade {
    private Long id;
    private String nome;
    private Long ibgeId;
    private String siafiId;
}
