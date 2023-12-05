package com.example.desafiojava.controller;


import com.example.desafiojava.exceptions.EmpresaExistenteException;
import com.example.desafiojava.model.DadosCnpjExterno;
import com.example.desafiojava.model.Empresa;
import com.example.desafiojava.model.EmpresaInputModel;
import com.example.desafiojava.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/empresa")
public class EmpresaController {


    //injeção dependência
    @Autowired
    private EmpresaService empresaService;

    @PostMapping
    public ResponseEntity<?> salvarEmpresa(@RequestBody EmpresaInputModel inputModel) {
        try {
            Empresa empresa = empresaService.converterParaEntidadeEmpresa(
                    inputModel.getDadosCnpjExterno(),
                    inputModel.getCnpj(),
                    inputModel.getEndereco(),
                    inputModel.getTelefone(),
                    inputModel.getData_cadastro()
            );
            Empresa empresaCriada = empresaService.salvarEmpresa(empresa);
            return new ResponseEntity<>(empresaCriada, HttpStatus.CREATED);
        } catch (EmpresaExistenteException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{cnpj}")
    public ResponseEntity<?> buscaPorCnpj(@PathVariable String cnpj){
        try{
            DadosCnpjExterno dadosCnpj = empresaService.buscarDadosCnpj(cnpj);
            return new ResponseEntity<>(dadosCnpj, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
