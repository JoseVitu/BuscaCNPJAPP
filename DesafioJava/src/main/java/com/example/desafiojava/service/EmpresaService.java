package com.example.desafiojava.service;


import com.example.desafiojava.exceptions.EmpresaExistenteException;
import com.example.desafiojava.model.DadosCnpjExterno;
import com.example.desafiojava.model.Empresa;
import com.example.desafiojava.model.EmpresaInputModel;
import com.example.desafiojava.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;

@Service
public class EmpresaService {

    //injeção de dependência
    @Autowired
    private EmpresaRepository empresaRepository;

    private final WebClient webClient;

    public EmpresaService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://publica.cnpj.ws/cnpj/").build();
    }

    public Empresa salvarEmpresa(Empresa empresa) {
        if (empresaRepository.existsEmpresaByCnpj(empresa.getCnpj())) {
            throw new EmpresaExistenteException("Esta empresa já existe!");
        } else {
            empresaRepository.saveAndFlush(empresa);
            return empresa;
        }
    }


    public Empresa converterParaEntidadeEmpresa(DadosCnpjExterno dadosCnpjExterno, Long cnpj, String endereco, String telefone, LocalDate dataCadastro) {
        Empresa empresa = new Empresa();
        empresa.setCnpj(cnpj);
        empresa.setRazaoSocial(dadosCnpjExterno.getRazaoSocial());
        empresa.setCidade(dadosCnpjExterno.getEstabelecimento().getCidade().getNome());
        empresa.setSituacaoCadastral(dadosCnpjExterno.getSituacaoCadastral());
        empresa.setDataCadastro(dataCadastro);
        empresa.setEndereco(endereco);
        empresa.setTelefone(telefone);
        return empresa;
    }




    public DadosCnpjExterno buscarDadosCnpj(String cnpj) throws Exception {
        String url = cnpj;
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(url).build())
                .retrieve()
                .bodyToMono(DadosCnpjExterno.class)
                .block();

    }



    }


