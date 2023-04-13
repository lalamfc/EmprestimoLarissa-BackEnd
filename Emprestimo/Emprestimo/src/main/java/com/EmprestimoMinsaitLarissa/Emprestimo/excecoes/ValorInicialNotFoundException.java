package com.EmprestimoMinsaitLarissa.Emprestimo.excecoes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.math.BigDecimal;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ValorInicialNotFoundException extends Exception {
    public ValorInicialNotFoundException(BigDecimal valorInicial){super(String.format("Valor inicial não válido!!", valorInicial));}
}
