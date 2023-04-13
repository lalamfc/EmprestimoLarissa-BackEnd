package com.EmprestimoMinsaitLarissa.Emprestimo.excecoes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClienteNotFoundException extends Exception {
    public ClienteNotFoundException(String cpf){super(String.format("Cliente não encontrado!", cpf));}
}
