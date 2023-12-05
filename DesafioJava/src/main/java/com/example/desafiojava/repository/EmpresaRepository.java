package com.example.desafiojava.repository;

import com.example.desafiojava.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    boolean existsEmpresaByCnpj(Long cnpj);


}
