package com.EmprestimoMinsaitLarissa.Emprestimo.excecoes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ClienteJaCadastradoException extends Exception {
    public ClienteJaCadastradoException(String cpf){super(String.format("Cliente Já Cadastrado!", cpf));}
}
