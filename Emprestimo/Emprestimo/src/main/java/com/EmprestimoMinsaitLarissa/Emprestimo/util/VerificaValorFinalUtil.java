package com.EmprestimoMinsaitLarissa.Emprestimo.util;

import com.EmprestimoMinsaitLarissa.Emprestimo.enums.Relacionamento;

import java.math.BigDecimal;
import java.math.MathContext;

public class VerificaValorFinalUtil {
    public static BigDecimal obterValorFinalCliente(Relacionamento relacionamento, BigDecimal valorInicial, int quantidadeEmprestCliente){
        BigDecimal retorno = new BigDecimal(0.0);
        if(Relacionamento.BRONZE.equals(relacionamento)){
            retorno = calcularValorFinalBronze(valorInicial);
        } else if (Relacionamento.PRATA.equals(relacionamento)) {
            retorno = calcularValorFinalPrata(valorInicial);
        } else if (Relacionamento.OURO.equals(relacionamento)){
            retorno = calcularValorFinalOuro(valorInicial, quantidadeEmprestCliente);
        }

        return retorno;
    }

    public static BigDecimal calcularValorFinalBronze(BigDecimal valorInicial) {
        BigDecimal multiplicador = null;
        BigDecimal valorPedidoNoEmprest = null;
        if (valorPedidoNoEmprest == valorInicial) {
            multiplicador = new BigDecimal(1.8);
        }
        return valorInicial.multiply(multiplicador, MathContext.DECIMAL32);
    }
    public static BigDecimal calcularValorFinalPrata(BigDecimal valorInicial) {
        BigDecimal valorMax = BigDecimal.valueOf(5000);
        if (valorInicial.compareTo(valorMax) == 1) {
            BigDecimal multiplicador = new BigDecimal(1.4);
            return valorInicial.multiply(multiplicador, MathContext.DECIMAL32);

        } else {
            BigDecimal multiplicador = new BigDecimal(1.6);
            return valorInicial.multiply(multiplicador, MathContext.DECIMAL32);

        }
    }
    public static BigDecimal calcularValorFinalOuro(BigDecimal valorInicial, int quantidadeEmprestCliente) {
        if (quantidadeEmprestCliente == 0) {
            BigDecimal multiplicador = new BigDecimal(1.2);
            return valorInicial.multiply(multiplicador, MathContext.DECIMAL32);
        } else {
            BigDecimal multiplicador = new BigDecimal(1.3);
            return valorInicial.multiply(multiplicador, MathContext.DECIMAL32);
        }
    }
}

