package com.EmprestimoMinsaitLarissa.Emprestimo.excecoes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmprestimoNotFoundException extends Exception {
    public EmprestimoNotFoundException(Long id){super(String.format("Empréstimo não encontrado!", id));}

}
